package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Vista extends Application {
    private Controlador controlador;
    private Modelo modelo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.controlador = new Controlador();
        this.modelo = new Modelo();
        escenaLista(primaryStage);
    }
    private void escenaLista (Stage stage) throws FileNotFoundException {
        VBox vBox = new VBox();
        crearelecciones(vBox,"Recommendation Type", "Recommend based on songs features", "Recommend based on guessed genre");
        crearelecciones(vBox,"Distance Type","Euclidean","Manhattan");
        crearlistacanciones(vBox);
        Scene scene = new Scene(vBox, 300, 540);
        stage.setScene(scene);
        stage.show();
    }
    private void crearelecciones(VBox vBox, String texto1, String texto2, String texto3) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,15));
        RadioButton radioButton1 = new RadioButton(texto2);
        RadioButton radioButton2 = new RadioButton(texto3);
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton2.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroupRecommendation);
        radioButton2.setToggleGroup(radioGroupRecommendation);
        vBox.getChildren().addAll(tiporecomendacion,radioButton1,radioButton2);
    }
    private void crearlistacanciones(VBox vBox) throws FileNotFoundException {
        Text titulolistacanciones = new Text("Song Titles");
        titulolistacanciones.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,20));
        ListView<String> listacanciones = modelo.anyadircanciones();
        vBox.getChildren().addAll(titulolistacanciones,listacanciones);
    }
}
