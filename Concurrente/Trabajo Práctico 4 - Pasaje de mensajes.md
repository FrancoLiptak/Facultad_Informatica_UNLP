## Pasaje de mensajes asincrónicos (PMA)

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
chan devolverLapizNegro(int numLapiz) // Devuelvo por dos canales distintos por eficiencia.
chan devolverLapizColor(int numLapi) // Devuelvo por dos canales distintos por eficiencia.

Process Abuela{
    int cantLapicesNegros = 15
    int cantLapicesColores = 10
    int idNiño
    int idLapizNiñoA // Al niño A le doy un lápiz del tipo que mayor cantidad tenga.
    int idLapizDevuelto
    char tipoLapizEnviado

    while(true){
        if(!empty(pedidoNiñoTipoC) && cantLapicesColores > 0) ->    receive pedidoNiñoTipoC(idNiño)
                                                                    send enviarLapizC[idNiño] (cantLapicesColores)
                                                                    cantLapicesColores --

        □ (!empty(pedidoNiñoTipoN) && cantLapicesNegros > 0) ->     receive pedidoNiñoTipoN(idNiño)
                                                                    send enviarLapizN[idNiño] (cantLapicesNegros)
                                                                    cantLapicesNegros --

        □ (!empty(pedidoNiñoTipoA) && ( cantLapicesNegros > 0 )) -> idLapizNiñoA = cantLapicesNegros
                                                                    cantLapicesNegros --
                                                                    tipoLapizEnviado = 'N'
                                                                    receive pedidoNiñoTipoA(idNiño)
                                                                    send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)

        □ (!empty(pedidoNiñoTipoA) && (cantLapicesColores > 0)) ->  idLapizNiñoA = cantLapicesColores
                                                                    cantLapicesColores --
                                                                    tipoLapizEnviado = 'C'
                                                                    receive pedidoNiñoTipoA(idNiño)
                                                                    send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)

        □ (!empty(devolverLapizNegro)) ->                           receive devolverLapizNegro(idLapizDevuelto)
                                                                    cantLapicesNegros ++

        □ (!empty(devolverLapizcolor)) ->                           receive devolverLapizColor(idLapizDevuelto)
                                                                    cantLapicesColores ++

}

Process NiñoA[a:1..N]{ #Usa cualquier tipo de lápiz
    int numLapiz
    while(true){
        send pedidoNiñoTipoC(a)
        receive enviarLapizA[a](numLapiz, tipoLapiz)
        delay(10*60)
        if ( tipoLapiz = "N" ){
            send devolverLapizNegro(numLapiz)
        }else{
            send devolverLapizColor(numLapiz)
        }
    }
}

Process NiñoN[n:1..N]{ #Solo usa lápices negros
    int numLapiz
    while(true){
        send pedidoNiñoTipoN(n)
        receive enviarLapizN[n](numLapiz)
        delay(10*60)
        send devolverLapizNegro(numLapiz)
    }
}

