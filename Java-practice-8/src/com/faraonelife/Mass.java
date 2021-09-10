package com.faraonelife;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Mass {
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtHeight;
    @FXML
    private Label index;
    @FXML
    public void onMass()
    { double wght=Double.parseDouble(txtWeight.getText());
    double hght=Double.parseDouble(txtHeight.getText());
    double bmi=wght/Math.pow(hght/100,2);
    index.setText(String.format("%f.2",bmi));

    }
}
