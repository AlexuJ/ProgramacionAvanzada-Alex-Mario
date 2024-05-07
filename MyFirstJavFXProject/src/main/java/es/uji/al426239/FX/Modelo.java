package es.uji.al426239.FX;

import es.uji.al426239.algoritmos.Algorithm;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;

import java.util.List;

public class Modelo {
    //sera con la llamada esperar a ver si le pasa el knn o el
    private  Algorithm algorithm;
    private Distance distance;
    private  int numeroIteracion;
    private  int numeroClusters;
    public  Modelo(){
        distance = new EuclideanDistance();
        algorithm = new KNN(distance);
    }

    public void  setAlgorithm(boolean alfa){
        if(alfa){
            algorithm = new KNN(distance);
        }else {
            algorithm = new KMeans(numeroClusters,numeroIteracion,100,distance);
        }
    }
    public  void  setDistance(Distance distance2){
        distance =distance2;
    }
    public  void  getnumeroIteraciones(int numero){
        numeroIteracion = numero;
    }
    public  void  getnumeroClusters(int numero){
        numeroIteracion = numero;
    }


}
