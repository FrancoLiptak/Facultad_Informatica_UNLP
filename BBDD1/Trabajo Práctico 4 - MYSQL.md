## Trabajo Práctico 4 - MYSQL

### 1) Crear usuarios para las bases de datos, usando los nombres reparacion para la versión normalizada y reparacion_dn para la desnormalizada. Habiendo creado estos usuarios, evitar el uso de root para el resto del trabajo práctico.

~~~

```sql```

CREATE USER 'reparacion'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON reparacion.* TO 'reparacion'@'localhost';

CREATE USER 'reparacion_dn'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON reparacion_dn.* TO 'reparacion_dn'@'localhost';

FLUSH PRIVILEGES;

~~~

### 1.1) Adicionalmente, en ambas bases:

#### - cree un usuario sólo con permisos para realizar consultas de selección, es decir que no puedan realizar cambios en la base. Use los nombres 'reparacion_select’ y 'reparacion_dn_select’.

~~~

CREATE USER 'reparacion_select'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT ON reparacion.* TO 'reparacion_select'@'localhost';

CREATE USER 'reparacion_dn_select'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT ON reparacion_dn.* TO 'reparacion_dn_select'@'localhost';

FLUSH PRIVILEGES;

~~~

#### - cree un usuario que pueda realizar consultas de selección, actualización y eliminación a nivel de filas, pero que no puedan modificar el esquema. Use los nombres 'reparacion_update’ y 'reparacion_dn_update’.

~~~

CREATE USER 'reparacion_update'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT, DELETE, UPDATE ON reparacion.* TO 'reparacion_update'@'localhost';

CREATE USER 'reparacion_dn_update'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT, DELETE, UPDATE ON reparacion_dn.* TO 'reparacion_dn_update'@'localhost';

FLUSH PRIVILEGES;

~~~

#### - cree un usuario que tenga los permisos de los anteriores, pero que además pueda modificar el esquema de la base de datos. Use los nombres 'reparacion_schema’ y 'reparacion_dn_schema’.

~~~

CREATE USER 'reparacion_schema'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT, DELETE, UPDATE, CREATE, DROP, INSERT, ALTER ON reparacion.* TO 'reparacion_schema'@'localhost';

CREATE USER 'reparacion_dn_schema'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT, DELETE, UPDATE, CREATE, DROP, INSERT, ALTER ON reparacion_dn.* TO 'reparacion_dn_schema'@'localhost';

FLUSH PRIVILEGES;

~~~

___

### 2) Listar dni, nombre y apellido de todos los clientes ordenados por dni en forma ascendente. Realice la consulta en ambas bases. ¿Qué diferencia nota en cuanto a performance? ¿Arrojan los mismos resultados? ¿Qué puede concluir en base a las diferencias halladas?

~~~

USE reparacion;
SELECT dniCliente, nombreApellidoCliente FROM cliente ORDER BY dniCliente ASC;

# 20000 rows in set (0.01 sec)

USE reparacion_dn;
SELECT dniCliente, nombreApellidoCliente FROM reparacion ORDER BY dniCliente ASC;
# 162252 rows in set (0.69 sec)

~~~

Podemos notar una gran diferencia en cuanto a performance. La base reparacion produjo menos cantidad de resultados, y en cuanto a tiempo, fue mucho menor.
reparacion_dn nos arroja muchas tuplas repetidas. Esto se debe a que, como no está normalizada, cada vez que un usuario realiza una nueva reparación, toda su información debe ser cargada en el sistema, ocacionando repetición de información.

___

### 3) Hallar aquellos clientes que para todas sus reparaciones siempre hayan usado su tarjeta de crédito primaria (nunca la tarjeta secundaria). Realice la consulta en ambas bases.

~~~

USE reparacion;
SELECT c.dniCliente, c.nombreApellidoCliente FROM cliente c WHERE NOT EXISTS (SELECT * FROM reparacion r WHERE c.dniCliente = r.dniCliente AND c.tarjetaSecundaria = r.tarjetaReparacion);

# 11976 rows in set (0.22 sec)

