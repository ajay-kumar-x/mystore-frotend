package org.mystore.model;


import jakarta.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String customerName;
    private String address;
    private String mobileNumber;

    private String status;
    private  double totalPrice;



    @Column(length = -1)   //longtext -> to set no limit on length of product lists
    private String products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
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

    public Orders(String customerName, String address, String mobileNumber, String status,double totalPrice, String products) {
        this.customerName = customerName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.status=status;
        this.totalPrice=totalPrice;
        this.products = products;
    }

    public Orders() {
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id +
                ", \"customerName\":\"" + customerName + "\"" +
                ", \"address\":\"" + address + "\"" +
                ", \"mobileNumber\":\"" + mobileNumber + "\"" +
                ", \"status\":\"" + status + "\"" +
                ", \"totalPrice\":\"" + totalPrice + "\"" +
                ", \"products\":" + products +
                '}';
    }
}
