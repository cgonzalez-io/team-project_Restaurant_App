CREATE SCHEMA IF NOT EXISTS RESTAURANT_DB;

<< << << < HEAD USE RESTAURANT_DB;

-- Create tables:
CREATE TABLE CUSTOMER (
    Customer_ID int NOT NULL,
    First_Name varchar(50),
    Last_Name varchar(50),
    Phone_Number varchar(15),
    Email varchar(50),
    Address varchar(100),
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (Customer_ID)
);

CREATE TABLE RESERVATION (
    Reservation_ID int NOT NULL,
    Customer_ID int,
    Number_Of_People int,
    Time time,
    Date date,
    CONSTRAINT RESERVATION_PK PRIMARY KEY (Reservation_ID),
    CONSTRAINT RESERVATION_CUSTOMER_FK FOREIGN KEY (Customer_ID) REFERENCES CUSTOMER (Customer_ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE TABLE_INFO (
    Table_ID int NOT NULL,
    Seating_Capacity int,
    Table_Number int,
    CONSTRAINT TABLE_INFO_PK PRIMARY KEY (Table_ID)
);

CREATE TABLE STAFF (
    Staff_ID int NOT NULL,
    First_Name varchar(50),
    Last_Name varchar(50),
    Role VARCHAR(50),
    Phone_Number varchar(15),
    Email varchar(50),
    Address varchar(100),
    CONSTRAINT STAFF_PK PRIMARY KEY (Staff_ID)
);

CREATE TABLE RESERVES (
    Reservation_ID int NOT NULL,
    Table_ID int NOT NULL,
    CONSTRAINT TABLE_RESERVATION_PK PRIMARY KEY (Reservation_ID, Table_ID),
    CONSTRAINT TABLE_RESERVATION_RESERVATION_FK FOREIGN KEY (Reservation_ID) REFERENCES RESERVATION (Reservation_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT TABLE_RESERVATION_TABLE_FK FOREIGN KEY (Table_ID) REFERENCES TABLE_INFO (Table_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ASSIGNED_TO (
    Table_ID int NOT NULL,
    Staff_ID int NOT NULL,
    Time time NOT NULL,
    Date date NOT NULL,
    CONSTRAINT STAFF_ASSIGNMENT_PK PRIMARY KEY (Table_ID, Staff_ID, time, date),
    CONSTRAINT STAFF_ASSIGNMENT_TABLE_FK FOREIGN KEY (Table_ID) REFERENCES TABLE_INFO (Table_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT STAFF_ASSIGNMENT_STAFF_FK FOREIGN KEY (Staff_ID) REFERENCES STAFF (Staff_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Insert data into tables:
INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address)
    VALUES (1, 'John', 'Johnson', '12345', 'john.johnson@email.com', '123 Street Rd'),
    (2, 'Bob', 'Smith', '34567', 'bob.smith@email.com', '223 Elm St'),
    (3, 'Emily', 'Brown', '98765', 'emily.brown@email.com', '777 Maple Avenue');

INSERT INTO RESERVATION (Reservation_ID, Customer_ID, Number_Of_People, Time, Date)
    VALUES (1, 1, 4, '18:30:00', '2024-06-21'),
    (2, 2, 2, '19:00:00', '2024-06-22'),
    (3, 3, 3, '20:00:00', '2024-06-23');

INSERT INTO TABLE_INFO (Table_ID, Seating_Capacity, Table_Number)
    VALUES (1, 4, 101),
    (2, 2, 102),
    (3, 6, 103);

INSERT INTO STAFF (Staff_ID, First_Name, Last_Name, ROLE, Phone_Number, Email, Address)
    VALUES (1, 'Tim', 'Smith', 'Waiter', '112233', 'tim@email.com', '123 Road St'),
    (2, 'Sarah', 'Jackson', 'Waiter', '223344', 'sarah@email.com', '234 Road Blvd'),
    (3, 'James', 'Carlson', 'Waiter', '334455', 'james@email.com', '531 Streets St');

INSERT INTO RESERVES (Reservation_ID, Table_ID)
    VALUES (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO ASSIGNED_TO (Table_ID, Staff_ID, Time, Date)
    VALUES (1, 1, '06:00:00', '2024-06-21'),
    (2, 2, '07:00:00', '2024-06-22'),
    (3, 3, '05:30:00', '2024-06-23');

-- SQL Queries:
-- Query to retrieve all customers
SELECT
    *
FROM
    CUSTOMER;

-- Query to retrieve all reservations for a specific date
SELECT
    *
FROM
    RESERVATION
WHERE
    Date = '2024-06-21';

-- Query to find available tables on a specific date and time
SELECT
    *
FROM
    TABLE_INFO
WHERE
    Table_ID NOT IN (
        SELECT
            Table_ID
        FROM
            RESERVES
        WHERE
            Reservation_ID IN (
                SELECT
                    Reservation_ID
                FROM
                    RESERVATION
                WHERE
                    Date = '2024-06-15'
                    AND Time = '05:30:00'));

-- Query to list all staff assignments for a specific date
SELECT
    *
FROM
    ASSIGNED_TO
WHERE
    Date = '2024-06-21';

-- Query to find reservations for a specific customer
SELECT
    *
FROM
    RESERVATION
WHERE
    Customer_ID = 1;

-- Query to find all tables reserved for a specific reservation
SELECT
    rt.Table_ID,
    t.Table_Number,
    t.Seating_Capacity
FROM
    RESERVES rt
    JOIN TABLE_INFO t ON rt.Table_ID = t.Table_ID
WHERE
    rt.Reservation_ID = 1;

== == == = USE RESTAURANT_DB;

-- Create tables:
CREATE TABLE CUSTOMER (
    Customer_ID int NOT NULL,
    First_Name varchar(50),
    Last_Name varchar(50),
    Phone_Number varchar(15),
    Email varchar(50),
    Address varchar(100),
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (Customer_ID)
);

CREATE TABLE RESERVATION (
    Reservation_ID int NOT NULL,
    Customer_ID int,
    Number_Of_People int,
    Time time,
    Date date,
    CONSTRAINT RESERVATION_PK PRIMARY KEY (Reservation_ID),
    CONSTRAINT RESERVATION_CUSTOMER_FK FOREIGN KEY (Customer_ID) REFERENCES CUSTOMER (Customer_ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE TABLE_INFO (
    Table_ID int NOT NULL,
    Seating_Capacity int,
    Table_Number int,
    CONSTRAINT TABLE_INFO_PK PRIMARY KEY (Table_ID)
);

CREATE TABLE STAFF (
    Staff_ID int NOT NULL,
    First_Name varchar(50),
    Last_Name varchar(50),
    Role VARCHAR(50),
    Phone_Number varchar(15),
    Email varchar(50),
    Address varchar(100),
    CONSTRAINT STAFF_PK PRIMARY KEY (Staff_ID)
);

CREATE TABLE RESERVES (
    Reservation_ID int NOT NULL,
    Table_ID int NOT NULL,
    CONSTRAINT TABLE_RESERVATION_PK PRIMARY KEY (Reservation_ID, Table_ID),
    CONSTRAINT TABLE_RESERVATION_RESERVATION_FK FOREIGN KEY (Reservation_ID) REFERENCES RESERVATION (Reservation_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT TABLE_RESERVATION_TABLE_FK FOREIGN KEY (Table_ID) REFERENCES TABLE_INFO (Table_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ASSIGNED_TO (
    Table_ID int NOT NULL,
    Staff_ID int NOT NULL,
    Time time NOT NULL,
    Date date NOT NULL,
    CONSTRAINT STAFF_ASSIGNMENT_PK PRIMARY KEY (Table_ID, Staff_ID, time, date),
    CONSTRAINT STAFF_ASSIGNMENT_TABLE_FK FOREIGN KEY (Table_ID) REFERENCES TABLE_INFO (Table_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT STAFF_ASSIGNMENT_STAFF_FK FOREIGN KEY (Staff_ID) REFERENCES STAFF (Staff_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Insert data into tables:
INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address)
    VALUES (1, 'John', 'Johnson', '12345', 'john.johnson@email.com', '123 Street Rd'),
    (2, 'Bob', 'Smith', '34567', 'bob.smith@email.com', '223 Elm St'),
    (3, 'Emily', 'Brown', '98765', 'emily.brown@email.com', '777 Maple Avenue');

INSERT INTO RESERVATION (Reservation_ID, Customer_ID, Number_Of_People, Time, Date)
    VALUES (1, 1, 4, '18:30:00', '2024-06-21'),
    (2, 2, 2, '19:00:00', '2024-06-22'),
    (3, 3, 3, '20:00:00', '2024-06-23');

INSERT INTO TABLE_INFO (Table_ID, Seating_Capacity, Table_Number)
    VALUES (1, 4, 101),
    (2, 2, 102),
    (3, 6, 103);

INSERT INTO STAFF (Staff_ID, First_Name, Last_Name, ROLE, Phone_Number, Email, Address)
    VALUES (1, 'Tim', 'Smith', 'Waiter', '112233', 'tim@email.com', '123 Road St'),
    (2, 'Sarah', 'Jackson', 'Waiter', '223344', 'sarah@email.com', '234 Road Blvd'),
    (3, 'James', 'Carlson', 'Waiter', '334455', 'james@email.com', '531 Streets St');

INSERT INTO RESERVES (Reservation_ID, Table_ID)
    VALUES (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO ASSIGNED_TO (Table_ID, Staff_ID, Time, Date)
    VALUES (1, 1, '06:00:00', '2024-06-21'),
    (2, 2, '07:00:00', '2024-06-22'),
    (3, 3, '05:30:00', '2024-06-23');

-- SQL Queries:
-- Query to retrieve all customers
SELECT
    *
FROM
    CUSTOMER;

-- Query to retrieve all reservations for a specific date
SELECT
    *
FROM
    RESERVATION
WHERE
    Date = '2024-06-21';

-- Query to find available tables on a specific date and time
SELECT
    *
FROM
    TABLE_INFO
WHERE
    Table_ID NOT IN (
        SELECT
            Table_ID
        FROM
            RESERVES
        WHERE
            Reservation_ID IN (
                SELECT
                    Reservation_ID
                FROM
                    RESERVATION
                WHERE
                    Date = '2024-06-15'
                    AND Time = '05:30:00'));

-- Query to list all staff assignments for a specific date
SELECT
    *
FROM
    ASSIGNED_TO
WHERE
    Date = '2024-06-21';

-- Query to find reservations for a specific customer
SELECT
    *
FROM
    RESERVATION
WHERE
    Customer_ID = 1;

-- Query to find all tables reserved for a specific reservation
SELECT
    rt.Table_ID,
    t.Table_Number,
    t.Seating_Capacity
FROM
    RESERVES rt
    JOIN TABLE_INFO t ON rt.Table_ID = t.Table_ID
WHERE
    rt.Reservation_ID = 1;

>> >> >> > development
