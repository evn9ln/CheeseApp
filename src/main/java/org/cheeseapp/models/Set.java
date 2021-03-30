package org.cheeseapp.models;

public class Set {
    private int orderId,productId,count;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
