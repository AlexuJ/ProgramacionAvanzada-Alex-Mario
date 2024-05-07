package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Text titulo = new Text("Recommendation Type");
        titulo.setFont(Font.font("Arial",FontWeight.BOLD,10));
        VBox vBox = new VBox(titulo);
        Scene scene = new Scene(vBox, 200, 500);
        stage.setScene(scene);
        stage.show();
    }
}
