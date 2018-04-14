# Sistemas Operativos
## Práctica 1

## Preguntas de repaso

### 1. ¿Qué es la shell? ¿Para qué sirve?

El término shell se emplea para referirse a aquellos programas que proveen una interfaz de usuario para acceder a los servicios del sistema operativo. Estos pueden ser gráficos o de texto simple, dependiendo del tipo de interfaz que empleen. Los shells están diseñados para facilitar la forma en que se invocan o ejecutan los distintos programas disponibles en la computadora (es un intérprete de comandos).

Entonces, la Shell es en pocas palabras el entorno de escritorio (DE) o Manejador de Ventanas (WM) que utilizamos para trabajar en nuestra PC, sin importar la distribución que utilicemos ya sea por medio de GUI’s (entornos gráficos) o por la terminal respecto a la interacción que requerimos para poder utilizar los servcios y aplicaciones que ofrecen los sistemas operativos.

Entonces, KDE es un Shell, XFCE es un Shell, LXDE es un Shell, iOS es un Shell, Android es un Shell, Windows Phone es un Shell, la terminal es un shell (via bash).

### 2. ¿En qué espacio (de usuario o de kernel) se ejecuta?

Ejecuta en espacio de usuario.

### 3. Si pensamos en el funcionamiento de una shell básica podríamos detallarlo secuencialmente de la siguiente manera:
#### - Esperar a que el usuario ingrese un comando
#### - Procesar la entrada del usuario y obtener el comando con sus parámetros
#### - Crear un nuevo proceso para ejecutar el comando, iniciarlo y esperar que retorne
#### - Presentar la salida (de haberla) al usuario
#### - Volver a empezar.

### Este tipo de comportamiento, típico de las shell interactivas, se conoce como REPL (Read-Eval-Print Loop, Ciclo de leer, interpretar e imprimir).

### Analice cómo implementaría este ciclo básico de interpretación de scripts.

Para implementar un REPL en Lisp, es necesario únicamente implementar estas tres funciones y una función de bucle infinito (obviamente, la implementación de eval será complicada, dado que se debe también implementar todas las funciones primitivas como car o + y operadores especiales como if.). Hecho esto, un REPL básico es una única línea de código: ´´´ (loop (print (eval (read)))) ´´´.

Una posible implementación de eval es un intérprete recursivo que actúa sobre el árbol de sintaxis abstracta creado por read. Otra posibilidad es compilar el árbol de sintaxis en código máquina y ejecutarlo.
Las implementaciones reales de REPLs en Lisp son en ocasiones mucho más complicadas.

## Consultar.

### 4. Investigue la system call fork:
#### (a). ¿Qué es lo que realiza?

Un fork (o bifurcación) hace referencia a la creación de una copia de sí mismo por parte de un proceso, que entonces actúa como "proceso hijo" del proceso original, ahora llamado "padre". Los procesos resultantes son idénticos, salvo que tienen distinto número de proceso (PID). La operación fork crea un espacio de direcciones separado para el nuevo proceso hijo. Este tiene una copia exacta de todos los segmentos de memoria del proceso padre.

Más generalmente, una bifurcación en un entorno multihilo significa que un hilo de ejecución se bifurca.

#### (b) ¿Qué retorna?

- Si fork() retorna un valor negativo, la creación del hijo no fue exitosa.
- Caso contrario, el proceso hijo retornará 0 y el proceso padre retorna el PID del proceso creado

#### (c) ¿Para qué podrian servir los valores que retorna?

A partir de los valores que retorna, se puede identificar cuál es el padre, y cuál es el hijo. También se puede usar frente a un error.

#### (d) ¿Por qué invocaria a la misma al implementar una shell?

Esto es así porque cuando alguien llama a una instrucción de la familia "exec" no se crea un nuevo proceso, sino que reemplaza la memoria e instrucciones del proceso actual con las del proceso que se quiere ejecutar. Entonces, cuando bash quiere ejecutar algo primero hace un fork() y luego ejecuta. Si no se hiciera así, se ejecutaría el proceso pero no podríamos acceder más a la terminal bash.

## Duda. Esto tiene que ver con ejecutar programas en background?

### 5. Investigue la system call exec:

#### (a) ¿Para qué sirve?

Es una funcionalidad de los sistemas operativos que permiten correr un archivo ejecutable en el contexto de un proceso ya existente, reemplazándolo.

#### (b) ¿Comó se comporta?

Esto es llamado "overlay". El PID original no cambia, pero el código de máquina, datos, heap y stack del proceso son reemplazados por los del nuevo programa.

#### (c) ¿Cuáles son sus diferentes declaraciones POSIX?

POSIX es el acrónimo de Portable Operating System Interface, y X viene de UNIX como seña de identidad de la API.

´´´
int execl(char const *path, char const *arg0, ...);
int execle(char const *path, char const *arg0, ..., char const *envp[]);
int execlp(char const *file, char const *arg0, ...);
int execv(char const *path, char const *argv[]);
int execve(char const *path, char const *argv[], char const *envp[]);
int execvp(char const *file, char const *argv[]);
´´´

