package punto2;

import java.util.Arrays;

public class PaintTest {

    public static void main(String[] args){
        Paint paint = new Paint();
        paint.init();
        Arrays.sort(paint.getPaleta());

        for(FiguraGeometrica figuraGeometrica : paint.getPaleta()){
            System.out.println(figuraGeometrica.getArea());
            if(figuraGeometrica.soyCirculo()){
                Circulo circulo = (Circulo) figuraGeometrica;
                System.out.println( circulo.getRadio() );
            }
        }
    }
}
