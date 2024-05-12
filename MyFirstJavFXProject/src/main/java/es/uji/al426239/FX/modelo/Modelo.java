package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.*;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
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
    private  String fichero_test ;
    private  String fichero_train ;
    private int numeroClusters;
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    private ReaderTemplate lectortrain ;
    private ReaderTemplate lectorTest;
    public Modelo() {
        this.numeroIteracion = 200;
        this.numeroRecomendaciones = 5;
        this.numeroClusters = 15;
    }
    public void IsKnn(){
        algorithm = new KNN(distance);
    }
    public void  IsKmeans() throws FileNotFoundException, FilaVacia, TablaVacia, Comparator {
        algorithm = new KMeans(numeroClusters, numeroIteracion, 100, distance);
        fichero_test = "songs_test_withoutnames.csv";
        fichero_train = "songs_train_withoutnames.csv";
        lectortrain = new CSVUnlabeledFileReader(fichero_train);
        lectorTest = new CSVUnlabeledFileReader(fichero_test);


    }
    public void IsEuclidean(){
        distance = new EuclideanDistance();
    }
    public void IsManhhatn(){
        distance = new ManhattanDistance();
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
        RecSys recsys = new RecSys(algorithm);
        recsys.train(lectortrain.readTableFromSource());
        recsys.run(lectorTest.readTableFromSource(), readNames(ruta + sep + "songs_test_names.csv"));
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
