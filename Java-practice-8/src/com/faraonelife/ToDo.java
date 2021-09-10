package com.faraonelife;

import com.faraonelife.Model.ToDoStore;
import com.faraonelife.Model.ToDoitem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class ToDo {
    @FXML
    public TextArea txtDesc;
    @FXML
    public Label lblDeadline;
    @FXML
    public ListView lstTidoItems;
    public BorderPane BrdrPn;

    @FXML
     public void initialize(){
lstTidoItems.setItems(ToDoStore.getInstance().getTodoitems());
lstTidoItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
handleClickView();
    }
});
    }

    public void handleClickView() {
        lstTidoItems.refresh();
        ToDoitem selectedItem = (ToDoitem) lstTidoItems.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtDesc.setText(selectedItem.getDetails());
            lblDeadline.setText(selectedItem.getDate().toString());
        }
    }
    @FXML
    public void deleteItem(ToDoitem item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Delete To Item");
        alert.setHeaderText("Delete Item: " + item.getShortdes());
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ToDoStore.getInstance().delete(item);
        }
    }
    @FXML
    public void handleDeleteItem(ActionEvent actionEvent) {
        ToDoitem item = (ToDoitem) lstTidoItems.getSelectionModel().getSelectedItem();
        deleteItem(item);
    }
    @FXML
    public void handleNewItemClick() throws IOException {
        Dialog<ButtonType>dialog=new Dialog<>();
        dialog.initOwner(BrdrPn.getScene().getWindow());
        dialog.setTitle("Add new item");
        FXMLLoader fxmlLoader=new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("ToDoDialog.fxml"));

    try {
        dialog.getDialogPane().setContent(fxmlLoader.load());
    }catch (IOException exception){
        System.out.println(exception.getMessage());
    }

    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType>result=dialog.showAndWait();
        if(result.isPresent()&& result.get()==ButtonType.OK){
         ToDoDialogCont cont=fxmlLoader.getController();
            ToDoitem newItem= cont.getItem();


            ToDoStore.getInstance().addToDoItem(newItem);
            lstTidoItems.getSelectionModel().select(newItem);

        }
    }
    @FXML
    public void showEditItemDialog() throws IOException {
        ToDoitem item = (ToDoitem) lstTidoItems.getSelectionModel().getSelectedItem();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(BrdrPn.getScene().getWindow());
        dialog.setTitle("Edit To Do Item");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ToDoDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ToDoDialogCont controller = fxmlLoader.getController();
        controller.setItem(item);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get()==ButtonType.OK) {
            item.load(controller.getItem());
            handleClickView();
            ToDoStore.getInstance().storeToDoItems();
        }
    }
    @FXML
    public void handleClose() {
        Platform.exit();
    }

}
