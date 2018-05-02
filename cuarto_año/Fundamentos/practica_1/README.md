# Práctica 1 - Máquinas de Turing (MT). 

## Ejercicio 1. Responder brevemente los siguientes incisos:

### 1. ¿Qué es un problema (computacional) de decisión? ¿Es el tipo de problema más general que se puede formular?

Un problema computacional de decisión es aquel donde se puede dar una respuesta por sí o por no ante una instancia. Las máquinas de Turing que resuelven este tipo de problemas son conocidas como *máquinas reconocedoras*. Se denomina así porque la resolución un problema consiste en reconocer el lenguaje que representa el problema, más precisamente, el lenguaje de las cadenas de símbolos que representan las instancias positivas del problema.

Un problema de decisión también se puede formalizar como el problema de decidir si una cierta frase pertenece a un conjunto dado de frases, también llamado lenguaje formal. El conjunto contiene exactamente las frases para las cuales la respuesta a la pregunta es positiva.

No es el tipo de problema más general que se puede formular. El tipo de problema más general es el de producir o calcular una solución.

### 2. ¿Qué cadenas forman el lenguaje aceptado por una MT?

Un lenguaje es un conjunto de cadenas de símbolos.

Considerando la visión de MT reconocedora de un lenguaje, si a partir de la entrada w la MT M se detiene en un estado q ∈ F, se dice que M acepta w. En cambio, cuando a partir de w la MT M se detiene en un estado q ∈ (Q – F) o no se detiene, se dice que M no acepta (o rechaza) w. El conjunto de las cadenas aceptadas por la MT M es el lenguaje aceptado o reconocido por M, y se denota con L(M). Considerando la visión de MT M calculadora, sólo cuando M se detiene en un estado q ∈ F debe tenerse en cuenta el contenido final de la cinta, es decir la cadena de salida (o simplemente la salida).

### 3. En la clase 1 se hace referencia al problema de satisfactibilidad de las fórmulas booleanas (se da como ejemplo la fórmula φ = (x1 v x2) ^ (x3 ^ x4) y la asignación A = (V, F, V, V)). Formular las tres formas del problema, teniendo en cuenta las tres visiones de MT consideradas: calculadora, aceptadora o reconocedora y generadora.

Necesitamos encontrar una asignación A de valores de entrada, tal que hace verdadera la fórmula booleana.

- Idea general: Tenemos que armar distintas combinaciones de valores para las variables de entrada. El objetivo será que alguna combinación provoque que toda la fórmula tenga "True" como resultado (lo cualsatisface la fórmula). Para este caso puntual, tenemos que cada una de las variables tiene dos valores posibles (Verdadero o Falso), y tenemos N variables, con N = 4. Por ende, tenemos 2^4 combinaciones posibles. (Generalizando, tenemos 2^N combinaciones posibles).

Considerando una MT reconocedora:
- La máquina tiene que recibir las cadenas. Dirá "si" para aquella combinación que provoque que la fórmula sea "True". "Falso" en caso contrario.

Considerando una MT calculadora:
- Tiene que recibir un input, y generará un resultado (uno solo). En este caso, ante la recepción de la fórmula booleana, la MT generaría la primera combinación de valores para la cual se satisface la expresión.

Considerando una MT generadora:
- Luego de recibir un input, va a buscar todos los valores para los cuales satisface la fórmula. Al encontrarlos, los devuelve todos juntos.

### 4. ¿Qué postula la Tesis de Church-Turing?

La tesis de Church-Turing formula hipotéticamente la equivalencia entre los conceptos de función computable y máquina de Turing, que expresado en lenguaje corriente vendría a ser "todo algoritmo es equivalente a una máquina de Turing"

### 5. ¿Cuándo dos MT son equivalentes? ¿Cuándo dos modelos de MT son equivalentes?

