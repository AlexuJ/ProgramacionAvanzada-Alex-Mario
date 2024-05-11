package es.uji.al426239.FX.controlador;

import es.uji.al426239.FX.modelo.Modelo;
import es.uji.al426239.FX.vista.Vista;
import es.uji.al426239.algoritmos.Algorithm;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controlador implements AnswerControlador{
    private Modelo modelo;
    private Vista vista;
    public  void getModelo(Modelo model){
        modelo = model;
    }
    public  void  getScene(Vista visual){
        visual = visual;
    }
    //esto podria ser un enum y esto se va directo a una interfaz
    @Override
    public void EventAlgorithm(int caso) {
       switch (caso){
        case 1 :
            modelo.IsKnn();
            break;

           default:
               modelo.IsKmeans();
       }
    }
    //esto igual un enum


    @Override
    public void EventDistance(int caso){
        switch (caso){
            case 1 :
                modelo.IsEuclidean();
                break;

            default:
                modelo.IsManhhatn();
        }
    }

}
