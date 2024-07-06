Restaurant Reservation System - Team 9
1. Use npm install to install the dependencies for the project.
2. pull the docker image from the docker hub using the command docker pull mysql:latest
https://hub.docker.com/_/mysql refer to this link for more information on the mysql docker image.
3.
    a. run the docker container using the command docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root mysql:latest
    or
    b. use the docker configuration file in the project to run the docker container.
4. Setting up testing environment
    a.  CREATE USER 'test_user'@'localhost' IDENTIFIED BY 'password';
        GRANT ALL PRIVILEGES ON *.* TO 'test_user'@'localhost';
        //this will setup the main test user
    b. Run docker with the following configuration
        Command:
        docker run -p 3306:3306 --env MYSQL_DATABASE=myDB --env MYSQL_ROOT_PASSWORD=password --env MYSQL_USER=admin --env
        MYSQL_PASSWORD=admin --name RestaurantDB --pull missing mysql:8.3 --default-authentication-plugin=mysql_native_password

5. OPTIONAL: If you want to use the database in the project, you can use the following command to import the database
    a. docker exec -i mysql mysql -uroot -ppassword < ./java/restaurant/sql/db_create/createFinal.sql
    b. docker exec -i RestaurantDB mysql -uadmin -padmin < ./java/restaurant/sql/db_insert/insertFinal.sql
Project Description:
This project implements a database system for managing restaurant reservations, customers, tables, and staff.

Database Tables and Relationships:
- Customer (CustomerID, Name, ContactDetails) - PK: CustomerID
- RestaurantTable (TableID, TableNumber, SeatingCapacity) - PK: TableID
- Staff (StaffID, Name, Role, ContactDetails) - PK: StaffID
- Reservation (ReservationID, CustomerID, TableID, Date, Time, NumberOfPeople) - PK: ReservationID, FK: CustomerID, TableID
- Assigned_To (StaffID, TableID, Date, Time) - PK: AssignmentID, FK: StaffID, TableID
- Reserves (CustomerID, ReservationID) - PK: ReservationID, FK: CustomerID

Number of Rows Inserted:
- Customer: 3 rows
- RestaurantTable: 3 rows
- Staff: 3 rows
- Reservation: 3 rows
- StaffAssignment: 3 rows
- Reserves: 3 rows

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
   - `createFinal.sql`
   - `insertFinal.sql`
2. Follow the instructions in the video for a detailed explanation of the project.

Thank you!
