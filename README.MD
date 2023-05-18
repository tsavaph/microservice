# Microservices of Historical Currency Converter
###  Services for historical currency conversion by date with endpoint in Telegram bot. Currently, database contains EUR-RUB, USD-RUB, GBP-RUB data.
Stack: Spring Boot, Spring Cloud (Feign, Eureka), Spring MVC, Spring Data, Validation,
Lombok, MapStruct, Maven, REST, PostgreSQL, Docker, Flyway, Swagger, telegrambots

## Microservices
1) ### forex-service
    Currency conversion service

    | Address                                                           | Method | Description                                            |
    |:------------------------------------------------------------------|:-------|:-------------------------------------------------------|
    | `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/day/{day}` | GET    | Get currency conversion data by exact date             |
    | `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}`           | GET    | Get average currency conversion data by year and month |

2) ### netflix-eureka-naming-server
    Server for connecting services all together and balancing load

3) ### currency-conversion-service (uses many forex-services)
    Service for calculating the conversion amount

   | Address                                                                                | Method | Description                                                      |
   |:---------------------------------------------------------------------------------------|:-------|:-----------------------------------------------------------------|
   | `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/day/{day}/quantity/{quantity} ` | GET    | Get conversion amount calculation data by exact date             |
   | `/from/{from}/to/{to}/year/{year}/`<br/>`month/{month}/quantity/{quantity}`            | GET    | Get average conversion amount calculation data by year and month |

4) ### telegram-bot-service (uses currency-conversion-service)
    Service for getting currency conversion values via Telegram Bot.

## Building
* ### Windows
  #### run `start build.bat bot_name bot_token`
* ### Linux
  #### run `bash build.sh bot_name bot_token`

## Launching
### Docker
#### Run `docker-compose -up`

### Ordinary
1) #### create PostgreSQL database 'forex'
2) #### type `java -jar microservice-eureka-naming-server/target/microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar`
3) #### type `java -jar microservice-forex-service/target/microservice-forex-service-0.0.1-SNAPSHOT.jar`
4) #### type `java -jar microservice-currency-conversion/target/microservice-currency-conversion-0.0.1-SNAPSHOT.jar`
5) #### type `java -jar -Dbot.username=bot_name -Dbot.token=bot_token microservice-telegram-bot/target/microservice-telegram-bot-0.0.1-SNAPSHOT.jar`

## Usage
1) ### forex-service
    #### go to `localhost:8000(8001, 8002 ...)`
2) ### currency-conversion-service
    #### go to `localhost:8100`
3) ### telegram-bot-service
    #### open Telegram and find `@TsavaphCurrencyHistoryBot`

## TODO
1) #### forex-service
   1) add more currency data from Russian Central Bank to database
   2) add more currency data from foreign central banks to database
2) #### all services
   1) add tests  
