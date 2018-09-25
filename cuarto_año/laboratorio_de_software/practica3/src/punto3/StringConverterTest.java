package punto3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringConverterTest {
    public static void main(String[] args) {
        StringConverterSet sc = new StringConverterSet();
        List a1 = new ArrayList();
        List a2 = new ArrayList();
        a1.add(1);
        a1.add(2);
        a2.add(3);
        a2.add(4);
        sc.add(a1);
        sc.add(a2);
        Iterator i = sc.iterator();

        while (i.hasNext()) System.out.println(i.next());
    }
}
