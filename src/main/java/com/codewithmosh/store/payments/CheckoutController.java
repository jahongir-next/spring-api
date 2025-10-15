package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.CheckoutRequest;
import com.codewithmosh.store.dtos.CheckoutResponse;
import com.codewithmosh.store.dtos.ErrorDto;
import com.codewithmosh.store.entities.OrderStatus;
import com.codewithmosh.store.exceptions.CartEmptyException;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.exceptions.PaymentException;
import com.codewithmosh.store.repositories.OrderRepository;
import com.codewithmosh.store.services.CheckoutService;
import com.codewithmosh.store.services.WebhookRequest;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

   private final CheckoutService checkoutService;
   private final OrderRepository orderRepository;



    @PostMapping
    public CheckoutResponse checkout(
           @Valid @RequestBody CheckoutRequest request
    ){
           return checkoutService.checkout(request);
    }


    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ){
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }



    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException(){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorDto("Error while creating a checkout session.")
                );
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleCartNotFountException(Exception ex){
       return ResponseEntity.badRequest().body(
               new ErrorDto(ex.getMessage())
       );
    }

}
