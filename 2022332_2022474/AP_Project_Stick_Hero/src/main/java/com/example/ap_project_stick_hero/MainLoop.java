package com.example.ap_project_stick_hero;


import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.transform.Source;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class MainLoop implements Initializable {

    private Event event;
    private Player player;
    private Platform platform;
    private LevelManager levelManager = new LevelManager(event);
    private Timeline timeline;
    private Timeline moveTimeline;
    private Timeline collisionTimeline;
    private int distance;
    private boolean flipped;
    private boolean collect = false;
    private static Rectangle stickTest;
    private static Rectangle Pillar_2;


    @FXML
    private Rectangle Pillar1;
    private double x1;
    private double y1;

    @FXML
    private ImageView hero;

    @FXML
    private ImageView cherry;

    @FXML
    private ImageView monster;

    @FXML
    private Rectangle Pillar2;
    private double x2;
    private double y2;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Rectangle stick;

    @FXML
    private Text scoreLabel;

    @FXML
    private Text cherryLabel;

    @FXML
    private Text reviveLabel;

    @FXML
    private Button save;

    @FXML
    private Button revive;

    private boolean reviveBool = true;

    Random random = new Random();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.getInstance(Pillar1.getWidth());
        Platform.getInstance(Pillar2.getWidth());

        cherryLabel.setText("Cherries : " + ScoreManager.getCherryCount());
        scoreLabel.setText("Score : " + ScoreManager.getScore());

        reviveLabel.setVisible(false);
        revive.setVisible(false);

        timeline = new Timeline(new KeyFrame(Duration.millis(30), e-> {
            stick.setHeight(stick.getHeight() + 4);
            stick.setY(stick.getY() - 4);
        }));

        cherry.setX(random.nextDouble(Pillar1.getWidth() + 15 , Pillar2.getX() - 15 ));
        monster.setX( (Pillar1.getWidth() + Pillar2.getX()) / 2 );

        moveTimeline = new Timeline(new KeyFrame(Duration.millis(250), e-> {
            if (hero.getBoundsInParent().intersects(cherry.getBoundsInParent())) {
                collect = true;
                try {
                    AudioManager.updateCherrySound();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                cherry.setVisible(false);
            }
        }));

        collisionTimeline = new Timeline(new KeyFrame(Duration.millis(10), e-> {
            if (Pillar2.getBoundsInParent().intersects(hero.getBoundsInParent()) && stick.getX() + stick.getWidth() <= Pillar2.getX() + Pillar2.getWidth() && stick.getX() + stick.getWidth() >= Pillar2.getX() && ScoreManager.getScore() != 0) {
                try {
                    endGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (Pillar2.getBoundsInParent().intersects(hero.getBoundsInParent())){
                try {
                    endGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(hero.getBoundsInParent().intersects(monster.getBoundsInParent())){
                try {
                    endGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }));



        collisionTimeline.setCycleCount(Animation.INDEFINITE);
        collisionTimeline.play();

        moveTimeline.setCycleCount(Animation.INDEFINITE);
        moveTimeline.play();

        timeline.setCycleCount(Animation.INDEFINITE);

        anchorPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                timeline.play();
            }
        });

        anchorPane.setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                timeline.stop();
                stick.setY(261);
                stick.setWidth(stick.getHeight());
                stick.setHeight(4);
                stick.setY(261);
                checkFall();
            }
        });

        anchorPane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                RotateTransition rotateTransition = new RotateTransition();
                rotateTransition.setNode(hero);
                rotateTransition.setDuration(Duration.millis(1));
                rotateTransition.setCycleCount(1);
                rotateTransition.setByAngle(180);
                rotateTransition.setAxis(Rotate.X_AXIS);
                if (flipped) {
                    flipped = false;
                    hero.setY(231);
                    rotateTransition.play();
                }
                else {
                    flipped = true;
                    hero.setY(265);
                    rotateTransition.play();
                }
            }
        });
    }

    private void initializeNextLevel(){

        anchorPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                timeline.play();
            }
        });

        anchorPane.setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                timeline.stop();
                stick.setY(261);
                stick.setWidth(stick.getHeight());
                stick.setHeight(4);
                stick.setY(261);
                Rectangle temp = Pillar1;
                Pillar1 = Pillar2;
                Pillar2 = temp;
                checkFall();
            }
        });
    }

    public static boolean checkStick(Rectangle stickTest, Rectangle Pillar_2) {
        if (stickTest.getX() + stickTest.getWidth() <= Pillar_2.getX() + Pillar_2.getWidth() && stickTest.getX() + stickTest.getWidth() >= Pillar_2.getX()) {
            return true;
        }
        else {
            return false;
        }
    }
    private void checkFall(){

        if(stick.getX() + stick.getWidth() <= Pillar2.getX() + Pillar2.getWidth() && stick.getX() + stick.getWidth() >= Pillar2.getX()){
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(hero);
            translateTransition.setDuration(Duration.millis(2000));
            translateTransition.setCycleCount(1);
            translateTransition.setToX(Pillar2.getX() - hero.getFitWidth());
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                try {
                    nextLevel();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        else{
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setNode(hero);
            translateTransition.setDuration(Duration.millis(2000));
            translateTransition.setCycleCount(1);
            translateTransition.setToX(stick.getWidth() + hero.getFitWidth());
            translateTransition.play();
            translateTransition.setOnFinished(actionEvent -> {
                if(ScoreManager.getCherryCount() >= 5 && reviveBool){
                    reviveBool = false;
                    reviveLabel.setVisible(true);
                    revive.setVisible(true);
                } else{
                    try {
                        endGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void nextLevel() throws InterruptedException {
        monster.setVisible(false);
        monster.setY(0);
        AudioManager.updateNextLevel();
        int scoreUpdate = ScoreManager.getScore() + 1;
        ScoreManager.setScore(scoreUpdate);
        if(collect){
            int scoreUpdate2 = ScoreManager.getScore() + 1;
            ScoreManager.setScore(scoreUpdate2);
            int cherryUpdate = ScoreManager.getCherryCount() + 1;
            ScoreManager.setCherryCount(cherryUpdate);
        }
        collect = false;
        cherryLabel.setText("Cherries : " + ScoreManager.getCherryCount());
        scoreLabel.setText("Score : " + ScoreManager.getScore());
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(hero);
        translateTransition.setDuration(Duration.millis(500));
        translateTransition.setCycleCount(1);
        translateTransition.setToX(0);
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            monster.setVisible(true);
            monster.setY(hero.getY());
        });

        Pillar1.setVisible(false);
        Pillar2.setX(Pillar1.getX());
        stick.setHeight(0);
        stick.setWidth(4);
        stick.setX(Pillar2.getWidth());
        hero.setX(Pillar2.getWidth() - hero.getFitWidth());
        double r1 = random.nextDouble(1, 5); double r2 = random.nextDouble(1, 5);
        Pillar1.setWidth(Pillar1.getWidth() + r1);
        Pillar2.setWidth(Pillar2.getWidth() + r2);
        Platform.getInstance(Pillar1.getWidth());
        Platform.getInstance(Pillar2.getWidth());

        Pillar1.setX(random.nextDouble(Pillar2.getWidth() + 100, Pillar2.getWidth() + 300 ));
        Pillar1.setVisible(true);

        if(Pillar2.getWidth() + 10 < Pillar1.getX() - 10){
            cherry.setVisible(true);
            cherry.setX(random.nextDouble(Pillar2.getWidth() + 15 , Pillar1.getX() - 15 ));
            monster.setX( (Pillar2.getWidth() + Pillar1.getX()) / 2 );
        }
        else{
            cherry.setVisible(true);
            cherry.setX(random.nextDouble(Pillar1.getWidth() + 15 , Pillar2.getX() - 15 ));
            monster.setX( (Pillar1.getWidth() + Pillar2.getX()) / 2 );
        }

        initializeNextLevel();
    }


    private void endGame() throws IOException {
        moveTimeline.stop();
        collisionTimeline.stop();
        TranslateTransition translateTransition2 = new TranslateTransition();
        translateTransition2.setNode(hero);
        translateTransition2.setDuration(Duration.millis(200));
        translateTransition2.setCycleCount(1);
        translateTransition2.setToY(2 * Pillar1.getHeight());
        translateTransition2.play();
        translateTransition2.setOnFinished(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stick-hero-exit-screen.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ExitController exitController = loader.getController();

            Scene nextScene = new Scene(root);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(nextScene);
            stage.show();


            try {
                AudioManager.updateFall();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            exitController.updateScore(ScoreManager.getScore());

        });

    }

    @FXML
    public void saveProgress(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("stick-hero-title-screen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StartController startController = loader.getController();

        Scene nextScene = new Scene(root);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(nextScene);
        stage.show();

        startController.updateHS(ScoreManager.getScore());

    }

    @FXML
    public void revive(ActionEvent event) throws IOException, InterruptedException {
        int scoreUpdate = ScoreManager.getScore() - 5;
        ScoreManager.setScore(scoreUpdate);
        int cherryUpdate = ScoreManager.getCherryCount() - 5;
        ScoreManager.setCherryCount(cherryUpdate);
        nextLevel();
        reviveLabel.setVisible(false);
        revive.setVisible(false);
    }

}
