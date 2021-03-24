package com.example.shoppingapp_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartListModel implements Serializable {

    @Expose()
    private String imageUrl;
    @Expose()
    private String productName;
    @Expose()
    private String productPrice;

    public CartListModel() {
    }

    public CartListModel(String imageUrl, String productName, String productPrice) {
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