- Dos MT son equivalentes (computacionalmente) si reconocen el mismo lenguaje.
- Dos modelos de MT son equivalentes cuando para toda MT M1 de un modelo existe una MT M2 equivalente del otro, es decir que L(M1) = L(M2).

## Ejercicio 2. Dado el alfabeto Ʃ = {a, b, c}:

### 1. Obtener el lenguaje Ʃ* y el conjunto de partes del subconjunto de Ʃ* con cadenas de a lo sumo dos símbolos. ¿Cuál es el cardinal (o tamaño) de este último conjunto?

Ʃ es el alfabeto o conjunto de símbolos. En este caso Ʃ = {a, b, c}.

Ʃ*: Es el lenguaje o conjunto de cadenas de símbolos de Ʃ. Es infinita.

Llamo Ʃ*' al conjunto de partes del subconjunto de Ʃ* con cadenas de a lo sumo dos símbolos.

Ʃ*' = { λ, (a), (b), (c), (ab), (ac), (bc), (ba), (ca), (cb), (aa), (bb), (cc) }, donde "λ" corresponde a la cadena vacía. El tamaño de Ʃ*' es 13.

### 2. Dado el lenguaje L = {a^n b^n c^n | n ≥ 0}, obtener la intersección Ʃ* ⋂ L, la unión Ʃ* ⋃ L, el complemento de L respecto de Ʃ*, y la concatenación Ʃ . L.

Todo lenguaje que consideremos será un subconjunto de Ʃ* (ya que Ʃ* es la totalidad del alfabeto): L ⊆ Ʃ*. Expresado de otra manera, podemos afirmar que: L ∈ P(Ʃ*).

- Intersección Ʃ* ⋂ L = L. --> {abc, aabbcc, aaabbbccc, ... }
- Unión Ʃ* ⋃ L = Ʃ* --> {Ø, a, b, c, ab, ac, bc, abc, aabbcc, aaabbbccc, ... }
- Complemento de L respecto de Ʃ* = Todo elemento que forme parte de Ʃ* y no de L. (Ʃ* - L). --> {ba, cab, aaccbb, … } 
- Concatenación Ʃ . L = Trabaja de forma similar al producto cartesiano (cada elemento del alfabeto, con cada elemento de L). --> {a,b,c, aabc, babc, cabc, aaabbcc, baabbcc, caabbcc, ...} 

## Ejercicio 3. Construir una MT (puede tener varias cintas) que acepte de la manera más eficiente posible el lenguaje L = {a^n b^n c^n | n ≥ 0}. Plantear primero la idea general.

- Idea general: La máquina va a comenzar apuntando con el cabezal al extremo izquierdo de la cinta. El primer elemento debe ser una "a", el cual debe reemplazar con "x". Irá hacia la derecha en busca de un caracter "b". Al encontrarlo, lo reemplazará con "y". Luego irá nuevamente hacia la derecha, en busca de un caracter "c", al cual reemplazará con "z". En este momento, deberá volver hacia la izquierda, buscando una "x". Luego, irá hacia la derecha, buscando una 'a'. Si la encuentra, debe repetir el proceso. Si no encuentra una "a", entonces tampoco debe encontrar caracteres "b", ni caracteres "c", ya que tiene que haber la misma cantidad de "a", "b", y "c".

- Construcción de la máquina de Turing: M = (Q, Ʃ, Γ, δ2, q0, qA, qR), con:
* Q = { qa, qb, qH }
    qa: es el estado en el que se busca una 'a'.
    qb: es el estado en el que se busca una 'b'.
    qc: es el estado en el que se busca una 'c'.
    qH: es el estado en el que no hay más 'a', y por ende no debería haber mas letras.
    qL: es el estado en el que vuelvo a la izquiera después de haber encontrado una 'c', y busco una 'x'.
* Ʃ = {a, b, c}
* Γ = {a, b, c, x, y, z, B}
* q0 = qa
* La función de transición δ es:

