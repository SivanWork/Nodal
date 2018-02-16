package com.example.ranad.nodalsystems.data_holder;

public class HomeGridElement {

    public int target;
    public String title;
    public int icon;
    public int background;

    public HomeGridElement(String title, int icon, int background, int target) {
        this.title = title;
        this.icon = icon;
        this.background = background;
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
