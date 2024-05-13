package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.*;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.sistemaderecomendacion.RecSys;
import javafx.scene.control.ListView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modelo {
    private Algorithm algorithm;
    private Distance distance;
    private int numeroIteracion;
    private int numeroClusters;
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    public Modelo() {
        this.numeroIteracion = 200;
        this.numeroRecomendaciones = 5;
        this.numeroClusters = 15;
    }
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
    public List<String> setRecomendaciones() throws FilaVacia, IOException, TablaVacia, Comparator {
        String sep = System.getProperty("file.separator");
        String ruta = "." + sep + "data"+ sep;
        String fichero_test = "songs_test_withoutnames.csv";
        String fichero_train = "songs_train_withoutnames.csv";
        RecSys recsys = new RecSys(new KMeans(numeroClusters,numeroIteracion,4321, distance));
        recsys.train(new CSVUnlabeledFileReader(ruta+fichero_train).readTableFromSource());
        recsys.run(new CSVUnlabeledFileReader(ruta+fichero_test).readTableFromSource(), readNames(ruta + sep + "songs_test_names.csv"));
        return recsys.recommend(getCancionRecomendada(),getNumeroRecomendaciones());
    }
    private List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
    public Distance getDistance() {
        return distance;
    }
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
    public String getCancionRecomendada() {
        return cancionRecomendada;
    }
    public void setCancionRecomendada(String cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }
    public int getNumeroRecomendaciones() {
        return numeroRecomendaciones;
    }
    public int getNumeroIteracion() {
        return numeroIteracion;
    }
    public  void  setnumeroIteraciones(int numero){
        numeroIteracion = numero;
    }
    public int getNumeroClusters() {
        return numeroClusters;
    }
    public  void  setnumeroClusters(int numero){
        numeroIteracion = numero;
    }
}
