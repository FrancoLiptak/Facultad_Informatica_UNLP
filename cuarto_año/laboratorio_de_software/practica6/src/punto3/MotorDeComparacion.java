package punto3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MotorDeComparacion {

    public List<String> comparar(Map<String, List<String>> map, String palabra){
        List<String> qGramas = CreadorQGramas.crearQGramasPara(palabra, 2);
        List<String> resultado = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for(String qgrama : entry.getValue()){
                if(qGramas.contains(qgrama)){
                    resultado.add(entry.getKey());
                    break;
                }
            }
        }

        return resultado;
    }
}
