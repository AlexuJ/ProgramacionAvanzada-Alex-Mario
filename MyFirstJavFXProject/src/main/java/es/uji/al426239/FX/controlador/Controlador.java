package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Controlador implements AnswerControlador {
    private Modelo modelo;
    private Vista vista;
    public ListView<String> anyadircanciones() throws FileNotFoundException {
        ListView<String> listacanciones = new ListView<>();
        String sep = System.getProperty("file.separator");
        String fichero = "." + sep + "data" + sep + "songs_test_names.csv";
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
    public void actualizarListaRecomendaciones(Spinner<Integer> spinner, HBox hBox) {
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            VBox vBox = (VBox) hBox.getParent();
            modelo.setNumeroRecomendaciones(newValue);
            vBox.getChildren().clear();
            try {
                vista.ensenyaRecomendaciones(hBox,vBox);
            } catch (FilaVacia | IOException | TablaVacia | Comparator e) {
                throw new RuntimeException(e);
            }
            vista.botonClose(vBox);
        });
        hBox.getChildren().clear();
    }
    public void EventAlgorithm(int caso) {
        if (caso == 1) {
            modelo.IsKnn();
        } else if (caso == 2) {
            modelo.IsKmeans();
        }
    }
    public void EventDistance(int caso) {
        if (caso == 1) {
            modelo.IsEuclidean();
        } else {
            modelo.IsManhattan();
        }
    }
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }
    public void setVista(Vista vista){
        this.vista = vista;
    }
}
