package com.springboot.spring_boot_1.model;
public class Product {
    private Integer productId;
    private String productCategories;
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductCategories() {
        return productCategories;
    }
    public void setProductCategories(String productCategories) {
        this.productCategories = productCategories;
    }
}