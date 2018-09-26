package punto2;

import com.sun.webkit.BackForwardList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListaColores {
    private JList listaColores;
    private JPanel mainPanel;
    private JButton agregarButton;
    private JButton borrarButton;
    private JTextField textField1;
    private DefaultListModel listModel;
    private List<String> colores;
    private List<String> coloresBase;

    public ListaColores() {
        //initializeListModel();
        initializeListModelWithImg();
        initializarColoresBase();
        colores = new ArrayList();
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeElement(listaColores.getSelectedValue());
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.add(listModel.getSize(), printImage(textField1.getText()));
            }
        });
    }

    public ImageIcon printImage(String color){
        if(!coloresBase.contains(color)){
            return new ImageIcon(getClass().getResource("img/pregunta.jpeg"));
        }else{
            return new ImageIcon(getClass().getResource("img/" + color));
        }
    }

    public void initializarColoresBase(){
        coloresBase = new ArrayList<>();
        coloresBase.add("amarillo.jpg");
        coloresBase.add("azul.jpeg");
        coloresBase.add("rojo.png");
    }

    public void initializeListModelWithImg(){
        listModel = new DefaultListModel();
        listModel.add(0, new ImageIcon(getClass().getResource("img/rojo.png")));
        listModel.add(1, new ImageIcon(getClass().getResource("img/amarillo.jpg")));
        listModel.add(2, new ImageIcon(getClass().getResource("img/azul.jpeg")));
        listaColores.setModel(listModel);
    }

    public void initializeListModel(){
        listModel = new DefaultListModel();
        listModel.addElement("Rojo");
        listModel.addElement("Azul");
        listModel.addElement("Amarillo");
        listaColores.setModel(listModel);
    }

    public JList getListaColores() {
        return listaColores;
    }

    public void setListaColores(JList listaColores) {
        this.listaColores = listaColores;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("ListaColores");
        frame.setContentPane(new ListaColores().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
