package punto1;

import java.util.Comparator;

public class Alumno{
    private String legajo;
    private String nombre;
    private String apellido;
    private Integer dni;

    public Alumno() {
    }

    public Alumno(String legajo, String nombre, String apellido, Integer dni) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public enum ComparatorNyA implements Comparator<Alumno> {
        INSTANCE;

        public int compare(Alumno a1, Alumno a2){
            int result = a1.getApellido().compareTo(a2.getApellido());
            if (result == 0){ result = a1.getNombre().compareTo(a2.getNombre()); }
            return result;
        }
    }

}
