package com.daknight.todo.ui.preferences;

import javafx.scene.text.Font;

public class FontConfig {
    private static final Preferences preferences = new Preferences();

    public static Font getFont() {
        return new Font(preferences.getFont(), (double) preferences.getFontSize());
    }
}
