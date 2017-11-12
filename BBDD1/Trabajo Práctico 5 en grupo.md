# Trabajo Práctico 5 - Repaso.

# Normalización.

## 9) Inscripciones.

### INSCRIPCIONES (#inscripcion, fechaInscripcion, #alumno, #comision, horario, #aula, nombreAula, capacidad, nombreAlumno, dniAlumno, #ayudante)

### Donde:

#### • Dentro de una cátedra, se quieren administrar las inscripciones de los alumnos a las diferentes comisiones.
#### • Una comisión tiene un aula y un horario asignado. Las aulas pueden compartirse, es decir que en un aula puede haber dos o más comisiones en el mismo horario.
#### • Cada aula tiene un nombre y una capacidad. El nombre puede repetirse para diferentes aulas.
#### • El #aula y #comisión son únicos en el sistema.
#### • Cada comisión lleva su propia cuenta de #inscripcion, es decir, los #inscripcion no son únicos en el sistema y pueden repetirse en diferentes comisiones.
#### • Una inscripción en una comisión se hace en una fecha y para un alumno determinado.
#### • En una misma fecha, un alumno puede inscibirse a diferentes comisiones.
#### • Un alumno tiene un #alumno, que es único. Además, tiene un nombre y un dni (que también es único).
#### • Cada comisión tiene asignados a varios ayudantes. El #ayudante es único en el sistema. Un ayudante puede estar asignado a más de una comisión.

DF1) #comision -> #aula, horario.
DF2) #aula -> capacidad, nombreAula.
DF3) #comision, #inscripcion -> fechaInscripcion, #alumno.
DF4) #comision, #inscripcion -> fechaInscripcion, dniAlumno.
DF5) #alumno -> nombreAlumno, dniAlumno.
DF6) dniAlumno -> #alumno, nombreAlumno.

