package punto2;

public class Test {

    public static void main(String[] args){
        // Veterinaria <Animal> vet = new Veterinaria <Gato>(); // NO FUNCIONA. DEL LADO DERECHO NO SE DEBERÍA ESPECIFICAR NADA, O <Animal>.

        // Veterinaria <Gato> vet = new Veterinaria <Animal>(); // TAMPOCO FUNCIONA.

        //Veterinaria<?> vet = new Veterinaria<Gato>();
        //vet.setAnimal(new Gato());                            // NO FUNCIONA.

        Veterinaria vet2 = new Veterinaria ();
        vet2.setAnimal(new Perro());

        //Veterinaria vet3 = new Veterinaria <?>();             // No se puede usar ? del lado derecho

        Veterinaria <? extends Animal> vet = new Veterinaria<Gato>();
    }
}
