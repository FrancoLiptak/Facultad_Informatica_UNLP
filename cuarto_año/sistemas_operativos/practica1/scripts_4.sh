#!/bin/bash

for element in *; do
    indice=1
    if [ -d "$element" ]; then #Si es un directorio, accedo
        cd "$element"
        for file in *; do 
            if [ -f "$file" ] && [ ${file##*.} == "jpg" ]; then
                extension=${file##*.}
                actual_file_name=${file%.*}
                directory_name=${element%.*}
                mv "$actual_file_name.$extension" "$directory_name-$indice.$extension"
                ((indice++))
            fi;
        done;
        cd ..
    fi;
done;