|     | a        | b        | c        | x        | y        | z        | B        |
| --- | -------- | -------- | -------- | -------- | -------- | -------- | -------- |
| qa  | qb, x, R |          |          |          |          |          | qA, B, S |
| qb  | qb, a, R | qc, y, R |          |          | qH, y, R |          |          |
| qc  |          | qc, y, R | qL, z, L |          |          |          |          |
| qH  |          |          |          |          | qH, y, R | qH, z, R | qA, B, S |
| qL  | qL, a, L | qL, b, L |          | qa, x, R | qL, y, L | qL, z, L |          |

Las celdas en blanco representan los casos de rechazo (estado qR)

## Ejercicio 4. Completar la prueba iniciada en la clase 1, de que L(M) = {a^n b^n | n ≥ 1}, siendo M la MT construida para aceptar dicho lenguaje.

![Sin titulo](img/ejercicio_4.png)

- Idea General. Un posible algoritmo es:

```
                                            aaaaabbbbb
                                            αaaaabbbbb
                                            αaaaaβbbbb
                                            ααaaaβbbbb
                                            ααaaaββbbb

                                            ..........
                                            αααααβββββ
```

- Construcción de la MT M = (Q, Ʃ, Γ, δ, q0, qA, qR):

* Q = {qa, qb, qL, qH}
    qa: M busca una a. 
    qb: M busca una b. 
    qL: M vuelve. 
    qH: no hay más a.

- Ʃ = {a, b}
- Γ = {a, b, α, β, B}
- q0 = qa

- Prueba de L(M) = L.
• Si w ∈ L, entonces w tiene la forma a^n b^n, con n ≥ 1

Por cómo está definida la función de transición δ, claramente a partir de w la MT M acepta w, es decir que w ∈ L(M)

• Si w ∉ L, entonces M no tiene la forma a^n b^n, con n ≥ 1. Se cumple w ∉ L(M):

- Si w = λ, M rechaza porque no está definido en δ el par (qa, B)
- Si w tiene un símbolo distinto de a o de b, M rechaza porque dicho símbolo no está considerado en δ
- Si w empieza con b, M rechaza porque no está definido en δ el par (qa, b)

*Agregadas por mi:*
- Si w encuentra yendo hacia la derecha una 'a' luego de encontrar una 'b' rechaza, ya que la forma debe ser 'ab' (con igual cantidad de 'a' y 'b'). (No está definido el par (qH, a)).
- Si w encuentra un caracter 'a' para el cual no encuentra su correspondiente (en cuanto a cantidad) caracter 'b', rechaza, ya que tiene que haber igual cantidad de 'a' y 'b'. (No está definido el par (qb, B))
- Si w encuentra un caracter 'b' para el cual no encuentra su correspondiente (en cuanto a cantidad) caracter 'a', rechaza, ya que tiene que haber igual cantidad de 'a' y 'b'. (No está definido el par (qH, b))

## Ejercicio 5. En la clase 1 se construyó una MT con dos cintas para aceptar el lenguaje L = {w | w ∈ {a, b}* y w es un palíndromo o “capicúa”}. Construir ahora una MT con una cinta para aceptar el mismo lenguaje (se puede considerar la idea de solución propuesta en clase).

- Sea L = {w | w ∈ {a, b}* y w es un palíndromo o “capicúa”}
- w es un palíndromo o “capicúa” sii w = wR, siendo wR la cadena inversa de w
- Queremos construir una MT M que acepte L

- Idea general: Lo ideal sería que la máquina vaya recorriendo de derecha a izquierda. Comparamos el primer caracter con el último, y a medida que realizo la verificación, voy marcándolos con un B (blanco). Si el resultado final es una cadena en blanco, entonces la entrada era un palídromo.

