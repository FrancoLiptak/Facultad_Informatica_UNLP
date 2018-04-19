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

##### Consultar duda en el código. No entiendo como controla los argumentos ni el resultado.

- argv contiene en su posición 0 el nombre del programa, posición 1 es N.
- El resultado tiene que ser N. Check es un entero que lo usa como un booleano.

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

El tamaño del bloque óptimo estará relacionado con el tamaño del bloque llevado a memoria caché. Con un tamaño de bloque grande, habrá muchos fallos de caché y habrá que ir muchas veces a memoria a buscar información. Por el contrario, si el bloque es chico, habrá que llevar y traer bloques con mucha frecuencia y la performance se ve perjudicada. Para este ejercicio, un bloque de 32 parece el tamaño óptimo.

##### Duda: ¿si trae mucha matriz (un tamaño de bloque muy grande) no es mejor?

- El tamaño óptimo del bloque depende de la caché.

### 4. Analizar el algoritmo triangular.c que resuelve la multiplicación de una matriz cuadrada por una matriz triangular inferior, ambas de N*N, ¿Cómo se podría optimizar el código? Implementar una solución optimizada y comparar los tiempos probando con diferentes tamaños de matrices.

Para optimizar el código, podemos cambiar la manera de inicializar la matriz C. En lugar de alocar espacio con ` C=(double*)malloc(sizeof(double)*N*N); ` , podemos iniciarlizarla con ` C=(double*)calloc(N*N,sizeof(double)); `. Esto inicializa la matriz en 0. También hay que eliminar la inicialización explícita en 0 de dicha matriz.

###### ¿Solamente eso se puede hacer?

- Otra cosa que se puede hacer es no inicializar la parte superior de la matriz BT en 0. Luego, tenemos que cambiar la linea ` C[i*N+j]=C[i*N+j] + A[i*N+k]*BT[k+j*N]; ` por ` C[i*N+j]=C[i*N+j] + A[i*N+k]*BT[j*N+(k-(k-j))]; `, para controlar que no se intente acceder a posiciones de la matriz que no tienen valores asignados (la solución original inicializa en 0, nosotros tenemos que evitar inicializar en 0 (y por ende tenemos que evitar consultar esas posiciones), ya que desperdicia tiempo y espacio de forma innecesaria).

### 5. El algoritmo fib.c resuelve la serie de Fibonacci, para un número N dado, utilizando dos métodos: recursivo e iterativo. Analizar los tiempos de ambos métodos ¿Cuál es más rápido? ¿Por qué?

La solución iterativa es más rápida.

En general, si comparamos una solución iterativa contra una solución recursiva para un problema dado tenemos que:

- La carga computacional (tiempo de CPU y espacio en memoria) asociada a las llamadas recursivas.
- La redundancia (algunas soluciones recursivas resuelven un problema en repetidas ocasiones).
- La complejidad de la solución (en ocasiones, la solución iterativa es muy difícil de encontrar).
- La concisión, legibilidad y elegancia del código resultante de la solución recursiva del problema.

Algo recursivo o recurrente es algo que se llama a si mismo. Tiene que ver con el principio de inducción.
La recursividad consume muchísima memoria, ya que mantiene las variables del método mientras que se ejecuta, y también mucho tiempo. La recursividad es costosa pero es más natural, se prefiere. Por ejemplo, Java no puede implementar de forma recursiva el cálculo del factorial de un millón, pues cualquier computador se quedaría sin memoria, sin embargo, es necesario de implementar para poder escribir y entender ciertos programas.

### 6. El algoritmo funcion.c resuelve, para un x dado, una operación matemática. El algoritmo compara dos alternativas de solución. ¿Cuál de las dos formas es más rápida? ¿Por qué?

En mi máquina local, tuve los siguientes resultados:

```
Funcion calculada...
 Tiempo total en segundos 0.4488320351 
 Tiempo promedio en segundos 0.0000000045 
Funcion calculada cada vez...
 Tiempo total en segundos 0.7574851513 
 Tiempo promedio en segundos 0.0000000076
 ```

 La razón de que la solución que primero calcula la función sea más rápida es la siguiente:
 - Como la segunda solución realiza el cálculo dentro del for, tenemos que se hace la cantidad de veces que el for itere. Si llamamos M a la operación que calcula la función, tenemos que el tiempo de ejecución es de (M+T)*N (siendo N la cantidad de vueltas al for, y "T" corresponde en este ejemplo a la suma del resultado).
 - La primer solución plantea calcular la función solo una vez, el tiempo sería M+N*T.

