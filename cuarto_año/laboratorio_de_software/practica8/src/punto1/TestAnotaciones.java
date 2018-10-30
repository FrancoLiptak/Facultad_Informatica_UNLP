package punto1;

public class TestAnotaciones {

    public static void main(String arg[]) throws Exception {
        new TestAnotaciones().testearYa();
    }

    @SuppressWarnings({"deprecation"})
    public void testearYa(){
        TestDeprecated t2 = new TestDeprecated();
        t2.hacer();
    }

    /**
     * A) Cuando se ejecuta 'TestAnotaciones' se imprime: "Testeando: 'Deprecated'".
     *
     * B) Si se elimina '@SuppressWarnings({"deprecation"})', el IDE nos indica que "hacer()" es un método deprecado.
     * Mas allá de eso, la ejecución produce el mismo resultado que en A).
     *
     * C) Si anotamos el método "testearYa()" podemos usar métodos deprecados sin warning solo en este scope.
     * Si anotamos la clase entonces podemos usar métodos deprecados en todos los métodos de la clase.
     */

}