* Q = { qab, qa, qb, qaB, qbB, qLB }
    - qab es el estado en el que se busca una 'a' o una 'b'.
    - qa es el estado en el que se busca que una 'a' sea el último caracter de la cadena. (La idea es que este estado esté presente en solo un movimiento, del blanco, a su inmediato anterior). Solo se mueve una vez a la izquierda en este estado.
    - qb es el estado en el que se busca que una 'b' sea el último caracter de la cadena. (La idea es que este estado esté presente en solo un movimiento, del blanco, a su inmediato anterior). Solo se mueve una vez a la izquierda en este estado.
    - qaB es el estado en el que se busca un blanco, luego de encontrar una 'a'.
    - qbB es el estado en el que se busca un blanco, luego de encontrar una 'b'.
    - qLB es el estado en el que se vuelve a la izquierda, hasta encontrar un blanco, para luego encontrar el próximo primer caracter

- Ʃ = {a, b }
- Γ = {a, b, B}
- q0 = qa

- La función de transición es:

|     | a         | b         | B         |
| --- | --------- | --------- | --------- |
| qab | qaB, B, R | qbB, B, R | qA, B, S  |
| qa  | qL, B, L  |           | qA, B, S  |
| qb  |           | qL, B, L  | qA, B, S  |
| qaB | qaB, a, R | qbB, b, R | qa, B, L  |
| qbB | qaB, a, R | qbB, b, R | qb, B, L  |
| qL  | qL, a, L  | qL, b, L  | qab, B, R |

## Ejercicio 6. En la clase 1 se construyó una MTN (MT no determinística) para aceptar las cadenas de la forma ha^n o hb^n, con n ≥ 0. Construir ahora una MTD (MT determinística) para lo mismo.

Es importante diferenciar entre una máquina de Turing determinística, de una no determinística:

*En una MT no determinística, en lugar de una función de transición δ, M tiene una relación de transición Δ, es decir que para un mismo par (q, a), la máquina puede responder de más de una manera, por ejemplo: Δ(q, a) = {(q1, a1, L), (q2, a2, R), (q3, a3, S)}*

• *La relación de transición se define así: Δ: Q x Γ  P((Q  {qA, qR}) x Γ x {L, R, S})*

• *Una MTN acepta si y sólo si al menos una computación acepta*

Resolución del ejercicio:

- Idea general: Tenemos que ver que el input comience con 'h'. Después, tiene que seguir con 'a' o 'b'. En caso de seguir con 'a', todos los caracteres deben ser 'a', hasta llegar a un blanco. Lo mismo en caso de que siga con una 'b' después de la 'h' inicial.

- Construcción de la MT M = (Q, Ʃ, Γ, δ, q0, qA, qR):

* Q = { qh, qa, qb, qab }
    qh es el estado inicial, en donde se busca una 'h'.
    qa es el estado en el que se encuentra una 'a', y se espera seguir encontrando 'a' o blanco (B).
    qb es el estado en el que se encuentra una 'b', y se espera seguir encontrando 'b' o blanco (B).
    qab es el estado en el que se encontró una 'h', y ahora se espera encontrar una 'a' o 'b'.
- Ʃ = {a, b, h}
- Γ = {a, b, h, B}
- q0 = qh

- Función de transición:

|     | h         | a        | b        | B        |
| --- | --------  | -------- | -------- | -------- |
| qh  | qab, x, R |          |          |          |
| qab |           | qa, a, R | qb, b, R | qA, B, S |
| qa  |           | qa, a, R |          | qA, B, S |
| qb  |           |          | qb, b, R | qA, B, S |

## Ejercicio 7. Construir una MT que calcule la resta de dos números (se puede considerar la idea de solución propuesta en la clase 1).

La resolución de este ejercicio la tomé del capítulo uno del libro.

Se va a construir una MT M que calcula la resta m – n, tal que m y n son dos números naturales representados en notación unaria. Cuando m ≤ n, M devuelve la cadena vacía λ. En la entrada, m y n aparecen separados por el dígito 0.

