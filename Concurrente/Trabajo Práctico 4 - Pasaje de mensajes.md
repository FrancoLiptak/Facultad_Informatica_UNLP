## Pasaje de mensajes sincrónicos

### 1. Supongamos que tenemos una abuela que tiene dos tipos de lápices para dibujar: 10 de colores y 15 negros. Además tenemos tres clases de niños que quieren dibujar con los lápices: los que quieren usar sólo los lápices de colores (tipo C), los que usan sólo los lápices negros (tipo N), y los niños que usan cualquier tipo de lápiz (tipo A).

##### Nota: se deben modelar los procesos niño y el proceso abuela.

#### a) Implemente un código para cada clase de niño de manera que ejecute pedido de lápiz, lo use por 10 minutos y luego lo devuelva y además el proceso abuela encargada de asignar los lápices.

~~~

chan pedidoNiñoTipoC (int id) 
chan pedidoNiñoTipoN (int id)
chan pedidoNiñoTipoA (int id)
chan enviarLapizA [1..N] (int numLapiz)
chan enviarLapizN [1..N] (int numLapiz)
chan enviarLapizC [1..N] (int numLapiz, char tipo)
chan devolverLapiz(int numLapiz, char tipo)

Process Abuela{
    int cantLapicesNegros = 15
    int cantLapicesColores = 10
    int idNiño
    int idLapizNiñoA // Al niño A le doy un lápiz del tipo que mayor cantidad tenga.
    int idLapizDevuelto
    char tipoLapizEnviado
    char tipoLapizDevuelto

    while(true){
        if(!empty(pedidoNiñoTipoC) && cantLapicesColores > 0){
            receive pedidoNiñoTipoC(idNiño)
            send enviarLapizC[idNiño] (cantLapicesColores)
            cantLapicesColores --
        }

        if(!empty(pedidoNiñoTipoN) && cantLapicesNegros > 0){
            receive pedidoNiñoTipoN(idNiño)
            send enviarLapizN[idNiño] (cantLapicesNegros)
            cantLapicesNegros --
        }

        if(!empty(pedidoNiñoTipoA) && ( cantLapicesNegros > 0 OR cantLapicesColores > 0)){
            if( cantLapicesNegros > cantLapicesColores ){
                idLapizNiñoA = cantLapicesNegros
                cantLapicesNegros --
                tipoLapizEnviado = 'N'
            }else{
                idLapizNiñoA = cantLapicesColores
                cantLapicesColores --
                tipoLapizEnviado = 'C'
            }
            receive pedidoNiñoTipoA(idNiño)
            send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)
        }

        if(!empty(devolverLapiz)){
            receive(idLapizDevuelto, tipoLapizDevuelto)
            if(tipoLapizDevuelto = 'N'){
                cantLapicesNegros ++
            }else{
                cantLapicesColores ++
            }
        }
    }

}

Process NiñoA[a:1..N]{ #Usa cualquier tipo de lápiz
    int numLapiz
    while(true){
        send pedidoNiñoTipoC(a)
        receive enviarLapizA[a](numLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, 'A')
    }
}

Process NiñoN[n:1..N]{ #Solo usa lápices negros
    int numLapiz
    while(true){
        send pedidoNiñoTipoN(n)
        receive enviarLapizN[n](numLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, 'N')
    }
}

Process NiñoC[c:1..N]{ #Solo usa lápices de colores
    int numLapiz
    char tipoLapiz
    while(true){
        send pedidoNiñoTipoC(c)
        receive enviarLapizC[c](numLapiz, tipoLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, tipoLapiz)
    }
}

~~~


#### b) Modificar el ejercicio para que a los niños de tipo A se les puede asignar un lápiz sólo cuando: hay lápiz negro disponible y ningún pedido pendiente de tipo N, o si hay lápiz de color disponible y ningún pedido pendiente de tipo C. 

~~~

chan pedidoNiñoTipoC (int id) 
chan pedidoNiñoTipoN (int id)
chan pedidoNiñoTipoA (int id)
chan enviarLapizA [1..N] (int numLapiz)
chan enviarLapizN [1..N] (int numLapiz)
chan enviarLapizC [1..N] (int numLapiz, char tipo)
chan devolverLapiz(int numLapiz, char tipo)

