/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Converter;

import Converter.*;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void test_getResult(){
        //Calculator ca = new Calculator();
        assertEquals(18.0,new Calculator().getResult(2.0,9.0));
        assertEquals(0,new Calculator().getResult(0,5.0));
        assertEquals ( 27.0, new Calculator().getResult(3.0,9.0) );
        assertEquals (36.0, new Calculator().getResult(3.0,12.0));
        assertEquals( 9.0, new Calculator().getResult( 1.0, 9.0 ));
    }
    @Test
    public void test_saveRate() {
        new FileOperator().saveRate("USD($)", "JPY(￥)", "110.0" );
        assertEquals(110.0, new FileOperator().getRate("USD($)", "JPY(￥)"));
        new FileOperator().saveRate("USD($)", "JPY(￥)", "112.8" );
        assertEquals(112.8, new FileOperator().getRate("USD($)", "JPY(￥)"));
        new FileOperator().saveRate("USD($)", "AUD($)", "4.8" );
        assertEquals(4.8, new FileOperator().getRate("USD($)", "AUD($)"));
        new FileOperator().saveRate("USD($)", "AUD($)", "2.3");
        assertEquals(2.3, new FileOperator().getRate("USD($)", "AUD($)"));
        new FileOperator().saveRate("CNY(¥)", "JPY(￥)", "19.9" );
        assertEquals(19.9, new FileOperator().getRate("CNY(¥)", "JPY(￥)"));
        new FileOperator().saveRate( "USD($)", "GBP(￡)","1.8");
        assertEquals(1.8, new FileOperator().getRate("USD($)", "GBP(￡)"));
        new FileOperator().saveRate( "USD($)", "GBP(￡)","1.7");
        assertEquals(1.7, new FileOperator().getRate("USD($)", "GBP(￡)"));
        new FileOperator().saveRate( "USD($)", "SGD($)","6.6");
        assertEquals(6.6, new FileOperator().getRate("USD($)", "SGD($)"));
        new FileOperator().saveRate( "USD($)", "SGD($)","6.1");
        assertEquals(6.1, new FileOperator().getRate("USD($)", "SGD($)"));

    }
    @Test
    public void test_getRate() {
        assertEquals(6.1, new FileOperator().getRate("USD($)", "SGD($)"));
        assertEquals(0.0, new FileOperator().getRate("USD($)", "AAA($)"));
        assertEquals(18.9, new FileOperator().getRate("CNY(¥)", "JPY(￥)"));
        assertEquals(2.3, new FileOperator().getRate("USD($)", "AUD($)"));
    }

    @Test
    public void test_EXChangeUI() {
        EXChangeUI exChangeUI = new EXChangeUI();
        assertEquals(8, exChangeUI.comboBox.getItemCount());
        assertEquals(8, exChangeUI.comboBox2.getItemCount());
        exChangeUI.comboBox.setSelectedIndex(2);
        exChangeUI.comboBox2.setSelectedIndex(7);
        exChangeUI.in_1.setText("10");
        exChangeUI.in_2.setText("30");
        exChangeUI.result_btn.doClick();
        assertEquals(" = 189.0", exChangeUI.out_1.getText());
        assertEquals(" = 567.0", exChangeUI.out_2.getText());
        assertEquals(" = 0.0", exChangeUI.out_3.getText());


    }

    @Test
    public void test_Exchange_rate() {
        DecimalFormat df = new DecimalFormat("#0.000000");

        Exchange_rate exchange_rate = new Exchange_rate();
        assertEquals(8, exchange_rate.comboBox.getItemCount());
        assertEquals(8, exchange_rate.comboBox2.getItemCount());
        exchange_rate.comboBox.setSelectedIndex(2);
        exchange_rate.comboBox2.setSelectedIndex(7);
        exchange_rate.in3.setText("19.9");
        exchange_rate.btn.doClick();
        assertEquals(19.9, new FileOperator().getRate("CNY(¥)", "JPY(￥)"));
        assertEquals(Double.parseDouble(df.format(1/19.9)), new FileOperator().getRate("JPY(￥)", "CNY(¥)"));

        assertEquals(8, exchange_rate.comboBox.getItemCount());
        assertEquals(8, exchange_rate.comboBox2.getItemCount());
        exchange_rate.comboBox.setSelectedIndex(2);
        exchange_rate.comboBox2.setSelectedIndex(7);
        exchange_rate.in3.setText("18.9");
        exchange_rate.btn.doClick();
        assertEquals(18.9, new FileOperator().getRate("CNY(¥)", "JPY(￥)"));
        assertEquals(Double.parseDouble(df.format(1/18.9)), new FileOperator().getRate("JPY(￥)", "CNY(¥)"));
    }


}