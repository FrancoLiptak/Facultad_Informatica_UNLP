package punto3;

public class Test {

    public static void main(String[] args){
        DiccionarioQGramas diccionarioQGramas = new DiccionarioQGramas(2);
        diccionarioQGramas.agregarPalabra("hola");
        diccionarioQGramas.agregarPalabra("horno");
        diccionarioQGramas.agregarPalabra("chau");

        MotorDeComparacion motorDeComparacion = new MotorDeComparacion();

        for(String s : motorDeComparacion.comparar(diccionarioQGramas.getMap(), "hormiga")){
            System.out.println(s);
        }
    }
}
