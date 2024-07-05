<<<<<<< HEAD

create schema myDB collate utf8mb4_0900_ai_ci;

USE myDB;
-- Create Customer table
CREATE TABLE Customer (
                          CustomerID INT PRIMARY KEY AUTO_INCREMENT,
                          Name VARCHAR(255) NOT NULL,
                          ContactDetails VARCHAR(255) NOT NULL
=======
CREATE SCHEMA myDB COLLATE utf8mb4_0900_ai_ci;

USE myDB;

-- Create Customer table
CREATE TABLE Customer (
    CustomerID int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255) NOT NULL,
    ContactDetails varchar(255) NOT NULL
>>>>>>> development
);

-- Create Table table
CREATE TABLE RestaurantTable (
<<<<<<< HEAD
                                 TableID INT PRIMARY KEY AUTO_INCREMENT,
                                 TableNumber INT NOT NULL,
                                 SeatingCapacity INT NOT NULL
=======
    TableID int PRIMARY KEY AUTO_INCREMENT,
    TableNumber int NOT NULL,
    SeatingCapacity int NOT NULL
>>>>>>> development
);

-- Create Staff table
CREATE TABLE Staff (
<<<<<<< HEAD
                       StaffID INT PRIMARY KEY AUTO_INCREMENT,
                       Name VARCHAR(255) NOT NULL,
                       Role VARCHAR(255) NOT NULL,
                       ContactDetails VARCHAR(255) NOT NULL
=======
    StaffID int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255) NOT NULL,
    Role VARCHAR(255) NOT NULL,
    ContactDetails varchar(255) NOT NULL
>>>>>>> development
);

-- Create Reservation table
CREATE TABLE Reservation (
<<<<<<< HEAD
                             ReservationID INT PRIMARY KEY AUTO_INCREMENT,
                             CustomerID INT,
                             TableID INT,
                             Date DATE NOT NULL,
                             Time TIME NOT NULL,
                             NumberOfPeople INT NOT NULL,
                             FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
                             FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
=======
    ReservationID int PRIMARY KEY AUTO_INCREMENT,
    CustomerID int,
    TableID int,
    Date date NOT NULL,
    Time time NOT NULL,
    NumberOfPeople int NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
>>>>>>> development
);

-- Create StaffAssignment table
CREATE TABLE StaffAssignment (
<<<<<<< HEAD
                                 AssignmentID INT PRIMARY KEY AUTO_INCREMENT,
                                 StaffID INT,
                                 TableID INT,
                                 Date DATE NOT NULL,
                                 Time TIME NOT NULL,
                                 FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
                                 FOREIGN KEY (TableID) REFERENCES RestaurantTable(TableID)
);
=======
    AssignmentID int PRIMARY KEY AUTO_INCREMENT,
    StaffID int,
    TableID int,
    Date date NOT NULL,
    Time time NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID),
    FOREIGN KEY (TableID) REFERENCES RestaurantTable (TableID)
);
>>>>>>> development
