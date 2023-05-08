REM Prepare jars

cd microservice-currency-conversion
call mvn clean package
cd ..

cd microservice-eureka-naming-server
call mvn clean package
cd ..

cd microservice-forex-service
call mvn clean package
cd ..

cd microservice-telegram-bot
call mvn clean package -DBOT_USERNAME=%1 -DBOT_TOKEN=%2
cd ..