La base de cada uno es "exec" (execute), seguido de uno o más letras:
- e - Se pasa explícitamente una matriz de punteros a variables de entorno a la nueva imagen de proceso.
- l - Los argumentos de la línea de comando se pasan individualmente (una lista) a la función.
- p - Utiliza la variable de entorno PATH para encontrar el archivo nombrado en el argumento del archivo para ser ejecutado.
- v - Los argumentos de la línea de comando se pasan a la función como una matriz (vector) de punteros.

*path*: El argumento especifica el nombre de ruta del archivo para ejecutar como la nueva imagen de proceso. Los argumentos que comienzan en arg0 son punteros a los argumentos que se pasarán a la nueva imagen de proceso. El valor argv es una matriz de punteros a argumentos.

*arg0*: El primer argumento arg0 debe ser el nombre del archivo ejecutable. Usualmente es el mismo valor que el pathargument. Algunos programas pueden confiar incorrectamente en este argumento proporcionando la ubicación del ejecutable, pero no hay garantía de esto ni está estandarizado en todas las plataformas.

*envp*: Argument envp es una matriz de indicadores para la configuración del entorno. Las llamadas ejecutadas por el administrador que terminan con un e modifican el entorno para la nueva imagen de proceso al pasar una lista de configuraciones de entorno a través del argumento envp. Este argumento es una matriz de punteros de caracteres; cada elemento (excepto el elemento final) apunta a una cadena terminada en nulo que define una variable de entorno.

### 6. Investigue la system call wait:

#### (a) ¿Para qué sirve?

Sirve para bloquear el proceso llamante hasta que uno de sus procesos hijos termine o se reciba un señal. Hay dos posibilidades cuando se llama a wait():

- Si hay procesos hijos corriendo al momento de hacer un wait(), el llamador será bloqueado hasta que alguno de sus hijos termine. En ese momento el llamador resume su ejecución.
- Si no hay procesos hijos corriendo al momento de hacer un wait(), este mismo no tiene efecto. Es como si nunca se hubiese realizado la llamada al wait().

Permite esperar a que un proceso lanzado en background termine.

#### (b) Sin ella, ¿qué sucedería, pensando en la implementación de la shell?

Sin el wait no se podría esperar a que una línea de comando termine de ejecutarse para poder recibir la siguiente.

## Redes y Sistemas Operativos

### 1. La herramienta netcat provee una forma sencilla de establecer una conexión TCP/IP. En una terminal levante una sesión de netcat en modo servidor, que escuche en la IP 127.0.0.1 (localhost) en un puerto a elección. En otra terminal conéctese, también vía netcat, al servidor recién levantado. Interactúe y experimente con ambas terminales. 

Para levantar una sesión netcat en modo servidor se debe escribir el comando nc -l [ port ]. Este parámetro (l = listen) se utiliza para indicar a nc que se tiene que quedar esperando recibir pedidos de conexión en lugar de inicializar una conexión con un host remoto. El número de puerto tiene que ser mayor a 1024 (uno no privilegiado).

Para abrir una conexión ahora es necesario que en otra terminal ejecutamos el comando nc [ ip ] [ port ]

Ejemplo:
nc -l 1234  → Servidor
nc 127.0.0.1 1234  → Cliente

### 2. netcat también es bueno al momento de transmitir archivos sobre una red TCP/IP. Utilizando dos terminales como se hizo en el ejercicio anterior, transmita el archivo /etc/passwd desde una sesión de netcat hacia la otra. Tip: recordar pipes y redirecciones. 


nc -l 1234  → Servidor
cat /etc/passwd | nc 127.0.0.1 1234 → Cliente

### 5. Intérprete y describa qué es lo que hace el siguiente fragmento de código extraído de la man page de netcat.
#### 1 → $ rm −f /tmp/f; mkfifo /tmp/f
#### 2 → $ cat /tmp/f | /bin/sh −i 2>&1 | nc −l 127.0.0.1 1234 > /tmp/f 

### Tip: man mkfifo

mkfifo es un comando que se utiliza para crear “named pipes”. En la línea 1, se está definiendo el named pipe bajo el nombre “/tmp/f”. La instrucción anterior se realiza para que, en el caso de que dicho pipe ya exista, al intentar crearlo con mkfifo no haya error alguno.

/bin/sh o dash permite generar una shell. Con el parámetro -i se fuerza a que la misma se comporte de forma interactiva. Seguido a esto hay una forma de redirección.
 [n1]<&n2    Entrada estándar duplicada (o n1) del descriptor de archivo n2.

En el tercer paso del pipe se establece un servidor escuchando en el local host en el puerto 80 usando netcat.

Al hacer esto, crea un fifo en /tmp/f y hace que nc escuche en el puerto 1234 de la dirección 127.0.0.1 en el lado del 'servidor', cuando un 'cliente' establece un conexión exitosa a ese puerto, / bin / sh se ejecuta en 'servidor' lado y el indicador de shell se da al lado 'cliente'.
