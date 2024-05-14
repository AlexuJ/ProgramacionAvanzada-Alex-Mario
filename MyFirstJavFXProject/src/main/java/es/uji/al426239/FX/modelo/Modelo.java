package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.*;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.sistemaderecomendacion.RecSys;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Algorithm algorithm;
    private Distance distance;
    private final int numeroIteracion;
    private final int numeroClusters;
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    private ReaderTemplate lectortrain;
    private ReaderTemplate lectortest;
    public Modelo() {
        this.numeroIteracion = 200;
        this.numeroRecomendaciones = 5;
        this.numeroClusters = 15;
    }
    public List<String> setRecomendaciones() throws FilaVacia, IOException, TablaVacia, Comparator {
        String sep = System.getProperty("file.separator");
        String ruta = "." + sep + "data"+ sep;
        RecSys recsys = new RecSys(algorithm);
        recsys.train(lectortrain.readTableFromSource());
        recsys.run(lectortest.readTableFromSource(), readNames(ruta + sep + "songs_test_names.csv"));
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
    public void IsKnn() {
        String sep = System.getProperty("file.separator");
        String ruta = "." + sep + "data"+ sep;
        algorithm = new KNN(distance);
        lectortrain = new CSVLabeledFileReader(ruta + "songs_train.csv");
        lectortest = new CSVLabeledFileReader(ruta + "songs_test.csv");
    }
    public void IsKmeans() {
        String sep = System.getProperty("file.separator");
        String ruta = "." + sep + "data"+ sep;
        algorithm = new KMeans(numeroClusters,numeroIteracion,4321,distance);
        lectortrain = new CSVUnlabeledFileReader(ruta + "songs_train_withoutnames.csv");
        lectortest = new CSVUnlabeledFileReader(ruta + "songs_test_withoutnames.csv");
    }
    public void IsEuclidean() {
        distance = new EuclideanDistance();
    }
    public void IsManhattan() {
        distance = new ManhattanDistance();
    }
    public void setNumeroRecomendaciones(int numeroRecomendaciones) {
        this.numeroRecomendaciones = numeroRecomendaciones;
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
}
