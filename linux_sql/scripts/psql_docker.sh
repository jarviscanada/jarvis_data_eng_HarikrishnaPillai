#! /bin/sh

#arguments
cmd=$1
db_username=$2
db_password=$3

#Start docker
sudo systemctl status docker || systemctl cmd

#check container status 
docker container inspect jrvs-psql
container_status=$?

#User switch case to handle create|stop|start opetions
case $cmd in 
  create)
  
  # Check if the container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1	
	fi

  #check # of CLI arguments
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi
  
  #Create container
	docker volume create db_username
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v db_username:/var/lib/postgres/data -p 5432:5432 postgres:9.6_alpine
	exit $?
	;;

  start|stop) 
  #check instance status; exit 1 if container has not been created
  if [ $container_status -ne 0 ]; then
     echo 'Container has not been created'
     exit 1
  fi

  #Start or stop the container
	docker container $cmd jrvs-psql
	exit $?
	;;	
  
  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac

