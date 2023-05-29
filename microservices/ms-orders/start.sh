#!/bin/sh
while ! nc -z $EUREKA_HOST $EUREKA_PORT ; do
    echo "Waiting for Eureka Server"
    sleep 3
done

java -jar app.jar
#