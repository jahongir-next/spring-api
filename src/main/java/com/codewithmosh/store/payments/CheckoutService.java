package com.codewithmosh.store.services;

import com.codewithmosh.store.payments.CheckoutRequest;
import com.codewithmosh.store.payments.CheckoutResponse;
import com.codewithmosh.store.exceptions.CartEmptyException;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.payments.PaymentException;
import com.codewithmosh.store.repositories.CartRepository;
import com.codewithmosh.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request){


        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if(cart == null){
          throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = com.codewithmosh.store.entities.Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        // Create a checkout session

        try{

            var session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        }catch(PaymentException e){
            orderRepository.delete(order);
            throw e;
        }
    }


    public void handleWebhookEvent(WebhookRequest request){

        paymentGateway.parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getPaymentStatus());
                    orderRepository.save(order);
                });


    }
}
