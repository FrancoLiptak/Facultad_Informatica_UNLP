package punto1.punto1B;

public class InstrumentoDeViento implements InstrumentoMusical {

    public void hacerSonar(){
        System.out.println("Sonar vientos");
    }

    public String queEs(){
        return "Instrumento de viento";
    }

    public void afinar(){
        System.out.println("afinando");
    }
}
