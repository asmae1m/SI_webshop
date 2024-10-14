package com.webshop.models;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "productID", nullable = false)
    private String productID;  // This will now be treated as the main ID

    @Column(name = "name", nullable = false)
    private String name;

    
    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = true)
    private String price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Product() {}

    // Constructor for CSV mapping
    public Product(String productID, String name, String description, String price) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
