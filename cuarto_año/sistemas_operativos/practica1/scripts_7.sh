#!/bin/bash

for file in *; do
    if [ -f "$file" ] && [ ${file##*.} == "txt" ];
    then
        cat $file >> libro.txt
    fi;
done;