cc: { #comision, #inscripcion, #ayudante }

Particiono con DF2
R1: ( * #aula *, capacidad, nombreAula )
R2: (#inscripcion, fechaInscripcion, #alumno, #comision, horario, #aula, nombreAlumno, dniAlumno, #ayudante)

DF5
R3: ( * #alumno *, nombreAlumno, dniAlumno )
R4: (#inscripcion, fechaInscripcion, #alumno, #comision, horario, #aula, #ayudante)

DF1
R5: ( * #comision *, #aula, horario)
R6: (#inscripcion, fechaInscripcion, #alumno, #comision, #ayudante)

DF3
R7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno). 
Por transitividad, puedo garantizar que, como tengo dos formas de identificar al alumno, no pierdo DF4.
R8: (#inscripcion, #comision, #ayudante)

Podemos afirmar que las siguientes tablas quedaron en BCNF
R1: ( * #aula *, capacidad, nombreAula )
R3: ( * #alumno *, nombreAlumno, dniAlumno )
R5: ( * #comision *, #aula, horario)
R7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno). 
R8: ( #inscripcion, #comision *, #ayudante )

DMS:

DM1: #comision -->> #ayudante.
DM2: #comision -->> #inscripcion.

DM1:
R9: ( * #comision , #ayudante *)
R10: ( * #comision, #inscripcion *)

Estan todas en 4FN
R1: ( * #aula *, capacidad, nombreAula )
R3: ( * #alumno *, nombreAlumno, dniAlumno )
R5: ( * #comision *, #aula, horario)
R7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno). 
R9: ( * #comision , #ayudante *)

R10 no es tenida en cuenta porque es proyeccion de R7.

___

## 10. Viveros

### INSTALACIONES(idCuidador, nyAp, dni, idVivero, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idPlanta, nombrePlanta, idEspecie, nombreEspecie, quimicoPlanta, consultorVivero)

### Donde:

#### • El idVivero es un identificador único que no se repite para diferentes viveros. Del vivero se conoce su nombre (diferentes viveros pueden tener el mismo nombre), los metros cuadrados que ocupa, la temperatura promedio que debe mantener y el cuidador responsable del mismo.
#### • Un mismo cuidador (idCuidador) puede cuidar diversos viveros. Tener en cuenta que un vivero tiene solamente un cuidador responsable asignado.
#### • Del cuidador se conoce su nombre y apellido y el dni. El idCuidador no se repite para diferentes cuidadores.
#### • El dni registrado en el esquema pertenece a un cuidador. El dni es un valor único que no se repite para diferentes cuidadores.
#### • El idPlanta es único. Por ejemplo, una planta es el helecho. De cada planta se conoce el nombre de la planta y la especie a la que pertenece
#### • El idEspecie es único. Un ejemplo de especie es el árbol. Cada planta pertenece a una única especie y a una especie pertenecen diversas plantas.
#### • A cada planta en un vivero (por ejemplo: helecho en el vivero 1) se le aplica un conjunto de químicos. Los mismos químicos se pueden aplicar a plantas de diferentes viveros y a diferentes plantas en el mismo vivero.
#### • Cada vivero tiene diversos consultores de viveros (consultorVivero), que son quienes asesoran ante dudas eventuales. El mismo consultor puede asesorar en diversos viveros

DF1) idVivero -> nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idCuidador
DF2) idVivero -> nombreVivero, mtrCuadradosVivero, tempPromedioVivero, dni
DF3) idCuidador -> nyAp, dni
DF4) dni -> idCuidador, nyAp.
DF5) idPlanta -> nombrePlanta, idEspecie.
DF6) idEspecie -> nombreEspecie

cc: { idVivero, quimicoPlanta, consultorVivero, idPlanta }

DF3
R1: (* idCuidador * , nyAp, dni )
R2: (idCuidador, idVivero, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idPlanta, nombrePlanta, idEspecie, nombreEspecie, quimicoPlanta, consultorVivero)

DF6
R3: ( * idEspecie *, nombreEspecie)
R4: (idCuidador, idVivero, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idPlanta, nombrePlanta, idEspecie, quimicoPlanta, consultorVivero)

DF5
R5: (* idPlanta *, nombrePlanta, idEspecie)
R6: (idCuidador, idVivero, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idPlanta, quimicoPlanta, consultorVivero)

DF1
R7: ( * idVivero *, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idCuidador )
R8: ( idVivero, idPlanta, quimicoPlanta, consultorVivero )

DMS

DM1: idVivero -->> consultorVivero
DM2: idVivero, idPlanta -->> quimicoPlanta

DM2
R9: ( * idVivero, idPlanta , quimicoPlanta * )
R10: ( idVivero, idPlanta, consultorVivero )

DM1: idVivero -->> consultorVivero
DM3: idVivero -->> idPlanta

DM1
R11: ( * idVivero , consultorVivero * )
R12: ( * idVivero , idPlanta * )

PARTICIONES EN 4FN

R1: (* idCuidador * , nyAp, dni )
R3: ( * idEspecie *, nombreEspecie)
R5: (* idPlanta *, nombrePlanta, idEspecie)
R7: ( * idVivero *, nombreVivero, mtrCuadradosVivero, tempPromedioVivero, idCuidador )
R9: ( * idVivero, idPlanta , quimicoPlanta * )
R11: ( * idVivero , consultorVivero * )

R12 es proyeccion de R9.

___

## 11. Transportes

### VIAJES(idTransporte, patenteT, idConductor,fechaViaje, idTipoMaterial, nombreM, gradoToxM, origen, destino, reglamentacionT, nombreC, domicilioC, modeloT, marcaT, toneladasT)

### Donde:
#### • el idTransporte identifica a un transporte y es único en el esquema
#### • patenteT es la patente de un transporte y es único en el esquema
#### • idConductor, identifica a cada conductor involucrado alguna vez en un viaje. El idConductor no se repite en el esquema
#### • de cada transporte se conoce su patente (patenteT), el modelo (modeloT), la marca (marcaT) y su tonelaje (toneladasT). Hay diversos transportes de igual modelo, marca y tonelaje
#### • de cada conductor, se conoce su nombre (nombreC) y el domicilio en el que vive (domicilioC)
#### • idTipoMaterial identifica a un tipo de material y es único en el esquema. Del tipo de material se conoce el nombre (nombreM) y el grado de toxicidad (gradoToxM) del mismo
#### • los transportes realizan diversos viajes en diferentes fechas (fechaViaje). En una fecha muchos transportes pueden realizar viajes. Un transporte en una fecha determinada realiza un único viaje, del cual se conoce el conductor asignado (idconductor), el origen, el destino y los tipos de materiales que transporta (idTipoMaterial). Notar que el mismo conductor puede ser asignado a diferentes transportes en diferentes viajes del mismo o de diferentes transportes. Por otro lado, notar que los tipos de materiales que se transportan en un viaje son muchos
#### • para cada transporte se mantiene un conjunto de reglamentaciones vigentes. Existen diversas reglamentaciones por transporte (reglamentacionT). Cada reglamentacionT es única en el esquema

DF1) idTransporte -> patenteT, modeloT, marcaT, toneladasT. elegi df5, lo que me obliga a elegir entre df5 y df7. 
DF2) patenteT -> idTransporte, modeloT, marcaT, toneladasT
DF3) idConductor -> nombreC, domicilioC
DF4) idTipoMaterial -> nombreM, gradoToxM
DF5) idTransporte, fechaViaje -> idConductor, origen, destino
DF6) patenteT, fechaViaje -> idConductor, origen, destino
DF7) idConductor, fechaViaje -> idTransporte, origen, destino
DF8) idConductor, fechaViaje -> patenteT, origen, destino

cc1: { idTransporte, fechaViaje, idTipoMaterial, reglamentacionT } *
cc2: { patenteT, fechaViaje, idTipoMaterial, reglamentacionT }
cc3: { idConductor, fechaViaje, idTipoMaterial, reglamentacionT }

DF1
R1: ( * idTransporte *, patenteT, modeloT, marcaT, toneladasT )
R2: (idTransporte, idConductor,fechaViaje, idTipoMaterial, nombreM, gradoToxM, origen, destino, reglamentacionT, nombreC, domicilioC)

DF3
R3: ( * idConductor *, nombreC, domicilioC )
R4: (idTransporte, idConductor,fechaViaje, idTipoMaterial, nombreM, gradoToxM, origen, destino, reglamentacionT)

DF4
R5: ( * idTipoMaterial * , nombreM, gradoToxM)
R6: (idTransporte, idConductor,fechaViaje, idTipoMaterial, origen, destino, reglamentacionT)

DF5
R7: ( * idTransporte, fechaViaje *, idConductor, origen, destino )
R9: ( idTransporte, fechaViaje, idTipoMaterial, reglamentacionT )

DMS

DM1: idTransporte -->> reglamentacionT
DM2: idTransporte, fechaViaje -->> idTipoMaterial

DM1
R10: ( * idTransporte, reglamentacionT * )
R11: ( * idTransporte, fechaViaje, idTipoMaterial * )

Las particiones en 4FN son:

R1: ( * idTransporte *, patenteT, modeloT, marcaT, toneladasT )
R3: ( * idConductor *, nombreC, domicilioC )
R5: ( * idTipoMaterial * , nombreM, gradoToxM )
R7: ( * idTransporte, fechaViaje *, idConductor, origen, destino )
R10: ( * idTransporte, reglamentacionT * )
R11: ( * idTransporte, fechaViaje, idTipoMaterial * )

___

## 12. Conferencias

### CONFERENCIA (#conferencia, nombreConferencia, #publicación, #simposio, tituloPublicacion, #autor, nombreAutor, #responsableSimposio, nombreResponsable, nombreSimposio)

#### Donde:

#### • Una conferencia tiene muchos simposios. Los #simposio se pueden repetir en diferentes conferencias.
#### • Un simposio tiene varios responsables, pero cada uno de ellos es responsable de un único simposio.
#### • En cada simposio se presentan publicaciones. Cada publicación se presenta en un único simposio de una conferencia.
#### • Una publicación tiene muchos autores.

DFS

* DF1: #conferencia -> nombreConferencia
* DF2: #responsableSimposio -> nombreResponsable, #simposio
* DF3: #autor -> nombreAutor
* DF4: #conferencia, #publicacion -> #simposio
* DF5: #publicacion -> tituloPublicacion

cc: { #conferencia, #publicacion, #autor, #responsableSimposio }

DF2
R1: ( * #responsableSimposio *, nombreResponsable, #simposio)
R2: (#conferencia, nombreConferencia, #publicación, tituloPublicacion, #autor, nombreAutor, #responsableSimposio, nombreSimposio)

Tengo que aplicar el algoritmo

~~~

Como sospechamos que se perdió DF4:

PASO 1
res = { #conferencia, #publicacion }
res = { #conferencia, #publicacion } U ( ( { #conferencia, #publicacion } ∩ ( * #responsableSimposio *, nombreResponsable, #simposio))+ ∩ ( * #responsableSimposio *, nombreResponsable, #simposio))
res = { #conferencia, #publicacion } U ( ( 0 )+ ∩ ( * #responsableSimposio *, nombreResponsable, #simposio))
res = { #conferencia, #publicacion } U ( 0 )
res = { #conferencia, #publicacion }

PASO 2
res = { #conferencia, #publicacion } U ( ( { #conferencia, #publicacion } ∩ ( R2 ) )+ ∩ ( R2 ))
res = { #conferencia, #publicacion } U ( ( { #conferencia, #publicacion } )+ ∩ ( R2 ) )
res = { #conferencia, #publicacion } U ( ( { #conferencia, #publicacion, #simposio, nombreConferencia } ∩ ( R2 ) )
res = { #conferencia, #publicacion } U ( { #conferencia, #publicacion, nombreConferencia } )

~~~

Como no se puede llegar a BCNF, llevo el esquema a 3FN

R1: ( * #conferencia *, nombreConferencia)
R2: ( * #responsableSimposio *, nombreResponsable, #simposio)
R3: ( * #autor *, nombreAutor)
R4: ( * #conferencia, #publicacion *, #simposio)
R5: ( * #publicacion *, tituloPublicacion)
R6: ( * #conferencia, #publicacion, #autor, #responsableSimposio *)

De R1 a R6 están en BCNF. Como todas están en BCNF las llevo a 4FN.

DMS

DM1: #conferencia, #responsableSimposio, #publicacion -->> #autor





