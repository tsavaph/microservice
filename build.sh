#!/bin/bash

# Prepare jars
(
    cd microservice-currency-conversion || exit
    mvn clean package
)
(
    cd microservice-eureka-naming-server || exit
    mvn clean package
)
(
    cd microservice-forex-service || exit
    mvn clean package
)
(
    cd microservice-telegram-bot || exit
    mvn clean package -DBOT_USERNAME=$1 -DBOT_TOKEN=$2
)