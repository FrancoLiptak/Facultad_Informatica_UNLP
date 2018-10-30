package punto1;

import java.util.TreeSet;

public class Test {

    public static void main(String[] args){
        Materia materia = new Materia("Labo");
        materia.setAlumnos(new TreeSet<>(Alumno.ComparatorNyA.INSTANCE));
        materia.agregaAlumno(new Alumno("1", "Fran", "Liptak", 39876543));
        materia.agregaAlumno(new Alumno("2", "Agus", "Pepe", 87654321));

        for(Alumno a : materia.getAlumnos()){
            System.out.println(a.getNombre());
        }
    }
}
