package com.daknight.todo.ui.menu;

import com.daknight.todo.ui.preferences.Preferences;
import com.daknight.todo.ui.preferences.PreferencesWindow;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

public class MenuBuilder {
    public static MenuBar build(Runnable onExit) {
        MenuBar menuBar = new MenuBar();
        Preferences preferences = new Preferences();

        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu windowMenu = new Menu("Window");
        Menu helpMenu = new Menu("Help");

        // fileMenu items
        MenuItem newFileItem = new MenuItem("New File");
        newFileItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCodeCombination.ALT_DOWN, KeyCodeCombination.SHIFT_DOWN));
        MenuItem openFileItem = new MenuItem("Open File");
        MenuItem recentFileItem = new MenuItem("Recent File");
        MenuItem saveFileItem = new MenuItem("Save File");
        saveFileItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN));
        MenuItem renameFileItem = new MenuItem("Rename File");
        MenuItem restartItem = new MenuItem("Restart");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> onExit.run());
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCodeCombination.ALT_DOWN));

        // editMenu items
        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCodeCombination.CONTROL_DOWN));
        MenuItem redoItem = new MenuItem("Redo");
        redoItem.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCodeCombination.CONTROL_DOWN));
        MenuItem cutItem = new MenuItem("Cut");
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCodeCombination.CONTROL_DOWN));
        MenuItem copyItem = new MenuItem("Copy");
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN));
        MenuItem pasteItem = new MenuItem("Paste");
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN));
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        MenuItem selectAllItem = new MenuItem("Select All");
        selectAllItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCodeCombination.CONTROL_DOWN));

        // windowMenu items
        MenuItem newWindowItem = new MenuItem("New Window");
        MenuItem editorItem = new MenuItem("Editor");
        MenuItem appearanceItem = new MenuItem("Appearance");
        MenuItem showViewItem = new MenuItem("Show View");
        MenuItem perspectiveItem = new MenuItem("Perspective");
        MenuItem navigationItem = new MenuItem("Navigation");
        MenuItem preferencesItem = new MenuItem("Preferences");
        preferencesItem.setOnAction(e -> {
            PreferencesWindow preferencesWindow = new PreferencesWindow(preferences);
            preferencesWindow.show();
        });

        fileMenu.getItems().addAll(newFileItem, openFileItem, recentFileItem, new SeparatorMenuItem(), saveFileItem, renameFileItem, new SeparatorMenuItem(), restartItem, exitItem);
        editMenu.getItems().addAll(undoItem, redoItem, new SeparatorMenuItem(), cutItem, copyItem, pasteItem, new SeparatorMenuItem(), deleteItem, selectAllItem);
        windowMenu.getItems().addAll(newWindowItem, editorItem, appearanceItem, new SeparatorMenuItem(), showViewItem, perspectiveItem, new SeparatorMenuItem(), navigationItem, new SeparatorMenuItem(),preferencesItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, windowMenu, helpMenu);
        return menuBar;
    }
}
