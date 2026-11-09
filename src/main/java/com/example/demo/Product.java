package com.example.demo;

import java.math.BigDecimal;

public class Product {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal unitPrice;

    public Product() {}

    public Product(String productName, String description, BigDecimal unitPrice) {
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}