package punto3;

import java.util.ArrayList;
import java.util.Iterator;

public class TestHashSetAgregados {

    public static void main(String[] args){
        HashSetAgregados<String> hsa = new HashSetAgregados<>();
        hsa.add("String 1");
        hsa.add("String 2");
        hsa.add("String 3");
        System.out.println("CantidadTotalAgregados: " + hsa.getCantidadAgregados());

        ArrayList<String> c = new ArrayList<>();
        c.add("Lalalalala1");
        c.add("Lalalalala2");
        c.add("Lalalalala3");
        System.out.println("C.size() " + c.size());
        hsa.addAll(c);
        System.out.println("CantidadTotalAgregados: " + hsa.getCantidadAgregados());

        Iterator it = hsa.iterator();
        while (it.hasNext()){
            System.out.println("Value: " + it.next() + " ");
        }
    }

}
