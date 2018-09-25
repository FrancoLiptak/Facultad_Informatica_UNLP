package punto3;

import java.util.Collection;

public class HashSetEliminados extends AbstractHashSet {
    private int cantidadEliminados = 0;

    public HashSetEliminados() {
        super();
    }

    public HashSetEliminados(Collection c) {
        super(c);
    }

    @Override
    public boolean remove(Object o){
        cantidadEliminados++;
        return super.add(o);
    }

    @Override
    public boolean removeAll(Collection c){
        return super.addAll(c);
    }

    public int getCantidadEliminados() {
        return cantidadEliminados;
    }
}
