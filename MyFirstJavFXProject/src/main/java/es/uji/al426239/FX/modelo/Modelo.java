package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.*;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.sistemaderecomendacion.RecSys;
import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Modelo {
    private final int numeroIteracion;
    private final int numeroClusters;
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    private final String ruta;
    private int eleccion;
    private final HashMap<Integer,  Algorithm> Algoritmos;
    private final HashMap<Integer,RecSys> Recomendadores;

    public Modelo() {
        String sep = FileSystems.getDefault().getSeparator();
        ruta = "." + sep + "data"+ sep;
        this.Algoritmos = new HashMap<>();
        this.Recomendadores = new HashMap<>();
        this.numeroIteracion = 200;
        this.numeroRecomendaciones = 5;
        this.numeroClusters = 15;
    }

    public void inicializar() throws IOException, FilaVacia, TablaVacia, Comparator {
        Algoritmos.put(0,new KNN(new EuclideanDistance()));
        Algoritmos.put(1,new KNN(new ManhattanDistance()));
        Algoritmos.put(2,new KMeans(numeroClusters,numeroIteracion,4321,new EuclideanDistance()));
        Algoritmos.put(3,new KMeans(numeroClusters,numeroIteracion,4321,new ManhattanDistance()));
        int i = 0;
        for (Algorithm algoritmo : Algoritmos.values()) {
            Recomendadores.put(i,new RecSys(algoritmo));
            entrenarunearRecsys(Recomendadores.get(i),i);
            i++;
        }
    }

    private void entrenarunearRecsys(RecSys recSys, int i) throws IOException, FilaVacia, TablaVacia, Comparator {
        if (i == 0 || i == 1) {
            recSys.train(new CSVLabeledFileReader(ruta+"songs_train.csv").readTableFromSource());
            recSys.run(new CSVLabeledFileReader(ruta+"songs_test.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
        } else if (i == 2 || i == 3) {
            recSys.train(new CSVUnlabeledFileReader(ruta+"songs_train_withoutnames.csv").readTableFromSource());
            recSys.run(new CSVUnlabeledFileReader(ruta+"songs_test_withoutnames.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
        }
    }

    public List<String> setRecomendaciones() throws FilaVacia {
        Recomendadores.get(eleccion).recommend(getCancionRecomendada(),getNumeroRecomendaciones()).clear();
        return Recomendadores.get(eleccion).recommend(getCancionRecomendada(),getNumeroRecomendaciones());
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

    public void setNumeroRecomendaciones(int numeroRecomendaciones) {
        this.numeroRecomendaciones = numeroRecomendaciones;
    }

    public String getCancionRecomendada() {
        return cancionRecomendada;
    }

    public int getNumeroRecomendaciones() {
        return numeroRecomendaciones;
    }

    public void setCancionRecomendada(String cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }

    public int getEleccion() {
        return eleccion;
    }

    public void setEleccion(int eleccion) {
        this.eleccion = eleccion;
    }
}