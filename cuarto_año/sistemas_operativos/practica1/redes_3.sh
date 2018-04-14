#!/bin/bash

# $* devuelve todos los parametros dentro de un arreglo
# $? devuelve el estado de salida del último comando ejecutado

# nc -z Specifies that nc should just scan for listening daemons, without
#       sending any data to them.  It is an error to use this option in
#       conjunction with the -l option.

# nc -w Connections which cannot be established or are idle timeout after
#       timeout seconds.  The -w flag has no effect on the -l option,
#       i.e. nc will listen forever for a connection, with or without the
#       -w flag.  The default is no timeout.


if [ $# -eq 0 ]
then
	echo "No ha ingresado ningun host."
	exit 1;
fi

array=()

for host in $*
do
  nc -z -w 5 $host 80
  
  if [ $? = 0 ]
  then
	array[${#array[*]}]=$host
  fi
done

echo "${array[*]}"