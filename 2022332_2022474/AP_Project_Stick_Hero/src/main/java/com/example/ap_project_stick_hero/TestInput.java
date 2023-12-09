package com.example.ap_project_stick_hero;

import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestInput {
    private Rectangle pillar2 = new Rectangle();
    private Rectangle stickTest = new Rectangle();

    @Test
    public void testSuccessfulLevel() {
        stickTest.setX(27);
        pillar2.setX(220);
        stickTest.setWidth(260);
        pillar2.setWidth(80);

        boolean ifSuccessful = MainLoop.checkStick(stickTest, pillar2);
        assertTrue(ifSuccessful);
    }

    @Test
    public void checkPillarHashMap() {
        Rectangle r1 = Platform.getInstance(15.0);
        Rectangle r2 = Platform.getInstance(15.0);
        boolean checkIfDifferent = (r1 != r2);
        assertFalse(checkIfDifferent);
    }

    @Test
    public void checkSamePillarHashMap() {
        Rectangle r1 = Platform.getInstance(15.0);
        Rectangle r2 = Platform.getInstance(20.0);
        boolean checkIfDifferent = (r1 != r2);
        assertTrue(checkIfDifferent);
    }

    @Test
    public void testFailure() {
        stickTest.setX(27);
        pillar2.setX(220);
        stickTest.setWidth(2600);
        pillar2.setWidth(80);

        boolean ifSuccessful = MainLoop.checkStick(stickTest, pillar2);
        assertFalse(ifSuccessful);
    }

    @Test
    public void testScoreManagerSingleton() {
        ScoreManager ScoreManager1 = ScoreManager.getInstance();
        ScoreManager ScoreManager2 = ScoreManager.getInstance();
        boolean sameManager = ScoreManager1.equals(ScoreManager2);
        assertTrue(sameManager);
    }


}
