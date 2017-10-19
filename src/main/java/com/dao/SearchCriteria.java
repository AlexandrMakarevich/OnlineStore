package com.dao;

public class SearchCriteria {

    private String orderStatus;
    private String productName;
    private Integer productQuantity;

    public boolean isOrderStatusNull() {
        if (orderStatus == null) {
            return true;
        }
        return false;
    }

    public boolean isProductNameNull() {
        if (productName == null) {
            return true;
        }
        return false;
    }

    public boolean isProductQuantityNull() {
        if (productQuantity == null) {
            return true;
        }
        return false;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
