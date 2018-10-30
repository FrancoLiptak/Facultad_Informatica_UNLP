package punto2;

public class Test2 {
    public int unMetodo(){
        try {
            System.out.println("Va a retornar 1");
            return 1;
        } finally {
            System.out.println("Va a retornar 2"); // Unreachable statement
            return 2;
        }
    }
    public static void main(String[] args) {
        Test2 res = new Test2();
        System.out.println(res.unMetodo());
    }
}
