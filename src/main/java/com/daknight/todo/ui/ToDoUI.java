package com.daknight.todo.ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ToDoUI extends BorderPane {
    public ToDoUI(String content) {
        TextArea textArea = new TextArea(content);
        textArea.setWrapText(true);
        this.setCenter(textArea);
    }
}
