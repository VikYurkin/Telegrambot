#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME=$1
export BOT_TOKEN=$2
export BOT_DB_USERNAME='postgres'
export BOT_DB_PASSWORD='9876543219'

# Start new deployment
docker-compose up --build -d