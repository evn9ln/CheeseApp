package org.cheeseapp.domain;

import javax.persistence.*;
import java.util.Calendar;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_заказа")
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name="id_пользователя")
    private Client idClient;
    @Column(name = "сумма_заказа")
    private Integer orderCost;
    @Column(name = "дата")
    private Calendar orderDate;
    @Column(name = "статус")
    private Boolean orderStatus;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Integer orderCost) {
        this.orderCost = orderCost;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }



    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", idClient=" + idClient +
                ", orderCost=" + orderCost +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
