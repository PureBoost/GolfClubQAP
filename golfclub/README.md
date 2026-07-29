# Golf Club Management API

This project is a Spring Boot REST API built for managing a golf club's members and tournaments. The application
allows users to add and view members, create and view tournaments, register members for tournaments, and search 
for members and tournaments using different search options.

## Screenshots
All screenshots for the project are available in the [Screenshots](Screenshots) folder.

## Search Endpoints

The API includes search endpoints for both Members and Tournaments.

## Members

### Search by Name

Find members by their name.

Example:
http://localhost:8080/members/search/name/Lucas


### Search by Membership Type

Example:
http://localhost:8080/members/search/type/Annual


### Search by Phone Number

Example:
http://localhost:8080/members/search/phone/7095551234


### Search by Tournament Start Date

Example:
http://localhost:8080/members/search/tournament-date/2026-08-01


## Tournament Search

### Search by Location

Example:
http://localhost:8080/tournaments/search/location?location=Paradise


### Search by Start Date

Example:
http://localhost:8080/tournaments/search/date?startDate=2026-08-01


# Running the Project in Docker

The project can be run locally using Docker.

Before running Docker, update application.properties to use the Docker MySQL container:

spring.datasource.url=jdbc:mysql://golfclub-mysql:3306/golfclub  
spring.datasource.username=root  
spring.datasource.password=password  

Start the containers:

**_docker compose up -d_**

The API will run on:

http://localhost:8080

To stop the containers:

docker compose down


# AWS RDS Connection

The local MySQL database was replaced with an AWS RDS MySQL database.

The Spring Boot datasource configuration was changed to use the RDS endpoint:

spring.datasource.url=jdbc:mysql://golfclub-db.ckhqum4cs2x0.us-east-1.rds.amazonaws.com:3306/golfclub
spring.datasource.username=root
spring.datasource.password=password

The RDS instance was configured with:

- MySQL Community Edition
- Port 3306
- Public access enabled
- Security group allowing inbound MySQL traffic from the development machine


# Issues Encountered and Solutions

While setting up AWS RDS, I ran into an issue where the database connection was being blocked.
The RDS instance was running, but my application could not connect until I configured the security group
to allow inbound MySQL traffic on port 3306 from my IP address.

Another issue was making sure the project could switch between the Docker MySQL database and the AWS RDS
database. I handled this by just updating the datasource in application.properties depending on
whether I was running the project locally with Docker or connecting to the RDS instance.