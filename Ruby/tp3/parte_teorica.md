## Excepciones

### 1. Investigá la jerarquía de clases que presenta Ruby para las excepciones. ¿Para qué se utilizan las siguientes clases?

https://airbrake.io/blog/ruby-exception-handling/ruby-exception-classes

- IOError: lectura de un stream cerrado, escribir a un sistema de sólo lectura y operaciones parecidas.

- NameError: el intérprete encuentra un identificador que no puede resolver ni como método, ni como variable.

- RuntimeError: es la excepción que se lanza por defecto. Por ejemplo, al poner "raise" sin ningún nombre de excepción.

- NotImplementedError: se intenta utilizar una característica o método que no está implementada en la plataforma actual.

- StopIteration: StopIteration es único en el ámbito de las excepciones de Ruby, ya que no se genera en tiempo de ejecución por algo que se vuelve loco, sino que el desarrollador lo plantea manualmente cuando hay una necesidad de detener una iteración activa.

- TypeError: un método recibe un argumento que no puede manejar.

- SystemExit: el método ` Kernel#exit ` fué invocado para finalizar el proceso actual.

___

### 2. ¿Cuál es la diferencia entre raise y throw? ¿Para qué usarías una u otra opción?

https://coderwall.com/p/lhkkug/don-t-confuse-ruby-s-throw-statement-with-raise

Throw and Catch: Se usa como un "go to". La idea es que la ejecución siga a partir de una determinada etiqueta. * No se usa para excepciones *.

Raise and rescue: maneja excepciones. La palabra clave ` raise ` por defecto levanta una excepción de la clase RuntimeError, como fué definido en el punto anterior. Para levantar cualquier otra excepción, será necesario indicar su nombre: ` rescue nombreExcepcion `.

Ruby emplea manejo de excepciones por terminación.

___

### 3. ¿Para qué sirven begin .. rescue .. else y ensure? Pensá al menos 2 casos concretos en que usarías estas sentencias en un script Ruby

#### Begin

Para manejar excepciones (handle exceptions), incluímos el código que pueda generar una excepción en un bloque begin-end y usamos una o más cláusulas rescue para indicarle a Ruby los tipos de excepción que queremos manejar. Es importante notar que el cuerpo de la definición de un método es un bloque begin-end explícito; begin es omitido y todo el cuerpo de la definición del método está sujeto al manejo de excepciones hasta que aparezca la palabra end.

#### Rescue

Para cada cláusula rescue en el bloque begin, Ruby compara la excepción generada con cada uno de los parámetros en turno. La ocurrencia tiene éxito si la excepción nombrada en la cláusula rescue es del mismo tipo que la excepción generada. El código en una cláusula else es ejecutado si el código en la expresiôn begin es ejecutado sin excepciones. Si una excepción ocurre, entonces la cláusula else no es ejecutada. El uso de una cláusula eles no es particularmente común en Ruby.

#### Else

Ejecuta su bloque si el nombre de la excepción levantada no coincide con ninguno definido detrás de ` rescue `.

#### Ensure

Si necesitas garantizar que algún proceso es ejecutado al final de un bloque de código sin importar si se generó una excepción o no, puedes usar la cláusula ensure. ensure va al final de la última cláusula rescue y contiene un bloque de código que siempre va a ser ejecutado.

##### Mas información y ejemplos en: https://es.wikibooks.org/wiki/Programaci%C3%B3n_en_Ruby/Manejo_de_excepciones

___

### 4. ¿Para qué sirve retry? ¿Cómo evitarías caer en un loop infinito al usarla?

Si ` retry ` aparece en la cláusula ` rescue ` de una expresión Begin, vuelve al comienzo del cuerpo del Begin.

~~~

begin
   do_something # exception raised
rescue
   # handles error
   retry  # restart from beginning
end

~~~

La idea de usar ` retry ` es de poder solucionar un error en tiempo de ejecución y volver a intentar ejecutar, de manera de que el programa siga su ejecución correctamente. Obviamente se esperará que el manejo de la excepción haya sido exitoso, de manera de no caer en un loop infinito.

___

### 5. ¿Cuáles son las diferencias entre los siguientes métodos?

