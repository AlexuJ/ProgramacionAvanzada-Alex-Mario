package es.uji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
    private int numClusters;
    private int numIterations;
    private long seed;
    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
    }
    public void train(Table datos) {
        List<Row> RepresentantesIniciales = Inicializar(datos);
    }
    private List<Row> Inicializar(Table datos) {
        Random random = new Random(100);
        List<Row> RepresentantesIniciales = new ArrayList<>();
        for (int i=0; i<getNumClusters(); i++) {
            RepresentantesIniciales.add(datos.getRowAt(random.nextInt(datos.getRows().size())));
        }
        return RepresentantesIniciales;
    }
    private Double CalcularCentroide(List<Double> datos) {
    }
    public int estimate(List<Double> dato) {
    }
    private int getNumClusters() {
        return numClusters;
    }
    private int getNumIterations() {
        return numIterations;
    }
    private long getSeed() {
        return seed;
    }
}