### 7. El algoritmo instrucciones.c compara el tiempo de ejecución de las operaciones básicas: suma (+), resta (-), multiplicación (*) y división (/), para dos operandos dados x e y. ¿Qué análisis se puede hacer de cada operación? ¿Qué ocurre si x e y son potencias de 2?

Las operaciones se hacen la misma cantidad de veces: 1000000000.

La salida en la terminal fue:

```
Suma...
 Tiempo total en segundos 1.8209850788 
 Tiempo promedio en segundos 0.0000000018 
Resta...
 Tiempo total en segundos 2.2676270008 
 Tiempo promedio en segundos 0.0000000023 
Producto...
 Tiempo total en segundos 2.1992869377 
 Tiempo promedio en segundos 0.0000000022 
Division...
 Tiempo total en segundos 5.1558990479 
 Tiempo promedio en segundos 0.0000000052
```

Esto es por la cantidad de ciclos de reloj que tiene que ejecutar. Por ejemplo:
- La suma no representa ninguna "instrucción extra".
- Para la resta A-B, primero se hace un cambio de signo de B, y luego ejecuta una suma.
- Para la multiplicacion son varias sumas.
- Para la division primero intenta cambiar el numero y despues hace la multiplicacion. Por ejemplo, es más eficiente la operación "4*0,5" que la operación "4/2".

### 8. En función del ejercicio 7 analizar el algoritmo instrucciones2.c que resuelve una operación binaria (dos operandos) con dos operaciones distintas. 

```
Division...
 Tiempo total en segundos 5.1570799351 
 Tiempo promedio en segundos 0.0000000052 
Producto...
 Tiempo total en segundos 2.1020569801 
 Tiempo promedio en segundos 0.0000000021 
Resultado correcto
```

La razón de que la multiplicación sea más rápida que la división es la definida en la respuesta al punto 7:

Esto es por la cantidad de ciclos de reloj que tiene que ejecutar. Por ejemplo:
- Para la multiplicacion son varias sumas.
- Para la division primero intenta cambiar el numero y despues hace la multiplicacion. Por ejemplo, es más eficiente la operación "4*0,5" que la operación "4/2".

### 9. Analizar el algoritmo iterstruc.c que resuelve una multiplicación de matrices utilizando dos estructuras de control distintas. ¿Cuál de las dos estructuras de control tiende a acelerar el cómputo?

El for tiende de acelerar el cómputo.

Para una matriz de 1024*1024 y 10 repeticiones:

```
Incializando matrices...
Calculando While...
Tiempo While en segundos 11.655083 
Calculando For...
 Tiempo For en segundos 11.701611 
```

Para una matriz de 1024*1024 y 20 repeticiones:

```
Incializando matrices...
Calculando While...
Tiempo While en segundos 11.596586 
Calculando For...
 Tiempo For en segundos 11.595632 
```

Para una matriz de 2048*2048 y 20 repeticiones:

```
Incializando matrices...
Calculando While...
Tiempo While en segundos 94.346017 
Calculando For...
 Tiempo For en segundos 93.159317
```

La conclusión depende de la arquitectura sobre la que estemos trabajando.
El while, como tal, es simplemente una iteración y está implementada en bajo nivel como saltos. Entonces, simplemente evalúa una condición y realiza el salto (pasando en limpio, el while evalúa un bloque, y ejecuta un bloque). Podríamos afirmar entonces, que el for debería ser más ineficiente, ya que ejecuta 3 bloques para evaluar la condición, y ejecuta el bloque que tiene adentro.

Los creadores del lenguaje C invirtieron tiempo en optimizar el for, pero no el while. El while es "normal". El for es más eficiente. Sin embargo, hay que ver si el compilador esté optimizando o no el for.

##### Duda, no entiendo lo de la repeticion que hay que pasarle al programa. No se donde lo usa.

- Las repeticiones las utiliza en la variable N. Si bien hay un N inicializado, la idea es no usarlo nunca. Lo vamos a reemplazar con el parámetro.


