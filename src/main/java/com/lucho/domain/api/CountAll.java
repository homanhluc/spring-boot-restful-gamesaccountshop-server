package com.lucho.domain.api;

public class CountAll {
    private long userCountAll;
    private long categoryCountAll;
    private long productCountAll;
    private long orderCountAll;
    public long getUserCountAll() {
        return userCountAll;
    }

    public void setUserCountAll(long userCountAll) {
        this.userCountAll = userCountAll;
    }

    public long getCategoryCountAll() {
        return categoryCountAll;
    }

    public void setCategoryCountAll(long categoryCountAll) {
        this.categoryCountAll = categoryCountAll;
    }

    public long getProductCountAll() {
        return productCountAll;
    }

    public void setProductCountAll(long productCountAll) {
        this.productCountAll = productCountAll;
    }

    public long getOrderCountAll() {
        return orderCountAll;
    }

    public void setOrderCountAll(long orderCountAll) {
        this.orderCountAll = orderCountAll;
    }
}
