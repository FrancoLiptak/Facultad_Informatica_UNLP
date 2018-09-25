package punto1;

import java.util.ArrayList;
import java.util.Iterator;

public class Stack {
    private ArrayList items;
    private StackIterator stackIterator;

    public static class StackIterator{
        public static Iterator iterator(ArrayList a){
            return a.iterator();
        }
    }

    public Stack() {
        items = new ArrayList();
    }

    public void push(Object o){
        items.add(items.size(), o);
    }

    public Object pop(){
        if(items.size() == 0)
            throw new IllegalStateException("Stack is empty");
        return items.remove(items.size() - 1);
    }

    public Boolean isEmpty(){
        return items.isEmpty();
    }

    public void finalize(){

    }

    public StackIterator getStackIterator() {
        if(stackIterator == null){ stackIterator = new StackIterator(); }
        return stackIterator;
    }

    public Iterator iterator(){
        return stackIterator.iterator(items);
    }

}
