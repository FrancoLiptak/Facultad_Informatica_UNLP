# Máquinas de Turing (MT).

## Ejercicio 1. Responder brevemente los siguientes incisos:
### - 1. ¿Qué es un problema (computacional) de decisión? ¿Es el tipo de problema más general que se puede formular?
### - 2. ¿Qué cadenas forman el lenguaje aceptado por una MT?
### - 3. En la clase 1 se hace referencia al problema de satisfactibilidad de las fórmulas booleanas (se da como ejemplo la fórmula φ = (x1 v x2) ^ (x3 ^ x4) y la asignación A = (V, F, V, V)). Formular las tres formas del problema, teniendo en cuenta las tres visiones de MT consideradas: calculadora, aceptadora o reconocedora, y generadora.
### - 4. ¿Qué postula la Tesis de Church-Turing?
### - 5. ¿Cuándo dos MT son equivalentes? ¿Cuándo dos modelos de MT son equivalentes?

## Ejercicio 2. Dado el alfabeto Ʃ = {a, b, c}:
### - 1. Obtener el lenguaje Ʃ* y el conjunto de partes del subconjunto de Ʃ* con cadenas de a lo sumo dos símbolos. ¿Cuál es el cardinal (o tamaño) de este último conjunto?
### - 2. Dado el lenguaje L = {a^n b^n c^n | n ≥ 0}, obtener la intersección Ʃ* ⋂ L, la unión Ʃ* ⋃ L, el complemento de L respecto de Ʃ*, y la concatenación Ʃ . L.

## Ejercicio 3. Construir una MT (puede tener varias cintas) que acepte de la manera más eficiente posible el lenguaje L = {a^n b^n c^n | n ≥ 0}. Plantear primero la idea general.

## Ejercicio 4. Completar la prueba iniciada en la clase 1, de que L(M) = {a^n b^n | n ≥ 1}, siendo M la MT construida para aceptar dicho lenguaje.

## Ejercicio 5. En la clase 1 se construyó una MT con dos cintas para aceptar el lenguaje L = {w | w ∈ {a, b}* y w es un palíndromo o “capicúa”}. Construir ahora una MT con una cinta para aceptar el mismo lenguaje (se puede considerar la idea de solución propuesta en clase).

## Ejercicio 6. En la clase 1 se construyó una MTN (MT no determinística) para aceptar las cadenas de la forma ha^n o hb^n, con n ≥ 0. Construir ahora una MTD (MT determinística) para lo mismo.

## Ejercicio 7. Construir una MT que calcule la resta de dos números (se puede considerar la idea de solución propuesta en la clase 1).

## Ejercicio 8. Construir una MT que genere todas las cadenas de la forma a^n b^n, con n ≥ 1 (se puede considerar la idea de solución propuesta en la clase 1).

## Ejercicio 9. Explicar (informal pero claramente) cómo simular una MT por otra que en un paso no pueda simultáneamente modificar un símbolo y moverse.

## Ejercicio 10. Explicar (informal pero claramente) cómo simular una MT por otra que no tenga el movimiento S (es decir el no movimiento).

## Ejercicio 11. Explicar (informal pero claramente) cómo simular una MT por otra que no tenga el movimiento L sino el movimiento JUMP, cuyo efecto es posicionar el cabezal en el símbolo de más a la izquierda.

## Ejercicio 12. Sea USAT el lenguaje de las fórmulas booleanas satisfactibles con exactamente una asignación de valores de verdad. P.ej. x1 ^ x2 pertenece a USAT, mientras que (x1 ^ x2) v x3 no. Indicar, justificando la respuesta, si la siguiente MTN acepta USAT:

### - 1. Si la fórmula de entrada no es correcta sintácticamente, rechaza.
### - 2. Genera no determinísticamente una asignación A, y si A no satisface la fórmula, rechaza.
### - 3. Genera no determinísticamente una asignación A’ ≠ A. Si A’ no satisface la fórmula, acepta, y si A’ la satisface, rechaza.

#### Ayuda: Considerar p.ej. el caso en que la fórmula tiene dos asignaciones que la satisfacen.