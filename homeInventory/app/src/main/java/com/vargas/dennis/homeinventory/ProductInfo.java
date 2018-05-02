package com.vargas.dennis.homeinventory;

public class ProductInfo {
    String modelNumber;
    String serialNumber;
    String productUrl;
    String purchaseDate;
    String returnDate;
    String warrentyEndDate;

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getWarrentyEndDate() {
        return warrentyEndDate;
    }

    public void setWarrentyEndDate(String warrentyEndDate) {
        this.warrentyEndDate = warrentyEndDate;
    }
}
