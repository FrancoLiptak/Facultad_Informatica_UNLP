## 1. Supongamos que tenemos una abuela que tiene dos tipos de lápices para dibujar: 10 de
colores y 15 negros. Además tenemos tres clases de niños que quieren dibujar con los
lápices: los que quieren usar sólo los lápices de colores (tipo C), los que usan sólo los
lápices negros (tipo N), y los niños que usan cualquier tipo de lápiz (tipo A).

### a) Implemente un código para cada clase de niño de manera que ejecute pedido de
lápiz, lo use por 10 minutos y luego lo devuelva y además el proceso abuela
encargada de asignar los lápices.

### b) Modificar el ejercicio para que a los niños de tipo A se les puede asignar un lápiz
sólo cuando: hay lápiz negro disponible y ningún pedido pendiente de tipo N, o si hay 
lápiz de color disponible y ningún pedido pendiente de tipo C. 
Nota: se deben modelar los procesos niño y el proceso abuela.

___

### 2. Se desea modelar el funcionamiento de un banco en el cual existen 5 cajas para realizar
pagos. Existen P personas que desean pagar. Para esto cada una selecciona la caja donde
hay menos personas esperando, una vez seleccionada espera a ser atendido. Nota:
maximizando la concurrencia, deben usarse los valores actualizados del tamaño de las
colas para seleccionar la caja con menos gente esperando.

___

### 3. Se debe modelar una casa de Comida Rápida, en el cual trabajan 2 cocineros y 3
vendedores. Además hay C clientes que dejan un pedido y quedan esperando a que se lo
alcancen.
Los pedidos que hacen los clientes son tomados por cualquiera de los vendedores y se lo
pasan a los cocineros para que realicen el plato. Cuando no hay pedidos para atender, los
vendedores aprovechan para reponer un pack de bebidas de la heladera (tardan entre 1 y
3 minutos para hacer esto).
Repetidamente cada cocinero toma un pedido pendiente dejado por los vendedores, lo
cocina y se lo entrega directamente al cliente correspondiente.
Nota: maximizar la concurrencia.

___

### 4. Se desea modelar una competencia de atletismo. Para eso existen dos tipos de procesos:
C corredores y un portero. Los corredores deben esperar que se habilite la entrada a la
pista, donde deben esperar que lleguen todos los corredores para comenzar. El portero es
el encargado de habilitar la entrada a la pista.
a) Implementar usando un coordinador.
b) Implementar sin usar un coordinador.
NOTAS: el proceso portero NO puede contabilizar nada, su única función es habilitar la
entrada a la pista; NO se puede suponer ningún orden en la llegada de los corredores al
punto de partida.

___

### 5. Suponga que N personas llegan a la cola de un banco. Una vez que la persona se agrega
en la cola no espera más de 15 minutos para su atención, si pasado ese tiempo no fue
atendida se retira. Para atender a las personas existen 2 empleados que van atendiendo
de a una y por orden de llegada a las personas.

___

### 6. Existe una casa de comida rápida que es atendida por 1 empleado. Cuando una persona
llega se pone en la cola y espera a lo sumo 10 minutos a que el empleado lo atienda.
Pasado ese tiempo se retira sin realizar la compra.
a) Implementar una solución utilizando un proceso intermedio entre cada persona y
el empleado.
b) Implementar una solución sin utilizar un proceso intermedio entre cada persona y
el empleado.