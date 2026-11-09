package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository, 
                        ProductRepository productRepository, 
                        OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        // 1. Validate Customer existence (FR-04)
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + request.customerId()));

        // 2. Validate at least one line item is present (FR-04)
        if (request.items() == null || request.items().isEmpty()) {
            throw new IllegalArgumentException("An order must contain at least one line item.");
        }

        Order order = new Order(customer);

        // 3. Process line items and capture unit prices (FR-02, FR-03, FR-07)[cite: 3]
        for (ItemRequest itemReq : request.items()) {
            if (itemReq.quantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive for all line items.");
            }

            Product product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemReq.productId()));

            order.addLineItem(product, itemReq.quantity());
        }

        // 4. Calculate business rule totals (BR-01 to BR-06)[cite: 3]
        order.calculateTotals();

        // 5. Persist atomically with cascade (FR-09)[cite: 3]
        return orderRepository.save(order);
    }
}