package punto3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextFileToTable {

    private JPanel mainPanel;
    private JTable mainTable;
    private JButton cargarTablaButton;
    private JTextArea textArea1;

    public TextFileToTable() {
        String columns[] = {"1", "2", "3"};
        DefaultTableModel tableModel = new DefaultTableModel(0, 2);
        tableModel.setColumnIdentifiers(columns);
        mainTable.setModel(tableModel);
        cargarTablaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("/home/francoliptak/facultad/Facultad_Informatica_UNLP/cuarto_año/laboratorio_de_software/practica4/src/punto3/text.txt");
                if(file.exists() && file.canRead()) {
                    textArea1.setText("El archivo existe");
                }else{
                    textArea1.setText("El archivo no existe");
                }

                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    Object[] tableLines = bufferedReader.lines().toArray();
                    for(int i = 0; i < tableLines.length; i++){
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split(":");
                        if(dataRow.length > 3) textArea1.setText("Demasiada informacion");
                        tableModel.insertRow(i, dataRow);
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TextFileToTable");
        frame.setContentPane(new TextFileToTable().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
