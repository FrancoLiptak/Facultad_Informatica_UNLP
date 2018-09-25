package punto3;

import punto1.Estudiante;

public class EstudianteSecundario extends Estudiante {
    private Integer materiasPrevias;
    private Double promedio;

    public EstudianteSecundario(Integer materiasPrevias, Double promedio) {
        this.materiasPrevias = materiasPrevias;
        this.promedio = promedio;
    }

    public Integer getMateriasPrevias() {
        return materiasPrevias;
    }

    public void setMateriasPrevias(Integer materiasPrevias) {
        this.materiasPrevias = materiasPrevias;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
