package es.uji.al426239.FX.principal;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Factoria;
import es.uji.al426239.FX.vista.FactoriaVista;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.*;
import es.uji.al426239.distance.FactoryDistance;
import es.uji.al426239.distance.intFactoriasDis;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class LanzadorAplicacion extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException, FilaVacia, TablaVacia, Comparator {
        Controlador controlador = new Controlador();
        intFactoriasDis factoriasDis = new FactoryDistance();
        IntFactoriasAl factoriaAlgoritmos = new factoriaAlgoritmos(factoriasDis);
        Modelo modelo = new Modelo(factoriaAlgoritmos);
        Vista vista = new Vista(primaryStage);
        Factoria factoria = new FactoriaVista();
        vista.setFactoria(factoria);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.inicio();
        modelo.inicializar();
    }
}