Idea general: Dado w = 1^m 0 1^n, con m ≥ 0 y n ≥ 0, la MT M itera de la siguiente manera. En cada paso elimina el primer 1 del minuendo, y correspondientemente reemplaza el primer 1 del sustraendo por un 0. Al final elimina todos los 0 (caso m > n), o bien elimina todos los dígitos (caso m ≤ n).

Construcción de la MT M.
La MT M = (Q, Ʃ, Γ, δ, q0, F) es tal que:
Q = {q0, q1, q2, q3, q4, q5, q6}.
El estado q0 es el estado de inicio de una iteración.
El estado q1 es el estado en que M busca el primer 0 yendo a la derecha. 
El estado q2 es el estado en que M encuentra el primer 0 yendo a la derecha. 
El estado q3 es el estado en que M encuentra un 1 después de un 0 yendo a la derecha. 
El estado q4 es el estado en que M yendo a la derecha buscando un 1 después de un 0 encuentra en cambio un blanco. 
El estado q5 es el estado en que M, iniciando una iteración, no encuentra como primer dígito un 1. 
El estado q6 es el estado final.

Ʃ = {1, 0}
Γ = {1, 0, B}
F = {q6}
La función de transición δ se define de la siguiente manera:
1. δ(q0, 1) = (q1, B, R) 
2. δ(q1, 1) = (q1, 1, R)
3. δ(q1, 0) = (q2, 0, R) 
4. δ(q2, 1) = (q3, 0, L)
5. δ(q2, 0) = (q2, 0, R) 
6. δ(q3, 0) = (q3, 0, L)
7. δ(q3, 1) = (q3, 1, L) 
8. δ(q3, B) = (q0, B, R)
9. δ(q2, B) = (q4, B, L) 
10. δ(q4, 0) = (q4, B, L)
11. δ(q4, 1) = (q4, 1, L) 
12. δ(q4, B) = (q6, 1, S)
13. δ(q0, 0) = (q5, B, R) 
14. δ(q5, 0) = (q5, B, R)
15. δ(q5, 1) = (q5, B, R) 
16. δ(q5, B) = (q6, B, S)

## Ejercicio 8. Construir una MT que genere todas las cadenas de la forma a^n b^n, con n ≥ 1 (se puede considerar la idea de solución propuesta en la clase 1).

Las MT que generan lenguajes tienen una cinta de salida de sólo escritura, en la que el cabezal se mueve únicamente hacia la derecha, y las cadenas se separan por el símbolo. Se asume que la entrada es la cadena vacía λ.

El lenguaje generado por una MT es el conjunto de cadenas que escribe en su cinta de salida. Las cadenas se pueden repetir, y no aparecen en ningún orden determinado.

La expresión G(M) denota el lenguaje generado por la MT M. 

- Idea general: Podemos plantear una MT M con dos cintas. Una cinta sirve para contar, y la otra para escribir los caracteres 'a' o 'b' según corresponda. Usamos notación unaria en la cinta que usamos para contar. Cuando leo B (blanco), pongo un valor unario "i" que represente la cantidad de apariciones de 'a' y 'b' en la cinta de escritura de caracteres. En la cinta de caracteres, a partir de B, escribo i veces 'a', e i veces 'b'. Luego imprimo un separador, e incremento el valor de la cinta contadora. Es infinito.

Pasado en limpio, los pasos serían:

1. i := 1
2. imprimir i veces a, imprimir i veces b, e imprimir separador
3. i := i + 1 y volver a (2)

## Ejercicio 9. Explicar (informal pero claramente) cómo simular una MT por otra que en un paso no pueda simultáneamente modificar un símbolo y moverse.

Se necesitaría un estado más para esa máquina. 

## Ejercicio 10. Explicar (informal pero claramente) cómo simular una MT por otra que no tenga el movimiento S (es decir el no movimiento).

