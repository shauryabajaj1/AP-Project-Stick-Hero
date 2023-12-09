package com.example.ap_project_stick_hero;
import javafx.event.Event;

//Using Singleton for one score manager
public class ScoreManager {
    private Event keyEvent;
    private static ScoreManager instance;

    public ScoreManager (Event keyEvent) {
        this.keyEvent = keyEvent;
    }

    public void update() {
        //logic here
    }

    private ScoreManager() {

    }

    public static synchronized ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    private static int score = 0;
    private static int cherryCount = 0;
    private static int highScore = 0;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ScoreManager.score = score;
    }

    public static int getCherryCount() {
        return cherryCount;
    }

    public static void setCherryCount(int cherryCount) {
        ScoreManager.cherryCount = cherryCount;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void updateHighScore(int currScore) {
        if(currScore >= ScoreManager.highScore){
            ScoreManager.highScore = currScore;
        }
    }
}
