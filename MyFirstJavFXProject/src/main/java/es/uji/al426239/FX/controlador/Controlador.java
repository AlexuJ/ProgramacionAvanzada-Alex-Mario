package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.AskModelo;
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
import java.util.List;
import java.util.Scanner;

public class Controlador {
String Distancia ;
String Algoritm ;
    private Modelo modelo;
    private Vista vista;
<<<<<<< HEAD

=======
    private AskModelo askModelo;
    private List<String> Algoritmo;
    private List<String> Distancias;
    public Controlador(AskModelo askModelo){
        this.askModelo = askModelo;
        Algoritmo = askModelo.GetAlgoritmos();
        Distancias = askModelo.GetDistancias();
    }
>>>>>>> 02f4723267c2cdeb66f6120c1e4525cbbf059f81
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
        ObservableList<String> items = FXCollections.observableArrayList(modelo.setRecomendaciones(Algoritm,Distancia));
        listarecomendaciones.setItems(items);
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
    }

    public void Evento(RadioButton radioButton) {
        switch (radioButton.getText()){
                case "Manhattan":
                    System.out.println("das");
                    Distancia = Distancias.get(1) ;
                    break;
                case "Euclidean":
                    System.out.println("asd");
                    Distancia =  Distancias.get(0);
                    break;
                case "Recommend based on songs features":
                    System.out.println("alfa");
                    Algoritm = Algoritmo.get(0);
                    break;
                case "Recommend based on guessed genre":
                    System.out.println("Joel");
                    Algoritm = Algoritmo.get(1);
                    break;
                    default:
                    throw  new IllegalArgumentException("Parametro no reconocido");

        }
    }
<<<<<<< HEAD

    public void restableceNumeroRecomendaciones() {
        modelo.setNumeroRecomendaciones(5);
    }

=======
    public boolean isReadyToRecommend() {
        return Algoritm != null && Distancia != null;
    }
>>>>>>> 02f4723267c2cdeb66f6120c1e4525cbbf059f81
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }

    public void setVista(Vista vista){
        this.vista = vista;
    }
    public List<String> GetAlgoritmos(){
        return Algoritmo;
    }

    public List<String> GetDistancias(){
        return Distancias;
    }
}
