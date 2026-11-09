package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderLineItem {
    private Long lineItemId;
    private int quantity;
    private BigDecimal capturedUnitPrice;

    @JsonIgnore
    private Order order;
    private Product product;

    public OrderLineItem() {}

    public OrderLineItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.capturedUnitPrice = product.getUnitPrice();
    }

    public BigDecimal getLineSubtotal() {
        return capturedUnitPrice.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    // Getters and Setters
    public Long getLineItemId() { return lineItemId; }
    public void setLineItemId(Long lineItemId) { this.lineItemId = lineItemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getCapturedUnitPrice() { return capturedUnitPrice; }
    public void setCapturedUnitPrice(BigDecimal capturedUnitPrice) { this.capturedUnitPrice = capturedUnitPrice; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}