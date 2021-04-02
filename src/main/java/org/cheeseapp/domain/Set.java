package org.cheeseapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "Sets")
public class Set {
    @ManyToOne
    @Id
    @JoinColumn(name = "ID заказа")
    private Order orderId;
    @OneToOne
    @Id
    @JoinColumn(name = "ID товара")
    private Product productId;
    @Column(name = "Количество")
    private int count;

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
