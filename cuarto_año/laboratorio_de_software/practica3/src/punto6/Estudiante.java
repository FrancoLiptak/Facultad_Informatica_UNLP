package punto6;

public class Estudiante {
    private String nombre;
    private String apellido;
    private Integer edad;
    private String legajo;
    private Integer materiasAprobadas;

    public Estudiante(String nombre, String apellido, Integer edad, String legajo, Integer materiasAprobadas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Integer getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(Integer materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", legajo='" + legajo + '\'' +
                ", materiasAprobadas=" + materiasAprobadas +
                '}';
    }
}