USE reparacion_dn;

SELECT r.dniCliente, r.nombreApellidoCliente FROM reparacion r WHERE NOT EXISTS (SELECT * FROM reparacion re WHERE r.dniCliente = re.dniCliente AND r.tarjetaSecundaria = re.tarjetaReparacion);

# 89603 rows in set (0.32 sec)

~~~

___

### 4) Crear una vista llamada ‘sucursalesPorCliente’ que muestre los dni de los clientes y los códigos de sucursales de la ciudad donde vive el cliente. Cree la vista en ambas bases.

~~~

USE reparacion;

CREATE VIEW sucursalesPorCliente
    AS SELECT dniCliente, ciudadCliente
    FROM cliente c INNER JOIN sucursal s ON (c.ciudadCliente = s.ciudadSucursal);

# Query OK, 0 rows affected (0.07 sec)

USE reparacion_dn;

CREATE VIEW sucursalesPorCliente 
    AS SELECT dniCliente, ciudadCliente 
    FROM reparacion r WHERE (r.ciudadCliente = r.ciudadSucursal)

# Query OK, 0 rows affected (0.06 sec)

~~~

___

### 5) En la base normalizada, hallar los clientes que dejaron vehículos a reparar en todas las sucursales de la ciudad en la que viven

#### Restricción: resolver este ejercicio sin usar la cláusula “NOT EXIST”.
#### Nota: limite su consulta a los primeros 100 resultados, caso contrario el tiempo que tome puede ser excesivo.

#### a. Realice la consulta sin utilizar la vista creada en el ej 4.

~~~

USE reparacion;

SELECT c.dniCliente
FROM cliente c
WHERE c.dniCliente NOT IN (    SELECT c2.dniCliente
                                FROM sucursal s INNER JOIN cliente c2 ON (s.ciudadSucursal = c2.ciudadCliente)
                                WHERE s.codSucursal <> All (    SELECT r.codSucursal
                                                                FROM reparacion r INNER JOIN cliente c3 ON (c3.dniCliente = r.dniCliente)
                                                                WHERE r.ciudadReparacionCliente = c3.ciudadCliente AND c2.dniCliente = c3.dniCliente
                                                            )
                            )
LIMIT 100;

# 13021 rows in set (1.17 sec)

~~~

#### b. Realice la consulta utilizando la vista creada en el ej 4.

~~~

SELECT c.dniCliente
FROM cliente c
WHERE c.dniCliente NOT IN  (    SELECT spc.dniCliente
                                FROM sucursalesPorCliente spc INNER JOIN sucursal s ON (s.ciudadSucursal = spc.ciudadCliente)
                                WHERE s.codSucursal <> ALL (    SELECT r.codSucursal
                                                                FROM reparacion r INNER JOIN cliente c2 ON (r.dniCliente = c2.dniCliente)
                                                                WHERE r.ciudadReparacionCliente = c.ciudadCliente AND spc.dniCliente = c2.dniCliente
                                                                ) 
                            )
LIMIT 100;

# 13021 rows in set (1.36 sec)

# Primero se resuelve la vista, y después se resuelve el resto de la consulta.

~~~
___

### 6) Hallar los clientes que en alguna de sus reparaciones hayan dejado como dato de contacto el mismo domicilio y ciudad que figura en su DNI. Realice la consulta en ambas bases.

~~~

USE reparacion;
Select DISTINCT c.dniCliente FROM cliente c INNER JOIN reparacion r ON (c.dniCliente = r.dniCliente) WHERE r.direccionReparacionCliente = c.domicilioCliente AND r.ciudadReparacionCliente = c.ciudadCliente;

# 18374 rows in set (0.18 sec)

USE reparacion_dn;
Select DISTINCT dniCliente FROM reparacion WHERE direccionReparacionCliente = domicilioCliente AND ciudadReparacionCliente = ciudadCliente;

# 18307 rows in set (0.71 sec)

~~~
___

### 7) Para aquellas reparaciones que tengan registrados mas de 3 repuestos, listar el DNI del cliente, el código de sucursal, la fecha de reparación y la cantidad de repuestos utilizados. Realice la consulta en ambas bases.