Bajo esta situación, entra la necesidad de hacer movimientos innecesarios del cabezal. De nuevo serán necesarios estados adicionales. Como no existe el movimiento S siempre se hará un movimiento adicional para la derecha o para la izquierda. Estos nuevos estados deberán realizar un movimiento en sentido contrario, para volver a la posición necesaria.

## Ejercicio 11. Explicar (informal pero claramente) cómo simular una MT por otra que no tenga el movimiento L sino el movimiento JUMP, cuyo efecto es posicionar el cabezal en el símbolo de más a la izquierda.

Puedo simularla utilizando 2 cintas. En la primera escribo el input y en la segunda llevo un contador. A medida que me muevo por el input voy contando la cantidad de pasos (menos el primero) que hago de modo que al intentar realizar el movimiento L se realiza un JUMP y luego tantos R (movimiento a derecha) como lleve la cuenta de la segunda cinta sin sumar y sin cambiar ningún símbolo, pero restando 1 al contador.

Por ejemplo:

Cinta 1: B1234B
Cinta 2: 111

Hasta ese punto, el cabezal en cinta 1 apunta al 4, y cinta 2 tiene el valor 3 en notación unaria. Si tengo que ir a la izquierda, hago un JUMP.
Miro la información de Cinta 2. Como tiene el valor 3, me muevo 3 veces a la derecha (quedo parado en el 3). A Cinta 2 le resto 1, quedando:

Cinta 1: B1234B
Cinta 2: 11

El cabezal apunta al 3 en Cinta 1.

Esto solo sirve para casos en los que no voy a escribir nunca antes de la "posición 0". Si puedo escribir más a la izquierda de la posición 0, esta solución no sirve.

Lo que habría que hacer, sería tener dos cintas:

Cinta 1: B123B
Cinta 2: B

Si quiero escribir en Cinta 1 en el primer B que se vé (antes del 1), entonces debería "backupear" la Cinta 1 en la Cinta 2, y luego hacer un corrimiento en Cinta 1. Quedaría algo como:

Cinta 1: 023B
Cinta 2: 1

Luego, tengo que seguir "backupeando", y reescribiendo en Cinta 1. El resultado final sería:

Cinta 1: 0123B
Cinta 2: 123

## Ejercicio 12. Sea USAT el lenguaje de las fórmulas booleanas satisfactibles con exactamente una asignación de valores de verdad. P.ej. x1 ^ x2 pertenece a USAT, mientras que (x1 ^ x2) v x3 no. Indicar, justificando la respuesta, si la siguiente MTN acepta USAT:

### 1. Si la fórmula de entrada no es correcta sintácticamente, rechaza.
### 2. Genera no determinísticamente una asignación A, y si A no satisface la fórmula, rechaza.
### 3. Genera no determinísticamente una asignación A’ ≠ A. Si A’ no satisface la fórmula, acepta, y si A’ la satisface, rechaza.

#### Ayuda: Considerar p.ej. el caso en que la fórmula tiene dos asignaciones que la satisfacen.

El OR satisface con 3 asignaciones (VV, VF, FV). El enunciado pide que la fórmula se satisfaga con solo una asignacion (por ejemplo, AND). Por ende, si la máquina acepta el caso OR, quiere decir que la máquina no acepta USAT.

Esta máquina acepta el OR, y por ende, no acepta USAT.

### Duda: Preguntar por dibujo

El dibujo debería ser un árbol, en el que se genera por cada rama una combinación distinta de valores de verdad. A modo de resumen, el árbol sería:

              *
              |
_______________________________
|      |            |         |
A      A            A         A
|      |            |         |
FF     FV          VF         VV
|   __ | __      __ | _        |
qR  |   |  |     |  |  |       |
    VV-FF-VF    VV-FF-FV      qA
    qA qR qA    qA qR qA

Como se rechaza cuando todas las ramas rechazan, y va a haber más de una rama que acepte, entonces la máquina acepta el OR, y no acepta USAT.

