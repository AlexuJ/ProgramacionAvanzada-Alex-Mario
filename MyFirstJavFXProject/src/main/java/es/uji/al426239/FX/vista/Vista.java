package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.collections.FXCollections;
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
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class Vista implements AskVista ,AnswerVista {
    private Controlador controlador;
    private Modelo modelo;
    private final Stage escenario;
    private FactoriaV factoria;
    public Vista(final Stage escenario) {
        this.escenario = escenario;
    }
    public void inicio() throws IOException {
        escenario.setScene(escenaListaCanciones());
        escenario.show();
    }
    private Scene escenaListaCanciones() throws IOException {
        VBox vBox = new VBox();
        crearelecciones(vBox,"Recommendation Type", "Recommend based on songs features", "Recommend based on guessed genre");
        crearelecciones(vBox,"Distance Type","Euclidean","Manhattan");
        controlador.crearlistacanciones(vBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        return new Scene(vBox);
    }
    private void crearelecciones(VBox vBox, String texto1, List<String> alfa) {
        Text tiporecomendacion = factoria.Ftexto(texto1);
        ListIterator<String> iterator = alfa.listIterator();
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        while (iterator.hasNext()){
            factoria.Cbotones(iterator.next(),radioGroupRecommendation);
        }
        seleccionarOpcion(radioButton1,radioButton2);
        vBox.getChildren().addAll(tiporecomendacion,radioButton1,radioButton2);
    }
    public void botonRecomendar(VBox vBox, ListView<String> listacanciones) {
        Button button = new Button("Recommend");
        button.setDisable(true);
        listacanciones.setOnMouseClicked(mouseEvent -> {
            modelo.setCancionRecomendada(listacanciones.getSelectionModel().getSelectedItem());
            button.setDisable(false);
            button.setOnAction(value -> {
                try {
                    escenario.setScene(escenaRecomendarTitulos());
                } catch (FilaVacia | IOException | TablaVacia | Comparator e) {
                    throw new RuntimeException(e);
                }
                escenario.show();
            });
        });
        vBox.getChildren().addAll(button);
    }
    private void seleccionarOpcion (RadioButton radioButton1, RadioButton radioButton2) {
        if (radioButton1.getText().equals("Recommend based on songs features")) {
            radioButton1.setOnAction(value -> controlador.EventAlgorithm(1));
            radioButton2.setOnAction(value -> controlador.EventAlgorithm(2));
        } else if (radioButton1.getText().equals("Euclidean")) {
            radioButton1.setOnAction(value -> controlador.EventDistance(1));
            radioButton2.setOnAction(value -> controlador.EventDistance(2));
        }
    }
    private Scene escenaRecomendarTitulos() throws FilaVacia, IOException, TablaVacia, Comparator {
        HBox hBox = anyadirNumeroRecomendaciones(new HBox());
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        VBox vBox = ensenyaRecomendaciones(hBox, new VBox());
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
    public VBox ensenyaRecomendaciones(HBox hBox, VBox vBox) throws FilaVacia, IOException, TablaVacia, Comparator {
        Text text = new Text("If you liked "+modelo.getCancionRecomendada()+" you might like");
        ListView<String> listarecomendaciones = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(modelo.setRecomendaciones());
        listarecomendaciones.setItems(items);
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
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
}

