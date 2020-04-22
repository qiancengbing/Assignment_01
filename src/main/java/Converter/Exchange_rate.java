package Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.io.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import  Converter.*;

public class Exchange_rate extends JFrame{

         //extends AFrame class


    JTextField in3 = new JTextField("RATE",10);
    JButton btn = new JButton("STORE");  //set button
    JLabel out = new JLabel("RESULT");      //set label
    FileOperator fileOperator = new FileOperator();
    JComboBox comboBox=new JComboBox();
    JComboBox comboBox2 =new JComboBox();
    JButton btnn = new JButton("Go Exchange!");
    DecimalFormat df = new DecimalFormat("#0.000000");

    public Exchange_rate() {
        setLayout(new FlowLayout());//set the layout
        this.setTitle("Changing Rate");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel label = new JLabel("EXCHANGE RATE");
        contentPane.add(label);


        comboBox.addItem("USD($)");
        comboBox.addItem("AUD($)");
        comboBox.addItem("CNY(¥)");
        comboBox.addItem("GBP(￡)");
        comboBox.addItem("EUR(€)");
        comboBox.addItem("CAD($)");
        comboBox.addItem("SGD($)");
        comboBox.addItem("JPY(￥)");
        contentPane.add(comboBox);


        comboBox2.addItem("USD($)");
        comboBox2.addItem("AUD($)");
        comboBox2.addItem("CNY(¥)");
        comboBox2.addItem("GBP(￡)");
        comboBox2.addItem("EUR(€)");
        comboBox2.addItem("CAD($)");
        comboBox2.addItem("SGD($)");
        comboBox2.addItem("JPY(￥)");
        contentPane.add(comboBox2);


        getContentPane().add(in3);

        getContentPane().add(btn);
        contentPane.add(btnn);
         getContentPane().add(out);
        btn.addActionListener(new BtnActionAdapter());  //new a action listener
        btnn.addActionListener(new rate_BtnActionAdapter());
        setSize(500, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //exit
        setVisible(true);  //显示界面

    }
    class BtnActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String str1 = (String) comboBox.getSelectedItem();
            String str2 = (String) comboBox2.getSelectedItem();
            File file2 = new File("CCR.txt");


            BufferedReader reader = null;
            char[] tempchars2 = new char[6]; //read 3 chars every time for each line
            String string;  //store currency
            int count = 0;  //judge whether it is the second currency
            try{
                Double.parseDouble(in3.getText());
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "rate must be number.", null, JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comboBox.getSelectedItem().equals(comboBox2.getSelectedItem()) | Double.parseDouble(in3.getText()) <= 0.0) {
                out.setText("Not Allowed");
            }
            else{
                fileOperator.saveRate((String)comboBox.getSelectedItem(), (String)comboBox2.getSelectedItem(), df.format(Double.parseDouble(in3.getText())));
                fileOperator.saveRate((String)comboBox2.getSelectedItem(), (String)comboBox.getSelectedItem(), df.format(1/Double.parseDouble(in3.getText())));



                out.setText("Seccess");
                in3.setText("");
            }

            }


        }


    class rate_BtnActionAdapter implements ActionListener {      //set another action adapter
        public void actionPerformed(ActionEvent e) {    // try go the another text field which can change the exchange rate.

            EXChangeUI exChangeUI = new EXChangeUI();
            dispose();

        }
    }
}