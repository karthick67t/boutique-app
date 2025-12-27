package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Product(){}

    public Long getProductId() { return productId; }
    public void setProductId(Long id) { this.productId = id; }

    public String getProductName() { return productName; }
    public void setProductName(String name) { this.productName = name; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}

