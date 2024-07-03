
create schema myDB collate utf8mb4_0900_ai_ci;

USE myDB;
-- Create Customer table
CREATE TABLE Customer (
                          CustomerID INT PRIMARY KEY AUTO_INCREMENT,
                          Name VARCHAR(255) NOT NULL,
                          ContactDetails VARCHAR(255) NOT NULL
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
                       Name VARCHAR(255) NOT NULL,
                       Role VARCHAR(255) NOT NULL,
                       ContactDetails VARCHAR(255) NOT NULL
);

-- Create Reservation table
CREATE TABLE Reservation (
                             ReservationID INT PRIMARY KEY AUTO_INCREMENT,
                             CustomerID INT,
                             TableID INT,
                             Date DATE NOT NULL,
                             Time TIME NOT NULL,
                             NumberOfPeople INT NOT NULL,
                             FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
                             FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
);

-- Create StaffAssignment table
CREATE TABLE StaffAssignment (
                                 AssignmentID INT PRIMARY KEY AUTO_INCREMENT,
                                 StaffID INT,
                                 TableID INT,
                                 Date DATE NOT NULL,
                                 Time TIME NOT NULL,
                                 FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
                                 FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
);