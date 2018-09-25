package punto1;

public class TestEstudiante {
    public static void main(String[] args){
        Estudiante [] estudiantes = {
                new Estudiante("Liptak", "Fran", "12345/1"),
                new Estudiante("Onofri", "Cami", "12345/2"),
                new Estudiante("Raimondi", "Seba", "12345/3"),
                new Estudiante("Rios", "Gasti", "12345/4"),
                new Estudiante("Borrelli", "Borre", "12345/5")
        };

        for(Estudiante unEstudiante : estudiantes){
            System.out.println(unEstudiante.toString());
        }

        Estudiante otroEstudiante = new Estudiante("Brost", "P", "12345/6");

        System.out.println(otroEstudiante.equals(estudiantes[2]));
    }
}
