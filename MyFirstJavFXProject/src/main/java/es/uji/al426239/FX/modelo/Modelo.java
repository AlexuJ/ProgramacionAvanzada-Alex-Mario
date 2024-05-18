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

public class Modelo implements AskModelo{
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    private int eleccion;
    private final HashMap<Integer,Algorithm> Algoritmos;
    private final HashMap<Integer,RecSys> Recomendadores;
    private HashMap<String,HashMap<String,RecSys>> Recomendador;
    private IntFactoriasAl factoriaAlgoritmos;
    private List<String> Algoritmo;
    private List<String> Distancias;


    public Modelo(IntFactoriasAl factoria) {
        this.Algoritmos = new HashMap<>();
        this.Recomendadores = new HashMap<>();
        this.Recomendador = new HashMap<>();
        this.numeroRecomendaciones = 5;
        factoriaAlgoritmos = factoria;
        Algoritmo = factoriaAlgoritmos.GetListaAlgoritmos();
        Distancias = factoriaAlgoritmos.GetListaDistancias();
    }
    public void inicializar() throws IOException, FilaVacia, TablaVacia, Comparator {
        int i = 0;
        for (Algorithm algoritmo : Algoritmos.values()) {
            Recomendadores.put(i,new RecSys(algoritmo));
            entrenarunearRecsys(Recomendadores.get(i),i);
            i++;
        }
    }
    private void entrenarunearRecsys(RecSys recSys, int i) throws IOException, FilaVacia, TablaVacia, Comparator {
        String sep = FileSystems.getDefault().getSeparator();
        String ruta = "." + sep + "data"+ sep;
        if (i == 0 || i == 1) {
            recSys.train(new CSVLabeledFileReader(ruta+"songs_train.csv").readTableFromSource());
            recSys.run(new CSVLabeledFileReader(ruta+"songs_test.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
        } else if (i == 2 || i == 3) {
            recSys.train(new CSVUnlabeledFileReader(ruta+"songs_train_withoutnames.csv").readTableFromSource());
            recSys.run(new CSVUnlabeledFileReader(ruta+"songs_test_withoutnames.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
        }
    }
    public List<String> setRecomendaciones() throws FilaVacia {
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
    public void setCancionRecomendada(String cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }
    public int getNumeroRecomendaciones() {
        return numeroRecomendaciones;
    }
    public int getEleccion() {
        return eleccion;
    }
    public void setEleccion(int eleccion) {
        this.eleccion = eleccion;
    }
    @Override
    public List<String> GetAlgoritmos(){
        return Algoritmo;
    }
    @Override
    public List<String> GetDistancias(){
        return Distancias;
    }

}
