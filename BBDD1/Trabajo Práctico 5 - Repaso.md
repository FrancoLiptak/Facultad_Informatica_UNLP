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

Primero tengo que encontrar las dependencias funcionales.

DF1) #aula -> nombreAula, capacidad.
DF2) #comision -> horario, #aula.
DF3) #alumno -> nombrealumno, dniAlumno.
DF4) dniAlumno -> nombreAlumno, #alumno.
DF5) #comision, #inscripcion -> fechaInscripcion, #alumno.
DF6) #comision, #inscripcion -> fechaInscripcion, dniAlumno.

Ahora tengo que encontrar la clave candidata.

La clave candidata va a estar compuesta por todos aquellos atributos que son determinantes y no son determinados, y aquellos atributos que no son ni determinantes ni determinados. La excepción a esta regla, son los casos en que no tengo atributos que cumplan la regla dicha anteriormente, y en estos casos, tendría más de una clave candidata.


CC: {{ #comision, #inscripcion, #ayudante }}
Ahora, intento llevar el esquema a 4FN.

¿ INSCRIPCIONES cumple BCNF ?

Para que una tabla cumpla BCNF, se debe cumplir que para toda dependencia funcional válida en el esquema, los determinantes de dichas dependencias funcionales, deben ser superclave del esquema.

Una superclave es un conjunto de uno o más atributos que, tomados colectivamente, permiten identificar de forma única una entidad en el conjunto de entidades.

En este caso, observamos que en INSCRIPCIONES valen desde DF1 hasta DF4, cuyos determinantes NO son superclave.

Particiono en base a DF1:

T1: ( * #aula *, nombreAula, capacidad)
T2: (* #inscripcion, fechaInscripcion *, #alumno, #comision, horario, #aula, nombreAlumno, dniAlumno, #ayudante)

Ahora, debo verificar que la partición se realizó sin consecuencias:

a.  ¿Perdí información con este particionamiento?

No. Podemos observar que T1 ∩ T2 = { #aula }. Sabemos que #aula es clave de la partición T1.

b. ¿Perdí dependencias funcionales con el particionamiento?

No. DF1 vale en T1. DF2 a DF6 valen en T2.

c. ¿T1 cumple con la definición de BCNF?

Si. En T1 vale DF1, donde su determinante es superclave del esquema T1.

d. ¿T2 cumple con la definición de BCNF?

No. En T2 siguen valiendo DF2, DF3 y DF4, cuyos determinantes no son superclave del esquema. Debo seguir particionando, y elijo particionar en base a DF2.

T3: (* #comision *, horario, #aula)
T4: (* #inscripcion, fechaInscripcion *, #alumno, #comision, nombreAlumno, dniAlumno, #ayudante)

a. ¿Perdí información con este particionamiento?

No. Podemos observar que T3 ∩ T4 = { #comision }. Sabemos que #comision es clave de la particion T3.

b. ¿Perdí dependencias funcionales con el particionamiento?

No. DF2 vale en T3. DF3 a DF6 valen en T4.

c. ¿T3 cumple con la definición de BCNF?

Si. En T3 vale DF2, donde su determinante es superclave del esquema T3.

d. ¿T4 cumple con la definición de BCNF?

No. En T4 siguen valiendo DF3 y DF4, cuyos determinantes no son superclave del esquema. Debo seguir particionando, y elijo particionar en base a DF3.

T5: (* #alumno *,  nombrealumno, dniAlumno)
T6: (* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante)

a. ¿Perdí información con este particionamiento?

No. Podemos observar que T5 ∩ T6 = { #alumno }. Sabemos que #alumno es clave de la particion T5.

b. ¿Perdí dependencias funcionales con el particionamiento?

DF3 y DF4 valen en T5. En T6 vale DF5, pero no podemos asegurar que vale DF6. Debemos aplicar el algoritmo para verificar que dicha DF sigue valiendo. En caso de que no siga valiendo, el esquema no podrá ser llevado a BCNF y por ende, deberíamos intentar llevarlo a 3FN.

~~~

Paso 1)

result = { #comision, #inscripcion } #A partir del determinante, tengo que intentar llegar a obtener todos los atributos de la DF.
i = 1
result = { #comision, #inscripcion } U (( { #comision, #inscripcion } ∩ (* #alumno *,  nombrealumno, dniAlumno) )+ ∩ (* #alumno *,  nombrealumno, dniAlumno))

Puedo observar que la primer interseccion es vacía. Por ende, la segunda también será vacía.
____

Paso 2)

result = { #comision, #inscripcion }
i = 2
result = { #comision, #inscripcion } U (( { #comision, #inscripcion } ∩ (* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante))+ ∩ (* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante))

El resultado de la primer intersección nos deja { #comision, #inscripcion }. Ahora debo aplicar el segundo algoritmo para resolver el +.

INICIO SEGUNDO ALGORITMO:

        Paso 1)

        result = { #comision, #inscripcion }
        i=1
        result = { #comision, #inscripcion } #No agrego nada a result.

        Paso 2)
        result = { #comision, #inscripcion }
        i = 2
        result = { #comision, #inscripcion, fechaInscripcion, #alumno, #ayudante }

        Como hubo cambios en result, itero nuevamente.

        Paso 3) 
        result = { #comision, #inscripcion, fechaInscripcion, #alumno, #ayudante }
        i = 1
        result = { #comision, #inscripcion, fechaInscripcion, #alumno, #ayudante, nombrealumno, dniAlumno }

        La ejecución del algoritmo no va a alterar mas el contenido de result. Vuelvo al primer algoritmo.

FIN SEGUNDO ALGORITMO.

result = { #comision, #inscripcion } U ( { #comision, #inscripcion, fechaInscripcion, #alumno, #ayudante, nombrealumno, dniAlumno } ∩ (* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante) )
result = { #comision, #inscripcion} U {* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante}
result = {* #inscripcion, fechaInscripcion *, #alumno, #comision, #ayudante}

COMO RESULT CAMBIO, PUEDO VOLVER A ITERAR.

Paso 3)

result = { #comision, #inscripcion, fechaInscripcion, #alumno, #ayudante, nombrealumno, dniAlumno }

Como result ya tiene los atributos que estaba buscando, puedo garantizar que es inutil seguir iterando.

~~~

No perdí dependencias funcionales.

c. ¿T5 cumple con la definición de BCNF?

Si. En T5 vale DF3 y DF4, donde sus determinantes son superclave del esquema T5.

d. ¿T6 cumple con la definición de BCNF?

No. En T6 siguen valiendo DF5 y DF6, cuyos determinantes no son superclave del esquema. Debo seguir particionando, y elijo particionar en base a DF5.

T7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno )
T8: ( * #comision, #inscripcion *, #ayudante )

a. ¿Perdí información con este particionamiento?

No. Podemos observar que T7 ∩ T8 = { #comision, #inscripcion }. Sabemos que #comision, #inscripcion es clave de la particion T7.

b. ¿Perdí dependencias funcionales con el particionamiento?

No. DF5 y DF6 valen en T7.

c. ¿T7 cumple con la definición de BCNF?

Si. En T7 valen DF5 y DF6, donde sus determinantes son superclave del esquema T7.

d. ¿T8 cumple con la definición de BCNF?

Si, porque representa a la clave del esquema, por lo cual todas las dependencias funcionales que se pueden llegar a presentar en el mismo serán triviales.

Por ende las particiones en BCNF son:

T1: ( * #aula *, nombreAula, capacidad )
T3: ( * #comision *, horario, #aula )
T5: ( * #alumno *,  nombrealumno, dniAlumno )
T7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno )
T8: ( * #comision, #inscripcion, #ayudante * )

Clave: { * #comision, #inscripcion, #ayudante * }

Ahora busco las dependencias multivaluadas para empezar a llevar el esquema a 4FN. Siempre las dependencias multivaluadas deben tener un único atributo.

DM1) #comision -->> #inscripcion.
DM2) #comision -->> #ayudante.
DM3) #ayudante -->> #comision.

En T1, T3, T5 y T7 no vale ninguna de las dependencias multivaluadas. Puedo asegurar que están en 4FN.

En T8 valen DM1, DM2, DM3, las cuales NO son triviales. Para que sean triviales, la suma de atributos determinados y determinantes debería ser igual al conjunto esquema.
Debo particionar T8 en base a alguna DM y elijo particionar en base a DM1.

T9: ( *#comision* , #inscripcion ) 
T10: ( *#comision*, #ayudante)

Veo que ahora DM1 vale en T9, y DM2 y DM3 valen en T10. Las 3 DM son triviales y por ende, puedo asegurar que están en 4FN.

Finalmente, las particiones en 4FN son:

- T1: ( * #aula *, nombreAula, capacidad )
- T3: ( * #comision *, horario, #aula )
- T5: ( * #alumno *,  nombrealumno, dniAlumno )
- T7: ( * #comision, #inscripcion *, fechaInscripcion, #alumno )
- T9: ( * #comision * , #inscripcion ) 
- T10: ( * #comision *, #ayudante)

___

## Conferencias

### CONFERENCIA (#conferencia, nombreConferencia, #publicación, #simposio, tituloPublicacion, #autor, nombreAutor, #responsableSimposio, nombreResponsable, nombreSimposio)

#### Donde:

#### • Una conferencia tiene muchos simposios. Los #simposio se pueden repetir en diferentes conferencias.
#### • Un simposio tiene varios responsables, pero cada uno de ellos es responsable de un único simposio.
#### • En cada simposio se presentan publicaciones. Cada publicación se presenta en un único simposio de una conferencia.
#### • Una publicación tiene muchos autores.


