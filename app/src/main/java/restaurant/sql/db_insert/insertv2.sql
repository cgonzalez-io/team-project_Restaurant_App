-- Insert sample data into Customer table
<<<<<<< HEAD
INSERT INTO Customer (FirstName, LastName, PhoneNumber, Email, Address) VALUES
                                                                            ('John', 'Doe', '1234567890', 'john@example.com', '123 Main St'),
                                                                            ('Jane', 'Smith', '0987654321', 'jane@example.com', '456 Oak St'),
                                                                            ('Michael', 'Brown', '5555555555', 'michael@example.com', '789 Pine St');

-- Insert sample data into RestaurantTable table
INSERT INTO RestaurantTable (TableNumber, SeatingCapacity) VALUES
                                                               (1, 4),
                                                               (2, 2),
                                                               (3, 6);

-- Insert sample data into Staff table
INSERT INTO Staff (FirstName, LastName, Role, PhoneNumber, Email, Address) VALUES
                                                                               ('Alice', 'Johnson', 'Waiter', '1111111111', 'alice@example.com', '321 Elm St'),
                                                                               ('Bob', 'Lee', 'Chef', '2222222222', 'bob@example.com', '654 Spruce St'),
                                                                               ('Carol', 'White', 'Manager', '3333333333', 'carol@example.com', '987 Cedar St');

-- Insert sample data into Reservation table
INSERT INTO Reservation (CustomerID, Date, Time, NumberOfPeople) VALUES
                                                                     (1, '2024-06-15', '18:00:00', 4),
                                                                     (2, '2024-06-16', '19:00:00', 2),
                                                                     (3, '2024-06-17', '20:00:00', 6);

-- Insert sample data into ReservationTable junction table
INSERT INTO ReservationTable (ReservationID, TableID) VALUES
                                                          (1, 1),
                                                          (1, 2),
                                                          (2, 2),
                                                          (3, 3);

-- Insert sample data into StaffAssignment table
INSERT INTO StaffAssignment (StaffID, TableID, Date, Time) VALUES
                                                               (1, 1, '2024-06-15', '17:00:00'),
                                                               (2, 2, '2024-06-16', '17:00:00'),
                                                               (3, 3, '2024-06-17', '17:00:00');
=======
INSERT INTO Customer (FirstName, LastName, PhoneNumber, Email, Address)
    VALUES ('John', 'Doe', '1234567890', 'john@example.com', '123 Main St'),
    ('Jane', 'Smith', '0987654321', 'jane@example.com', '456 Oak St'),
    ('Michael', 'Brown', '5555555555', 'michael@example.com', '789 Pine St');

-- Insert sample data into RestaurantTable table
INSERT INTO RestaurantTable (TableNumber, SeatingCapacity)
    VALUES (1, 4),
    (2, 2),
    (3, 6);

-- Insert sample data into Staff table
INSERT INTO Staff (FirstName, LastName, ROLE, PhoneNumber, Email, Address)
    VALUES ('Alice', 'Johnson', 'Waiter', '1111111111', 'alice@example.com', '321 Elm St'),
    ('Bob', 'Lee', 'Chef', '2222222222', 'bob@example.com', '654 Spruce St'),
    ('Carol', 'White', 'Manager', '3333333333', 'carol@example.com', '987 Cedar St');

-- Insert sample data into Reservation table
INSERT INTO Reservation (CustomerID, Date, Time, NumberOfPeople)
    VALUES (1, '2024-06-15', '18:00:00', 4),
    (2, '2024-06-16', '19:00:00', 2),
    (3, '2024-06-17', '20:00:00', 6);

-- Insert sample data into ReservationTable junction table
INSERT INTO ReservationTable (ReservationID, TableID)
    VALUES (1, 1),
    (1, 2),
    (2, 2),
    (3, 3);

-- Insert sample data into StaffAssignment table
INSERT INTO StaffAssignment (StaffID, TableID, Date, Time)
    VALUES (1, 1, '2024-06-15', '17:00:00'),
    (2, 2, '2024-06-16', '17:00:00'),
    (3, 3, '2024-06-17', '17:00:00');
>>>>>>> development
