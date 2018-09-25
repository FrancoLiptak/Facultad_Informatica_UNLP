package punto5;

public class PoolConexiones {
    private static PoolConexiones instance;

    private PoolConexiones() {
    }

    public static PoolConexiones getInstance() {
        if(instance == null) { instance = new PoolConexiones(); }
        return instance;
    }
}
