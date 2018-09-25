public abstract class InstrumentoMusicalImpl implements InstrumentoMusical {

    public abstract String queEs();

    @Override
    public void afinar() {
        System.out.println("Afinando");
    }

    @Override
    public void afinar(FrecuenciaDeLA frecuenciaDeLA) {
        System.out.println("Afinando con frecuencia: " + frecuenciaDeLA.name());
    }

    @Override
    public void hacerSonar() {
        System.out.println("Sonando");
    }

    @Override
    public void hacerSonar(Nota n, Integer duracion) {
        System.out.println("Sonando nota: " + n + " con duracion: " + duracion);
    }

}
