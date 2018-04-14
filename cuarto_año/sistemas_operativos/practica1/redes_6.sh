#!/bin/bash

# Agradecimientos a Franco Borrelli

if [ "$#" != 2 ] 
then
	echo "Pasame los parametros bien imbecil"
	exit 1;
fi

if [ "$1" != 's' ] && [ "$1" != 'c' ]
then
	echo "El primer parametro tiene que ser una c o una s, no es muy dificil";
	exit 2;
fi

if [ "$1" == 's' ]
then	
        echo "Started listening on port 1234."
        echo "Stream (Press ctrl + c to end session) >"
	awk  -v date="$(date)" -v user="$2" '{ print date,",",user,"says:\n",$0; fflush()}' | nc -l 12345
else
	awk  -v date="$(date)" -v user="$2" '{ print date,",",user,"says:\n",$0; fflush()}' | nc localhost 12345
fi
