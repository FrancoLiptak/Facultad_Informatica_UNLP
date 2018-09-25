public class TestGuirarra {

    public static void main(String[] args){
        Guitarra guitarra = new Guitarra();

        System.out.println(guitarra.queEs());

        guitarra.afinar();

        guitarra.afinar(FrecuenciaDeLA.F440);
        guitarra.afinar(FrecuenciaDeLA.F444);
        guitarra.afinar(FrecuenciaDeLA.F446);
        guitarra.afinar(FrecuenciaDeLA.F480);

        guitarra.hacerSonar();

        guitarra.hacerSonar(Nota.DO, 1);
        guitarra.hacerSonar(Nota.RE, 2);
        guitarra.hacerSonar(Nota.MI, 3);
        guitarra.hacerSonar(Nota.FA, 4);
        guitarra.hacerSonar(Nota.SOL, 5);
        guitarra.hacerSonar(Nota.LA, 6);
        guitarra.hacerSonar(Nota.SI, 7);
    }
}
