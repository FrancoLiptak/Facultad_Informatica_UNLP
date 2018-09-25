package punto3;

import punto1.Estudiante;

public class EstudiantePrimario extends Estudiante {
    private Integer ausencias;
    private Boolean recursante;

    public EstudiantePrimario(Integer ausencias, Boolean recursante) {
        this.ausencias = ausencias;
        this.recursante = recursante;
    }

    public Integer getAusencias() {
        return ausencias;
    }

    public void setAusencias(Integer ausencias) {
        this.ausencias = ausencias;
    }

    public Boolean getRecursante() {
        return recursante;
    }

    public void setRecursante(Boolean recursante) {
        this.recursante = recursante;
    }
}
