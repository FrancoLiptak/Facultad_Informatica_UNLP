package punto1;

class Excepcion1 extends Exception{}
class Excepcion2 extends Excepcion1{}

public class Test1 {
    public static void main(String[] args) {
        try {
            throw new Excepcion2();
        } catch(Excepcion2 e2) {
            System.out.println("Se capturó la Excepción2");
        } catch(Excepcion1 e1) {
            System.out.println("Se capturó la Excepción1");
        }
    }

    /**
    Como Excepcion2 hereda de Excepcion1, al tirar la Excepcion2 primero era atrapada
    por el catch de Excepcion1. Por eso se invierte el orden (primero se maneja
    Excepcion2)
     **/
}
