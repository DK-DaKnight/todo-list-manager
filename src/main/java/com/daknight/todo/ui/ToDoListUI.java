package com.daknight.todo.ui;

import com.daknight.todo.applogic.ui.file.FileHandler;
import com.daknight.todo.ui.menu.MenuBuilder;
import com.daknight.todo.ui.preferences.FontConfig;
import com.daknight.todo.ui.preferences.Preferences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ToDoListUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ToDo Manager");

        BorderPane root = new BorderPane();
        TabPane tabPane = new TabPane();
        Preferences preferences = new Preferences();

        FileHandler fileHandler = new FileHandler(primaryStage, tabPane);

        Label testLabel = new Label("Hallo Welt");

        root.setTop(MenuBuilder.build(primaryStage::close, fileHandler));
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("ToDo Manager");
        primaryStage.show();
    }
}
