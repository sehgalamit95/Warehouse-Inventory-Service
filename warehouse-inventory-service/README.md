# Warehouse Inventory Service

warehouse-inventory-service

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.

### Prerequisites

Things you need to get the project up and running on a local machine:

* Java 11
* Maven latest

### Installing

Follow the below steps to build the application:


* Build the jar file using maven

mvn clean install


### Running the application

* Running the jar from command line (if using maven to build the jar file)

java -jar -Dspring.profiles.active=default target/warehouse-inventory-service-0.0.1-SNAPSHOT.jar
or
mvn spring-boot:run


#### Swagger Endpoint

* Open 'http://localhost:8080/swagger-ui.html' in the web browser to open swagger to check REST apis.

#### REST services

* REST services can be accessed on: http://localhost:8080

#### Actuators

* Can be accessed on: http://localhost:8080/actuator

#### H2-Console
* Can be accessed on: http://localhost:8080/h2-console with below details: 
  * JDBC URL:  jdbc:h2:mem:testdb
  * User Name: sa
  * Password: (no password required)

#### Postman Testing
* Sample package included for testing: IKEA_Warehouse_Inventory_Service.postman_collection