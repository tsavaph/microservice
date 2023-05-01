# Historical Currency Microservice Application
###  Services for getting currency data by date. Currently database contains EUR-RUB, USD-RUB, GBP-RUB data.
Stack: Spring Boot, Spring Cloud (Feign, Eureka), Spring MVC, Spring Data,
Validation, Lombok, MapStruct, Maven, REST, PostgreSQL, Docker, Flyway, Swagger

## Microservices
### 1) forex-service
Currency conversion service

| Address                                                           | Method | Description                                            |
|:------------------------------------------------------------------|:-------|:-------------------------------------------------------|
| `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/day/{day}` | GET    | Get currency conversion data by exact date             |
| `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}`           | GET    | Get average currency conversion data by year and month |

### 2) netflix-eureka-naming-server
Server for connecting services all together and balancing load

### 3) currency-conversion-service (uses many forex-services)
Service for calculating the conversion amount 

| Address                                                                                | Method | Description                                                      |
|:---------------------------------------------------------------------------------------|:-------|:-----------------------------------------------------------------|
| `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/day/{day}/quantity/{quantity} ` | GET    | Get conversion amount calculation data by exact date             |
| `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/quantity/{quantity}`            | GET    | Get average conversion amount calculation data by year and month |

## Building
#### For each service run `mvn package`

## Launching
### Docker
#### Run `docker-compose -up`

### Ordinary
#### 1) create PostgreSQL database 'forex'
#### 2) type `java -jar target/microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar`
#### 3) type `java -jar target/microservice-forex-service-0.0.1-SNAPSHOT.jar`
#### 4) type `java -jar target/microservice-currency-conversion-0.0.1-SNAPSHOT.jar`

## Usage
### 1) forex-service
#### go to `localhost:8000(8001, 8002 ...)`
### 2) currency-conversion-service
#### go to `localhost:8100`

## TODO
### 1) Database
#### 1. Add more currencies data from Russian Central Bank
#### 2. Add more currencies data from foreign central banks
### 2) Services
#### 1. Create service with Telegram Bot
