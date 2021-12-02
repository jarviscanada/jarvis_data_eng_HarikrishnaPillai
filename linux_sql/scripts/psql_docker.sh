#! /bin/sh

#Capture CLI arguments
cmd=$1
db_username=$2
db_password=$3

#start docker if docker server is not running use cmd argument
sudo systemctl status docker || systemctl $cmd docker

#inspect container , by using #? store the same to container_status variable
docker container inspect jrvs-psql
container_status=$?

#Basic switch case to Start,stop container or create database.
case $cmd in
create)

    #Condition to check if the container is not working
    if [ $container_status -eq 0 ]; then
        echo 'Container already exists'
        exit 1
    fi

    # '$#' checks for the number of arguments and checks if user supplied username and password
    if [ $# -ne 3 ]; then
        echo 'Create/start/stop requires username and password'
        exit 1
    fi
    #docker volume and docker run commands to create database using jrvs-psql
    docker volume create $db_username
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -d -v $db_username:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
    exit $?
    ;;

start | stop)
    #Condition to check if the container has not been created
    if [ $container_status -ne 0 ]; then
        echo 'container has not been not created'
        exit 1
    fi

    #This step perform the required operation given at $1 of the cli argument.
    docker container $cmd jrvs-psql
    exit $?
    ;;

*)
    #This code executes if the user specifies apart from start,stop or create operation.
    echo 'Illegal command'
    echo 'Commands: start|stop|create'
    exit 1
    ;;
esac