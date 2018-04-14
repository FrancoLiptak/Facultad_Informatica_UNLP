#!/bin/bash

array=(`find . -type f -perm -111`)

#Los arreglos siempre contienen strings separados por espacios

function cantidad(){
	echo "${#array[*]}"	
}

function archivos(){
    IFS=$'\n' sorted=($(sort <<<"${array[*]}"))
    echo "${sorted[@]}"
}

# Las funciones pasan a ser similares a comandos 

cantidad
archivos