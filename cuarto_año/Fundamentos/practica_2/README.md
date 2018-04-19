# Práctica 2 - Jerarquía de la Computabilidad.

## Ejercicio 1. Responder brevemente los siguientes incisos:

### 1. ¿En qué difiere un lenguaje recursivo de un lenguaje recursivamente numerable no recursivo?

### 2. ¿En qué difiere un lenguaje recursivamente numerable de uno que no lo es?

### 3. Probar que R ⊆ RE ⊆  L.

### 4. ¿Cuándo un lenguaje está en la clase CO-RE? ¿Puede un lenguaje estar al mismo tiempo en la clase RE y en la clase CO-RE? ¿Para todo lenguaje de la clase CO-RE existe una MT que lo acepta?

### 5. Justificar por qué los lenguajes Ʃ* y ∅ son recursivos.

### 6. Si L ⊆ Ʃ*, ¿se cumple que L ∈ R?

### 7. Justificar por qué un lenguaje finito es recursivo.

### 8. Justificar por qué si L1 ∈ CO-RE y L2 ∈ CO-RE, entonces (L1 ⋂ L2) ∈ CO-RE.


## Ejercicio 2. Considerando el Lema 2 estudiado en la Clase Teórica 2 (propiedad de clausura de la clase R con respecto a la operación de intersección):

### 1. Indicar cómo se implementaría copiar el input w en la cinta 2 de la MT M construida.

### 2. Indicar cómo se implementaría borrar el contenido de la cinta 2 de M.

### 3. Probar la correctitud de la construcción: 

- **(a) M para siempre**

- **(b) L(M) = L1 ⋂ L2**

### 4. Probar las otras propiedades de clausura de R mencionadas.

## Ejercicio 3. Considerando el Lema 3 estudiado en la Clase Teórica 2 (propiedad de clausura de la clase RE con respecto a la operación de unión):

### 1. Indicar cómo se implementaría la suma de 1 al contador i en la MT M construida.

### 2. Indicar cómo se implementaría ejecutar en M, i pasos de las MT M1 y M2.

### 3. Probar la correctitud de la construcción: L(M) = L1 ⋃ L2.

### 4. Probar las otras propiedades de clausura de RE mencionadas.

## Ejercicio 4. Considerando el Lema 4 estudiado en la Clase Teórica 2 (R = RE ⋂ CO-RE): 

### 1. Construir la MT M.
### 2. Probar la correctitud de la construcción.

## Ejercicio 5. Verificar la correctitud de las construcciones de las MT efectuadas en la Clase Práctica 2, es decir las correspondientes a las pruebas de:

### 1. La propiedad de clausura de R con respecto a la operación de concatenación.

### 2. La propiedad de clausura de RE con respecto a la operación de concatenación.

### 3. La propiedad de clausura de RE con respecto a la operación de unión utilizando una MTN. 

## Ejercicio 6. Sean L1 y L2 dos lenguajes recursivamente numerables de números naturales representados en notación unaria (por ejemplo, el número 5 se representa con 11111). Probar que también es recursivamente numerable el lenguaje L = {x | x es un número natural representado en notación unaria, y existen y, z, tales que y + z = x, con y ∈ L1, z ∈ L2}. Ayuda: la prueba es similar a la de la propiedad de clausura de la clase RE con respecto a la operación de concatenación.

## Ejercicio 7. Dada una MT M1 con Ʃ = {0, 1}:

### 1. Construir una MT M2 que determine si L(M1) tiene al menos una cadena.

### 2. ¿Se puede construir además una MT M3 para determinar si L(M1) tiene a lo sumo una cadena? Justificar.

#### Ayuda para la parte (1): Si L(M1) tiene al menos una cadena, entonces existe al menos una cadena w de unos y ceros, de tamaño n, tal que M1 a partir de w acepta en k pasos. Teniendo en cuenta esto, pensar cómo M2 podría simular M1 considerando todas las cadenas de unos y ceros hasta encontrar eventualmente una que M1 acepte (¡cuidándose de los casos en que M1 entre en loop!).
