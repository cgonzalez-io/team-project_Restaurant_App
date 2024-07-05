-- Insert sample data into Customer table
INSERT INTO Customer (Name, ContactDetails)
    VALUES ('John Doe', 'john@example.com'),
    ('Jane Smith', 'jane@example.com'),
    ('Michael Brown', 'michael@example.com');

-- Insert sample data into RestaurantTable table
INSERT INTO RestaurantTable (TableNumber, SeatingCapacity)
    VALUES (1, 4),
    (2, 2),
    (3, 6);

-- Insert sample data into Staff table
INSERT INTO Staff (Name, ROLE, ContactDetails)
    VALUES ('Alice Johnson', 'Waiter', 'alice@example.com'),
    ('Bob Lee', 'Chef', 'bob@example.com'),
    ('Carol White', 'Manager', 'carol@example.com');

-- Insert sample data into Reservation table
INSERT INTO Reservation (CustomerID, TableID, Date, Time, NumberOfPeople)
    VALUES (1, 1, '2024-06-15', '18:00:00', 4),
    (2, 2, '2024-06-16', '19:00:00', 2),
    (3, 3, '2024-06-17', '20:00:00', 6);

-- Insert sample data into StaffAssignment table
INSERT INTO StaffAssignment (StaffID, TableID, Date, Time)
    VALUES (1, 1, '2024-06-15', '18:00:00'),
    (2, 2, '2024-06-16', '19:00:00'),
    (3, 3, '2024-06-17', '20:00:00');

