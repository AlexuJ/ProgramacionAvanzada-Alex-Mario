package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
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
        crearelecciones(vBox,"Recommendation Type", "Recommend based on songs features", "Recommend based on guessed genre",TiposDeEvento.Algoritm);
        crearelecciones(vBox,"Distance Type","Euclidean","Manhattan",TiposDeEvento.Distance);
        controlador.crearlistacanciones(vBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        return new Scene(vBox);
    }
<<<<<<< HEAD
    private void crearelecciones(VBox vBox, String texto1, List<String> textos) {
        vBox.getChildren().add(factoria.Texto(texto1));
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        List<RadioButton> botones = new ArrayList<>();
        for (String texto : textos) {
            botones.add(factoria.Botones(texto,radioGroupRecommendation));
        }
        seleccionarOpcion(botones.get(1),botones.get(2));
    private void crearelecciones(VBox vBox, String texto1, List<String> alfa,TiposDeEvento tiposDeEvento) {
        Text tiporecomendacion = factoria.Ftexto(texto1);
        ListIterator<String> iterator = alfa.listIterator();
        ObservableList ds = vBox.getChildren();
        ds.add(tiporecomendacion);
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        List<Toggle> listaBotones = new ArrayList<>();
        while (iterator.hasNext()){
          Toggle f =  factoria.Cbotones(iterator.next(),radioGroupRecommendation);
          listaBotones.add(f);
          ds.add(f);
        }
        seleccionarOpcion(listaBotones,tiposDeEvento);
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
    private void seleccionarOpcion (List<Toggle> botones,TiposDeEvento tiposDeEvento) {
            if(tiposDeEvento == TiposDeEvento.Algoritm){
               factoria.Evenbotones(botones,tiposDeEvento);
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
}