~~~

USE reparacion;

SELECT r.dniCliente, r.codSucursal, r.fechaInicioReparacion, COUNT(rep.repuestoreparacion) AS cantidad_repuestos FROM reparacion r INNER JOIN repuestoreparacion rep ON (r.fechaInicioReparacion = rep.fechaInicioReparacion) WHERE (r.dniCliente = rep.dniCliente) GROUP BY r.dniCliente, r.fechaInicioReparacion HAVING COUNT(DISTINCT rep.repuestoreparacion) > 3;

# 4068 rows in set (0.10 sec)

USE reparacion_dn;

SELECT dniCliente, codSucursal, fechaInicioReparacion, COUNT(repuestoreparacion) AS cantidad_repuestos FROM reparacion GROUP BY dniCliente, fechaInicioReparacion HAVING COUNT(DISTINCT repuestoreparacion) > 3;

# 3964 rows in set (0.12 sec)

~~~
___

## En la base normalizada realice los siguientes ejercicios:

### 8) Agregar la siguiente tabla:

#### REPARACIONESPORCLIENTE
#### idRC: int(11) PK AI
#### dniCliente: int(11)
#### cantidadReparaciones: int(11)
#### fechaultimaactualizacion: datetime
#### usuario: char(16)

~~~

CREATE TABLE reparacionesporcliente  (
    idRC int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,  
    dniCliente int(11) NOT NULL,  
    cantidadReparaciones int(11) NOT NULL,
    fechaultimaactualizacion datetime NOT NULL,  
    usuario char(16) NOT NULL
    );

~~~

___

### 9) Stored procedures

### a) Crear un stored procedure que realice los siguientes pasos dentro de una transacción:

#### - Realizar una consulta que para cada cliente (dniCliente), calcule la cantidad de reparaciones que tiene registradas. Registrar la fecha en la que se realiza la consulta y el usuario con el que la realizó.

#### - Guardar el resultado de la consulta en un cursor.

#### - Iterar el cursor e insertar los valores correspondientes en la tabla REPARACIONESPORCLIENTE.

~~~
# Consultar lugar del NOW y CURRENT en el cursor.

DELIMITER //
CREATE PROCEDURE ejercicio9()

BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE dniCliente INT(11);
    DECLARE cantidadReparaciones INT(11);
    DECLARE fechaultimaactualizacion DATETIME;
    DECLARE usuario CHAR(16);
    DECLARE obtenerInformacion CURSOR FOR 
                                        SELECT c.dniCliente, COUNT(r.fechaInicioReparacion) as cantidad_reparaciones, NOW(), CURRENT_USER() FROM cliente c INNER JOIN reparacion r ON (c.dniCliente = r.dniCliente) GROUP BY c.dniCliente;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    START TRANSACTION;
        OPEN obtenerInformacion;
        ciclo_loop: LOOP
            FETCH obtenerInformacion INTO dniCliente, cantidadReparaciones, fechaultimaactualizacion, usuario;

            if (fin=1) then
                LEAVE ciclo_loop;
            end if;

            INSERT INTO reparacionesporcliente (dniCliente, cantidadReparaciones, fechaultimaactualizacion, usuario) VALUES (dniCliente, cantidadReparaciones, fechaultimaactualizacion, usuario);
        END LOOP ciclo_loop;
        CLOSE obtenerInformacion;

    COMMIT;

END //

~~~

### b) Ejecute el stored procedure.

~~~

CALL ejercicio9;

~~~
___

### 10) Crear un trigger de modo que al insertar un dato en la tabla REPARACION, se actualice la cantidad de reparaciones del cliente, la fecha de actualización y el usuario responsable de la misma (actualiza la tabla REPARACIONESPORCLIENTE).

~~~

