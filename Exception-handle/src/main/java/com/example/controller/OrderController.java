package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.entity.Order;
import com.example.exception.ErrorDetails;
import com.example.exception.NoOrderFoundException;
import com.example.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Save a new order", description = "This endpoint allows you to save a new order.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order saved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/saveOrder")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @Operation(summary = "Find an order by ID", description = "This endpoint allows you to find an order by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order found"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/byId/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @ExceptionHandler(NoOrderFoundException.class)
    public ResponseEntity<ErrorDetails> noOrderFoundException(NoOrderFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            request.getDescription(false),
            "ORDER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
