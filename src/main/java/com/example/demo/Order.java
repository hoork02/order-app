package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderId;
    private LocalDateTime orderDate = LocalDateTime.now();
    private String status = "COMPLETED";
    private Customer customer;
    private List<OrderLineItem> lineItems = new ArrayList<>();
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal discountAmount = BigDecimal.ZERO;
    private BigDecimal taxAmount = BigDecimal.ZERO;
    private BigDecimal finalAmount = BigDecimal.ZERO;

    public Order() {}

    public Order(Customer customer) {
        this.customer = customer;
        this.status = "COMPLETED";
    }

    public void addLineItem(Product product, int quantity) {
        OrderLineItem item = new OrderLineItem(this, product, quantity);
        lineItems.add(item);
    }

    public void calculateTotals() {
        subtotal = lineItems.stream()
                .map(OrderLineItem::getLineSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        if (subtotal.compareTo(new BigDecimal("1000.00")) >= 0) {
            discountAmount = subtotal.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
        } else {
            discountAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal taxableAmount = subtotal.subtract(discountAmount);
        taxAmount = taxableAmount.multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP);
        finalAmount = subtotal.subtract(discountAmount).add(taxAmount).setScale(2, RoundingMode.HALF_UP);
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<OrderLineItem> getLineItems() { return lineItems; }
    public void setLineItems(List<OrderLineItem> lineItems) { this.lineItems = lineItems; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
}