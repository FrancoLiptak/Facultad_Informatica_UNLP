#!/bin/bash

cd 
if [ -d ./backup/ ];
then
    sudo rm ./backup/*
else
    mkdir backup
fi

for file in *; do
    if [ -f "$file" ];
    then
        newname=$(tr '[:lower:]' '[:upper:]' <<< "${file}")
        cp "$file" ./backup/"$newname"
    fi;
done;