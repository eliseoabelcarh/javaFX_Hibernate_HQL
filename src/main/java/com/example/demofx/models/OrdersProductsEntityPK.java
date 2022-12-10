package com.example.demofx.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class OrdersProductsEntityPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "order_id", insertable = false, updatable = false, nullable = false)
    private Integer orderId;
    @Column(name = "product_id", insertable = false, updatable = false, nullable = false)
    private Integer productId;
    @Column(name = "unique_id")
    private String uniqueId;

    public OrdersProductsEntityPK() {
    }

    public OrdersProductsEntityPK(Integer orderId, Integer productId, String uniqueId) {
        super();
        this.orderId = orderId;
        this.productId = productId;
        this.uniqueId = uniqueId;
    }

    public OrdersProductsEntityPK(Integer orderId, Integer productId) {
        super();
        this.uniqueId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersProductsEntityPK that = (OrdersProductsEntityPK) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(uniqueId, that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, uniqueId);
    }

    @Override
    public String toString() {
        return "OrdersProductsEntityPK{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }





//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OrdersProductsEntityPK that = (OrdersProductsEntityPK) o;
//        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(uniqueId, that.uniqueId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(orderId, productId, uniqueId);
//    }
}
