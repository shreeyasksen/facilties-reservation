# Facility Reservation API

- [Facility Reservation API](#facility-reservation-api)
  - [Overview](#overview)
- [Explore REST APIs](#explore-rest-apis)
  - [Facility](#facility)
    - [Create facility](#create-facility)
    - [Update existing facility](#update-existing-facility)
    - [Get all existing facilities](#get-all-existing-facilities)
    - [Get all available facilities](#get-all-available-facilities)
    - [Get a user specified facility](#get-a-user-specified-facility)
    - [Delete facility](#delete-facility)
  - [Reservation](#reservation)
    - [Create reservation](#create-reservation)
    - [Get all reservations](#get-all-reservations)
    - [Get reservation](#get-reservation)
    - [Delete reservation](#delete-reservation)
  - [Customer](#customer)
    - [Create customer](#create-customer)
    - [Get all customers](#get-all-customers)
    - [Get customer](#get-customer)
    - [Delete customer](#delete-customer)
- [Database](#database)
  - [facility](#facility)
  - [reservation](#reservation)
  - [customer](#customer)
- [Testing](#testing)
- [Tech Stack](#tech-stack)

## Overview

- This project is a Facility Reservation `RESTful API` designed to **create facilities and associated reservations.**
- The user can create a facility, search for available facilities, search for all facilitis within the database, search all reservations within the database, make reservations to a specific facility, delete reservations as well as facilitis.
- Necessary validation is incorporated within the API that prevents illogical operations from occurring such as making an overlapping reservation to a facility or deleting a facility that contains reservations.
- The Facility-Reservation-API follows a standard "`User` <-> `Controller` <-> `Validator` (Only interacts with Controller) <-> `Service` <-> `Repository` <-> `Database`" API schema.

# Explore REST APIs

## Facility

| Method | Endpoint                                                | Description                                         | Valid API Calls                                                 |
| ------ | ------------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------------------- |
| POST   | /facility                                               | Create a facility object                            | [Create facility](#create-facility)                             |
| PATCH  | /facility                                               | Updates an existing facility                        | [Update existing facility](#update-existing-facility)           |
| GET    | /facility                                               | Get all existing facilities                         | [Get all existing facilities](#get-all-existing-facilities)     |
| GET    | facility/availabilitySearch?dateFrom={from}&dateTo={to} | Get all available facility between specified dates. | [Get all available facilities](#get-all-available-facility)     |
| GET    | /facility/{id}                                          | Get a user specified facility                       | [Get a user specified facility](#get-a-user-specified-facility) |
| DELETE | /facility/{id}                                          | Delete a user specified Facility                    | [Delete facility](#delete-facility)                             |

### Create facility

- Description: Create a facility object.
- Validation (Throws an `InvalidRequestException` when):
  - Type is not SPORTS, LEISURE, or BUSINESS
  - Date is not YYYY-MM-DD
  - Only one date is recieved
  - `availableTo` date comes before `availableFrom` date
  - Name is `null`
- Request Body:
  ```json
  {
    "name": "String",
    "type": "String: (SPORTS, LEISURE, BUSINESS)",
    "description": "String",
    "availableFrom": "YYYY-MM-DD",
    "availableTo": "YYYY-MM-DD",
    "status": true
  }
  ```

### Update existing facility

- Description: Updates an existing facility object.
- Validation (Throws an `InvalidRequestException` when):
  - ID does not exist
  - Type is not SPORTS, LEISURE, BUSINESS
  - Date is not YYYY-MM-DD
  - Only one date is recieved
  - `availableTo` date comes before `availableFrom` date
  - Name is `null`
- Request Body:
  ```json
  {
    "id": 0,
    "name": "String",
    "type": "String: (SPORTS, LEISURE, BUSINESS)",
    "description": "String",
    "availableFrom": "YYYY-MM-DD",
    "availableTo": "YYYY-MM-DD",
    "status": true
  }
  ```

### Get all existing facilities

- Description: Get all existing facilities
- Validation (Throws an `InvalidRequestException` when):
  - N/A
- Request Body: N/A

### Get all available facilites

- Description: Gets all available facility inventories between specified dates. This endpoint takes into account pre-exisiting reservations and facility availability dates and only returns facilities that do not have overlapping reservations and do not have availibility dates that start or end between the user specified dates.
- Validation (Throws an `InvalidRequestException` when):
  - Dates are not in YYYY-MM-DD format
  - `dateFrom` comes after `dateTo`
  - One or no dates are inputted
- Request Body:
  - Path Variables dateTo and dateFrom, in YYYY-MM-DD format

### Get a user specified facility

- Description: Get a user specified facility object.
- Validation (Throws an `InvalidRequestException` when):
  - ID does not exist
- Request Body: N/A

### Delete facility

- Description: Delete a user specified Facility
- Validation (Throws an `InvalidRequestException` when):
  - ID does not exist
  - Reservations for this facility object exist (A Reservations object that contains a foreign key associated with the user inputted ID exists)
- Request Body:
  - Path Variable Integer "id"

## Reservation

| Method | Endpoint          | Description                                                 | Valid API Calls                               |
| ------ | ----------------- | ----------------------------------------------------------- | --------------------------------------------- |
| POST   | /reservation      | Creates a facility reservation with an existing facility id | [Create reservation](#create-reservation)     |
| GET    | /reservation/{id} | Get all exsiting facility reservations                      | [Get all reservations](#get-all-reservations) |
| GET    | /reservations     | Get an exsiting user specified Facility reservation         | [Get reservation](#get-reservation)           |
| DELETE | /reservation/{id} | Delete an existing user specified reservation               | [Delete reservation](#delete-reservation)     |

### Create reservation

- Description: Creates a facility reservation with an existing facility id.
- Validation (Throws an `InvalidRequestException` when):
  - The facility object ID does not exist (The foreign key does not exist)
  - Check in and check out dates are not in YYYY-MM-DD format
  - Check in and check out dates do not exist
  - Check in date comes after check out date
  - Number of guests is not an Integer
  - A reservation object with overlapping dates already exists for the facility object
- Request Body:
  ```json
  {
    "facilityId": 0,
    "checkIn": "YYYY-MM-DD",
    "checkOut": "YYYY-MM-DD",
    "guests": 0,
    "status": true
  }
  ```

### Get all reservations

- Description: Get all exsiting facility reservations.

### Get reservation

- Description: Get an exsiting user specified Facility reservation
- Validation (Throws an `InvalidRequestException` when):
  - ID does not exist
- Request Body:
  - Path variable Integer "id"

### Delete reservation

- Description: Delete an existing user specified reservation.
- Validation (Throws an `InvalidRequestException` when):
  - ID does not exist
- Request Body:
  - Path variable Integer "id"

# Database

- The database structure contains 3 tables, a `facility` table that contains the facility object.

- And a `customer` table that contains customers objects and is associated with the `reservation` table through the `reservation table's Id` by storing it as a foreign key in its "reservation Id" value.

- And a `reservation` table that contains reservations objects and is associated with the `facility` table through the `facility table's Id` by storing it as a foreign key in its "facility Id" value, and is also associated with the `customer` table through the `customer table's Id` by storing it as a foreign in its "customer Id" value.

- The table structures and values are as follows:

## facility

- Id: Long (Primary Key)
- Name: VarChar
- Type: VarChar [ SPORTS, LEISURE, BUSINESS ]
- Description: VarChar
- Availiable From: Date YYYY-MM-DD
- Available To: Date YYYY-MM-DD
- Status: Boolean

## reservation

- Id: UUID (Primary Key)
- Customer Id: UUID (Foreign Key)
- Facility Id: Long (Foreign Key)
- Check in Date: Date YYYY-MM-DD
- Check out Date: Date YYYY-MM-DD
- Number of Guests: Integer
- Status: Boolean

## customer

- Id: UUID (Primary Key)
- Name: VarChar
- Reservation Id: UUID (Foreign Key)
- Email: Email
- ContactNumber: String
- Status: Boolean

# Testing

- WIP

# Tech Stack

- API Creation:
  - Java
  - PostgreSQL
  - SpringBoot
  - Hibernate
  - JPA
  - Lombok
- Testing:
  - Mockito
  - H2
- User Input Testing:
  - Postman

Reference and Credits to https://github.com/marioszocs/spring-boot-hotel-reservation-api
