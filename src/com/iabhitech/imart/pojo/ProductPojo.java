/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.pojo;

import java.util.Objects;

/**
 *
 * @author Abhineet Verma
 */
public class ProductPojo {

    private String productId;
    private String productName;
    private String productCompany;
    private double productPrice;
    private double ourPrice;
    private int tax;
    private int quantity;
    private double total;

    public ProductPojo() {
    }

    public ProductPojo(String productId, String productName, String productCompany, double productPrice, double ourPrice, int tax, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.productPrice = productPrice;
        this.ourPrice = ourPrice;
        this.tax = tax;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ProductPojo{" + "productId=" + productId + ", productName=" + productName + ", productCompany=" + productCompany + ", productPrice=" + productPrice + ", ourPrice=" + ourPrice + ", tax=" + tax + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.productId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductPojo other = (ProductPojo) obj;
        return Objects.equals(this.productId, other.productId);
    }

}
