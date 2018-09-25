package punto2;

public class Paint{
    private FiguraGeometrica[] paleta;

    public Paint() {
    }

    public FiguraGeometrica[] getPaleta() {
        return paleta;
    }

    public void setPaleta(FiguraGeometrica[] paleta) {
        this.paleta = paleta;
    }

    public void init(){
        FiguraGeometrica[] aux = {
                new Rectangulo("verde", 2, 3),
                new Rectangulo("rojo", 4, 10),
                new Circulo("azul", 2),
                new Circulo("amarillo", 3)
        };

        this.setPaleta(aux);
    }
}
