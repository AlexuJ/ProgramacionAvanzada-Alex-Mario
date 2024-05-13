package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;

public class Controlador implements AnswerControlador {
    private Modelo modelo;
    private Vista vista;
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }
    public void setVista(Vista vista){
        this.vista = vista;
    }
    public void EventAlgorithm(int caso) {
        if (caso == 1) {
            modelo.IsKnn();
        } else if (caso == 2) {
            modelo.IsKmeans();
        }
    }
    public void EventDistance(int caso) {
        if (caso == 1) {
            modelo.IsEuclidean();
        } else {
            modelo.IsManhattan();
        }
    }
}
