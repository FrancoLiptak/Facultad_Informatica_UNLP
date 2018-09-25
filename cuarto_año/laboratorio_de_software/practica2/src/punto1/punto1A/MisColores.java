package punto1.punto1A;

public class MisColores implements ColImpresion, ColArcoIris {
    public MisColores() {

        // No funciona porque tanto punto1.punto1A.ColImpresion como punto1.punto1A.ColArcoIris tienen definido AMARILLO (el compilador no sabe a cuál se hace referencia).

        // int unColor= AMARILLO;
    }
}
