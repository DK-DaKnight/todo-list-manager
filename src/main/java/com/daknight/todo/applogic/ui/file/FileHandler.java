package com.daknight.todo.applogic.ui.file;

import com.daknight.todo.ui.ToDoUI;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class FileHandler {
    private final Stage primaryStage;
    private final TabPane tabPane;
    private final LinkedList<File> recentFiles = new LinkedList<>();
    private final int MAX_RECENT_FILES = 5;

    public FileHandler(Stage primaryStage, TabPane tabPane) {
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
    }

    public void openFileAndCreateTab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todo files", "*.todo"));
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            addRecentFile(file);
            try {
                String content = Files.readString(file.toPath());
                ToDoUI toDoUI = new ToDoUI(content);
                Tab tab = new Tab(file.getName(), toDoUI);
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createFileAndCreateTab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Create new ToDo file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ToDo files", "*.todo"));
        fileChooser.setInitialFileName("new_todolist.todo");
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            addRecentFile(file);
            try {
                Files.writeString(file.toPath(), "");
                ToDoUI toDoUI = new ToDoUI("  [\n" +
                        "  {\n" +
                        "    \"todo_id\": 1,\n" +
                        "    \"todo_title\": null,\n" +
                        "    \"todo_description\": null,\n" +
                        "    \"todo_deadline\": null,\n" +
                        "    \"todo_completed\": null\n" +
                        "  }\n" +
                        "]\n" +
                        "\n");
                Tab tab = new Tab(file.getName(), toDoUI);
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRecentFile(File file) {
        recentFiles.addFirst(file);
        if (recentFiles.size() > MAX_RECENT_FILES) {
            recentFiles.removeLast();
        }
    }

    public Menu createRecentFilesMenu() {
        Menu recentFileMenu = new Menu("Recent File");
        for (File file : recentFiles) {
            MenuItem item = new MenuItem(file.getName());
            item.setOnAction(e -> openFileFromRecent(file));
            recentFileMenu.getItems().add(item);
        }
        return recentFileMenu;
    }

    private void openFileFromRecent(File file) {
        if (file.exists()) {
            try {
                String content = Files.readString(file.toPath());
                ToDoUI toDoUI = new ToDoUI(content);
                Tab tab = new Tab(file.getName(), toDoUI);
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                addRecentFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist anymore " + file.getAbsoluteFile());
        }
    }
}
