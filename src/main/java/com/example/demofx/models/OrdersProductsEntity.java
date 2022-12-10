package com.example.demofx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_products", schema = "demousers")
public class OrdersProductsEntity {

    @EmbeddedId
    OrdersProductsEntityPK id;
    @Basic
    @Column(name = "qty", nullable = false)
    private Integer qty;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrdersEntity ordersByOrderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductsEntity productsByProductId;

    public OrdersProductsEntity() {
    }

    public OrdersProductsEntity(OrdersProductsEntityPK id) {
        this.id = id;
    }

    public OrdersProductsEntity (OrdersEntity ordersByOrderId, ProductsEntity productsByProductId, Integer qty, Double price) {
        this.id = new OrdersProductsEntityPK(ordersByOrderId.getId(), productsByProductId.getId());
        this.ordersByOrderId = ordersByOrderId;
        this.productsByProductId = productsByProductId;
        this.qty = qty;
        this.price = price;
    }
    public OrdersProductsEntity(OrdersProductsEntityPK id, Integer qty, Double price) {
        this.id = id;
        this.qty = qty;
        this.price = price;
    }

    public OrdersProductsEntityPK getId() {
        return id;
    }

    public void setId(OrdersProductsEntityPK id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }

    @Override
    public String toString() {
        return "OrdersProductsEntity{" +
                "id=" + id +
                ", qty=" + qty +
                ", price=" + price +
                ", ordersByOrderId=" + ordersByOrderId +
                ", productsByProductId=" + productsByProductId +
                '}';
    }




}
