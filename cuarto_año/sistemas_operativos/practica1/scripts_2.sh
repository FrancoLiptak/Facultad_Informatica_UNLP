while true # Podemos usar ':' como reemplazo de "true"
 do
  pgrep $1 | wc -l
  sleep 15s
 done

# accedo al parámetro con $1.
# pgrep devuelve el ID de los procesos que coincidan con $1.
# wc -l cuenta la cantidad de lineas devueltas por lo anterior.