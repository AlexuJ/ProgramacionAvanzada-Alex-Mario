package es.uji.al426239.algoritmos;

import es.uji.al426239.distance.FactoryDistance;
import es.uji.al426239.distance.intFactoriasDis;

import java.util.ArrayList;
import java.util.List;

public class factoriaAlgoritmos implements IntFactoriasAl {

        private intFactoriasDis Factoriadistancias;


        private  int numeroIteracion;
        private  int numeroClusters;
        private List<String> Algoritmos;
        private List<String> Distancias;

        private  int seed;
        public factoriaAlgoritmos(intFactoriasDis distacias) {
            Factoriadistancias = distacias;
            this.numeroIteracion = 200;
            this.numeroClusters = 15;
            this.seed = 4321;
            Distancias = Factoriadistancias.GetLista();
            Algoritmos = new ArrayList<>(List.of("KNN","KMEANS"));
        }
        @Override
        public Algorithm Selecion(String elecion, String distance){
           elecion = elecion.toUpperCase();
            Algorithm algorithm;
            switch (elecion) {
                case "KNN":
                    algorithm = IsKnn(distance);
                    break;
                case "KMEANS":
                     algorithm = IsKmeans(distance);
                    break;

                default:
                    throw  new IllegalArgumentException("Parametro no reconocido");
            }
            return algorithm;
        }
        private KNN IsKnn(String disce){
            return  new KNN(Factoriadistancias.Selecion(disce));
        }
        private KMeans IsKmeans(String disce){
            return  new KMeans(numeroClusters,numeroIteracion,seed,Factoriadistancias.Selecion(disce));
        }
        @Override
        public List<String> GetListaAlgoritmos(){
            return Algoritmos;
        }
        @Override
        public List<String> GetListaDistancias(){
            return Distancias;
        }



}
