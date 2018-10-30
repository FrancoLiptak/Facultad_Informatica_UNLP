package punto3;

import java.util.ArrayList;
import java.util.List;

public class CreadorQGramas {

    public static String ponerLimites(String palabra){
        return "#" + palabra + "$";
    }

    public static List<String> crearQGramasPara(String palabra, Integer q) {
        String palabraConLimites = ponerLimites(palabra);
        List<String> qGramas = new ArrayList<>();
        for (int i = 0; i < (palabraConLimites.length() - q - 1); i++) {
            qGramas.add(palabraConLimites.substring(i, i+q));
        }
        return qGramas;
    }
}
