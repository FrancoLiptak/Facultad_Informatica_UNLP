public class TestCharly {

    public static void main(String[] args) {
        Nota[] notas = {Nota.DO, Nota.FA, Nota.LA, Nota.MI, Nota.RE, Nota.SI, Nota.SOL};
        int[] duraciones = {1, 2, 3, 4, 5, 6, 7};
        CharlySingleton.INSTANCE.tocarCancion(notas, duraciones);
    }

}
