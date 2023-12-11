package com.example.a_martmobprog;

public class HorizontalNavigationItems {
    String productName;
    String  productPrice;
    int productImage;

    public HorizontalNavigationItems(int productImage, String productName, String productPrice ){
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;

    }
    public int getImageResource() {
        return productImage;
    }

    public String getDescription() {
        return productName;
    }

    public  String getProductPrice(){return  productPrice;}
}
