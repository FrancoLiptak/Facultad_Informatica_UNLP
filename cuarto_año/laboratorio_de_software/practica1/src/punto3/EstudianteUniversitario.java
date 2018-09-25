package punto3;

import punto1.Estudiante;

import java.util.Date;

public class EstudianteUniversitario extends Estudiante {
    private Date fechaIngreso;

    public EstudianteUniversitario(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
