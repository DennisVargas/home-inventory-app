package com.vargas.dennis.homeinventory;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class InventoryItem {
    private byte[] image;
    private String name;
    private String quantity;
    private String price;
    private ArrayList<String> tags;
    private String notes;
    private ProductInfo productInfo;
    private LendingInfo lendInfo;

    public InventoryItem(byte[] image, String name, String quantity, String price,
                         ArrayList<String> tags, String notes,
                         ProductInfo productInfo, LendingInfo lendInfo) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.tags = tags;
        this.notes = notes;
        this.productInfo = productInfo;
        this.lendInfo = lendInfo;
    }


    private class LendingInfo{
        String borrowerName;
        String lentOn;

        public String getBorrowerName() {
            return borrowerName;
        }

        public void setBorrowerName(String borrowerName) {
            this.borrowerName = borrowerName;
        }

        public String getLentOn() {
            return lentOn;
        }

        public void setLentOn(String lentOn) {
            this.lentOn = lentOn;
        }
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public LendingInfo getLendInfo() {
        return lendInfo;
    }

    public void setLendInfo(LendingInfo lendInfo) {
        this.lendInfo = lendInfo;
    }
}
