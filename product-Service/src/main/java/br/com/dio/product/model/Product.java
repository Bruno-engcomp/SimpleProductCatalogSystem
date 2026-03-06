package br.com.dio.product.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false, precision = 2)
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price)
    {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
