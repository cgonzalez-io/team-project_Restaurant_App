-- Create the database
CREATE SCHEMA IF NOT EXISTS RestaurantDB;

-- Use the created database
USE RestaurantDB;

-- Create Customer table
CREATE TABLE Customer (
    CustomerID int PRIMARY KEY AUTO_INCREMENT,
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    PhoneNumber varchar(20),
    Email varchar(255),
    Address varchar(255)
);

-- Create Table table
CREATE TABLE RestaurantTable (
    TableID int PRIMARY KEY AUTO_INCREMENT,
    TableNumber int NOT NULL,
    SeatingCapacity int NOT NULL
);

-- Create Staff table
CREATE TABLE Staff (
    StaffID int PRIMARY KEY AUTO_INCREMENT,
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    Role VARCHAR(255) NOT NULL,
    PhoneNumber varchar(20),
    Email varchar(255),
    Address varchar(255)
);

-- Create Reservation table
CREATE TABLE Reservation (
    ReservationID int PRIMARY KEY AUTO_INCREMENT,
    CustomerID int,
    Date date NOT NULL,
    Time time NOT NULL,
    NumberOfPeople int NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-- Create ReservationTable junction table to represent the relationship between Reservation and Table
CREATE TABLE ReservationTable (
    ReservationID int,
    TableID int,
    PRIMARY KEY (ReservationID, TableID),
    FOREIGN KEY (ReservationID) REFERENCES Reservation (ReservationID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
);

-- Create StaffAssignment table to represent the relationship between Staff and Table
CREATE TABLE StaffAssignment (
    AssignmentID int PRIMARY KEY AUTO_INCREMENT,
    StaffID int,
    TableID int,
    Date date NOT NULL,
    Time time NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
);
