package punto3;

import javax.swing.*;

public class Corredor implements Runnable {
    private String nombre;
    private Integer longitudCarrera;
    private Integer pasosDados = 0;
    private String recorridoActual = "";
    private JTextField j;

    public Corredor(String nombre, Integer longitudCarrera) {
        this.nombre = nombre;
        this.longitudCarrera = longitudCarrera;
    }

    public void setJ(JTextField j) {
        this.j = j;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run(){
        Monitor m = Monitor.INSTANCE;
        for(int i = pasosDados; i < longitudCarrera; i++){
            pasosDados++;
            recorridoActual += "-";
            m.permitirCorrer(j, nombre + recorridoActual);
        }
    }


}
