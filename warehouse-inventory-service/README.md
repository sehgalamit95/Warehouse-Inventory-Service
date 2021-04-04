# widget-service
widget-service

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.

### Prerequisites

Things you need to get the project up and running on a local machine:

* Java 11
* Maven latest
* Docker latest version

### Installing

Follow the below steps to build the application:


* Build the jar file using maven

mvn clean install


or

* Build the jar and the docker image of the application by executing below command

docker build -t widget-service:0.0.1-SNAPSHOT .



### Running the application

* Starting a docker container with the docker image image

docker run --name widget_service -p 8080:8080 -d widget-service:0.0.1-SNAPSHOT


or

* Running the jar from command line (if using maven to build the jar file)

java -jar -Dspring.profiles.active=default .target/widget-service-0.0.1-SNAPSHOT.jar


#### Swagger Endpoint

* Open 'http://localhost:8080/swagger-ui.html' in the web browser to open swagger to check REST apis.

#### REST services

* REST services can be accessed on: http://localhost:8080

#### Actuators

* Can be accessed on: http://localhost:8080/actuator
