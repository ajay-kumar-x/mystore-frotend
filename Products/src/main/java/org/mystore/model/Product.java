package org.mystore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String category;
    private String subcategory;
    private int price;
    private String description;
    private int quantity;
    private String primary_image;
    private String other_images;

    private String size;

    public Product(Long id, String category, String subcategory, int price, String description, int quantity, String primary_image, String other_images, String size) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.primary_image = primary_image;
        this.other_images = other_images;
        this.size = size;
    }

    // Default constructor
    public Product() {}


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrimary_image() {
        return primary_image;
    }

    public void setPrimary_image(String primary_image) {
        this.primary_image = primary_image;
    }

    public String getOther_images() {
        return other_images;
    }

    public void setOther_images(String other_images) {
        this.other_images = other_images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", primary_image='" + primary_image + '\'' +
                ", other_images='" + other_images + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
