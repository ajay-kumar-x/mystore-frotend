package org.mystore.model;

import java.util.List;

public class OrderDetail {
    private int id;
    private String customerName;
    private String address;
    private String mobileNumber;
    private String status;
    private double totalPrice;
    private List<Product> products;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

 class Product {
    private int id;
    private String category;
    private String description;
    private double price;
    private String primary_image;
    private String size;
    private int quantity;

    // getters and setters omitted for brevity

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getCategory() {
         return category;
     }

     public void setCategory(String category) {
         this.category = category;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public double getPrice() {
         return price;
     }

     public void setPrice(double price) {
         this.price = price;
     }

     public String getPrimary_image() {
         return primary_image;
     }

     public void setPrimary_image(String primary_image) {
         this.primary_image = primary_image;
     }

     public String getSize() {
         return size;
     }

     public void setSize(String size) {
         this.size = size;
     }

     public int getQuantity() {
         return quantity;
     }

     public void setQuantity(int quantity) {
         this.quantity = quantity;
     }
 }