DELIMITER |
CREATE TRIGGER ejercicio10 AFTER INSERT ON reparacion 
    FOR EACH ROW 
    BEGIN

        IF (NEW.dniCliente IN (SELECT dniCliente FROM reparacionesporcliente))
        THEN
            UPDATE reparacionesporcliente
            SET cantidadReparaciones = cantidadReparaciones + 1, fechaultimaactualizacion = NOW(), usuario = CURRENT_USER()
            WHERE NEW.dniCliente = reparacionesporcliente.dniCliente;
        ELSE
            INSERT INTO reparacionesporcliente (dniCliente, cantidadReparaciones, fechaultimaactualizacion, usuario) VALUES (NEW.dniCliente, 1, NOW(), CURRENT_USER());
        END IF;
    END
|

~~~

___

### 11) Crear un stored procedure que sirva para agregar una reparación, junto con una revisión de un empleado (REVISIONREPARACION) y un repuesto (REPUESTOREPARACION) relacionados dentro de una sola transacción. El stored procedure debe recibir los siguientes parámetros: dniCliente, codSucursal, fechaReparacion, cantDiasReparacion, telefonoReparacion, empleadoReparacion, repuestoReparacion.

~~~

DELIMITER //
CREATE PROCEDURE ejercicio11(IN dni INT(11), IN sucursal INT, IN fechaReparacion DATETIME, IN cantDiasRep INT,
    IN telefonoRep VARCHAR(45), IN empleadoRep VARCHAR(30), IN repuestoRep VARCHAR(30))


BEGIN
   
   DECLARE direccion VARCHAR(255);
   DECLARE tarjeta VARCHAR(255);
   DECLARE ciudad VARCHAR(255);

   SELECT domicilioCliente, ciudadCliente, tarjetaPrimaria INTO direccion, ciudad, tarjeta FROM cliente c WHERE c.dniCliente = dni;


    START TRANSACTION;

        INSERT INTO reparacion (codSucursal, dniCliente, fechaInicioReparacion, cantDiasReparacion, telefonoReparacionCliente,
    direccionReparacionCliente, ciudadReparacionCliente, tarjetaReparacion) VALUES (sucursal, dni, fechaReparacion, cantDiasRep, telefonoRep, direccion, ciudad, tarjeta);

        INSERT INTO revisionreparacion (dniCliente, fechaInicioReparacion, empleadoReparacion) VALUES (dni, fechaReparacion, empleadoRep);

        INSERT INTO repuestoreparacion (dniCliente, fechaInicioReparacion, repuestoReparacion) VALUES (dni, fechaReparacion, repuestoRep);

    COMMIT;

END //

~~~

___

### 12) Ejecutar el stored procedure del punto 11 con los siguientes datos:

#### - dniCliente: 1009443
#### - codSucursal: 100
#### - fechaReparacion: 2013-12-14 12:20:31
#### - empleadoReparacion: ‘Maidana’
#### - repuestoReparacion: ‘bomba de combustible’
#### - cantDiasReparacion: 4
#### - telefonoReparacion: 4243-4255


~~~

CALL ejercicio11(1009443, 100, "2013-12-14 12:20:31", 4, "4243-4255", "Maidana", "bomba de combustible");

~~~
___

### 13) Realizar las inserciones provistas en el archivo inserciones.sql. Validar mediante una consulta que la tabla REPARACIONESPORCLIENTE se esté actualizando correctamente.

___

### 14) Considerando la siguiente consulta

~~~ 

EXPLAIN select count(r.dniCliente) from reparacion r, cliente c, sucursal s, revisionreparacion rv where r.dnicliente=c.dnicliente
and r.codsucursal=s.codsucursal
and r.dnicliente=rv.dnicliente
and r.fechainicioreparacion=rv.fechainicioreparacion
and empleadoreparacion = 'Maidana'
and s.m2<200
and s.ciudadsucursal='La Plata';

~~~

### Analice su plan de ejecución mediante el uso de la sentencia EXPLAIN.

La sentencia EXPLAIN se utiliza para optimizar consultas. Esta sentencia nos permitirá obtener información sobre el plan de ejecución de nuestras consultas realizadas contra nuestra la base de datos. De esta forma, podremos analizar este plan de ejecución para saber cómo optimizar la ejecución de dichas consultas.

