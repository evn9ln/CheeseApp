package org.cheeseapp.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sets")
public class Set implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "set_id")
    private Integer setId;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    @Column(name = "number")
    private Integer number;

    public Set() {
    }

    public Set(Order orderId, Product productId, Integer number) {
        this.orderId = orderId;
        this.productId = productId;
        this.number = number;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
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
}
