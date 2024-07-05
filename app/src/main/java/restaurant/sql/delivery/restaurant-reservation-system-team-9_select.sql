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
