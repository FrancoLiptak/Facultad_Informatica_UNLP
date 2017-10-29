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


### 2. ¿Cuál es la diferencia entre raise y throw? ¿Para qué usarías una u otra opción?

https://coderwall.com/p/lhkkug/don-t-confuse-ruby-s-throw-statement-with-raise

Throw and Catch: Se usa como un "go to". La idea es que la ejecución siga a partir de una determinada etiqueta. * No se usa para excepciones *.

Raise and rescue: maneja excepciones. La palabra clave ` raise ` por defecto levanta una excepción de la clase RuntimeError, como fué definido en el punto anterior. Para levantar cualquier otra excepción, será necesario indicar su nombre: ` rescue nombreExcepcion `.

Ruby emplea manejo de excepciones por terminación.

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