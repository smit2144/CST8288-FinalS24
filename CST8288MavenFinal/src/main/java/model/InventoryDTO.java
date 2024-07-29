package model;

import java.util.Date;

public class InventoryDTO {
    private int itemID;
    private int userID;
    private String name;
    private int quantity;
    private Date expirationDate;
    private String surplusStatus;
    private String plan;
    private double price;
    private String location;
    private String foodGroup;

    public InventoryDTO() {
    }

    public InventoryDTO(int itemID, int userID, String name, int quantity, Date expirationDate, String surplusStatus,
                        String plan, double price, String location, String foodGroup) {
        this.itemID = itemID;
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.surplusStatus = surplusStatus;
        this.plan = plan;
        this.price = price;
        this.location = location;
        this.foodGroup = foodGroup;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSurplusStatus() {
        return surplusStatus;
    }

    public void setSurplusStatus(String surplusStatus) {
        this.surplusStatus = surplusStatus;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }
}
