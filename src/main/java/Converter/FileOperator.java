package Converter;

import java.io.*;
import javax.swing.*;


public class FileOperator {
    private File file = new File("CCR.txt");

    public void saveRate(String currency_1, String currency_2, String rate) {
        if (currency_1.equals(currency_2)){
            JOptionPane.showMessageDialog(null, "No rate, please go to change the rate.", null, JOptionPane.ERROR_MESSAGE);
        }
        String rateLine = currency_1 + ' ' + currency_2 + ' ' + rate.toString() + '\r';
        BufferedReader reader = null;
        String temString;  //store currency
        String[] lines = new String[50]; //store lines read from previous txt file
        int lineNumber = 0; //to decide whether there is lines already
        int target = 0; //record whether there is the same pair already
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath())));
            while ((temString = reader.readLine()) != null){ //save all lines and record the target
                lines[lineNumber] = temString + '\r';
                lineNumber ++;

                if (temString.substring(0, 13).equals(rateLine.substring(0, 13))){
                    target = lineNumber;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        try {
            FileWriter writer = null;
            writer = new FileWriter(file);
            writer.write(""); //clear txt file in order to rewrite
            if (lineNumber == 0){ //if there is no line in txt file, just write
                writer = new FileWriter(file, true);
                writer.write(rateLine);
            }
            else { //if there are lines,  write lines and check target to update
                writer = new FileWriter(file, true);
                for(int i = 0; i < lineNumber; i++){
                    if (target == 0){ //no same pair exists
                        writer.write(rateLine);
                        target --;
                    }
                    if (i == target - 1){ //update
                        writer.write(rateLine);
                    }
                    else writer.write(lines[i]);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double getRate(String currency_1, String currency_2) {
        BufferedReader reader = null;
        char[] tempchars = new char[6]; //read 6 chars every time for each line
        String string;  //store currency
        int count = 0;  //judge whether it is the second currency
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath())));
            while (reader.read(tempchars) != -1) {
                string = new String(tempchars);
                if (count == 0) {
                    if (!currency_1.equals(string)) {   //not found, read next line
                        count = 0;
                        reader.readLine();
                        continue;
                    } else {
                        count = 1;
                        reader.read();  //consistent with currency_1, read space
                    }
                } else {
                    if (!currency_2.equals(string)) {   //not found, read next line,
                        // and set count to 0 indicating next char set is for currency_1
                        count = 0;
                        reader.readLine();
                        continue;
                    } else {    //find
                        reader.read();
                        Double rate = new Double(reader.readLine());
                        reader.close();
                        return rate;
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return 0.0;
    }

}