Process Abuela{
    int cantLapicesNegros = 15
    int cantLapicesColores = 10
    int idNiño
    int idLapizNiñoA // Al niño A le doy un lápiz del tipo que mayor cantidad tenga.
    int idLapizDevuelto
    char tipoLapizEnviado
    char tipoLapizDevuelto

    while(true){
        if(!empty(pedidoNiñoTipoC) && cantLapicesColores > 0){
            receive pedidoNiñoTipoC(idNiño)
            send enviarLapizC[idNiño] (cantLapicesColores)
            cantLapicesColores --
        }

        if(!empty(pedidoNiñoTipoN) && cantLapicesNegros > 0){
            receive pedidoNiñoTipoN(idNiño)
            send enviarLapizN[idNiño] (cantLapicesNegros)
            cantLapicesNegros --
        }

        if(!empty(pedidoNiñoTipoA) && ( cantLapicesNegros > 0 && empty(pedidoNiñoTipoN) ) OR ( cantLapicesColores > 0) && empty(pedidoNiñoTipoC)){
            if( empty(pedidoNiñoTipoA) && ( cantLapicesColores > cantLapicesNegros ) ){ 
                # Primero intento dar lápiz de color, ya que la mayoría de los lapices son de colores.
                idLapizNiñoA = cantLapicesColores
                cantLapicesColores --
                tipoLapizEnviado = 'C'
            }else{
                if ( empty(pedidoNiñoTipoN) ){
                    idLapizNiñoA = cantLapicesNegros
                    cantLapicesNegros --
                    tipoLapizEnviado = 'N'
                }
            }
            receive pedidoNiñoTipoA(idNiño)
            send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)
        }

        if(!empty(devolverLapiz)){
            receive(idLapizDevuelto, tipoLapizDevuelto)
            if(tipoLapizDevuelto = 'N'){
                cantLapicesNegros ++
            }else{
                cantLapicesColores ++
            }
        }
    }

}

Process NiñoA[a:1..N]{ #Usa cualquier tipo de lápiz
    int numLapiz
    while(true){
        send pedidoNiñoTipoC(a)
        receive enviarLapizA[a](numLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, 'A')
    }
}

Process NiñoN[n:1..N]{ #Solo usa lápices negros
    int numLapiz
    while(true){
        send pedidoNiñoTipoN(n)
        receive enviarLapizN[n](numLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, 'N')
    }
}

Process NiñoC[c:1..N]{ #Solo usa lápices de colores
    int numLapiz
    char tipoLapiz
    while(true){
        send pedidoNiñoTipoC(c)
        receive enviarLapizC[c](numLapiz, tipoLapiz)
        delay(10*60)
        send devolverLapiz(numLapiz, tipoLapiz)
    }
}

~~~

___

### 2. Se desea modelar el funcionamiento de un banco en el cual existen 5 cajas para realizar pagos. Existen P personas que desean pagar. Para esto cada una selecciona la caja donde hay menos personas esperando, una vez seleccionada espera a ser atendido. 

#### Nota: maximizando la concurrencia, deben usarse los valores actualizados del tamaño de las colas para seleccionar la caja con menos gente esperando.

~~~
chan pedidoCaja(int idPersona)
chan recibirCaja(int idCaja)
chan pedidoAtencion[1..5](int idPersona)
chan respuestaAtencion[1..5](boolean atencion) // Consultar la variable atencion

Process Banco{
    array of int colaCajas[1..5]
    int idPersona
    int idCajaMenorCola

    while(true){
        if(!empty(pedidoCaja)){ // ¿Este if está al pedo?
            receive pedidoCaja(idPersona)
            idCajaMenorCola = detectMin(colaCajas[]) // Asumo que me devuelve la posición con menor valor.
            colaCajas[idCajaMenorCola]++
            send recibirCaja(idCajaMenorCola)
        }
    }
}


Process Persona[p:1..P]{
    int idCajaMenorCola
    boolean atencion

    send pedidoCaja(p)
    receive recibirCaja(idCajaMenorCola)
    send pedidoAtencion[idCajaMenorCola](p)
    receive respuestaAtencion[idCajaMenorCola](atencion)
}

