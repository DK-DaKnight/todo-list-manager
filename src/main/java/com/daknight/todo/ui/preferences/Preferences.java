package com.daknight.todo.ui.preferences;

public class Preferences {
    private String theme;
    private String lang;
    private String font;
    private int fontSize;

    public Preferences() {
        this.theme = "Light";
        this.lang = "en";
        this.font = "Arial";
        this.fontSize = 12;
    }

    public String getTheme() {
        return this.theme;
    }
    public String getLang() {
        return this.lang;
    }
    public String getFont() {
        return this.font;
    }
    public int getFontSize() {
        return this.fontSize;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public void setFont(String font) {
        this.font = font;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
