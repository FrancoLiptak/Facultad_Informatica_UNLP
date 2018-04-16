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
- Además, en SumMulMatrices, tenemos que la linea ` tot[i*N+j]= cd[i*N+j]+ ab[i*N+j]; ` está definida en función de las variables i y j, pero no de k, lo cual nos permite sacarla de ese último for. Este simple cambio hace que SumMulMatrices tenga mejor tiempo de ejecución que SumMulMatricesOpt.
- Otro dato interesante, es que las únicas matrices donde importa el valor inicial son ab y cd. Podríamos no inicializar las otras.
- La inicialización podría realizarse en la creación, con la función ` calloc `. Por ejemplo: ` ab = (double*) calloc(N*N, sizeof(double)); `.

### 3. Describir brevemente cómo funciona el algoritmo multBloques.c que resuelve la multiplicación de matrices cuadradas de N*N utilizando una técnica de multiplicación por bloques. Ejecutar el algoritmo utilizando distintos tamaños de matrices y distintos tamaño de bloque. Comparar los tiempos con respecto a la multiplicación de matrices optimizada del ejercicio 1. Según el tamaño de las matrices y de bloque elegido ¿Cuál es más rápido? ¿Por qué? ¿Cuál sería el tamaño de bloque óptimo para un determinado tamaño de matriz?

El algoritmo funciona de la siguiente manera:

- Verifica los valores pasados como parámetros.
- Calcula la dimensión de la matriz, la cantidad de datos de la matriz, y la cantidad de datos por bloque.
- Aloca el espacio necesario para las 3 matrices.
- La matriz A está compuesta por datos random entre 0 y 9.
- La matriz B es una matriz identidad. Cuando el numeroFila = numeroColumna, el valor será 1.0. Caso contrario será 0.0.
- Se realiza el producto: Para esto, es necesario entender como se está almacenando la matriz. La matriz en este caso está dividida en bloques, y cada bloque tiene filas y columnas. Por ejemplo: siendo N la longitud de una fila, tenemos que I * N nos indica "la fila" actual en la matriz (está entre comillas porque en realidad, en la memoria son posiciones consecutivas, no hay filas y columnas). Si a eso le sumamos J, tenemos que estamos en una determinada posicion dentro de una fila (J es el desplazamiento a partir de la posición base de la fila). Al multiplicar todo por el tamaño del bloque, estamos viendo que en realidad, I*N+J nos indica un corrimiento dentro del bloque. Al hacer el producto de esta manera, ahorramos los accesos a memoria para buscar información, gracias al principio de localidad espacial.

Para tamaños de bloque chicos, el ejercicio 1 es más eficiente. Podemos asociar esto a la cantidad de desplazamientos que hay que realizar en multBloques en este caso, donde no se aprovecha el principio de localidad espacial. Si aumenta el número de bloques, la multiplicación por bloques mejora el rendimiento.

### (Preguntar cual es el tamaño de bloque óptimo).

### 4. Analizar el algoritmo triangular.c que resuelve la multiplicación de una matriz cuadrada por una matriz triangular inferior, ambas de N*N, ¿Cómo se podría optimizar el código? Implementar una solución optimizada y comparar los tiempos probando con diferentes tamaños de matrices.

Para optimizar el código, podemos cambiar la manera de inicializar la matriz C. En lugar de alocar espacio con ` C=(double*)malloc(sizeof(double)*N*N); ` , podemos iniciarlizarla con ` C=(double*)calloc(N*N,sizeof(double)); `. Esto inicializa la matriz en 0. También hay que eliminar la inicialización explícita en 0 de dicha matriz.

# Dudas: Creo que la matriz BT no se inicializa con 1. ¿Solamente eso se puede hacer?

### 5. El algoritmo fib.c resuelve la serie de Fibonacci, para un número N dado, utilizando dos métodos: recursivo e iterativo. Analizar los tiempos de ambos métodos ¿Cuál es más rápido? ¿Por qué?

La solución iterativa es más rápida.

En general, si comparamos una solución iterativa contra una solución recursiva para un problema dado tenemos que:

- La carga computacional (tiempo de CPU y espacio en memoria) asociada a las llamadas recursivas.
- La redundancia (algunas soluciones recursivas resuelven un problema en repetidas ocasiones).
- La complejidad de la solución (en ocasiones, la solución iterativa es muy difícil de encontrar).
- La concisión, legibilidad y elegancia del código resultante de la solución recursiva del problema.

Algo recursivo o recurrente es algo que se llama a si mismo. Tiene que ver con el principio de inducción.
La recursividad consume muchísima memoria, ya que mantiene las variables del método mientras que se ejecuta, y también mucho tiempo. La recursividad es costosa pero es más natural, se prefiere. Por ejemplo, Java no puede implementar de forma recursiva el cálculo del factorial de un millón, pues cualquier computador se quedaría sin memoria, sin embargo, es necesario de implementar para poder escribir y entender ciertos programas.