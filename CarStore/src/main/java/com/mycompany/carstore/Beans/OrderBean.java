package com.mycompany.carstore.Beans;

public class OrderBean extends ProductBean {
    private int orderId;
    private int uid;
    private int qunatity;
    private String date;

    public OrderBean() {
    }

    public OrderBean(int uid, int qunatity, String date) {
        this.uid = uid;
        this.qunatity = qunatity;
        this.date = date;
    }

    public OrderBean(int orderId, int uid, int qunatity, String date) {
        this.orderId = orderId;
        this.uid = uid;
        this.qunatity = qunatity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderBean{" + "orderId=" + orderId + ", uid=" + uid + ", qunatity=" + qunatity + ", date=" + date + '}';
    }
    
}
