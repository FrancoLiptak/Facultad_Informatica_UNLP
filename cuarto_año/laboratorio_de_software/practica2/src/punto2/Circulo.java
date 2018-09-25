package punto2;

public class Circulo extends FiguraGeometrica{
    private int radio;

    public Circulo() {
    }

    public Circulo(String color, int radio) {
        super(color);
        this.radio = radio;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public void dibujar(){
        System.out.println("Se dibuja un circulo de radio " + radio + " y color " + this.getColor());
    }

    @Override
    public int getArea(){
        return (int)(Math.PI *  Math.pow(radio,2));
    }

    @Override
    public Boolean soyCirculo(){
        return true;
    }
}
