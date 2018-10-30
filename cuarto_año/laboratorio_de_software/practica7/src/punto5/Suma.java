package punto5;

public class Suma {
    public static void main(String[] args){
        int suma = 0;
        try{
            for(int i=0;i<args.length;i++)
                suma+= Integer.parseInt(args[i]);
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.print("La suma es:"+suma);
        }
    }

    // En el inciso A no fue necesario capturar la excepción porque se ingresaron solo números
}
