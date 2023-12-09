package com.example.ap_project_stick_hero;

import javafx.scene.shape.Rectangle;

public abstract class GameElement extends Rectangle {

    public GameElement(double height, double width) {

        super(width, height);

    }

    public abstract void updateStatus();

}
