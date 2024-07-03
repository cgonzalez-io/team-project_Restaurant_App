-- Insert data into tables:

INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address) VALUES
                                                                                            (1, 'John', 'Johnson', '12345', 'john.johnson@email.com', '123 Street Rd'),
                                                                                            (2, 'Bob', 'Smith', '34567', 'bob.smith@email.com', '223 Elm St'),
                                                                                            (3, 'Emily', 'Brown', '98765', 'emily.brown@email.com', '777 Maple Avenue');

INSERT INTO RESERVATION (Reservation_ID, Customer_ID, Number_Of_People, Time, Date) VALUES
                                                                                        (1, 1, 4, '18:30:00', '2024-06-21'),
                                                                                        (2, 2, 2, '19:00:00', '2024-06-22'),
                                                                                        (3, 3, 3, '20:00:00', '2024-06-23');

INSERT INTO TABLE_INFO (Table_ID, Seating_Capacity, Table_Number) VALUES
                                                                      (1, 4, 101),
                                                                      (2, 2, 102),
                                                                      (3, 6, 103);

INSERT INTO STAFF (Staff_ID, First_Name, Last_Name, Role, Phone_Number, Email, Address) VALUES
                                                                                            (1, 'Tim', 'Smith', 'Waiter', '112233', 'tim@email.com', '123 Road St'),
                                                                                            (2, 'Sarah', 'Jackson', 'Waiter', '223344', 'sarah@email.com', '234 Road Blvd'),
                                                                                            (3, 'James', 'Carlson', 'Waiter', '334455', 'james@email.com', '531 Streets St');

INSERT INTO RESERVES (Reservation_ID, Table_ID) VALUES
                                                    (1, 1),
                                                    (2, 2),
                                                    (3, 3);

INSERT INTO ASSIGNED_TO (Table_ID, Staff_ID, Time, Date) VALUES
                                                             (1, 1, '06:00:00', '2024-06-21'),
                                                             (2, 2, '07:00:00', '2024-06-22'),
                                                             (3, 3, '05:30:00', '2024-06-23');
