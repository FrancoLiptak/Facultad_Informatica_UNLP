#!/bin/bash

if [ $# -eq 0 ]
then
	echo "Pelotudo no dijiste ningun host con el puerto 80 abierto."
	exit 1;
fi

array=()

for host in $*
do
   result=`printf "HEAD / HTTP/1.0\r\n\r\n"| nc $host 80 | grep "Content-Length" | grep -o '[0-9]*'`;
   echo "$host: $result"
done