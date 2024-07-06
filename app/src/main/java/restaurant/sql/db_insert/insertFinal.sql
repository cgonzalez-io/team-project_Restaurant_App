-- Use the RestaurantDB database
USE RestaurantDB;

-- Insert sample data into CUSTOMER table
INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address)
VALUES
    (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', '123 Main St'),
    (2, 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', '456 Oak St'),
    (3, 'Alice', 'Johnson', '5555555555', 'alice.johnson@example.com', '789 Pine St');

-- Insert sample data into TABLE_INFO table
INSERT INTO TABLE_INFO (Table_ID, Seating_Capacity, Table_Number)
VALUES
    (1, 4, 1),
    (2, 2, 2),
    (3, 6, 3);

-- Insert sample data into STAFF table
INSERT INTO STAFF (Staff_ID, First_Name, Last_Name, Role, Phone_Number, Email, Address)
VALUES
    (1, 'Bob', 'Lee', 'Chef', '1111111111', 'bob.lee@example.com', '321 Elm St'),
    (2, 'Carol', 'White', 'Waiter', '2222222222', 'carol.white@example.com', '654 Spruce St'),
    (3, 'David', 'Brown', 'Manager', '3333333333', 'david.brown@example.com', '987 Cedar St');

-- Insert sample data into RESERVATION table
INSERT INTO RESERVATION (Customer_ID, Date, Time, NumberOfPeople)
VALUES
    (1, '2024-07-10', '18:00:00', 4),
    (2, '2024-07-11', '19:00:00', 2),
    (3, '2024-07-12', '20:00:00', 6);

-- Insert sample data into RESERVES table (linking reservations with tables)
INSERT INTO RESERVES (Reservation_ID, Table_ID)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3);

-- Insert sample data into ASSIGNED_TO table (linking staff with tables for specific times)
INSERT INTO ASSIGNED_TO (Table_ID, Staff_ID, Time, Date)
VALUES
    (1, 1, '18:00:00', '2024-07-10'),
    (2, 2, '19:00:00', '2024-07-11'),
    (3, 3, '20:00:00', '2024-07-12');
