package es.uji;

import java.util.*;

public class Kmeans {
    private final int numClusters;
    private final int numIterations;
    private final long seed;
    private Map<Integer,List<Row>> Grupos;
    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Grupos = new HashMap<>();
    }
    public void train(Table datos) {
        List<Row> Representantes = inicializar(datos);
        for (int i=0; i<numIterations; i++) {
            for (Row fila : datos.getRows()) {
                AsignarGrupo(fila,Representantes);
            }
            Representantes = CalcularCentroides();
        }
    }
    private List<Row> inicializar(Table datos) {
        Random random = new Random(100);
        List<Row> Representantes = new ArrayList<>();
        for (int i=0; i < getNumClusters(); i++) {
            int fila = random.nextInt(datos.getRows().size());
            if (!Representantes.contains(datos.getRowAt(fila))) {
                Representantes.add(datos.getRowAt(fila));
            }
            Grupos.put(i, new ArrayList<>());
        }
        return Representantes;
    }
    private void AsignarGrupo(Row fila, List<Row> Representantes) {
        double menor = 10.0;
        double cercania = 0.0;
        int grupo = 0;
        for (int i=0; i < Representantes.size(); i++) {
            for (Number dato : Representantes.get(i).getData()) {
                cercania += dato.doubleValue()-fila.getData().get(i).doubleValue();
            }
            if (cercania < 0) {
                cercania *= -1;
            }
            if (cercania < menor) {
                menor = cercania;
                grupo = i;
            }
        }
        Grupos.get(grupo).add(fila);
    }
    private List<Row> CalcularCentroides() {
        List<Row> CentroidesGrupos = new ArrayList<>();
        int i = 1;
        for (List<Row> grupo : Grupos.values()) {
            Row Centroide = new Row();
            for (Number dato : grupo.get(i).getData()) {
                Centroide.sumeData(i++,dato);
                if (i == 3) {
                    i = 1;
                }
            }
            for (int j=0; j < Centroide.size(); j++) {
                Centroide.splitData(j, grupo.size());
            }
            CentroidesGrupos.add(Centroide);
        }
        return CentroidesGrupos;
    }
    public int estimate(List<Double> dato) {
        return 0;
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
