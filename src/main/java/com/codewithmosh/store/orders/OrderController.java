package com.codewithmosh.store.orders;

import com.codewithmosh.store.common.ErrorDto;
import com.codewithmosh.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final AuthService authService;
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {

        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable(name = "orderId") Long orderId) {

        return orderService.getOrder(orderId);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFoundException(OrderNotFoundException ex) {

        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
          new ErrorDto(ex.getMessage())
        );
    }

}