#### a) ¿Qué atributos del plan de ejecución encuentra relevantes para evaluar la performance de la consulta?

Para esto, debemos ver la cantidad de filas escaneadas, y también si se han usado índices. Si no hubiera indices, se recorrerían todos los registros de la tabla para obtener el resultado.

En el campo REF vemos que una fila está en NULL, y las otras muestran atributos.

(REF: Las columnas del índice que se está usando, o una constante si esta es posible.)

Por ende, vemos que en la fila que está en NULL no se está usando ninguna columna, por ende ningún index.

#### b) Observe en particular el atributo type ¿cómo se están aplicando los JOIN entre las tablas involucradas?

Index: Escaneo completo de la tabla para cada combinación de filas de las tablas previas, revisando únicamente el índice.

Ref: Todas las filas con valores en el índice que coincidan serán leídos desde esta tabla por cada combinación de filas de las tablas previas. Similar a eq_ref, pero usado cuando usa sólo un prefijo más a la izquierda de la clave o si la clave no es UNIQUE o PRIMARY KEY. Si la clave que es usada coincide sólo con pocas filas, esta union es buena.

Eq_ref: Una fila de la tabla 1 será leída por cada combinación de filas de la tabla 2. Este tipo es usado cuando todas las partes de un índice se usan en la consulta y el índice es UNIQUE o PRIMARY KEY.

#### c) Según lo que observó en los puntos anteriores, ¿qué mejoras se pueden realizar para optimizar la consulta?

Primero que nada, vemos que la fila en la cual se observa mayor cantidad de rows, es la fila en la cual se usa 'index', y 'ref' es null.

Deberíamos usar indices.

#### d) Aplique las mejoras propuestas y vuelva a analizar el plan de ejecución. ¿Qué cambios observa?

Como se puede observar, elegí crear distintos indices, como también modificar la consulta. Respecto a esta consulta en particular, solo con agregar el índice "empleado", la cantidad de rows disminuyó mucho. Sin embargo, se añadieron otros índices debido a que ante un hipotético crecimiento de la base de datos en cuanto a datos que contiene, podrían beneficiarme en otras consultas.

A su vez, también modifiqué la consulta. Para empezar, notamos que la tabla "cliente" era innecesaria. El único atributo que se usaba era dniCliente, el cuál también está presente en reparación. Tener una tabla menos nos va a facilitar la consulta, en el sentido de que se deben cruzar menos tablas y por ende habrá menos operaciones.

Varias cuestiones a analizar en el WHERE en la consulta original se han movido al FROM. Esto es debido a que estoy intentando que en resultados intermedios de la consulta, me queden conjuntos más chicos (es decir, estoy filtrando). Por ende, las últimas operaciones se hacen sobre conjuntos mas chicos, y por ende, tenemos mejor performance.

~~~

CREATE INDEX metros_cuadrados ON sucursal (m2);
CREATE INDEX ciudad ON sucursal (ciudadsucursal);
CREATE INDEX empleado ON revisionreparacion (empleadoreparacion);
CREATE INDEX r_fechainicio ON reparacion (fechaInicioReparacion);

EXPLAIN 
SELECT COUNT(r.dniCliente) 
FROM reparacion r INNER JOIN (SELECT codsucursal FROM sucursal WHERE m2<200 AND ciudadsucursal='La Plata') AS s ON (r.codsucursal = s.codsucursal), revisionreparacion rv 
WHERE r.dnicliente=rv.dnicliente AND r.fechainicioreparacion=rv.fechainicioreparacion AND empleadoreparacion = 'Maidana';

~~~

___

### 15) Análisis de permisos.

### a) Para cada punto de la práctica incluido en el cuadro, ejecutarlo con cada uno de los usuarios creados en el punto 1 e indicar con cuáles fue posible realizar la operación.

### b) Determine para cada caso, cuál es el conjunto de permisos mínimo.

### c) Desde su punto de vista y contemplando lo visto en la materia, explique cuál es la manera óptima de asignar permisos a los usuarios.

