package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.FX.modelo.Modelo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Vista {
    private Controlador controlador;
    private Modelo modelo;
    private Stage escenario;

    public Vista(final Stage escenario) {
        this.escenario = escenario;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    public Scene escenaListaCanciones() throws FileNotFoundException {
        VBox vBox = new VBox();
        crearelecciones(vBox,"Recommendation Type", "Recommend based on songs features", "Recommend based on guessed genre");
        crearelecciones(vBox,"Distance Type","Euclidean","Manhattan");
        crearlistacanciones(vBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        return new Scene(vBox,300,600);
    }
    private void crearelecciones(VBox vBox, String texto1, String texto2, String texto3) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD,15));
        RadioButton radioButton1 = new RadioButton(texto2);
        RadioButton radioButton2 = new RadioButton(texto3);
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton2.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroupRecommendation);
        radioButton2.setToggleGroup(radioGroupRecommendation);
        seleccionarOpcion(radioButton1,radioButton2);
        vBox.getChildren().addAll(tiporecomendacion,radioButton1,radioButton2);
    }
    private void seleccionarOpcion (RadioButton radioButton1, RadioButton radioButton2) {
        if (radioButton1.getText().equals("Recommend based on songs features")) {
            radioButton1.setOnAction(value -> controlador.setAlgorithm(true));
            radioButton2.setOnAction(value -> controlador.setAlgorithm(false));
        } else {
            radioButton1.setOnAction(value -> controlador.setDistance(true));
            radioButton2.setOnAction(value -> controlador.setDistance(false));
        }
    }
    private void crearlistacanciones(VBox vBox) throws FileNotFoundException {
        Text titulolistacanciones = new Text("Song Titles");
        titulolistacanciones.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,20));
        ListView<String> listacanciones = controlador.anyadircanciones();
        vBox.getChildren().addAll(titulolistacanciones,listacanciones);
        botonRecomendar(vBox, listacanciones);
    }
    private void botonRecomendar(VBox vBox, ListView<String> listacanciones) {
        Button button = new Button("Recommend");
        button.setDisable(true);
        listacanciones.setOnMouseClicked(mouseEvent -> {
            controlador.setCancionRecomendada(listacanciones.getSelectionModel().getSelectedItem());
            button.setDisable(false);
            button.setOnAction(value -> {
                escenario.setScene(escenaRecomendarTitulos());
                escenario.show();
            });
        });
        vBox.getChildren().addAll(button);
    }
    public Scene escenaRecomendarTitulos() {
        HBox hBox = anyadirNumeroRecomendaciones(new HBox());
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        VBox vBox = ensenyaRecomendaciones(hBox, new VBox());
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10));
        botonClose(vBox);
        return new Scene(vBox, 350, 300);
    }
    private HBox anyadirNumeroRecomendaciones(HBox hBox) {
        Text texto = new Text("Number of recommendations:");
        Spinner<Integer> stringSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1,1));
        stringSpinner.getValueFactory().setValue(5);
        hBox.getChildren().addAll(texto,stringSpinner);
        return hBox;
    }
    private VBox ensenyaRecomendaciones(HBox hBox, VBox vBox) {
        Text text = new Text("If you liked "+controlador.getCancionRecomendada()+" you might like");
        vBox.getChildren().addAll(hBox,text,new ListView<String>());
        return vBox;
    }
    private void botonClose(VBox vBox) {
        Button button = new Button("Close");
        vBox.getChildren().addAll(button);
        button.setOnAction(value -> escenario.close());
    }
}

