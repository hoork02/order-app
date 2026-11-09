package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return orderService.getAllCustomers();
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return orderService.getAllProducts();
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order createdOrder = orderService.createOrder(request);
            return ResponseEntity.status(201).body(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

// Request DTO Records
record OrderRequest(Long customerId, List<ItemRequest> items) {}
record ItemRequest(Long productId, int quantity) {}