package es.uji.al426239.FX.principal;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.AskModelo;
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
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class LanzadorAplicacion extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException, FilaVacia, TablaVacia, Comparator {
        intFactoriasDis factoriasDis = new FactoryDistance();
        String sep = FileSystems.getDefault().getSeparator();
        String ruta = "." + sep + "data"+ sep;
        List<String> ficheros =new ArrayList<>(List.of("songs_train.csv","songs_train_withoutnames.csv","songs_test.csv","songs_test_withoutnames.csv"));
        IntFactoriasAl factoriaAlgoritmos = new factoriaAlgoritmos(factoriasDis,200,1,15,ficheros,ruta);
        Modelo modelo = new Modelo(factoriaAlgoritmos);
        AskModelo askModelo = modelo;
        Controlador controlador = new Controlador(askModelo);
        Vista vista = new Vista(primaryStage);
        Factoria factoria = new FactoriaVista();
        vista.setFactoria(factoria);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.inicio();
    }
}