Process NiñoC[c:1..N]{ #Solo usa lápices de colores
    int numLapiz
    char tipoLapiz
    while(true){
        send pedidoNiñoTipoC(c)
        receive enviarLapizC[c](numLapiz)
        delay(10*60)
        send devolverLapizColor(numLapiz)
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
chan devolverLapizNegro(int numLapiz) // Devuelvo por dos canales distintos por eficiencia.
chan devolverLapizColor(int numLapi) // Devuelvo por dos canales distintos por eficiencia.

Process Abuela{
    int cantLapicesNegros = 15
    int cantLapicesColores = 10
    int idNiño
    int idLapizNiñoA // Al niño A le doy un lápiz del tipo que mayor cantidad tenga.
    int idLapizDevuelto
    char tipoLapizEnviado

    while(true){
        if(!empty(pedidoNiñoTipoC) && cantLapicesColores > 0) ->    receive pedidoNiñoTipoC(idNiño)
                                                                    send enviarLapizC[idNiño] (cantLapicesColores)
                                                                    cantLapicesColores --

        □ (!empty(pedidoNiñoTipoN) && cantLapicesNegros > 0) ->     receive pedidoNiñoTipoN(idNiño)
                                                                    send enviarLapizN[idNiño] (cantLapicesNegros)
                                                                    cantLapicesNegros --

        □ (!empty(pedidoNiñoTipoA) && ( cantLapicesNegros > 0 && empty(pedidoNiñoTipoN))) ->    idLapizNiñoA = cantLapicesNegros
                                                                                                cantLapicesNegros --
                                                                                                tipoLapizEnviado = 'N'
                                                                                                receive pedidoNiñoTipoA(idNiño)
                                                                                                send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)

        □ (!empty(pedidoNiñoTipoA) && (cantLapicesColores > 0 && empty(pedidoNiñoTipoC))) ->    idLapizNiñoA = cantLapicesColores
                                                                                                cantLapicesColores --
                                                                                                tipoLapizEnviado = 'C'
                                                                                                receive pedidoNiñoTipoA(idNiño)
                                                                                                send enviarLapizN[idNiño] (idLapizNiñoA, tipoLapizEnviado)

        □ (!empty(devolverLapizNegro)) ->                           receive devolverLapizNegro(idLapizDevuelto)
                                                                    cantLapicesNegros ++

        □ (!empty(devolverLapizcolor)) ->                           receive devolverLapizColor(idLapizDevuelto)
                                                                    cantLapicesColores ++

}

Process NiñoA[a:1..N]{ #Usa cualquier tipo de lápiz
    int numLapiz
    while(true){
        send pedidoNiñoTipoC(a)
        receive enviarLapizA[a](numLapiz, tipoLapiz)
        delay(10*60)
        if ( tipoLapiz = "N" ){
            send devolverLapizNegro(numLapiz)
        }else{
            send devolverLapizColor(numLapiz)
        }
    }
}

Process NiñoN[n:1..N]{ #Solo usa lápices negros
    int numLapiz
    while(true){
        send pedidoNiñoTipoN(n)
        receive enviarLapizN[n](numLapiz)
        delay(10*60)
        send devolverLapizNegro(numLapiz)
    }
}

Process NiñoC[c:1..N]{ #Solo usa lápices de colores
    int numLapiz
    char tipoLapiz
    while(true){
        send pedidoNiñoTipoC(c)
        receive enviarLapizC[c](numLapiz)
        delay(10*60)
        send devolverLapizColor(numLapiz)
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
    int idCaja

    while(true){
        if(!empty(avisarFinalizacion)) ->   receive avisarFinalizacion(idCaja)
                                        colaCajas[idCaja]--
        □ (!empty(pedidoCaja) and empty(avisarFinalizacion)) ->  receive pedidoCaja(idPersona)
                                                                idCajaMenorCola = detectMin(colaCajas[])
                                                                colaCajas[idCajaMenorCola]++
                                                                send recibirCaja(idCajaMenorCola)
        end if

    }
}


Process Persona[p:1..P]{
    int idCajaMenorCola
    boolean atencion

    send pedidoCaja(p)
    receive recibirCaja(idCajaMenorCola)
    send pedidoAtencion[idCajaMenorCola](p)
    receive respuestaAtencion[idCajaMenorCola](atencion)
    send avisarFinalizacion(idCajaMenorCola)
}

Process Caja[c:1..5]{
    int idPersonaAAtender

    while(true){
        receive pedidoAtencion[c](idPersonaAAtender)
        resultadoAtencion = atenderPersona(idPersonaaAtender) // Asumo que me devuelve un boolean
        send respuestaAAtencion[c] (resultadoAtencion)
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

Process Administrador{
    cola colaPedidos
    int idCliente
    int idVendedor

    while(true){
        if(!empty(realizarPedido)) ->   receive realizarPedido(idCliente)
                                        encolar(colaPedidos, idCliente) // Encolo el cliente que realizó el pedido.
        □ (!empty(tomarPedido)) ->      receive tomarPedido(idVendedor)
                                        if(empty(colaPedidos)){
                                            send darPedido[idVendedor](null)
                                        }else{
                                            desencolar(colaPedidos, idCliente)
                                            send darPedido[idVendedor](idCliente)
                                        }
    }
}

Process Vendedor[v:1..3]{
    int idCliente

    while(true){

        send tomarPedido(v)
        receive (idCliente) // Obtengo el cliente al cual le tengo que cocinar.
        if ( idCliente = null ){
            minutos = random(1,3)
            delay(minutos)
        }else{
            send cocinarEnBaseAPedido(idCliente)
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

chan habilitarPista()
chan esperarHabilitacion[1..C]
chan avisarLlegada()

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
    int cantCorredores = 0
    while( cantCorredores < C ){
        receive avisarLlegada()
        cantCorredores ++
    }

    // Cuando sale es porque cantCorredores = C

    send habilitarPista()
}

~~~

#### b) Implementar sin usar un coordinador.

~~~

chan habilitarPista()
chan esperarHabilitacion[1..C]
chan avisarLlegada()

Process Portero{

    send avisarLlegada(0)
    receive sePuedeHabilitar()
    
    for(int i = 1; i <= C; i++){
        send esperarHabilitacion[i] 
    }

    
}

Process Corredores[c:1..C]{
    int cantCorredores

    receive avisarLlegada(cantCorredores)
    send avisarLlegada(cantCorredores + 1 )

    if ( cantCorredores + 1 = C ){
        send sePuedeHabilitar()
    }

    receive esperarHabilitacion[c]()

}

~~~
___

### 5. Suponga que N personas llegan a la cola de un banco. Una vez que la persona se agrega en la cola no espera más de 15 minutos para su atención, si pasado ese tiempo no fue atendida se retira. Para atender a las personas existen 2 empleados que van atendiendo de a una y por orden de llegada a las personas.

___

### 6. Existe una casa de comida rápida que es atendida por 1 empleado. Cuando una persona llega se pone en la cola y espera a lo sumo 10 minutos a que el empleado lo atienda. Pasado ese tiempo se retira sin realizar la compra.

#### a) Implementar una solución utilizando un proceso intermedio entre cada persona y el empleado.
#### b) Implementar una solución sin utilizar un proceso intermedio entre cada persona y el empleado.
___

## Pasaje de mensajes sincrónicos (PMS)

### 7. En una estación de comunicaciones se cuenta con 10 radares y una unidad de procesamiento que se encarga de procesar la información enviada por los radares. Cada radar repetidamente detecta señales de radio durante 15 segundos y le envía esos datos a la unidad de procesamiento para que los analice. Los radares no deben esperar a ser atendidos para continuar trabajando.

##### Nota: maximizar la concurrencia.

~~~

Process Radar[r:1..10]{
    string datos

    while(true){
        delay(15)
        datos = detectarSeñalDeRadio()
        Administrador!datosSeñalDeRadio(datos)
    }
}

Process Administrador{
    string datos
    
    while(true){
        if  Radar?datosSeñalDeRadio(datos) --> encolar(cola, datos)
            !empty(cola); UnidadProcesamiento ? damedatos()--> datosAProcesar!(desencolar(cola,datos))
        end if
}

Process UnidadProcesamiento{
    string datos

    while(true){
        Administrador!damedatos()
        Administrador?datosAProcesar(datos)
        procesar(datos)
    }
}

~~~

___

### 8. Supongamos que tenemos una abuela que tiene dos tipos de lápices para dibujar: 10 de colores y 15 negros. Además tenemos tres clases de niños que quieren dibujar con los lápices: los que quieren usar sólo los lápices de colores (tipo C), los que usan sólo los lápices negros (tipo N), y los niños que usan cualquier tipo de lápiz (tipo A).

##### Nota: se deben modelar los procesos niño y el proceso abuela.

#### a) Implemente un código para cada clase de niño de manera que ejecute pedido de lápiz, lo use por 10 minutos y luego lo devuelva y además el proceso abuela encargada de asignar los lápices.

~~~

Process Abuela{
	int idNiño, negro, color;
	string lapiz;
	negro:= 15;
	color:= 10;
	while (true){
		if negro > 0; NiñoN[*]?PedirLapizNegro(idNiño) →    negro:= negro - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘negro’);

		□ color > 0; NiñoC[*]?PedirLapizColor(idNiño) →     color:= color - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘color’);

		□ negro > 0; NiñoA[*]?PedirLapiz(idNiño) →          negro:= negro - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘negro’);

		□ color > 0; NiñoA[*]?PedirLapiz(idNiño) →          color:= color - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘color’);

		□ NiñoC[*]?DevolverLapizColor(lapiz) → color:= color + 1; 
		□ NiñoN[*]?DevolverLapizNegro(lapiz) → negro:= negro + 1; 
		□ NiñoA[*]?DevolverLapizColor(lapiz) → color:= color + 1; 
		□ NiñoA[*]?DevolverLapizNegro(lapiz) → negro:= negro + 1; 

		end if
}

}


Process NiñoA[i: 1..A]{
	string lapiz;
	while(true){
		Abuela!PedirLapiz(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		if (lapiz == “negro”){
			Abuela!DevolverLapizNegro(lapiz);
		else{
	        Abuela!DevolverLapizColor(lapiz);
        }
    }
}


Process NiñoC[i: 1..C]{
	string lapiz;
	while(true){
		Abuela!PedirLapizColor(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		Abuela!DevolverLapizColor(lapiz);
    }
}


Process NiñoN[n: 1..N]{
	string lapiz;
	while(true){
		Abuela!PedirLapizNegro(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		Abuela!DevolverLapizNegro(lapiz);
    }
}

~~~

#### b) Modificar el ejercicio para que a los niños de tipo A se les puede asignar un lápiz sólo cuando: hay lápiz negro disponible y ningún pedido pendiente de tipo N, o si hay lápiz de color disponible y ningún pedido pendiente de tipo C.

~~~
## CONSULTAR.

Process Coordinador{
	cola colaN, colaC, colaA;
	while(true){
	    if NiñoN[*]?PedirLapizNegro(idNiño) → encolar (colaN, idNiño);
        □ NiñoC[*]?PedirLapizColor(idNiño) → encolar (colaC, idNiño);
        □ NiñoA[*]?PedirLapiz(idNiño) → encolar (colaA, idNiño);
        □ (empty(ColaC) && empty(ColaN)); Abuela!atenderNiñoA( desencolar(ColaA) );
        □  (!empty(colaC)); Abuela!atenderNiñoC( desencolar(colaC) );
        □  (!empty(ColaN)); Abuela!atenderNiñoN( desencolar(ColaN) );
	}
}

Process Abuela{
	int idNiño, negro, color;
	string lapiz;
	negro:= 15;
	color:= 10;

	while (true){
		if negro > 0; Coordinador?atenderNiñoN(idNiño) →    negro:= negro - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘negro’);

		□ color > 0; Coordinador?atenderNiñoC(idNiño) →     color:= color - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘color’);

		□ negro > 0; Coordinador?atenderNiñoA(idNiño) →     negro:= negro - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘negro’);

		□ color > 0; Coordinador?atenderNiñoA(idNiño) →     color:= color - 1; 
                                                            NiñoN[idNiño]!RecibirLapiz(‘color’);

		□ NiñoC[*]?DevolverLapizColor(lapiz) →              color:= color + 1; 
		□ NiñoN[*]?DevolverLapizNegro(lapiz) →              negro:= negro + 1; 
		□ NiñoA[*]?DevolverLapizColor(lapiz) →              color:= color + 1; 
		□ NiñoA[*]?DevolverLapizNegro(lapiz) →              negro:= negro + 1; 

		end if
}

}

Process NiñoA[i: 1..A]{
	string lapiz;
	while(true){
		Coordinador!PedirLapiz(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		if (lapiz == “negro”){
			Abuela!DevolverLapizNegro(lapiz);
		else{
	        Abuela!DevolverLapizColor(lapiz);
        }
    }
}


Process NiñoC[i: 1..C]{
	string lapiz;
	while(true){
		Coordinador!PedirLapizColor(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		Abuela!DevolverLapizColor(lapiz);
    }
}


Process NiñoN[n: 1..N]{
	string lapiz;
	while(true){
		Coordinador!PedirLapizNegro(i);
		Abuela?RecibirLapiz(lapiz);
		delay(10);
		Abuela!DevolverLapizNegro(lapiz);
    }
}

~~~
___

### 9. Se debe modelar la atención en una panadería por parte de 3 empleados. Hay C clientes que ingresan al negocio para ser atendidos por cualquiera de los empleados, los cuales deben atenderse de acuerdo al orden de llegada.

##### Nota: maximizar la concurrencia.

Process Empleado[e:1..3]{
    int idCliente

    while(true){
        Panaderia!tomarPedido(e)
        Panaderia?enviarPedido(idCliente)
        "atenderCliente"
        Cliente[idCliente]!notificacionAtencion()
    }

}

Process Panaderia{
    int idCliente
    int idEmpleado
    cola colaClientes

    while(true){
        if Cliente[*]?llegaCliente(idCliente) --> encolar(colaClientes, idCliente);
            !empty(colaClientes); Empleado[*]?tomarPedido(idEmpleado) --> desencolar(colaClientes, idCliente)
                                                                          Empleado[idEmpleado]!enviarPedido(idCliente);
        end if
    }
}

Process Cliente[c:1..C]{
    Panaderia!llegaCliente(c)
    Empleado[*]?notificacionAtencion()
}

~~~

___

### 10.Existe una casa de comida rápida que es atendida por 1 empleado. Cuando una persona llega se pone en la cola y espera a lo sumo 10 minutos a que el empleado lo atienda. Pasado ese tiempo se retira sin realizar la compra.

#### a) Implementar una solución utilizando un proceso intermedio entre cada persona y el empleado.

~~~

Process Empleado{
    int idPersona
    string estadoPersona

    while(true){

        Cola!EmpleadoPidePersona()
        Cola?desencolarPersona(idPersona)
        estadoPersona[idPersona]!EmpleadoConsultaEstado()
        if estadoPersona[idPersona]?EmpleadoRecibeEstado(estadoPersona) --> if ( estadoPersona = "espera" ) 
                                                                        estadoPersona[idPersona]!EmpleadoSetteaEstado("atendido")
                                                                        atenderPersona(idPersona)
                                                                        Persona[idPersona]!esperarAtencion()
                                                                    end if
        end if
    }
}

Process Cola{
    cola ColaPersonas
    int idPersona

    while(true){
        if Persona?encolarPersona(idPersona) --> encolar(colaClientes, idPersona);
        □ (!empty(colaClientes)); Empleado?damePersona() --> Empleado!desencolarPersona( desencolar(colaClientes, idPersona) );
        end if
    }

}

Process estadoPersona[e:1..N]{
    string estado ='esperando'

    do Empleado?EmpleadoConsultaEstado() -->    EmpleadoRecibeEstado!(estado)
                                                EmpleadoSetteaEstado?(nuevoEstado)
                                                estado = nuevoEstado
                                                Persona[e]!resultadoEspera("atendido")

    □   Timer?TimerConsultaEstado() -->         TimerRecibeEstado!(estado)
                                                TimerSetteaEstado?(nuevoEstado)
                                                estado = nuevoEstado
                                                Persona[e]!resultadoEspera("irse")
    end do
}

Process Timer[t:1..N]{
    string estadoPersona
    Persona?iniciarTimer()
    delay(10*60)
  
    estadoPersona[idPersona]!TimerConsultaEstado(t)
    if estadoPersona[t]?TimerRecibeEstado(estadoPersona) --> if ( estadoPersona = "espera" ) 
                                                        estadoPersona[t]!TimerSetteaEstado("irse")
                                                        end if
    end if
}

Process Persona[p:1..N]{
    string estado

    Timer[p]!iniciarTimer()
    Cola!encolarPersona(p)
    Estado?resultadoEspera(estado)

    if (estado = "atendido"){
        Empleado?esperarAtencion()
    }

}

~~~

#### b) Implementar una solución sin utilizar un proceso intermedio entre cada persona y el empleado.

Este ejercicio puede realizarse, pero vale aclarar algo importante: El rol del proceso estadoPersona (en el ejercicio A), cumple la función de solucionar el problema de que uno de los dos procesos (Timer o Empleado), se queden colgados. Por ejemplo, si el Timer provoca que el Cliente se vaya, el Empleado se quedaría colgado. Si por el contrario, el Empleado atiende, provocaría que la persona se vaya, y el Timer quedaría colgado. Se podria hacer que esto no ocurra enviandole un mensaje a aquel proceso que llegue segundo, pero supondria “sincronizar” tanto el timer como al empleado, lo cual es ineficiente y va en contra a lo que dice el enunciado.s