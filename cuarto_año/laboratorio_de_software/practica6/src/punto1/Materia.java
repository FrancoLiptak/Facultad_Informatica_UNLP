package punto1;

import java.util.*;

public class Materia {
    private String nombre;
    private SortedSet<Alumno> alumnos;

    public Materia() {
    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia(String nombre, SortedSet<Alumno> alumnos) {
        this.nombre = nombre;
        this.alumnos = alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(SortedSet<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void agregaAlumno(Alumno a){
        if (this.alumnos == null) { this.alumnos = new TreeSet<>(); }
        this.alumnos.add(a);
    }
}
