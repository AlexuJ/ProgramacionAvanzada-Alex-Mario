package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
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
        crearlistacanciones(vBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        return new Scene(vBox);
    }
    public void crearlistacanciones(VBox vBox) throws FileNotFoundException {
        Text titulolistacanciones = new Text("Song Titles");
        titulolistacanciones.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD,20));
        ListView<String> listacanciones = modelo.anyadircanciones();
        vBox.getChildren().addAll(titulolistacanciones,listacanciones);
        botonRecomendar(vBox,listacanciones);
    }
    private void agregarBotones(VBox vBox) {
        botonVolver(vBox);
        botonClose(vBox);
    }
    public VBox ensenyaRecomendaciones(HBox hBox, VBox vBox) throws FilaVacia, IOException, TablaVacia, Comparator {
        Text text = new Text("If you liked "+modelo.getCancionRecomendada()+" you might like");
        ListView<String> listarecomendaciones = new ListView<>();
        controlador.SetRecomendaciones();
        ObservableList<String> items = modelo.getRecomendaciones() ;
        listarecomendaciones.setItems(items);
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
    }

    public ListView<String> anyadircanciones() throws FileNotFoundException {
        ListView<String> listacanciones = new ListView<>();
        String sep = FileSystems.getDefault().getSeparator();
        String fichero = "." + sep + "data" + sep + "songs_train_names.csv";
        Scanner sc = new Scanner(new File(fichero));
        while (sc.hasNextLine()) {
            listacanciones.getItems().add(sc.nextLine());
        }
        return listacanciones;
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
    private VBox ensenyaRecomendaciones2(HBox hBox, VBox vBox) throws FilaVacia, IOException, TablaVacia, Comparator {
        Text text = new Text("If you liked " + modelo.getCancionRecomendada() + " you might like");
        ListView<String> listarecomendaciones = new ListView<>();
        ObservableList items = controlador.ensenyaRecomendaciones();
        listarecomendaciones.setItems(items);
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
    }

    private Scene escenaRecomendarTitulos() throws FilaVacia, IOException, TablaVacia, Comparator {
        HBox hBox = anyadirNumeroRecomendaciones(new HBox());
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        VBox alfa = new VBox();
        VBox vBox = ensenyaRecomendaciones(hBox, alfa);
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
        actualizarListaRecomendaciones(spinner,hBox);
        hBox.getChildren().addAll(texto,spinner);
        return hBox;
    }
    public void actualizarListaRecomendaciones(Spinner<Integer> spinner, HBox hBox) {
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            VBox vBox = (VBox) hBox.getParent();
            modelo.setNumeroRecomendaciones(newValue);
            vBox.getChildren().clear();
            try {
                modelo.reset();
                ensenyaRecomendaciones(hBox, vBox);
            } catch (FilaVacia | IOException | TablaVacia | Comparator e) {
                throw new RuntimeException(e);
            }
            agregarBotones(vBox);
        });
        hBox.getChildren().clear();
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
                modelo.reset();
                controlador.reset();
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

