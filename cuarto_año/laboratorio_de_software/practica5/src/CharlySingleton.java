public enum CharlySingleton {
    INSTANCE(new Piano());

    Piano piano;

    private CharlySingleton(Piano piano) {
        this.piano = piano;
    }

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }

    public void tocarCancion(Nota[] notas, int[] duraciones){
        for(int i = 0; ((i < notas.length) && (i < duraciones.length)); i++){
            piano.hacerSonar(notas[i], duraciones[i]);
        }
    }
}
