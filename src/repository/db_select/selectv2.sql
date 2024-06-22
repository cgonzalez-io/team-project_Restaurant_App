-- Query to retrieve all customers
SELECT * FROM Customer;

-- Query to retrieve all reservations for a specific date
SELECT * FROM Reservation WHERE Date = '2024-06-15';

-- Query to find available tables on a specific date and time
SELECT * FROM RestaurantTable
WHERE TableID NOT IN (
    SELECT TableID FROM ReservationTable WHERE ReservationID IN (
        SELECT ReservationID FROM Reservation WHERE Date = '2024-06-15' AND Time = '18:00:00'
    )
);

-- Query to list all staff assignments for a specific date
SELECT * FROM StaffAssignment WHERE Date = '2024-06-15';

-- Query to find reservations for a specific customer
SELECT * FROM Reservation WHERE CustomerID = 1;

-- Query to find all tables reserved for a specific reservation
SELECT rt.TableID, t.TableNumber, t.SeatingCapacity
FROM ReservationTable rt
         JOIN RestaurantTable t ON rt.TableID = t.TableID
WHERE rt.ReservationID = 1;
