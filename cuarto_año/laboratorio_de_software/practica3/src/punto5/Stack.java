package punto5;

import java.util.ArrayList;
import java.util.Iterator;

public class Stack {
    private ArrayList items;

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

    public Iterator iterator(){
        StackIterator stackIterator = new StackIterator() {
            @Override
            public Iterator iterator(ArrayList a) {
                return a.iterator();
            }
        };

        return stackIterator.iterator(items);
    }

}