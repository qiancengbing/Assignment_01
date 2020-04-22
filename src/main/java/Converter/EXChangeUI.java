package Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Exchanger;

import Converter.*;


public class EXChangeUI extends JFrame{    //Graph input and output

    //extends继承AFrame类
    JTextField in_1 = new JTextField(10);  //text field length in 10
    JTextField in_2 = new JTextField(10);
    JTextField in_3 = new JTextField(10);
    //JTextField in_4 = new JTextField(10);
    //JTextField in_5 = new JTextField(10);
    JComboBox comboBox=new JComboBox();
    JComboBox comboBox2 =new JComboBox();
    JButton result_btn = new JButton("EXCHANGE! ");  //button
    JButton rate_btn = new JButton("change the rate");
    JLabel out_1 = new JLabel(" = ");      //set some labels
    JLabel out_2 = new JLabel(" = ");
    JLabel out_3 = new JLabel(" = ");
    JLabel jLabel_1 = new JLabel("Currency: ");
    JLabel jLabel_2 = new JLabel("Desired Currency: ");
    JLabel jLabel_3 = new JLabel("Amount 1: ");
    JLabel jLabel_4 = new JLabel("Amount 2: ");
    JLabel jLabel_5 = new JLabel("Amount 3: ");
    JLabel jLabel_6 = new JLabel("");
    JLabel jLabel_7 = new JLabel("");
    JLabel jLabel_8 = new JLabel("");

    public EXChangeUI() {
        //setLayout(new FlowLayout());
        setLayout(new GridLayout(10,2));   //set the format and we set it to grid layout
        getContentPane().add(jLabel_1);
        getContentPane().add(jLabel_2);
        //getContentPane().add(in_4);
        //getContentPane().add(in_5);
        comboBox.addItem("USD($)");
        comboBox.addItem("AUD($)");
        comboBox.addItem("CNY(¥)");
        comboBox.addItem("GBP(￡)");
        comboBox.addItem("EUR(€)");
        comboBox.addItem("CAD($)");
        comboBox.addItem("SGD($)");
        comboBox.addItem("JPY(￥)");
        getContentPane().add(comboBox);


        comboBox2.addItem("USD($)");
        comboBox2.addItem("AUD($)");
        comboBox2.addItem("CNY(¥)");
        comboBox2.addItem("GBP(￡)");
        comboBox2.addItem("EUR(€)");
        comboBox2.addItem("CAD($)");
        comboBox2.addItem("SGD($)");
        comboBox2.addItem("JPY(￥)");
        getContentPane().add(comboBox2);


        getContentPane().add(jLabel_3);
        getContentPane().add(jLabel_6);
        getContentPane().add(in_1);
        getContentPane().add(out_1);
        getContentPane().add(jLabel_4);
        getContentPane().add(jLabel_7);
        getContentPane().add(in_2);
        getContentPane().add(out_2);
        getContentPane().add(jLabel_5);
        getContentPane().add(jLabel_8);
        getContentPane().add(in_3);
        getContentPane().add(out_3);
        getContentPane().add(result_btn);
        getContentPane().add(rate_btn);
        result_btn.addActionListener(new result_BtnActionAdapter());  //new a action listener
        rate_btn.addActionListener(new rate_BtnActionAdapter());
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //exit
        setVisible(true);  //display

    }


    class result_BtnActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(comboBox.getSelectedItem().equals(comboBox2.getSelectedItem())){
                JOptionPane.showMessageDialog(null, "Please select different currencies", null, JOptionPane.ERROR_MESSAGE);
                return;
            }

            String s1 = "0";
            String s2 = in_2.getText();
            String s3 = in_3.getText();
            String s4 = (String)comboBox.getSelectedItem();
            String s5 = (String)comboBox2.getSelectedItem();    //get the content of the text field
            FileOperator fileOperator = new FileOperator();

            if (!in_1.getText().equals("")){
                s1 = in_1.getText();
            }
            if (s2.equals("")){
                s2 = "0";
            }
            if (s3.equals("")){
                s3 = "0";
            } //consider the special case, which the input is null, we tried two ways to avoid it.
            try{
                double d1 = Double.parseDouble(s1);
                double d2 = Double.parseDouble(s2);
                double d3 = Double.parseDouble(s3);
                if (d1 < 0.0 || d2 < 0.0 || d3 < 0.0){
                    JOptionPane.showMessageDialog(null, "item must not less than 0.", null, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double rate = fileOperator.getRate(s4, s5);
                if (rate == 0.0){
                    JOptionPane.showMessageDialog(null, "No such rate, please go to change the rate.", null, JOptionPane.ERROR_MESSAGE);
                }
                else{
                    // double d4 = String.parseString(s4);
                    // double d5 = String.parseString(s5);       //change them to double number.
                    Calculator caculator = new Calculator();
                    double sq1 = caculator.getResult(d1, rate);
                    double sq2 = caculator.getResult(d2, rate);
                    double sq3 = caculator.getResult(d3, rate);;
                    out_1.setText( " = " + sq1);
                    out_2.setText( " = " + sq2);
                    out_3.setText( " = " + sq3);   //display the result
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "rate must be number.", null, JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

    }
    class rate_BtnActionAdapter implements ActionListener {      //set another action adapter
        public void actionPerformed(ActionEvent e) {    // try go the another text field which can change the exchange rate.
            // String s = in.getText();
            // double d = Double.parseDouble(s);
            // double sq = d * d;
            // out.setText( d + "result: " + sq);
            Exchange_rate exchange_rate = new Exchange_rate();

            dispose();
        }

    }
}
