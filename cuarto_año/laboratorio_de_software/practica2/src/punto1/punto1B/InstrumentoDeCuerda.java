package punto1.punto1B;

public class InstrumentoDeCuerda implements InstrumentoMusical{

    public void hacerSonar(){
        System.out.println("Sonar Cuerdas");
    }

    public String queEs() {
        return "Instrumento de Cuerda";
    }

    public void afinar(){
        System.out.println("afinando");
    }
}
