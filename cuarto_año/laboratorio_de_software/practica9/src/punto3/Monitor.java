package punto3;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public enum Monitor {
    INSTANCE;

    public static void permitirCorrer(JTextField j, String s){
        j.setText(s);
        try {
            TimeUnit.MILLISECONDS.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
