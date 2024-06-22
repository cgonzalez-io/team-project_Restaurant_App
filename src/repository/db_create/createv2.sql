-- Create the database
CREATE DATABASE RestaurantDB;

-- Use the created database
USE RestaurantDB;

-- Create Customer table
CREATE TABLE Customer (
                          CustomerID INT PRIMARY KEY AUTO_INCREMENT,
                          FirstName VARCHAR(255) NOT NULL,
                          LastName VARCHAR(255) NOT NULL,
                          PhoneNumber VARCHAR(20),
                          Email VARCHAR(255),
                          Address VARCHAR(255)
);

-- Create Table table
CREATE TABLE RestaurantTable (
                                 TableID INT PRIMARY KEY AUTO_INCREMENT,
                                 TableNumber INT NOT NULL,
                                 SeatingCapacity INT NOT NULL
);

-- Create Staff table
CREATE TABLE Staff (
                       StaffID INT PRIMARY KEY AUTO_INCREMENT,
                       FirstName VARCHAR(255) NOT NULL,
                       LastName VARCHAR(255) NOT NULL,
                       Role VARCHAR(255) NOT NULL,
                       PhoneNumber VARCHAR(20),
                       Email VARCHAR(255),
                       Address VARCHAR(255)
);

-- Create Reservation table
CREATE TABLE Reservation (
                             ReservationID INT PRIMARY KEY AUTO_INCREMENT,
                             CustomerID INT,
                             Date DATE NOT NULL,
                             Time TIME NOT NULL,
                             NumberOfPeople INT NOT NULL,
                             FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Create ReservationTable junction table to represent the relationship between Reservation and Table
CREATE TABLE ReservationTable (
                                  ReservationID INT,
                                  TableID INT,
                                  PRIMARY KEY (ReservationID, TableID),
                                  FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID),
                                  FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
);

-- Create StaffAssignment table to represent the relationship between Staff and Table
CREATE TABLE StaffAssignment (
                                 AssignmentID INT PRIMARY KEY AUTO_INCREMENT,
                                 StaffID INT,
                                 TableID INT,
                                 Date DATE NOT NULL,
                                 Shift VARCHAR(255) NOT NULL,
                                 FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
                                 FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
);
