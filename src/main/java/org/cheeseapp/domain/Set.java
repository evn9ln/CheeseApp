package org.cheeseapp.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sets")
//Composite-id class must implement Serializable
public class Set implements Serializable {

    @ManyToOne
    @Id
    @JoinColumn(name = "order_id")
    private Order orderId;
    @OneToOne
    @Id
    @JoinColumn(name = "product_id")
    private Product productId;
    @Column(name = "number")
    private Integer number;

    public Set() {
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Set{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", number=" + number +
                '}';
    }
}
