package Punto6;

public class Rectangulo extends FiguraGeometrica{
    private int alto;
    private int ancho;

    public Rectangulo() {
    }

    public Rectangulo(String color, int alto, int ancho) {
        super(color);
        this.alto = alto;
        this.ancho = ancho;
    }

    @Override
    public void dibujar(){
        System.out.println("Se dibuja un circulo de ancho " + ancho + " alto " + alto + " y color " + this.getColor());
    }

    @Override
    public int getArea(){
        return alto * ancho;
    }

    @Override
    public Boolean soyRectangulo(){
        return true;
    }
}
