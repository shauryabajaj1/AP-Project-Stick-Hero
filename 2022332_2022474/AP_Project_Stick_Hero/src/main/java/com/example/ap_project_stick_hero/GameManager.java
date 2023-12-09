package com.example.ap_project_stick_hero;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public abstract class GameManager {
    private KeyEvent keyEvent;

    public GameManager (KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }

    static public void updateCherrySound() throws InterruptedException {
        Thread soundThread = new Thread(() -> {
            Media sound = new Media(new File("cherryPickup.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.play();
        });

        soundThread.start();
        soundThread.join();
    }

    static public void updateFall() throws InterruptedException {
        Thread soundThread = new Thread(() -> {
            Media sound = new Media(new File("deathSound.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.play();
        });

        soundThread.start();
        soundThread.join();
    }

    static public void updateNextLevel() throws InterruptedException {
        Thread soundThread = new Thread(() -> {
            Media sound = new Media(new File("succesSound.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.play();
        });

        soundThread.start();
        soundThread.join();
    }


}


