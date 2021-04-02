package org.cheeseapp.domain;

import java.util.Calendar;

public class Order {
    private int orderId, idClient, orderCost;
    private Calendar orderDate;
    private Boolean orderStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
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
