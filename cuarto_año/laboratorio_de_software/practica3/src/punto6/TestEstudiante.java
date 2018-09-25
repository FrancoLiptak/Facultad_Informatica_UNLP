package punto6;

import java.util.Arrays;
import java.util.Comparator;

public class TestEstudiante {

    public static void main(String[] args){
        Estudiante[] estudiantes = {
                new Estudiante("Franco", "Liptak", 22, "12407/4", 30),
                new Estudiante("Franco", "Borrelli", 22, "1117/2", 29),
                new Estudiante("Pedro", "Brost", 22, "11111/2", 28)
        };

        // compare returns a negative integer, zero, or a positive integer
        // as the first argument is less than, equal to, or greater than the second.

        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return o1.getMateriasAprobadas() - o2.getMateriasAprobadas();
            }
        });

        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.toString());
        }

        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return o2.getEdad() - o1.getEdad();
            }
        });

        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.toString());
        }

        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return o1.getLegajo().compareTo(o2.getLegajo());
            }
        });

        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.toString());
        }

        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                int nameCmp = o2.getNombre().compareTo(o1.getNombre());
                if (nameCmp != 0) {
                    return nameCmp;
                }
                return o2.getApellido().compareTo(o1.getApellido());
            }
        });

        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.toString());
        }

    }
}
