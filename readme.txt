Restaurant Reservation System - Team 9
//TODO: look into using the test data generator python package to generate test data for the database.
//TODO: Add PMD and Docker to the project.
//TODO: Add a docker compose task to gradle to run the docker container. Perhaps containerize the application as well.
1. Use npm install to install the dependencies for the project.
2. pull the docker image from the docker hub using the command docker pull mysql:latest
https://hub.docker.com/_/mysql refer to this link for more information on the mysql docker image.
3.
    a. run the docker container using the command docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root mysql:latest
    or
    b. use the docker configuration file in the project to run the docker container.

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
[future deliverable]

Instructions:
1. Run the scripts in the following order:
   - `restaurant_team_number_create.sql`
   - `restaurant_team_number_insert.sql`
   - `restaurant_team_number_select.sql`
2. Follow the instructions in the video for a detailed explanation of the project.

Thank you!
