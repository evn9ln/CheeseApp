package org.cheeseapp.domain;

import java.util.ArrayList;
import java.util.Date;

public class OrderInfo {
    private Integer orderId;
    private String clientName;
    private String phoneNumber;
    private Date date;
    private Integer orderSum;
    private Boolean status;
    private ArrayList<ProductStatistic> productList;

    public OrderInfo(Integer orderId, String clientName, String phoneNumber, Date date, Integer orderSum, ArrayList<ProductStatistic> productList,Boolean status) {
        this.orderId = orderId;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.orderSum = orderSum;
        this.productList = productList;
        this.status=status;
    }

    public OrderInfo(String clientName, String phoneNumber, Date date, Integer orderSum, Boolean status, ArrayList<ProductStatistic> productList) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.orderSum = orderSum;
        this.status = status;
        this.productList = productList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Integer orderSum) {
        this.orderSum = orderSum;
    }

    public ArrayList<ProductStatistic> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductStatistic> productList) {
        this.productList = productList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
