package model;

import java.util.Date;

/**
 * @author Hussein
 */

public class TransactionDTO {
	private int transactionID;
	private String itemName;
	private int itemID;
	private int userID;
	private int quantity;
	private double amount;
	private Date date;
	private String transactionType; 


//Construtors
public TransactionDTO() {
}


public TransactionDTO(int transactionID, String itemName, int itemID, int userID, int quantity, double amount,
		Date date, String transactionType) {
	super();
	this.transactionID = transactionID;
	this.itemName = itemName;
	this.itemID = itemID;
	this.userID = userID;
	this.quantity = quantity;
	this.amount = amount;
	this.date = date;
	this.transactionType = transactionType;
}

//Getters and Setters
public int getTransactionID() {
	return transactionID;
}


public void setTransactionID(int transactionID) {
	this.transactionID = transactionID;
}


public String getItemName() {
	return itemName;
}


public void setItemName(String itemName) {
	this.itemName = itemName;
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


public int getQuantity() {
	return quantity;
}


public void setQuantity(int quantity) {
	this.quantity = quantity;
}


public double getAmount() {
	return amount;
}


public void setAmount(double amount) {
	this.amount = amount;
}


public Date getDate() {
	return date;
}


public void setDate(Date date) {
	this.date = date;
}


public String getTransactionType() {
	return transactionType;
}


public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}

//toString method
@Override
public String toString() {
	return "TransactionDTO [transactionID=" + transactionID + ", itemName=" + itemName + ", itemID=" + itemID
			+ ", userID=" + userID + ", quantity=" + quantity + ", amount=" + amount + ", date=" + date
			+ ", transactionType=" + transactionType + "]";
}


}