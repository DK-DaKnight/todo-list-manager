package com.daknight.todo.ui;

import com.daknight.todo.ui.menu.MenuBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ToDoListUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ToDo Manager");

        BorderPane root = new BorderPane();

        root.setTop(MenuBuilder.build(primaryStage::close));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("ToDo Manager");
        primaryStage.show();
    }
}
