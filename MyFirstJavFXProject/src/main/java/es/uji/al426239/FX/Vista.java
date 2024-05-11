package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Vista extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setScene(new Controlador(primaryStage).escenaListaCanciones());
        primaryStage.show();
    }
}
