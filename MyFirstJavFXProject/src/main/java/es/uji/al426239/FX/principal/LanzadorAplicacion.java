package es.uji.al426239.FX.principal;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
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
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.inicio();
    }
}
