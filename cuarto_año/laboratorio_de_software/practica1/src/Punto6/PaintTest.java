package Punto6;

public class PaintTest {

    public static void main(String[] args){
        Paint paint = new Paint();
        paint.init();
        for(FiguraGeometrica figuraGeometrica : paint.getPaleta()){
            System.out.println(figuraGeometrica.getArea());
            if(figuraGeometrica.soyCirculo()){
                Circulo circulo = (Circulo) figuraGeometrica;
                System.out.println( circulo.getRadio() );
            }
        }
    }
}
