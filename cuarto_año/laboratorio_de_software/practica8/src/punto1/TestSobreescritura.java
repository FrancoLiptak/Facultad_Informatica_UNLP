package punto1;

public class TestSobreescritura {

    @Override
    public String toString(){
        return super.toString() + "Testeando: 'Override'";
    }

    public static void main(String[] args){
        TestSobreescritura testSobreescritura = new TestSobreescritura();
        System.out.println(testSobreescritura.toString());
    }

    /**
     * Cuando se ejecuta el método toString lo que ocurre es que se ejecuta el toString de Object, el cual
     * imprime lo siguiente: 'nombrePaquete.nombreClase@hashcode' y a eso le agrega el string "Testeando: 'Override'"
     */
}
