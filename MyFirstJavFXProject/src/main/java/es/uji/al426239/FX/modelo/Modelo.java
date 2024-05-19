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
<<<<<<< HEAD

    public Modelo() {
=======
    private HashMap<String,HashMap<String,RecSys>> Recomendador;
    private IntFactoriasAl factoriaAlgoritmos;
    private List<String> Algoritmo;
    private List<String> Distancias;


    public Modelo(IntFactoriasAl factoria) {
>>>>>>> 02f4723267c2cdeb66f6120c1e4525cbbf059f81
        this.Algoritmos = new HashMap<>();
        this.Recomendadores = new HashMap<>();
        this.Recomendador = new HashMap<>();
        this.numeroRecomendaciones = 5;
        factoriaAlgoritmos = factoria;
        Algoritmo = factoriaAlgoritmos.GetListaAlgoritmos();
        Distancias = factoriaAlgoritmos.GetListaDistancias();
    }
<<<<<<< HEAD

    public void inicializar() throws FilaVacia, IOException, TablaVacia, Comparator {
        Algoritmos.put(0,new KNN(new EuclideanDistance()));
        Algoritmos.put(1,new KNN(new ManhattanDistance()));
        Algoritmos.put(2,new KMeans(numeroClusters,numeroIteracion,4321,new EuclideanDistance()));
        Algoritmos.put(3,new KMeans(numeroClusters,numeroIteracion,4321,new ManhattanDistance()));
        int i = 0;
        for (Algorithm algoritmo : Algoritmos.values()) {
            Recomendadores.put(i,new RecSys(algoritmo));
            entrenarunearRecsys(Recomendadores.get(i),i);
            i++;
=======
    public List<String> setRecomendaciones(String Algorithm,String Distance) throws FilaVacia, IOException, TablaVacia, Comparator {
        System.out.println("Creando recomendador");
        RecSys recSys;
        if (Recomendador.containsKey(Algorithm)){
            HashMap<String,RecSys> auxiliar = Recomendador.get(Algorithm);
            if (auxiliar.containsKey(Distance)){
                recSys = auxiliar.get(Distance);
            }else {
                recSys = factoriaAlgoritmos.Selecion(Algorithm,Distance);
                auxiliar.put(Distance,factoriaAlgoritmos.Selecion(Algorithm,Distance));
                Recomendador.put(Algorithm,auxiliar);
            }
        }else {
            System.out.println("Primera");
            HashMap<String,RecSys> auxiliar = new HashMap<>();
            System.out.println("MAPA");
            recSys = factoriaAlgoritmos.Selecion(Algorithm,Distance);
            System.out.println("fabrica");
            auxiliar.put(Distance,recSys);
            Recomendador.put(Algorithm,auxiliar);
>>>>>>> 02f4723267c2cdeb66f6120c1e4525cbbf059f81
        }
        System.out.println("Ayuda");
        return    recSys.recommend(getCancionRecomendada(),getNumeroRecomendaciones());
    }

<<<<<<< HEAD
    private void entrenarunearRecsys(RecSys recSys, int i) throws IOException, FilaVacia, TablaVacia, Comparator {
        String sep = FileSystems.getDefault().getSeparator();
        String ruta = "." + sep + "data"+ sep;
        if (i == 0 || i == 1) {
            recSys.train(new CSVLabeledFileReader(ruta+"songs_train.csv").readTableFromSource());
            recSys.run(new CSVLabeledFileReader(ruta+"songs_train.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
        } else if (i == 2 || i == 3) {
            recSys.train(new CSVUnlabeledFileReader(ruta+"songs_train_withoutnames.csv").readTableFromSource());
            recSys.run(new CSVUnlabeledFileReader(ruta+"songs_train_withoutnames.csv").readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
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

=======
>>>>>>> 02f4723267c2cdeb66f6120c1e4525cbbf059f81
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
