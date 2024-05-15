package com.example.carsimulation;
//car image: https://di-uploads-pod16.dealerinspire.com/pattypeckhonda/uploads/2020/12/2021-civic-typeR-boost-blue-pearl.png
//background: https://img.freepik.com/free-vector/modern-city-buildings_1441-3042.jpg?w=996&t=st=1682909980~exp=1682910580~hmac=4146c8bb572887e624f196df30d653edd7e791a1f25f52e05f4a2186a83bfebe


import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CarSimulation extends Application {

    private static final double SPEED_INCREMENT = 2.0;
    private static final double MAX_SPEED = 20.0;
    private double speed = 0.0;
    private boolean engineStarted = false;
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        VBox controls = new VBox(10);

        Button startButton = new Button("Start");
        Button gasButton = new Button("Gas");
        Button brakeButton = new Button("Brake");
        Button stopButton = new Button("Stop");

        controls.getChildren().addAll(startButton, gasButton, brakeButton, stopButton);
        controls.setLayoutX(10);
        controls.setLayoutY(10);

        Image backgroundImage = new Image("https://img.freepik.com/free-vector/modern-city-buildings_1441-3042.jpg?w=996&t=st=1682909980~exp=1682910580~hmac=4146c8bb572887e624f196df30d653edd7e791a1f25f52e05f4a2186a83bfebe");
        ImageView backgroundView1 = new ImageView(backgroundImage);
        ImageView backgroundView2 = new ImageView(backgroundImage);
        backgroundView2.setX(SCENE_WIDTH);
        backgroundView1.setFitWidth(SCENE_WIDTH);
        backgroundView1.setFitHeight(SCENE_HEIGHT);
        backgroundView2.setFitWidth(SCENE_WIDTH);
        backgroundView2.setFitHeight(SCENE_HEIGHT);
        backgroundView2.setX(SCENE_WIDTH);

        Image carImage = new Image("https://di-uploads-pod16.dealerinspire.com/pattypeckhonda/uploads/2020/12/2021-civic-typeR-boost-blue-pearl.png");
        ImageView carView = new ImageView(carImage);
        carView.setX(50);
        carView.setY(200);

        root.getChildren().addAll(backgroundView1, backgroundView2, carView, controls);

        startButton.setOnAction(e -> {
            engineStarted = true;
            TranslateTransition shake = new TranslateTransition(Duration.millis(100), carView);
            shake.setByX(5);
            shake.setCycleCount(6);
            shake.setAutoReverse(true);
            shake.play();
        });

        gasButton.setOnAction(e -> {
            if (engineStarted) {
                speed = Math.min(speed + SPEED_INCREMENT, MAX_SPEED);
            }
        });

        brakeButton.setOnAction(e -> speed = Math.max(speed - SPEED_INCREMENT, 0));

        stopButton.setOnAction(e -> {
            if (speed > 0) {
                return;
            }
            engineStarted = false;
            speed = 0;
        });

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 16_000_000) {
                    backgroundView1.setX(backgroundView1.getX() + speed / 2);
                    backgroundView2.setX(backgroundView2.getX() + speed / 2);

                    if (backgroundView1.getX() >= SCENE_WIDTH) {
                        backgroundView1.setX(backgroundView2.getX() - SCENE_WIDTH);
                    }
                    if (backgroundView2.getX() >= SCENE_WIDTH) {
                        backgroundView2.setX(backgroundView1.getX() - SCENE_WIDTH);
                    }

                    lastUpdate = now;
                }
            }
        };

        timer.start();

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Car Simulation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
