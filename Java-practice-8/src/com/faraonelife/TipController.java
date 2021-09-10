package com.faraonelife;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipController {
    private int currentPercentage = 10;

    @FXML
    private TextField txtAmount;
    @FXML
    private Label lblPercentage;
    @FXML
    private Slider slrPercentage;
    @FXML
    private TextField txtTip;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtNumPeople;
    @FXML
    private TextField txtEachPays;

    @FXML
    public void initialize(){
        slrPercentage.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                handleSlider();
            }
        });
    }

    @FXML
    public void handleSlider(){
        this.currentPercentage = (int)slrPercentage.getValue();
        lblPercentage.setText(String.format("%d%%", this.currentPercentage));
    }

    @FXML
    public void handleCalculateTip(){
        double amount = Double.parseDouble(txtAmount.getText());
        double tip = (this.currentPercentage / 100.0) * amount;
        double total = amount + tip;
        int    numOfPeople = Integer.parseInt(txtNumPeople.getText());
        double eachPays = total / numOfPeople;

        txtTip.setText(""+tip);
        txtTotal.setText(""+total);
        txtEachPays.setText(""+eachPays);
    }
}
