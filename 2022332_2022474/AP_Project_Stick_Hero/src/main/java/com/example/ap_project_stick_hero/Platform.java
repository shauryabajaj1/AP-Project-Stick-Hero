package com.example.ap_project_stick_hero;

import java.util.HashMap;
import java.util.Map;


//Using Flyweight for Pillars
public class Platform extends GameElement {
    private static final Map<Double, Platform> flyweightPlatforms = new HashMap<>();
    private final double width;

    public static Map<Double, Platform> getFlyweightPlatforms() {
        return flyweightPlatforms;
    }

    private Platform(double width) {
        super(50, width);
        this.width = width;
    }

    public static Platform getInstance(Double key) {
        if (!flyweightPlatforms.containsKey(key)) {
            flyweightPlatforms.put(key, new Platform(key));
        }
        return flyweightPlatforms.get(key);
    }

    @Override
    public void updateStatus() {
        // Specific status update logic
    }

    public boolean hasCollided() {
        boolean collision = false;
        return collision;
    }

    public boolean ifPerfect() {
        boolean perfect = false;
        return perfect;
    }


}
