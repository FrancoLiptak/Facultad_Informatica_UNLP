package punto3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiccionarioQGramas {
    private Map<String, List<String>> map = new HashMap<>();
    private Integer q;

    public DiccionarioQGramas(Integer q) {
        this.q = q;
    }

    public void agregarPalabra(String palabra){
        map.put(palabra, CreadorQGramas.crearQGramasPara(palabra, q));
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }

    public Integer getQ() {
        return q;
    }

    public void setQ(Integer q) {
        this.q = q;
    }
}
