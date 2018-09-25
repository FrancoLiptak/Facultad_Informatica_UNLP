package punto3;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetAgregados<E> {
    private int cantidadAgregados;
    private HashSet<E> hs;

    public HashSetAgregados() {
        hs = new HashSet<E>();
    }

    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

    public boolean add(E e) {
        cantidadAgregados ++;
        return hs.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        cantidadAgregados += c.size();
        return hs.addAll(c);
    }

    public Iterator iterator() {
        return hs.iterator();
    }
}
