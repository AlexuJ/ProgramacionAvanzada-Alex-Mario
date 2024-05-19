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
    private RecSys recomendadorActual;
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
        }
        System.out.println("Ayuda");
        System.out.println(cancionRecomendada);
        System.out.println(recSys.recommend(getCancionRecomendada(),getNumeroRecomendaciones()));
        recomendadorActual = recSys;
        return    recSys.recommend(getCancionRecomendada(),getNumeroRecomendaciones());


    }
    public void reset(){
        recomendadorActual.reset();
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
    @Override
    public List<String> GetAlgoritmos(){
        return Algoritmo;
    }
    @Override
    public List<String> GetDistancias(){
        return Distancias;
    }

}
