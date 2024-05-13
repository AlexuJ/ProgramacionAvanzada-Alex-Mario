package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;
import java.io.FileNotFoundException;

public class Controlador implements AnswerControlador {
    private Modelo modelo;
    private String cancionRecomendada;
    private Vista vista;
    public Controlador() {
        this.modelo = new Modelo();
    }
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }
    public void setScene(Vista visual){
        this.vista = visual;
    }
    public void EventAlgorithm(int caso) throws FileNotFoundException, FilaVacia, TablaVacia, Comparator {
        if (caso == 1) {
            modelo.setAlgorithm(new KNN(modelo.getDistance()));
        } else {
            modelo.setAlgorithm(new KMeans(modelo.getNumeroClusters(), modelo.getNumeroIteracion(), 4321,modelo.getDistance()));
        }
    }
    public void EventDistance(int caso) {
        if (caso == 1) {
            modelo.setDistance(new EuclideanDistance());
        } else {
            modelo.setDistance(new ManhattanDistance());
        }
    }
}
