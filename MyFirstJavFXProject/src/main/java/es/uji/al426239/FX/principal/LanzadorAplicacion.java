package es.uji.al426239.FX.principal;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class LanzadorAplicacion extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, FilaVacia, TablaVacia, Comparator {
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();
        Vista vista = new Vista(primaryStage);
        controlador.setModelo(modelo);
        controlador.setScene(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.inicio();
    }
}
