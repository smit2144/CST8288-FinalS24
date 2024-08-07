DROP DATABASE IF EXISTS FoodWasteReductionDatabase;
CREATE DATABASE FoodWasteReductionDatabase;
USE FoodWasteReductionDatabase;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_id INT PRIMARY KEY auto_increment,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone INT,
    user_type VARCHAR(50) 
    CHECK (user_type IN ('Retailer', 'Consumer', 'Charitable Organization')) NOT NULL,
    isSubscribed BOOLEAN DEFAULT FALSE, 
    count INT DEFAULT 0
);

DROP TABLE IF EXISTS Feedback;
CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    feedback TEXT NOT NULL,
    feedback_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS Inventory;
CREATE TABLE Inventory (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'Available' CHECK (status IN ('Available', 'Surplus', 'Claimed', 'Purchased')),
    price DECIMAL(10, 2) NOT NULL
);