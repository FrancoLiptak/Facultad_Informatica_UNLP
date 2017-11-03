## Trabajo Práctico 5 - ADA.

### 1. Se requiere modelar un puente de un solo sentido, el puente solo soporta el peso de 3 autos, 2 camionetas o un camión.

~~~

Procedure puente_unidireccional is

    TASK TYPE Auto;
    TASK TYPE Camioneta;
    TASK TYPE Camion;

    TASK Puente IS
    ENTRY auto_pide_puente;
    ENTRY auto_sale;
    ENTRY camioneta_pide_puente;
    ENTRY camioneta_sale;
    ENTRY camion_pide_puente;
    ENTRY camion_sale;
    END Puente;

    TASK BODY Puente IS

    int cantAutos = 0
    int cantCamionetas = 0 
    int cantCamiones = 0

    BEGIN
        LOOP
            SELECT
                WHEN ( cantAutos = 0 && cantCamionetas = 0 && cantCamiones = 0) =>  ACCEPT camion_pide_puente IS
                                                                                    cantCamiones ++;
                                                                                    END camion_pide_puente
            OR
                WHEN ( cantAutos < 3 && cantCamionetas = 0 && cantCamiones = 0) =>  ACCEPT auto_pide_puente IS
                                                                                    cantAutos ++;
                                                                                    END auto_pide_puente
            OR
                WHEN ( cantAutos = 0 && cantCamionetas < 2 && cantCamiones = 0) =>  ACCEPT camioneta_pide_puente IS
                                                                                    cantCamionetas ++;
                                                                                    END camioneta_pide_puente
            OR
                ACCEPT auto_sale;
                cantAutos --;
            OR 
                ACCEPT camioneta_sale;
                cantCamionetas --;
            OR
                ACCEPT camion_sale;
                cantCamiones --;
            END SELECT;
        END LOOP;
    END Puente;

    TASK  BODY Auto IS
        BEGIN
            Puente.auto_pide_puente;
            pasar()
            Puente.auto_sale;
        END
    END Auto;

    TASK  BODY Camion IS
        BEGIN
            Puente.camion_pide_puente;
            pasar()
            Puente.camion_sale;
        END
    END Camion;

    TASK  BODY Camioneta IS
        BEGIN
            Puente.camioneta_pide_puente; 
            # llamo de esta manera a la accion, y no "pedirPuente" generico, ya que me importa la cantidad de camionetas (y de otros vehículos) que haya.
            pasar()
            Puente.camioneta_sale;
        END
    END Camioneta;

End puente_unidireccional


~~~

___

### 2. Se quiere modelar la cola de un banco que atiende un solo empleado, los clientes llegan y si esperan más de 10 minutos se retiran.

~~~

Procedure Banco IS

TASK TYPE Cliente;

TASK Empleado IS
    ENTRY cliente_solicita_atencion;
END Empleado

TASK BODY Empleado IS
BEGIN
    LOOP
        ACCEPT cliente_solicita_atencion do
            atender();
        END cliente_solicita_atencion;
    END LOOP
END Empleado

TASK BODY Cliente IS
BEGIN
    SELECT
        Empleado.cliente_solicita_atencion;
    OR DELAY 10*60
    END SELECT
    irse()
END Cliente;

End Banco

~~~

___

### 3. Se debe modelar un buscador para contar la cantidad de veces que aparece un número dentro de un vector distribuido entre las N tareas contador. Además existe un administrador que decide el número que se desea buscar y se lo envía a los N contadores para que lo busquen en la parte del vector que poseen.

~~~
# CHEQUEAR.

Procedure buscador IS

TASK Contador IS;
    Entry numeroABuscar(numero: IN Integer)
End Contador;

TASK Administrador IS;
    Entry cantidadEncontrada(cantidad: IN Integer)
End Administrador;

type Contadores is array (1 .. N) of Contador;

TASK BODY Contador IS
    mi_parte is array 1..C of Integer;
    coincidencias: Integer;
    coincidencias := 0;
    
    BEGIN
        ACCEPT numeroABuscar(num) DO
            numeroABuscar:= num;
        END numeroABuscar;

        for I in 1..C LOOP
            if (mi_parte(i) = numeroABuscar) then
                coincidencias:= coincidencias + 1;
            end if
        END LOOP
        Administrador.cantidadEncontrada(coincidencias);
    END Contador

