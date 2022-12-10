package com.example.demofx.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "orders", schema = "demousers")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<OrdersProductsEntity> ordersProductsById;

    public OrdersEntity() {
    }

    public OrdersEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<OrdersProductsEntity> getOrdersProductsById() {
        return ordersProductsById;
    }

    public void setOrdersProductsById(Collection<OrdersProductsEntity> ordersProductsById) {
        this.ordersProductsById = ordersProductsById;
    }
}
