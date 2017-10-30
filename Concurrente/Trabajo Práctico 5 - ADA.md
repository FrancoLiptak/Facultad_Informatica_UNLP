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
Procedure Buscador_de_numeros is 

TASK Administrador IS
	Entry cantidadDeApariciones(cantidad: IN Integer );
END Administrador;

TASK TYPE Contador IS
	Entry numeroABuscar(numero: IN Integer);
END Contador;

type Contadores is array (1 .. N) of Contador;

TASK BODY Administrador IS

    total: Integer;

    BEGIN
        for I in 1..N loop # Le pido al contador que busque el número.
            Contadores(I).numeroABuscar( random() );
        end loop;

        for I in 1..N loop
            ACCEPT cantidadDeApariciones(cantidad: IN Integer) do # Acepto el número que los contadores me devuelven.
                total := total + cantidad;
            END cantidadDeApariciones;
        END loop
    END Administrador

TASK BODY Contador IS
    apariciones: Integer;
    mi_parte_del_vector is array (1.. T) of Integer;
    numABuscar: Integer;

    BEGIN
        ACCEPT numeroABuscar(numero: IN Integer) do
            numABuscar:= numero;
        END numeroABuscar

        for I in 1..T loop
            if (mi_parte_del_vector(i) = numABuscar ) then
                apariciones := apariciones + 1;
            end if;
        end loop

        Administrador.cantidadDeApariciones(apariciones);
        end Contador

End Buscador_de_numeros;


~~~

___

### 4. Se debe controlar el acceso a una base de datos. Existen A procesos de Tipo 1, B procesos de Tipo 2 y C procesos de Tipo 3 que trabajan indefinidamente de la siguiente manera:

#### • Proceso Tipo 1: intenta escribir, si no lo logro en 2 minutos, espera 5 minutos y vuelve a intentarlo.
#### • Proceso Tipo 2: intenta escribir, si no lo logra en 5 minutos, intenta leer, si no lo logra en 5 minutos vuelve a comenzar.
#### • Proceso Tipo 3: intenta leer, si no puede inmediatamente entonces espera hasta poder escribir.

### Un proceso que quiera escribir podrá acceder si no hay ningún otro proceso en la base de datos, al acceder escribe y avisa que termino de escribir. Un proceso que quiera leer podrá acceder si no hay procesos que escriban, al acceder lee y avisa que termino de leer. Siempre se le debe dar prioridad al pedido de acceso para escribir sobre el pedido de acceso para leer.

~~~



~~~

___

### 5. Se dispone de un sistema compuesto por 1 central y 2 procesos. Los procesos envían señales a la central. La central comienza su ejecución tomando una señal del proceso 1, luego toma aleatoriamente señales de cualquiera de los dos indefinidamente. Al recibir una señal de proceso 2, recibe señales del mismo proceso durante 3 minutos. 

### El proceso 1 envía una señal que es considerada vieja (se deshecha) si en 2 minutos no fue recibida. 

### El proceso 2 envía una señal, si no es recibida en ese instante espera 1 minuto y vuelve a mandarla (no se deshecha).

~~~

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

~~~

___

### 7. En una clínica existe un médico de guardia que recibe continuamente peticiones de atención de las E enfermeras que trabajan en su piso y de las P personas que llegan a la clínica ser atendidos.

### Cuando una persona necesita que la atiendan espera a lo sumo 5 minutos a que el médico lo haga, si pasado ese tiempo no lo hace, espera 10 minutos y vuelve a requerir la atención del médico. Si no es atendida tres veces, se enoja y se retira de la clínica.

### Cuando una enfermera requiere la atención del médico, si este no lo atiende inmediatamente le hace una nota y se la deja en el consultorio para que esta resuelva su pedido en el momento que pueda (el pedido puede ser que el médico le firme algún papel). Cuando la petición ha sido recibida por el médico o la nota ha sido dejada en el escritorio, continúa trabajando y haciendo más peticiones.

### El médico atiende los pedidos teniendo dándoles prioridad a los enfermos que llegan para ser atendidos. Cuando atiende un pedido, recibe la solicitud y la procesa durante un cierto tiempo. Cuando está libre aprovecha a procesar las notas dejadas por las enfermeras.

##### NOTA: maximice la concurrencia.

~~~

~~~