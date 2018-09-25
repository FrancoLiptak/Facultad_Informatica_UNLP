package punto2;

public abstract class FiguraGeometrica implements Comparable<FiguraGeometrica>{
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

    @Override
    public int compareTo(FiguraGeometrica o) {
        if (this.getArea() > o.getArea()) return 1;
        if (this.getArea() < o.getArea()) return -1;
        return 0;
    }
}
