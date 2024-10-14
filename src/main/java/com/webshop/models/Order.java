package com.webshop.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;	

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    private String status;
    
    

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    // Constructors, Getters, and Setters
    public Order() {}

    public Order(Customer customer, List<OrderItem> orderItems, Double totalPrice,  String status) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderItems = orderItems;
        this.orderDate = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