TASK BODY Administrador IS;
    numeroABuscar: Integer;

    BEGIN
        numeroABuscar:= random();
        for I in 1..N LOOP
            Contadores(I).numeroABuscar(numeroABuscar)
        END LOOP

        for I in 1..N LOOP
            ACCEPT cantidadEncontrada(cantidad) DO
                total := total + cantidad;
            END cantidadEncontrada;
        END LOOP
    END Administrador; 

END buscador;


~~~

___

### 4. Se debe controlar el acceso a una base de datos. Existen A procesos de Tipo 1, B procesos de Tipo 2 y C procesos de Tipo 3 que trabajan indefinidamente de la siguiente manera:

#### • Proceso Tipo 1: intenta escribir, si no lo logro en 2 minutos, espera 5 minutos y vuelve a intentarlo.
#### • Proceso Tipo 2: intenta escribir, si no lo logra en 5 minutos, intenta leer, si no lo logra en 5 minutos vuelve a comenzar.
#### • Proceso Tipo 3: intenta leer, si no puede inmediatamente entonces espera hasta poder escribir.

### Un proceso que quiera escribir podrá acceder si no hay ningún otro proceso en la base de datos, al acceder escribe y avisa que termino de escribir. Un proceso que quiera leer podrá acceder si no hay procesos que escriban, al acceder lee y avisa que termino de leer. Siempre se le debe dar prioridad al pedido de acceso para escribir sobre el pedido de acceso para leer.

~~~

#CHEQUEAR EL PROCESO BASE.

Procedure base_de_datos IS

TASK TYPE Tipo1;
TASK TYPE Tipo2;
TASK TYPE Tipo3;

TASK Base IS
    Entry pedido_escritura;
    Entry finalizar_escritura;
    Entry pedido_lectura;
    Entry finalizar_lectura;
END Base;

