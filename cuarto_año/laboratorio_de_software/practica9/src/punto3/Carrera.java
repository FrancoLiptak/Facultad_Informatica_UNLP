package punto3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Carrera {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private List<JTextField> textFields;
    private JPanel mainPanel;
    private JButton startButton;
    private List<Corredor> corredores;
    private Integer longitudCarrera = 100;
    private ExecutorService exec = Executors.newFixedThreadPool(5);

    public Carrera() {
        initializeCorredores();
        prepararCarrera();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarCarrera();
            }
        });
    }

    public void arrancarCarrera(){
        for(int i = 0; i < corredores.size(); i++){
            exec.execute(corredores.get(i));
        }
        exec.shutdown();

    }

    public void prepararCarrera(){
        textFields = new ArrayList<>();
        textFields.add(textField1);
        textFields.add(textField2);
        textFields.add(textField3);
        textFields.add(textField4);
        textFields.add(textField5);

        for(int i = 0; i<5; i++){
            corredores.get(i).setJ(textFields.get(i));
            textFields.get(i).setText(corredores.get(i).getNombre());
        }
    }

    public void initializeCorredores(){
        corredores = new ArrayList<>();
        for(int i = 1; i<6; i++ ){
            corredores.add(new Corredor("Corredor-"+i, longitudCarrera));
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        Carrera app = new Carrera();
        frame.setContentPane(app.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
