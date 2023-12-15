# Facilites-Reservation
About it:
The Facilities Reservation System is a web-based application designed to fasten the process of reserving facilities within an organization. Whether it's meeting rooms,conference halls  for business,party halls, swimming pools and sports hall for leisure this system provides a centralized platform for users to check availability, make reservations, and manage bookings efficiently.
#OVERVIEW:
This project is a Facilities Reservation RESTful API designed to create Facilities and associated reservations.
The user can create facilities, search for available facilities, search for all facilities within the database,  make reservations for a specific facility, delete reservations if do not want .
The Facilities-Reservation-API follows a standard "User <-> Controller <->Validator (Only interacts with Controller) <-> Service <-> Repository <-> Database" API schema.


Method	  Endpoint	           Description	                                 Valid API Calls
POST	    /api/v1/facility	    Create a facility object	                        Create facility 
PATCH	    /api/v1/facility	   Updates an existing hotel	                    Update existing facility
GET	      /api/v1/facility	    Get all existing hotels	                      Get all existing facility
GET	      /api/v1/facilities/
availabilitySearch?
dateFrom={from}&dateTo={to}  Get all available hotels between specified dates. Get all available hotels
GET	     /api/v1/facilities/{id}	Get a user specified 	                        Get a user specified facility
DELETE	/api/v1/facilities/{id}	   Delete a user specified facility	               Delete facility

For Facilities 




Create Facilities
Description: Create a Facility object.
Validation (Throws an InvalidRequestException when):
if different facilities is not SPORT,LEISURE,BUSINESS
Date is not YYYY-MM-DD
Only one date is recieved
availableTo date comes before availableFrom date
Name is null
Request Body:
   { 
   "name" : "String", 
   "type" : "String: (SPORT,LEISURE,BUSINESS)", 
   "description" : "String",   
   "availableFrom" : "YYYY-MM-DD", 
   "availableTo" : "YYYY-MM-DD", 
   "status": true 
   }



   Delete Facilities
Description: Delete a user specified Facility
Validation (Throws an InvalidRequestException when):
ID does not exist
Reservations for this facility exist (A Reservations object that contains a foreign key associated with the user inputted ID exists)
Request Body:
Path Variable Integer "id"



#RESERVATION
Method	      Endpoint	                   Description	                                                   Valid API Calls
POST	        /api/v1/reservation	         Creates a facility reservation with an existing facility id	   Create reservation
GET	          /api/v1/reservation/{id}	   Get all exsiting facilities reservations	                           Get all reservations
GET	          /api/v1/reservations	       Get an exsiting user specified facility reservation	               Get reservation
DELETE	      /api/v1/reservation/{id}	   Delete an existing user specified reservation	                  Delete reservation

Create reservation
Description: Creates a facility reservation with an existing facility mid.
Validation (Throws an InvalidRequestException when):
The facility object ID does not exist (The foreign key does not exist)
Check in and check out dates are not in YYYY-MM-DD format
Check in and check out dates do not exist
Check in date comes after check out date
Number of guests is not an Integer
A reservation object with overlapping dates already exists for the hotel object
Request Body:
  {
    "hotelId": 0,
    "checkIn": "YYYY-MM-DD",
    "checkOut": "YYYY-MM-DD",
    "guests": 0, 
    "status": true
  } 

  Delete reservation
Description: Delete an existing user specified reservation.
Validation (Throws an InvalidRequestException when):
ID does not exist
Request Body:
Path variable Integer "id"




#DATABASE 
The database structure contains three tables, a Facilities table that contains the Different facilities listed as in  enum,
 a reservation table that contains reservations objects and is associated with the facilities table through the facility Id by storing it as a foreign key in its "Facility Id" value ands lastly a customer tabele that contains the customers name with an associated id which combines the facilities and the reservation.
The table structures and values are as follows:
FACILITY
Id: Integer (Primary Key)
Name: VarChar
Type: VarChar [ SPORTS, LEISURE, BUSINESS ]
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
TESTING
Tests for this project incorporated UNIT TESTING
We used PostgressSQL,Mockito  and  H2 in memory databases for the integration and unit tests.
More elaborate descriptions of the tests and their functionalities can be found in the test folder within this project.
Tech Stack
API Finding
Jupiterapi
Java
PostgressSQL
MySQL
SpringBoot
JPA
Testing:
Mockito
H2
Springboot test
User Input Testing:
Postman

Reference and Credits to https://github.com/marioszocs/spring-boot-hotel-reservation-api
