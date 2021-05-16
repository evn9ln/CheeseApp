package org.cheeseapp.domain;

public class ProductStatistic {
    private Integer id;
    private String productName;
    private Integer count;
    private Integer cost;

    public ProductStatistic(Integer id, String productName, Integer count, Integer cost) {
        this.id = id;
        this.productName = productName;
        this.count = count;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
