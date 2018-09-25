package punto3;

import java.util.Collection;

public class HashSetAgregadosV2<E> extends AbstractHashSet{
    private int cantidadAgregados = 0;

    public HashSetAgregadosV2() {
        super();
    }

    public HashSetAgregadosV2(Collection c) {
        super(c);
    }

    @Override
    public boolean add(Object o){
        cantidadAgregados++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c){
        return super.addAll(c);
    }

    public int getCantidadAgregados​() {
        return cantidadAgregados;
    }

}
