package com.example.ap_project_stick_hero;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunnerClass {
    public static void main(String[] args) {
        Result result =
                JUnitCore.runClasses(TestInput.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
    }

