package Punto6;

public abstract class FiguraGeometrica {
    private String color;

    public FiguraGeometrica() {
    }

    public FiguraGeometrica(String color) {
        this.color = color;
    }

    public abstract void dibujar();

    public abstract int getArea();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean soyRectangulo(){
        return false;
    }

    public Boolean soyCirculo(){
        return false;
    }
}
