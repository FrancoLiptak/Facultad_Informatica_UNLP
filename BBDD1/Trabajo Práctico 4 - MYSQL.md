## Trabajo Práctico 4 - MYSQL

### 1) Crear usuarios para las bases de datos, usando los nombres reparacion para la versión normalizada y reparacion_dn para la desnormalizada. Habiendo creado estos usuarios, evitar el uso de root para el resto del trabajo práctico.

~~~

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

### 3) Hallar aquellos clientes que para todas sus reparaciones siempre hayan usado su tarjeta de crédito primaria (nunca la tarjeta secundaria). Realice la consulta en ambas bases.

~~~

USE reparacion;
SELECT c.dniCliente, c.nombreApellidoCliente FROM cliente c WHERE NOT EXISTS (SELECT * FROM reparacion r WHERE c.dniCliente = r.dniCliente AND c.tarjetaSecundaria = r.tarjetaReparacion);

# 11976 rows in set (0.22 sec)

USE reparacion_dn;

SELECT r.dniCliente, r.nombreApellidoCliente FROM reparacion r WHERE NOT EXISTS (SELECT * FROM reparacion re WHERE r.dniCliente = re.dniCliente AND r.tarjetaSecundaria = re.tarjetaReparacion);

# 89603 rows in set (0.32 sec)
~~~

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

### 5) En la base normalizada, hallar los clientes que dejaron vehículos a reparar en todas las sucursales de la ciudad en la que viven

#### Restricción: resolver este ejercicio sin usar la cláusula “NOT EXIST”.
#### Nota: limite su consulta a los primeros 100 resultados, caso contrario el tiempo que tome puede ser excesivo.

#### a. Realice la consulta sin utilizar la vista creada en el ej 4.

~~~



~~~

#### b. Realice la consulta utilizando la vista creada en el ej 4.

~~~

~~~

### 6) Hallar los clientes que en alguna de sus reparaciones hayan dejado como dato de contacto el mismo domicilio y ciudad que figura en su DNI. Realice la consulta en ambas bases.

~~~

USE reparacion;
SELECT dniCliente, nombreApellidoCliente FROM cliente WHERE EXISTS (Select * FROM cliente c INNER JOIN reparacion r ON (c.dniCliente = r.dniCliente) WHERE r.direccionReparacionCliente = c.domicilioCliente AND r.ciudadReparacionCliente = c.ciudadCliente);

# 20000 rows in set (0.02 sec)

USE reparacion_dn;
SELECT dniCliente, nombreApellidoCliente FROM reparacion WHERE EXISTS (Select * FROM reparacion WHERE direccionReparacionCliente = domicilioCliente AND ciudadReparacionCliente = ciudadCliente);

# 162252 rows in set (0.10 sec)

~~~

### 7) Para aquellas reparaciones que tengan registrados mas de 3 repuestos, listar el DNI del cliente, el código de sucursal, la fecha de reparación y la cantidad de repuestos utilizados. Realice la consulta en ambas bases.

~~~

USE reparacion;
SELECT r.dniCliente, r.codSucursal, r.fechaInicioReparacion, COUNT(rep.repuestoreparacion) AS cantidad_repuestos FROM reparacion r INNER JOIN repuestoreparacion rep ON (r.fechaInicioReparacion = rep.fechaInicioReparacion) WHERE (r.dniCliente = rep.dniCliente) GROUP BY r.fechaInicioReparacion HAVING COUNT(rep.repuestoreparacion) > 3;

# 4068 rows in set (0.10 sec)

USE reparacion_dn;
SELECT r.dniCliente, r.codSucursal, r.fechaInicioReparacion, COUNT(r.repuestoreparacion) AS cantidad_repuestos FROM reparacion r INNER JOIN reparacion rep ON (r.dniCliente = rep.dniCliente) WHERE (r.fechaInicioReparacion = rep.fechaInicioReparacion) GROUP BY r.fechaInicioReparacion HAVING COUNT(r.repuestoreparacion) > 3;

# CONSULTAR, EL SEGUNDO ESTÁ MAL, PERO NO SE SOLUCIONARLO.

~~~



