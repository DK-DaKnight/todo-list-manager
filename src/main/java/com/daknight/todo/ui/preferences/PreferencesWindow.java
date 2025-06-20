package com.daknight.todo.ui.preferences;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PreferencesWindow {
    private final Preferences preferences;

    public PreferencesWindow(Preferences preferences) {
        this.preferences = preferences;
    }

    public void show() {
        Stage preferencesStage = new Stage();
        preferencesStage.setTitle("Preferences");
        preferencesStage.initModality(Modality.APPLICATION_MODAL);

        ListView<String> categoryList = new ListView<>();
        categoryList.getItems().addAll("User Interface", "Editor Settings");
        categoryList.setPrefWidth(150);

        VBox contentArea = new VBox(10);
        contentArea.setPadding(new Insets(20));

        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(100);
        saveButton.setPrefHeight(25);

        Label themeLabel = new Label("Theme");
        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("Light", "Dark");
        themeBox.setValue(preferences.getTheme());
        themeBox.setPrefWidth(800);
        themeBox.setPrefHeight(30);

        Label langLabel = new Label("Language");
        ComboBox<String> langBox = new ComboBox<>();
        langBox.getItems().addAll("en", "de", "it");
        langBox.setValue(preferences.getLang());
        langBox.setPrefWidth(800);
        langBox.setPrefHeight(30);

        Label fontLabel = new Label("Font");
        ComboBox<String> fontBox = new ComboBox<>();
        fontBox.getItems().addAll("Font1", "Font2", "Font3");
        fontBox.setValue(preferences.getFont());
        fontBox.setPrefWidth(800);
        fontBox.setPrefHeight(30);

        Label fontSizeLabel = new Label("Font Size");
        ComboBox<Integer> fontSizeBox = new ComboBox<>();
        for (int i = 1; i <= 80; i++) {
            fontSizeBox.getItems().add(i);
        }
        fontSizeBox.setValue(preferences.getFontSize());
        fontSizeBox.setPrefWidth(800);
        fontSizeBox.setPrefHeight(30);

        categoryList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            contentArea.getChildren().clear();

            if ("User Interface".equals(newVal)) {
                contentArea.getChildren().addAll(themeLabel, themeBox, langLabel, langBox);
            } else if ("Editor Settings".equals(newVal)) {
                contentArea.getChildren().addAll(fontLabel, fontBox, fontSizeLabel, fontSizeBox);
            }
        });

        categoryList.getSelectionModel().selectFirst();

        saveButton.setOnAction(e -> {
            preferences.setTheme(themeBox.getValue());
            preferences.setLang(langBox.getValue());
            preferences.setFont(fontBox.getValue());
            preferences.setFontSize(fontSizeBox.getValue());

            preferencesStage.close();
        });

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(categoryList);
        mainLayout.setCenter(contentArea);
        mainLayout.setBottom(new HBox(saveButton));

        BorderPane.setMargin(saveButton, new Insets(10));
        HBox bottomBox = (HBox) mainLayout.getBottom();
        bottomBox.setPadding(new Insets(0, 20, 10, 20));
        bottomBox.setSpacing(10);
        bottomBox.setStyle("-fx-alignment: center-right;");

        Scene scene = new Scene(mainLayout, 800, 400);
        preferencesStage.setScene(scene);
        preferencesStage.showAndWait();
    }
}
