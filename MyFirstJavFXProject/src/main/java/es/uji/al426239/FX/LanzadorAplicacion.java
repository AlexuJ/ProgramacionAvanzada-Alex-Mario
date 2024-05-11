package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class LanzadorAplicacion extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(primaryStage);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        primaryStage.setScene(vista.escenaListaCanciones());
        primaryStage.show();
    }
}
