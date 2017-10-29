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

Throw and Catch: Se usa como un "go to". La idea es que la ejecución siga a partir de una determinada etiqueta. No se usa para excepciones.

Raise and rescue: sí maneja excepciones. La palabra clave ` raise ` por defecto levanta una excepción de la clase RuntimeError, como fué definido en el punto anterior. Para levantar cualquier otra excepción, será necesario indicar su nombre: ` rescue nombreExcepcion `.

Ruby emplea manejo de excepciones por terminación.