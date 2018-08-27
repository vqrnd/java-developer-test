#!/bin/sh

nohup java -jar /masoud/apps/mqtt-jwt-masoud-api-0.0.1-alpha-SNAPSHOT.jar &
echo $! > /masoud/apps/mqtt-jwt-masoud-api.pid