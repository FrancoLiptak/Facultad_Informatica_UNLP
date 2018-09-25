public enum Nota {
    DO("C"),
    RE("D"),
    MI("E"),
    FA("F"),
    SOL("G"),
    LA("A"),
    SI("B");

    private String letraCifradoAmericano;

    Nota(String letraCifradoAmericano) {
        this.letraCifradoAmericano = letraCifradoAmericano;
    }

    public String getLetraCifradoAmericano() {
        return letraCifradoAmericano;
    }

}
