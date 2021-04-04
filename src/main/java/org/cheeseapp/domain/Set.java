package org.cheeseapp.domain;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
@Table(name = "sets")
public class Set implements Serializable {
    @ManyToOne
    @Id
    @JoinColumn(name = "id_заказа")
    private Order orderId;
    @OneToOne
    @Id
    @JoinColumn(name = "id_товара")
    private Product productId;
    @Column(name = "Количество")
    private Integer count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Set{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}
