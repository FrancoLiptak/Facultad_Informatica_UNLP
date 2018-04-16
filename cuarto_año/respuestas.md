# Práctica Nro. 1

## Optimización de algoritmos secuenciales

### 1. Analizar el algoritmo matrices.c que resuelve la multiplicación de matrices cuadradas de N*N. ¿Dónde cree que se producen demoras? ¿Cómo podría optimizarse el código? Implementar una solución optimizada y comparar los tiempos probando con diferentes tamaños de matrices.

Podemos basarnos en dos puntos importantes para mejorar la performance:
- El código de la cátedra realiza el recorrido de las matrices por filas. Para mejorarlo, debería recorrerse por columnas. La razón es la manera en que se organizan los datos en la memoria caché del procesador (principio de localidad espacial).
- Las llamadas a funciones obligan a hacer un cambio de contexto, el cual es costoso. Si reemplazaramos las llamadas a funciones por su código, el tiempo se reduciría.

En una matriz de 1024 * 1024 en mi máquina local, tuve los siguientes tiempos:
- Corriendo el programa tal y como está, tardó 71.960486 segundos.
- Bajamos a 53.595559 cambiando solo el tipo de acceso a la matriz (en lugar de ser por filas, lo hice por columnas).
- Finalmente bajamos a 11.716093, quitando los llamados a funciones.

### Consultar duda en el código. No entiendo como controla los argumentos ni el resultado.

### 2. Analizar los algoritmos SumMulMatrices.c y SumMulMatricesOpt.c que resuelven la siguiente operación (A*B) + (C*D) donde A, B, C y D son matrices cuadradas de N*N. Comparar los tiempos probando con diferentes tamaños de matrices, ¿Cuál es más rápido? ¿Por qué?

Con tamaño de matriz 256*256:

- SumMulMatricesOpt: Tiempo en segundos 0.376359
- SumMulMatrices: Tiempo en segundos 0.487863

Con tamaño de matriz 512*512:

- SumMulMatricesOpt: Tiempo en segundos 3.012367
- SumMulMatrices: Tiempo en segundos 3.818597

Con tamaño de matriz 1024*1024:

- SumMulMatricesOpt: Tiempo en segundos 24.768667
- SumMulMatrices: Tiempo en segundos 29.987039

- La diferencia entre ambos en código es por como realizan la multiplicación. En SumMulMatrices tenemos que toda la operación se hace dentro de 3 for anidados. En SumMulMatricesOpt tenemos que cada operacion se hace en for anidados diferentes. 
- Además, en SumMulMatrices, tenemos que la linea ´´´ tot[i*N+j]= cd[i*N+j]+ ab[i*N+j]; ´´´ está definida en función de las variables i y j, pero no de k, lo cual nos permite sacarla de ese último for. Este simple cambio hace que SumMulMatrices tenga mejor tiempo de ejecución que SumMulMatricesOpt.
- Otro dato interesante, es que las únicas matrices donde importa el valor inicial son ab y cd. Podríamos no inicializar las otras.
- La inicialización podría realizarse en la creación, con la función ´´´ calloc ´´´. Por ejemplo: ´´´ ab = (double*) calloc(N*N, sizeof(double)); ´´´.