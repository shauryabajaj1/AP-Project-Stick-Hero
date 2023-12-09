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
import java.util.Objects;



public class StartController {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Button exitButton;

    @FXML
    private Text high_Score;

    public void startGame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stick-hero-main.fxml")));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateHS(int score){
        ScoreManager.updateHighScore(score);
        high_Score.setText("High Score : " + ScoreManager.getHighScore());
    }


    @FXML
    private void exitGame(ActionEvent event){
        System.exit(0);
    }

}