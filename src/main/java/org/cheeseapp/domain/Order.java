package org.cheeseapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Date;

@Entity //указатель hibernate, что этот класс сущность бд (реляционное отображение)
@Table(name = "orders")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //автогенерация айди от 1
    @Column(name = "order_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "order_sum")
    private Integer orderSum;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private Boolean status;
    public Order(User userId){
        this.userId=userId;
        this.date=new Date();
        this.status=false;
        this.orderSum=0;

    }

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Integer getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Integer orderSum) {
        this.orderSum = orderSum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderSum=" + orderSum +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}



