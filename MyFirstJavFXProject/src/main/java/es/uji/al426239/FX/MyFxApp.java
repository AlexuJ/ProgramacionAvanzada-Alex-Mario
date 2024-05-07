package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyFxApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My First JavaFX App");
        Label label = new Label("Hello World, JavaFX !");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
