package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyFirstJavFXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBoxExperiments escena = new HBoxExperiments();
        escena.start(primaryStage);
    }
}
