package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class Vista extends Application {
    private Controlador controlador;
    private Modelo modelo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        escenaLista(primaryStage);
    }
    private void escenaLista (Stage stage) {
        stage.setTitle("Song recommender");
        Text titulo = new Text("Recommendation Type");
        titulo.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,15));
        RadioButton radioButton1 = new RadioButton("Recommend based on song features");
        RadioButton radioButton2 = new RadioButton("Recommend based on guessed genre");
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton2.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        ToggleGroup toggleGroup = new ToggleGroup();
        VBox vBox = new VBox(titulo,radioButton1,radioButton2);
        Scene scene = new Scene(vBox, 300, 600);
        stage.setScene(scene);
        stage.show();
    }
}
