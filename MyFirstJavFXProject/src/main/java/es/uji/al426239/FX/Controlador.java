package es.uji.al426239.FX;

import es.uji.al426239.algoritmos.Algorithm;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controlador {
    private Algorithm algorithm;
    private Distance distance;
    private int numeroIteracion;
    private int numeroClusters;
    private String cancionRecomendada;
    public void setAlgorithm(boolean alfa) {
        if (alfa) {
            algorithm = new KNN(distance);
        } else {
            algorithm = new KMeans(numeroClusters, numeroIteracion, 100, distance);
        }
    }
    public void setDistance(boolean alfa) {
        if (alfa) {
            distance = new EuclideanDistance();
        } else {
            distance = new ManhattanDistance();
        }
    }
    public ListView<String> anyadircanciones() throws FileNotFoundException {
        ListView<String> listacanciones = new ListView<>();
        String sep = System.getProperty("file.separator");
        String fichero = "." + sep + "data" + sep + "songs_train_names.csv";
        Scanner sc = new Scanner(new File(fichero));
        while (sc.hasNextLine()) {
            listacanciones.getItems().add(sc.nextLine());
        }
        return listacanciones;
    }
    public String getCancionRecomendada() {
        return cancionRecomendada;
    }
    public void setCancionRecomendada(String cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }
    public  void  getnumeroIteraciones(int numero){
        numeroIteracion = numero;
    }
    public  void  getnumeroClusters(int numero){
        numeroIteracion = numero;
    }
}
