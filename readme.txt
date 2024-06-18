Restaurant Reservation System - Team 9
//TODO: look into using the test data generator python package to generate test data for the database.

use npm install to install the dependencies for the project.

Project Description:
This project implements a database system for managing restaurant reservations, customers, tables, and staff.

Database Tables and Relationships:
- Customer (CustomerID, Name, ContactDetails) - PK: CustomerID
- RestaurantTable (TableID, TableNumber, SeatingCapacity) - PK: TableID
- Staff (StaffID, Name, Role, ContactDetails) - PK: StaffID
- Reservation (ReservationID, CustomerID, TableID, Date, Time, NumberOfPeople) - PK: ReservationID, FK: CustomerID, TableID
- StaffAssignment (AssignmentID, StaffID, TableID, Date, Time) - PK: AssignmentID, FK: StaffID, TableID

Number of Rows Inserted:
- Customer: 3 rows
- RestaurantTable: 3 rows
- Staff: 3 rows
- Reservation: 3 rows
- StaffAssignment: 3 rows

Important SQL Queries:
1. Retrieve all customers.
2. Retrieve all reservations for a specific date.
3. Find available tables on a specific date and time.
4. List all staff assignments for a specific date.
5. Find reservations for a specific customer.

Video URL:
[Include the YouTube video link here]

Instructions:
1. Run the scripts in the following order:
   - `restaurant_team_number_create.sql`
   - `restaurant_team_number_insert.sql`
   - `restaurant_team_number_select.sql`
2. Follow the instructions in the video for a detailed explanation of the project.

Thank you!
