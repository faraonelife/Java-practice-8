package com.faraonelife;

import com.faraonelife.Model.ToDoitem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ToDoDialogCont {
    @FXML
    public TextField txtShortDescription;
    @FXML
    public TextArea txtDetails;
    @FXML
    public DatePicker calDeadline;

    public ToDoitem getItem(){
        ToDoitem result=new ToDoitem(
                txtShortDescription.getText(),
                txtDetails.getText(),
                calDeadline.getValue()

                                     );
        return result;
    }

    public void setItem(ToDoitem item) {
        calDeadline.setValue(item.getDate());
        txtDetails.setText(item.getDetails());
        txtShortDescription.setText(item.getShortdes());
    }
}