~~~

def opcion_1
    a = [1, nil, 3, nil, 5, nil, 7, nil, 9, nil]
    b = 3
    c = a.map do | x|
        x * b
    end
    puts c.inspect
rescue
    0
end

~~~

La ejecución de opcion_1 retorna 0. Podemos afirmar que se produce una excepción del tipo NoMethodError cuando se ejecuta ` nil * 3 `.

~~~

def opcion_2
    c = begin
        a = [1, nil, 3, nil, 5, nil, 7, nil, 9, nil]
        b = 3
        a.map do | x|
            x * b
        end
        rescue
            0
        end
    puts c.inspect
end

~~~

La ejecución de opcion_2 retorna nil e imprime 0. La ejecución del bloque termina y se ejecuta ` rescue `. Por esto es que imprime 0 (la variable C queda con valor 0). 

~~~

def opcion_3
    a = [1, nil, 3, nil, 5, nil, 7, nil, 9, nil]
    b = 3
    c = a.map { | x| x * b } rescue 0
    puts c.inspect
end

~~~

Mismo caso que con la opcion_2.

~~~

def opcion_4
    a = [1, nil, 3, nil, 5, nil, 7, nil, 9, nil]
    b = 3
    c = a.map { | x| x * b rescue 0 }
    puts c.inspect
end

~~~

Como el rescue está dentro del bloque, se ejecutará para cada instrucción que levante una excepción, y la ejecución continuará sin problemas. La variable 0 queda con valor [ 3, 0, 9, 0, 15, 0, 21, 0, 27, 0 ].

___

### 8. Sea el siguiente código:

~~~

def fun3
    puts "Entrando a fun3"
    raise RuntimeError, "Excepción intencional"
    puts "Terminando fun3"
rescue NoMethodError => e
    puts "Tratando excepción por falta de método"
rescue RuntimeError => e
    puts "Tratando excepción provocada en tiempo de ejecución"
rescue
    puts "Tratando una excepción cualquiera"
ensure
    puts "Ejecutando ensure de fun3"
end

def fun2(x)
    puts "Entrando a fun2"
    fun3
    a = 5 / x
    puts "Terminando fun2"
end

def fun1(x)
    puts "Entrando a fun1"
    fun2 x
rescue
    puts "Manejador de excepciones de fun1"
    raise
ensure
    puts "Ejecutando ensure de fun1"
end

begin
    x = 0
    begin
        fun1 x
    rescue Exception => e
        puts "Manejador de excepciones de Main"
        if x == 0
            puts "Corrección de x"
            x = 1
            retry
        end
    end
    puts "Salida"
end

~~~ 

#### 1. Seguí el flujo de ejecución registrando la traza de impresiones que deja el programa y justificando paso a paso.

- Entrando a fun1

- Entrando a fun2

- Entrando a fun3

- Tratando excepción provocada en tiempo de ejecución: levanta intencionalmente esta excepción.

- Ejecutando ensure de fun3: ensure siempre se ejecuta.

- Manejador de excepciones de fun1: hay una división por 0 en fun2, lo que provoca el fin de su ejecución. La atrapa el rescue de fun1.

- Ejecutando ensure de fun1: ensure siempre se ejecuta.

- Manejador de excepciones de Main: la clausula rescue que se ejecuta en fun1 levanta una excepción con la palabra clave "raise" la cual levanta una excepción de tipo Exception.

- Corrección de x: x vale 0.

- Entrando a fun1: comienza la ejecución otra vez por el "retry".

- Entrando a fun2

- Entrando a fun3

- Tratando excepción provocada en tiempo de ejecución

- Ejecutando ensure de fun3

- Terminando fun2

- Ejecutando ensure de fun1

- Salida


#### 2. ¿Qué pasaría si se permuta, dentro de fun3, el manejador de excepciones para RuntimeError y el manejador de excepciones genérico (el que tiene el rescue vacío)?

#### 3. ¿La palabra reservada retry que función cumple? ¿Afectaría el funcionamiento del programa si se mueve la línea x = 0 dentro del segundo begin (inmediatamente antes de llamar a fun1 con x)?