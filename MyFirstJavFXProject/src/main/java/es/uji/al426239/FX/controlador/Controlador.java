package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;

import java.io.FileNotFoundException;

public class Controlador implements AnswerControlador {
    private Modelo modelo;
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
            modelo.IsKnn();
        } else {
            modelo.IsKmeans();
        }
    }
    public void EventDistance(int caso) {
        if (caso == 1) {
            modelo.IsEuclidean();
        } else {
            modelo.IsManhhatn();
        }
    }
}
