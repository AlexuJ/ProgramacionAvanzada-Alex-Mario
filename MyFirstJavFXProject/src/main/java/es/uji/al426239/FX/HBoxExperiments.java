package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HBoxExperiments extends Application  {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HBox Experiment 1");
        Label label1 = new Label("Pulsa el botón: ");
        Button button1 = new Button("Button Number 1");
        HBox hbox = new HBox(label1, button1);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
