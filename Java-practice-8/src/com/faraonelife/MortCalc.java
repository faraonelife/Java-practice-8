package com.faraonelife;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MortCalc {
    @FXML
     private TextField txtCredit;
    @FXML
    private TextField txtPayment;
    @FXML
    private Slider Rate;
    @FXML
    private Slider slrDuration;
    @FXML
    private Label lblMnth;



    @FXML
 public void onOperate(){
   double crdt=Double.parseDouble(txtCredit.getText());
        double pymnt=Double.parseDouble(txtPayment.getText());
        double rate=Rate.getValue()/100;
        double dur=slrDuration.getValue();
        double rateMnth= rate/12;
        double durMnth=dur*12;

        double mnthPay=(crdt-pymnt)*rateMnth*Math.pow(1+rateMnth,durMnth)/(Math.pow(1+rateMnth,durMnth)-1);
        lblMnth.setText(String.format("%.f",mnthPay));

 }
}