Process Caja[c:1..5]{
    int idPersonaAAtender

    while(true){
        if(!empty(pedidoAtencion[c])){ // ¿Este if está al pedo?
            receive pedidoAtencion[c](idPersonaAAtender)
            resultadoAtencion = atenderPersona(idPersonaaAtender) //Asumo que me devuelve un boolean
            send respuestaAAtencion[c] (resultadoAtencion)
        }
    }
}

~~~

___

### 3. Se debe modelar una casa de Comida Rápida, en el cual trabajan 2 cocineros y 3 vendedores. Además hay C clientes que dejan un pedido y quedan esperando a que se lo alcancen. 

### Los pedidos que hacen los clientes son tomados por cualquiera de los vendedores y se lo pasan a los cocineros para que realicen el plato. Cuando no hay pedidos para atender, los vendedores aprovechan para reponer un pack de bebidas de la heladera (tardan entre 1 y 3 minutos para hacer esto).

### Repetidamente cada cocinero toma un pedido pendiente dejado por los vendedores, lo cocina y se lo entrega directamente al cliente correspondiente.

##### Nota: maximizar la concurrencia.

~~~

chan realizarPedido(int idCliente)
chan cocinarEnBaseAPedido(int idCliente)
chan entregarPlato[1..C](string plato) // Representa al plato con una descripción o nombre. ¿Puedo usar canal sin variables?

Process Cocinero[c:1..2]{
    int idCliente

    while(true){
        receive cocinarEnBaseAPedido(idCliente)
        plato = cocinar() // Asumo que me devuelve el plato
        send entregarPlato[idCliente](plato)
    }
}

Process Vendedor[v:1..3]{
    int idCliente

    while(true){
        if(!empty(realizarPedido)){
            receive realizarPedido(idCliente)
            send cocinarEnBaseAPedido(idCliente)
        }else{
            minutos = random(1,3)
            delay(minutos) // Esto simboliza la parte de la heladera. Creo que está mal porque la heladera deberia modelarse.
        }
    }

}

Process Clientes[c:1..C]{
    send realizarPedido(c)
    receive entregarPlato[c](plato)
    comer(plato)
}

~~~
___

### 4. Se desea modelar una competencia de atletismo. Para eso existen dos tipos de procesos: C corredores y un portero. Los corredores deben esperar que se habilite la entrada a la pista, donde deben esperar que lleguen todos los corredores para comenzar. El portero es el encargado de habilitar la entrada a la pista.

##### NOTAS: el proceso portero NO puede contabilizar nada, su única función es habilitar la entrada a la pista; NO se puede suponer ningún orden en la llegada de los corredores al punto de partida.

#### a) Implementar usando un coordinador.

~~~

Process Portero{

    receive habilitarPista() // se puede no poner variables?
    for(int i = 1; i <= C; i++){
        send esperarHabilitacion[i] 
    }

    
}

Process Corredores[c:1..C]{
    send avisarLlegada()
    receive esperarHabilitacion[c]()

}

Process Coordinador{
    int cantCorredores
    while( cantCorredores < C ){
        receive avisarLlegada(idCorredor)
        cantCorredores ++
    }

    // Cuando sale es porque cantCorredores = C

    send habilitarPista()
}

~~~

#### b) Implementar sin usar un coordinador.

___

### 5. Suponga que N personas llegan a la cola de un banco. Una vez que la persona se agrega en la cola no espera más de 15 minutos para su atención, si pasado ese tiempo no fue atendida se retira. Para atender a las personas existen 2 empleados que van atendiendo de a una y por orden de llegada a las personas.

___

### 6. Existe una casa de comida rápida que es atendida por 1 empleado. Cuando una persona llega se pone en la cola y espera a lo sumo 10 minutos a que el empleado lo atienda. Pasado ese tiempo se retira sin realizar la compra.

#### a) Implementar una solución utilizando un proceso intermedio entre cada persona y el empleado.
#### b) Implementar una solución sin utilizar un proceso intermedio entre cada persona y el empleado.