package com.faraonelife.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ToDoStore {
    private ObservableList<ToDoitem>todoitems;
    private DateTimeFormatter formater;

    private static ToDoStore instance=new ToDoStore();
    public  static ToDoStore getInstance(){
        return instance;
    }

private ToDoStore(){
    formater= DateTimeFormatter.ofPattern("dd-MM-yyyy");
    todoitems= FXCollections.observableArrayList();
    todoitems.add(new ToDoitem("Lecture 1 ","Create lecture 1", LocalDate.now().plusDays(5)));
    todoitems.add(new ToDoitem("Tutorial ","Create tut", LocalDate.now().plusDays(2)));
}
    private static String filename = "TodoListItems.txt";

    public ObservableList<ToDoitem> getTodoitems() {
        return todoitems;
    }

    public void setTodoitems(ObservableList<ToDoitem> todoitems) {
        this.todoitems = todoitems;
    }

    public void add(ToDoitem item) throws IOException {
        todoitems.add(item);}

        public void loadTodoItems() throws IOException {
            todoitems = FXCollections.observableArrayList();
            Path path = Paths.get(filename);

            BufferedReader br = Files.newBufferedReader(path);
            String input;

            try {
                while((input = br.readLine())!=null) {
                    String[] itemPieces = input.split("\t");
                    String shortDescription = itemPieces[0];
                    String details = itemPieces[1];
                    String dateString = itemPieces[2];

                    LocalDate date = LocalDate.parse(dateString, formater);
                    ToDoitem todoItem = new ToDoitem(shortDescription, details, date);
                    todoitems.add(todoItem);
                }
            } finally {
                if (br != null) br.close();
            }
        }

    public void storeToDoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            for (ToDoitem item:todoitems) {
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortdes(),
                        item.getDetails(),
                        item.getDate().format(formater)));
                bw.newLine();
            }
        } finally {
            if (bw != null) bw.close();
        }
    }
    public void addToDoItem(ToDoitem item){
        todoitems.add(item);
    }
    public void delete(ToDoitem item){
        todoitems.remove(item);
    }
}
