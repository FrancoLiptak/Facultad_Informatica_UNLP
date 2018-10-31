package punto1;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class App {
    private JPanel mainPanel;
    private JLabel imgLabel;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("App");
        App app = new App();
        frame.setContentPane(app.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        while (true) {
            TimeUnit.SECONDS.sleep(1);
            app.imgLabel.setVisible(!app.imgLabel.isVisible());
        }

    }
}
