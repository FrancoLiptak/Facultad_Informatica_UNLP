public interface InstrumentoMusical {
    void hacerSonar();
    String queEs();
    void afinar();
    void hacerSonar(Nota n, Integer duracion);
    void afinar(FrecuenciaDeLA frecuenciaDeLA);
}
