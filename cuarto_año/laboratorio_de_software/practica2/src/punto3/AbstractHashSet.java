package punto3;

import java.util.*;

public class AbstractHashSet<E> implements Set {
    private HashSet hashSet;

    public AbstractHashSet() {
    }

    public AbstractHashSet(Collection c) {
        this.hashSet = new HashSet<>(c);
    }

    @Override
    public int size(){
        return hashSet.size();
    }

    @Override
    public boolean isEmpty(){
        return hashSet.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        return hashSet.contains(o);
    }

    @Override
    public Iterator iterator(){
        return hashSet.iterator();
    }

    @Override
    public Object[] toArray(){
        return hashSet.toArray();
    }

    @Override
    public Object[] toArray(Object[] a){
        return hashSet.toArray(a);
    }

    @Override
    public boolean add(Object o){
        return hashSet.add(o);
    }

    @Override
    public boolean remove(Object o){
        return hashSet.remove(o);
    }

    @Override
    public boolean containsAll(Collection c){
        return hashSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection c){
        return hashSet.addAll(c);
    }

    @Override
    public boolean retainAll(Collection c){
        return hashSet.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c){
        return hashSet.removeAll(c);
    }

    @Override
    public void clear(){
        hashSet.clear();
    }
}
