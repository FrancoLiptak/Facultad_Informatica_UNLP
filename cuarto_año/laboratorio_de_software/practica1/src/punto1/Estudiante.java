package punto1;

public class Estudiante {
    private String apellido;
    private String nombre;
    private String legajo;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Estudiante() {
    }

    public Estudiante(String apellido, String nombre, String legajo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
    }

    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer("Estudiante{");
        tmp.append("nombre: " + nombre + ", ");
        tmp.append("apellido: " + apellido + ", ");
        tmp.append("legajo: " + legajo);
        tmp.append("}");

        return tmp.toString();
    }

}
