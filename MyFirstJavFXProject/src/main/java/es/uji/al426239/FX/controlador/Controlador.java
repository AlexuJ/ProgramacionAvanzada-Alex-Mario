package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.FilaVacia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

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

    public void crearlistacanciones(VBox vBox) throws FileNotFoundException {
        Text titulolistacanciones = new Text("Song Titles");
        titulolistacanciones.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD,20));
        ListView<String> listacanciones = anyadircanciones();
        vBox.getChildren().addAll(titulolistacanciones,listacanciones);
        vista.botonRecomendar(vBox,listacanciones);
    }

    public void actualizarListaRecomendaciones(HBox hBox, int newValue) throws FilaVacia {
        VBox vBox = (VBox) hBox.getParent();
        vBox.getChildren().clear();
        modelo.setNumeroRecomendaciones(newValue);
        ensenyaRecomendaciones(hBox, vBox);
        agregarBotones(vBox);
    }

    private void agregarBotones(VBox vBox) {
        vista.botonVolver(vBox);
        vista.botonClose(vBox);
    }

    public VBox ensenyaRecomendaciones(HBox hBox, VBox vBox) throws FilaVacia {
        Text text = new Text("If you liked "+modelo.getCancionRecomendada()+" you might like");
        ListView<String> listarecomendaciones = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(modelo.setRecomendaciones());
        listarecomendaciones.setItems(items);
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
    }

    public void Evento(RadioButton radioButton) {
        if (radioButton.getText().equals("Recommend based on songs features")) {
            modelo.setEleccion(0);
        } else if (radioButton.getText().equals("Recommend based on guessed genre")) {
            modelo.setEleccion(2);
        } else if (radioButton.getText().equals("Manhattan") && modelo.getEleccion() == 0) {
            modelo.setEleccion(1);
        } else if (radioButton.getText().equals("Manhattan") && modelo.getEleccion() == 2) {
            modelo.setEleccion(3);
        } else if (radioButton.getText().equals("Euclidean") && modelo.getEleccion() == 1) {
            modelo.setEleccion(0);
        } else if (radioButton.getText().equals("Euclidean") && modelo.getEleccion() == 3) {
            modelo.setEleccion(2);
        }
    }

    public void restableceNumeroRecomendaciones() {
        modelo.setNumeroRecomendaciones(5);
    }

    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }

    public void setVista(Vista vista){
        this.vista = vista;
    }
}
