#!/bin/bash
#Setup and validate arguments

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args
if [ $# -ne  5 ]; then
  echo "Not valid set of commands"
  exit 1
fi

#Save machine statistics commands to variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
lscpu_out=$(lscpu)

#Retrieve hardware specification variables

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU(\s)MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2(\s)cache" | awk '{print $3}' | xargs)
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}'|xargs)
timestamp=$(vmstat -t| awk '{print $18"\t"$19}' | tail -n1|xargs)

#Insertion of hardware specification into our database
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)VALUES('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', $cpu_mhz, '$l2_cache', '$total_mem', '$timestamp')";

#setup password as env variable
export PGPASSWORD=$psql_password

#Command to insert a row of the above insert values
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
esac
