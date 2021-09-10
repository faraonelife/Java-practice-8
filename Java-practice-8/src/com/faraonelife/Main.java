package com.faraonelife;

import com.faraonelife.Model.ToDoStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TipCalc.fxml"));
        primaryStage.setTitle("Tip Party Calculator");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        Parent root1 = FXMLLoader.load(getClass().getResource("MortCalc.fxml"));
        primaryStage.setTitle("Mortgage Calculator");
        primaryStage.setScene(new Scene(root1, 300, 275));
        primaryStage.show();

        Parent root2 = FXMLLoader.load(getClass().getResource("Bmi.fxml"));
        primaryStage.setTitle("BMI Mass Calculator");
        primaryStage.setScene(new Scene(root2, 400, 275));
        primaryStage.show();

        Parent root3 = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
        primaryStage.setTitle("ToDo List");
        primaryStage.setScene(new Scene(root3, 800, 475));
        primaryStage.show();
    } @Override
    public void stop() throws Exception {
        try {
            ToDoStore.getInstance().storeToDoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            ToDoStore.getInstance().loadTodoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
