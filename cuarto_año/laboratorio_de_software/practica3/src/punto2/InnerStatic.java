package punto2;

import java.util.Scanner;

import static java.lang.Math.pow;

public class InnerStatic {
    static double PI = 3.1416;

    static class Circulo{

        static double radio;

        static void setRadio(double radio){
            radio = radio;
        }

        static double getArea(){
            double a = PI * pow(radio, 2);
            System.out.println("El area es: " + a);
            return a;
        }

        static double getLongitudCircunsferencia(){
            double l = 2 * PI * radio;
            System.out.println("La longitud es: " + l);
            return l;
        }

        static Boolean radioEstaDefinido(){
            return radio != 0.0;
        }
    }

    static void setRadio(double radio) {
        Circulo.radio = radio;
    }

    static double getArea(){
        if(!Circulo.radioEstaDefinido()){
            leerRadio();
        }
        return Circulo.getArea();
    }

    static double getLongitudCircunsferencia(){
        if(!Circulo.radioEstaDefinido()){
            leerRadio();
        }
        return Circulo.getLongitudCircunsferencia();
    }

    static void leerRadio(){
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese el radio del circulo");
        setRadio(input.nextInt());
    }

}
