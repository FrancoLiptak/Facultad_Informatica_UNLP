package punto3;

@Servidor(direccion = "localhost", puerto = 8080, archivo = "prueba.txt")
public class ClaseCualquiera {

    @Invocar
    public static void invocado(){
        System.out.println("Invocado");
    }

    @Invocar
    public static void invocadoPorDios(){
        System.out.println("Aguante Riquelme");
    }
}
