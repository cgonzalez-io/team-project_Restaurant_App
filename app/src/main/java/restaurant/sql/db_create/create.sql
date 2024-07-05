CREATE SCHEMA myDB COLLATE utf8mb4_0900_ai_ci;

USE myDB;

-- Create Customer table
CREATE TABLE Customer (
    CustomerID int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255) NOT NULL,
    ContactDetails varchar(255) NOT NULL
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
    Name varchar(255) NOT NULL,
    Role VARCHAR(255) NOT NULL,
    ContactDetails varchar(255) NOT NULL
);

-- Create Reservation table
CREATE TABLE Reservation (
    ReservationID int PRIMARY KEY AUTO_INCREMENT,
    CustomerID int,
    TableID int,
    Date date NOT NULL,
    Time time NOT NULL,
    NumberOfPeople int NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
);

-- Create StaffAssignment table
CREATE TABLE StaffAssignment (
    AssignmentID int PRIMARY KEY AUTO_INCREMENT,
    StaffID int,
    TableID int,
    Date date NOT NULL,
    Time time NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
);
