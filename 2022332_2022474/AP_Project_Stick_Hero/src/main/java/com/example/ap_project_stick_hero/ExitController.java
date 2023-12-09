package com.example.ap_project_stick_hero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ExitController{

    @FXML
    private Text scoreLabel1;

    @FXML
    private Button restart;

    public void updateScore(int score){
        scoreLabel1.setText("Score : " + score);
        ScoreManager.updateHighScore(score);
        ScoreManager.setScore(0);
        ScoreManager.setCherryCount(0);
    }

    @FXML
    public void restartGame(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stick-hero-title-screen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StartController startController = loader.getController();

        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
        stage.show();

        startController.updateHS(ScoreManager.getScore());
    }


}
