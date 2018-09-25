package punto1;

public class StackTest {

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push("Hola");
        stack.push("chau");

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        stack.iterator();

        Stack.StackIterator stackIterator = new Stack.StackIterator();
    }

}
