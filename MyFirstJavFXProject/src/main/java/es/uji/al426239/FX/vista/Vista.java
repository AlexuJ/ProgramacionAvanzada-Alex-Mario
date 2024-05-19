package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class Vista implements AskVista ,AnswerVista {
    private Controlador controlador;
    private Modelo modelo;
    private final Stage escenario;
    private Factoria factoria;
    private List<String> Algoritmo;
    private List<String> Distancias;
    private Button buttonRecomend;
    public Vista(final Stage escenario) {
        this.escenario = escenario;
    }
    public void inicio() throws IOException {
        Algoritmo = controlador.GetAlgoritmos();
        Distancias = controlador.GetDistancias();
        escenario.setScene(escenaListaCanciones());
        escenario.show();
    }
    private Scene escenaListaCanciones() throws IOException {
        VBox vBox = new VBox();
        crearelecciones(vBox,"Recommendation Type", Algoritmo);
        crearelecciones(vBox,"Distance Type",Distancias);
        controlador.crearlistacanciones(vBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        return new Scene(vBox);
    }
    private void crearelecciones(VBox vBox, String texto1, List<String> textos) {
        vBox.getChildren().add(factoria.Texto(texto1));
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        for (String texto : textos) {
            RadioButton boton = factoria.Botones(texto, radioGroupRecommendation);
            boton.setOnAction(value -> {
                    controlador.Evento(boton);
                    actualizarEstadoBotonRecomendar();

            });
            vBox.getChildren().add(boton);
        }
    }
    public void botonRecomendar(VBox vBox, ListView<String> listacanciones) {
        buttonRecomend = new Button("Recommend");
        buttonRecomend.setDisable(true); // Inicialmente deshabilitado
        listacanciones.setOnMouseClicked(mouseEvent -> {
            modelo.setCancionRecomendada(listacanciones.getSelectionModel().getSelectedItem());
            actualizarEstadoBotonRecomendar();
        });
        buttonRecomend.setOnAction(value -> {
            try {
                escenario.setScene(escenaRecomendarTitulos());
            } catch (FilaVacia | IOException | TablaVacia | Comparator e) {
                throw new RuntimeException(e);
            }
            escenario.show();
        });
        vBox.getChildren().addAll(buttonRecomend);
    }

    private void actualizarEstadoBotonRecomendar() {
        // Asegúrate de ejecutar en el hilo de JavaFX
            if (controlador.isReadyToRecommend() && modelo.getCancionRecomendada() != null) {
                buttonRecomend.setDisable(false);
            } else {
                buttonRecomend.setDisable(true);
            }

    }

    private Scene escenaRecomendarTitulos() throws FilaVacia, IOException, TablaVacia, Comparator {
        HBox hBox = anyadirNumeroRecomendaciones(new HBox());
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        VBox vBox = controlador.ensenyaRecomendaciones(hBox, new VBox());
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10));
        botonVolver(vBox);
        botonClose(vBox);
        return new Scene(vBox, 350, 400);
    }
    private HBox anyadirNumeroRecomendaciones(HBox hBox) {
        Text texto = new Text("Number of recommendations:");
        Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1,1));
        spinner.getValueFactory().setValue(modelo.getNumeroRecomendaciones());
        controlador.actualizarListaRecomendaciones(spinner,hBox);
        hBox.getChildren().addAll(texto,spinner);
        return hBox;
    }
    public void botonClose(VBox vBox) {
        Button button = new Button("Close");
        vBox.getChildren().addAll(button);
        button.setOnAction(value -> escenario.close());
    }
    public void botonVolver(VBox vBox) {
        Button button = new Button("Volver");
        vBox.getChildren().add(button);
        button.setOnAction(value -> {
            try {
                escenario.setScene(escenaListaCanciones());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    public void setFactoria(Factoria facto){
        this.factoria = facto;
    }
}

