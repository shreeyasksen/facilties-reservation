# Facilites-Reservation
The Facilities Reservation System is a web-based application designed to fasten the process of reserving and managing facilities within an organization. Whether it's meeting rooms, conference halls, or other resources, this system provides a centralized platform for users to check availability, make reservations, and manage bookings efficiently.
OVERWIEW
This project is a Facility Reservation RESTful API designed to create facilities and reservations like Sports hall, party hall, conference hall, meetings hall.
The user can create a facility, search for available facilities, search for facilities within the database, search all reservations within the database, make reservations to a specific facility, delete facility reservations .
The Facilities-Reservation-API follows a standard "User <-> Controller <-> Validator (Only interacts with Controller) <-> Service <-> Repository <-> Database" API schema.


#FACILITY
Method	  Endpoint	                   Description	                                                                Valid API Calls
POST	  /api/v1/Facility	                 Create a Facility object	                                                     Create Facility
PUT	  /api/v1/Facility	                 Updates an existing Facility                                                  Update existing facility
GET	   /api/v1/Facility	                 Get all existing Facilities	                                                  Get all existing facility
GET	  /api/v1/Facility
/availabilitySearch?dateFrom=
{from}&dateTo={to}	                 Get all available hotels between specified dates.	                        Get all available facilities
GET	   /api/v1/Facility/{id}	             Get a user specified hotel	                                                Get a user specified facilities
DELETE	/api/v1/Facility/{id}	         Delete a user specified Hotel	                                            Delete facility


Create Facility
Description: Create a facility object.
Validation (Throws an InvalidRequestException when):
Type is not SPORTS, BUSINESS, or LEISURE
Date is not YYYY-MM-DD
Only one date is recieved
availableTo date comes before availableFrom date
Name is null
Request Body:
   { 
   "name" : "String", 
   "type" : "String: (SPORTS, BUSINESS, LEISURE)", 
   "description" : "String",   
   "availableFrom" : "YYYY-MM-DD", 
   "availableTo" : "YYYY-MM-DD", 
   "status": true 
   }

  Delete facility
Description: Delete a user specified facility
Validation (Throws an InvalidRequestException when):
ID does not exist
Reservations for this hotel object exist (A Reservations object that contains a foreign key associated with the user inputted ID exists)
Request Body:
Path Variable Integer "id"

#Same is for RESERVATION AND CUSTOMER.




#DATABASE
The database structure contains two tables, a hotel table that contains the hotel object.
And a reservation table that contains reservations objects and is associated with the hotel table through the hotel table's Id by storing it as a foreign key in its "hotel Id" value.
The table structures and values are as follows:
FACILITY
Id: Integer (Primary Key)
Name: VarChar
Type: VarChar [ SPORTS,LEISURE, BUSINESS ]
Description: VarChar
Availiable From: Date YYYY-MM-DD
Available To: Date YYYY-MM-DD
Status: Boolean
RESERVATION
Id: Integer (Primary Key)
Inventory Id: Integer (Foreign Key)
Check in Date: Date YYYY-MM-DD
Check out Date: Date YYYY-MM-DD
Number of Guests: Integer
Status: Boolean
CUSTOMER
Id: Integer (Primary Key)
Name:VarChar
Type: VarChar [ CUSTOMER 1,CUSTOMER 2,CUSTOMER 3]
Description:VarChar
Inventory Id: Integer (Foreign Key)
Check in Date: Date YYYY-MM-DD
Check out Date: Date YYYY-MM-DD
Number of Guests: Integer
Status: Boolean

TESTING
Tests for this project incorporated both Unit tests, Junit and  Integration tests.
I used Mockito the several unit tests,PostgressSQL,Springboot and H2 in memory databases for the integration tests.
More elaborate descriptions of the tests and their functionalities can be found in the test folder within this project.
Tech Stack
API Creation:
Java
MySQL
SpringBoot
Hibernate
JPA
PostgressSql
Testing:
JUnit 5
Mockito
H2
User Input Testing:
Jupiterapitest
Springboottest
Postman


Reference and Credits to https://github.com/marioszocs/spring-boot-hotel-reservation-api