TASK BODY Base IS
    cantProcesos: Integer;
    cantLeyendo: Integer;
    BEGIN
        LOOP
            SELECT
                WHEN ( pedido_escritura'COUNT = 0 ) =>      ACCEPT pedido_lectura; #    De esta manera manejo la prioridad.
                                                            cantLeyendo:= cantLeyendo + 1;
            OR
                ACCEPT finalizar_lectura;
                cantLetendo:= cantLeyendo - 1;
            OR
                WHEN ( cantLeyendo = 0) =>  ACCEPT pedido_escritura;
                                            ACCEPT finalizar_escritura;
            END SELECT
        END LOOP
    END Base;
            

TASK BODY Tipo1 IS
    BEGIN
        LOOP
            SELECT
                Base.pedido_escritura;
                escribir();
                Base.finalizar_escritura;
            OR DELAY 2*60;
                DELAY 5*60;
            END SELECT
        END LOOP
    END Tipo1

TASK BODY Tipo2 IS
    BEGIN
        LOOP
            SELECT
                Base.pedido_escritura;
                escribir();
                Base.finalizar_escritura;
            OR DELAY 5*60;
                SELECT
                    Base.pedido_lectura;
                    leer();
                    Base.finalizar_lectura;
                OR DELAY 5*60;
                END SELECT
            END SELECT
        END LOOP
    END Tipo2;

TASK BODY Tipo3 IS
    BEGIN
        LOOP
            SELECT
                Base.pedido_lectura;
                leer();
                Base.finalizar_lectura;
            ELSE
                Base.pedido_escritura;
                escribir();
                Base.finalizar_escritura;
            END SELECT
        END LOOP
    END Tipo3;



~~~

___

### 5. Se dispone de un sistema compuesto por 1 central y 2 procesos. Los procesos envían señales a la central. La central comienza su ejecución tomando una señal del proceso 1, luego toma aleatoriamente señales de cualquiera de los dos indefinidamente. Al recibir una señal de proceso 2, recibe señales del mismo proceso durante 3 minutos. 

### El proceso 1 envía una señal que es considerada vieja (se deshecha) si en 2 minutos no fue recibida. 

### El proceso 2 envía una señal, si no es recibida en ese instante espera 1 minuto y vuelve a mandarla (no se deshecha).

~~~

Procedure Señales IS

TASK Central IS
    Entry enviaseñal1(señal1: IN señal)
    Entry enviaseñal2(señal2: IN señal)
    Entry seTerminoTiempo()
END Central;

TASK TYPE Proceso1;
TASK TYPE Proceso2;
TASK TYPE Timer;

TASK BODY Central IS
    BEGIN
        ACCEPT enviaseñal1(señal1);

        LOOP
            SELECT
                ACCEPT enviaseñal1;
            OR
                ACCEPT enviaseñal2(señal2);

                Timer.iniciarTimer();
                
                WHILE ( !finTiempo ) LOOP

                    SELECT  
                        when ( COUNT' seTerminoTiempo() = 0 ) => ACCEPT enviaseñal2(señal2);
                    OR
                        ACCEPT seTerminoTiempo();
                        finTiempo = true;
                    END SELECT
                END WHILE
        END LOOP
    END Central

TASK BODY Timer IS;
    BEGIN
        ACCEPT iniciarTimer()
        delay(3*60)
        Central.seTerminoTiempo();
    END Timer;             


TASK BODY Proceso1 IS;
    BEGIN
        LOOP
            señal := generarSeñal()
            SELECT
                Centrar.enviaseñal1(señal)
            OR DELAY 2*60
            END SELECT
        END LOOP
    END Proceso1;


TASK BODY Proceso2 IS;
    BEGIN
        LOOP
            señal:= generarSeñal()
            while ( !envioExitoso ) LOOP
                SELECT
                    Centrar.enviaseñal2(señal)
                    envioExitoso := true
                ELSE
                    DELAY 60
                END SELECT
            END while
        END LOOP
    END Proceso2;

~~~

___

### 6. Suponga que existen N usuarios que deben ejecutar su programa, para esto comparten K procesadores.

### Los usuarios solicitan un procesador al administrador. Una vez que el administrador les entregó el número de procesador, el usuario le da su programa al procesador que le fue asignado. Luego el usuario espera a que:

#### • El procesador le avise si hubo algún error en una línea de código con lo cual el usuario arregla el programa y se lo vuelve a entregar al procesador, es decir queda nuevamente en la cola de programas a ejecutar por su procesador. El usuario no termina hasta que el procesador haya ejecutado su programa correctamente (sin errores).

#### El procesador le avise que su programa termino, con lo cual termina su ejecución.

### El administrador tomará los pedidos de procesador hechos por los usuarios y balanceara la carga de programas que tiene cada procesador, de esta forma le entregará al usuario un número de procesador.

### El procesador ejecutará un Round-Robin de los programas listos a ejecutar. Cada programa es ejecutado línea por línea por medio de la función EJECUCIÓN la cual devuelve:

#### • 1 error en la ejecución.
#### • 2 normal.
#### • 3 fin de programa.

##### NOTA: Suponga que existe también la función LineaSiguiente que dado un programa devuelve la línea a ser ejecutada. Maximice la concurrencia en la solución.

~~~

Procedure Punto6 IS

TASK Usuario IS
    Entry procesadorAsignado;
    Entry devolucionPrograma;
End Usuario;

TASK Administrador IS
    Entry solicitarProcesador;
End Administrador;

TASK Procesador IS
    Entry recibirPrograma;
End Procesador;

type Procesadores is array (1 .. N) of Procesador;
type Usuarios is array (1 .. N) of Usuario;

TASK BODY Administrador IS;
    int idUsuario;
    int procesadorAAsignar;
    procesadoresEnEjecucion IS array (1..N);

    BEGIN
        LOOP
            ACCEPT solicitarProcesador(procesadorAAsignar) IS // No necesito idUser porque devuelvo en el mismo llamado.
                procesadoraAsignar = Min(procesadoresEnEjecucion)
                procesadoresEnEjecucion(procesadoraAsignar) ++
            END solicitarProcesador();

            ACCEPT liberarProcesador(procesadorALiberar) IS
                procesadoresEnEjecucion(procesadorALiberar) --
            END liberarProcesador();

    END Administrador;


TASK BODY Procesador IS;
    string linea;
    int resultado;
    cola programasEnEjecucion;

    BEGIN
        LOOP
            SELECT
                ACCEPT recibirPrograma(programa, idUsuario) IS
                    programasEnEjecucion.encolar(programa, idUsuario);
                END recibirPrograma;
            ELSE // si pusiera OR WHEN tendría poner una sentencia recepcion.
                if ( programasEnEjecucion.length > 0 ) then
                    
                    programa, idUsuarioActual = programasEnEjecucion.desencolar()

                    linea = Linasiguiente(programa);
                    resultado = EJECUCION (LINEA);

                    if ( resultado != 2) then
                        Usuario(idUsuario).devolucionPrograma(resultado)
                    else
                        programasEnEjecucion.encolar(idUsuarioActual, programa)
                    end if
                else // para que no quede en loop 
                    ACCEPT recibirPrograma(programa, idUsuario) IS
                    programasEnEjecucion.encolar(programa, idUsuario);
                    END recibirPrograma;
                end if
            END SELECT
        END LOOP
    END Procesador;


    END Procesador;


TASK BODY Usuario IS
    int numProcesador
    string programa
    int respuestaPrograma = 1; // Asumo que arranca "con error", ya que todavía no lo comprobé.

    BEGIN
        programa = escribirPrograma()
        Administrador.solicitarProcesador()
        ACCEPT procesadorAsignado(numProcesador)

        while ( respuestaPrograma != 3 ) LOOP

            Procesadores(numProcesador).recibirPrograma(programa)
            ACCEPT devolucionPrograma(respuestaPrograma)

            if(respuestaPrograma = 1) then
                corregirLinea(programa)
            else
                Administrador.liberarProcesador(numProcesador)
            end if

        END WHILE
    END

~~~

___

### 7. En una clínica existe un médico de guardia que recibe continuamente peticiones de atención de las E enfermeras que trabajan en su piso y de las P personas que llegan a la clínica ser atendidos.

### Cuando una persona necesita que la atiendan espera a lo sumo 5 minutos a que el médico lo haga, si pasado ese tiempo no lo hace, espera 10 minutos y vuelve a requerir la atención del médico. Si no es atendida tres veces, se enoja y se retira de la clínica.

### Cuando una enfermera requiere la atención del médico, si este no lo atiende inmediatamente le hace una nota y se la deja en el consultorio para que esta resuelva su pedido en el momento que pueda (el pedido puede ser que el médico le firme algún papel). Cuando la petición ha sido recibida por el médico o la nota ha sido dejada en el escritorio, continúa trabajando y haciendo más peticiones.

### El médico atiende los pedidos teniendo dándoles prioridad a los enfermos que llegan para ser atendidos. Cuando atiende un pedido, recibe la solicitud y la procesa durante un cierto tiempo. Cuando está libre aprovecha a procesar las notas dejadas por las enfermeras.

##### NOTA: maximice la concurrencia.

~~~
# FALTA CHEQUEAR.

Procedure Clinica;

TASK TYPE Persona;
TASK TYPE Enfermera;

TASK TYPE Escritorio IS
    Entry obtenerNota(OUT nota);
    Entry dejarNota(IN nota);
END Escritorio;

TASK TYPE Medico IS
    Entry atenderEnfermera;
    Entry atenderPaciente;
END Medico;


pacientes:= array (1..P) of Persona; # PREGUNTAR QUE ONDA ESTO PORQUE NO LO USO PERO CREO QUE DEBERÍA IR.
enfermeras:= array (1..E) of Enfermera;


TASK BODY Escritorio IS
    cola notas;

    BEGIN
        SELECT
            WHEN ( notas.length > 0 ) => ACCEPT obtenerNota(OUT nota) IS
                                            nota := notas.desencolar();
                                        END obtenerNota;
        OR
            ACCEPT dejarNota (nota) IS
                notas.encolar(nota);
            END dejarNota;
        END SELECT
    END BEGIN


    END Escritorio;

TASK BODY Medico IS
    BEGIN
        SELECT
            WHEN ( atenderPaciente'COUNT = 0) =>    Escritorio.obtenerNota(nota) IS
                                                        firmarNota(nota);
                                                    End obtenerNota;

            OR WHEN ( atenderPaciente'COUNT = 0 ) =>    ACCEPT atenderEnfermera IS
                                                            atender();
                                                        End atenderEnfermera;
            OR
                ACCEPT atenderPaciente() IS
                    atender();
                END atenderPaciente;
        END SELECT
    END Medico;
        

TASK BODY Persona IS
    cantSolicitudes: Integer;

    BEGIN

    cantSolicitudes:= 0;

    while ( !atendido OR cantSolicitudes != 3 ) LOOP
        SELECT
            Medico.atenderPaciente;
        OR DELAY 5*60
            cantSolicitudes := cantSolicitudes + 1;
            DELAY 10*60
        END SELECT
    END While

    if( cantSolicitudes = 3 ) then
        enojarse();
        irse();
    end if

    END Persona;

TASK BODY Enfermera IS
    BEGIN
        LOOP

            SELECT
                Medico.atenderEnfermera;
            ELSE
                nota = hacerNota();
                Escritorio.dejarNota( nota );
            END SELECT
        END LOOP
    END Enfermera;

End Clinica;

~~~