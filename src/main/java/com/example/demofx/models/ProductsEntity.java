package com.example.demofx.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "products", schema = "demousers")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "product_name", nullable = true, length = 255)
    private String productName;
    @Basic
    @Column(name = "product_sku", nullable = true, length = 255)
    private String productSku;
    @Basic
    @Column(name = "product_epc", nullable = true, length = 255)
    private String productEpc;
    @Basic
    @Column(name = "product_price", nullable = false, precision = 0)
    private Double productPrice;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<OrdersProductsEntity> ordersProductsById;

    public ProductsEntity() {
    }

    public ProductsEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductEpc() {
        return productEpc;
    }

    public void setProductEpc(String productEpc) {
        this.productEpc = productEpc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Collection<OrdersProductsEntity> getOrdersProductsById() {
        return ordersProductsById;
    }

    public void setOrdersProductsById(Collection<OrdersProductsEntity> ordersProductsById) {
        this.ordersProductsById = ordersProductsById;
    }
}
