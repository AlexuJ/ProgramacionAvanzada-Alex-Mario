package es.uji.al426239.Kmeans;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaTable.Table;
import java.util.*;

public class Kmeans {
    private final int numClusters;
    private final int numIterations;
    private final long seed;
    private final List<Row> Representantes;
    private final Map<Integer,List<Row>> Grupos;
    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Representantes = new ArrayList<>();
        this.Grupos = new HashMap<>();
    }
    public void train(Table datos) {
        inicializar(datos);
        for (int i=0; i < numIterations; i++) {
            for (Row fila : datos.getRows()) {
                Grupos.get(estimate(fila.getData())).add(fila);
            }
            calcularCentroides();
        }
    }
    private void inicializar(Table datos) {
        Random random = new Random(100);
        for (int i=0; i < getNumClusters(); i++) {
            int fila = random.nextInt(datos.getRows().size());
            if (!Representantes.contains(datos.getRowAt(fila))) {
                Representantes.add(datos.getRowAt(fila));
            }
            Grupos.put(i, new ArrayList<>());
        }
    }
    private void calcularCentroides() {
        Representantes.clear();
        for (List<Row> grupo : Grupos.values()) {
            Row Centroide = new Row();
            for (Row fila : grupo) {
                for (int i = 0; i < fila.getData().size(); i++) {
                    Centroide.sumeData(i, fila.getData().get(i));
                }
            }
            for (int j=0; j < Centroide.size();j ++) {
                Centroide.splitData(j, grupo.size());
            }
            Representantes.add(Centroide);
        }
    }
    public int estimate(List<Number> dato) {
        double menor = Double.MAX_VALUE;
        double cercania = 0.0;
        int grupo = 0;
        for (int i=0; i < Representantes.size(); i++) {
            for (int j=0; j < Representantes.get(i).getData().size(); j++) {
                cercania += Representantes.get(i).getData().get(j).doubleValue()-dato.get(j).doubleValue();
            }
            if (cercania < 0) {
                cercania *= -1;
            }
            if (cercania < menor) {
                menor = cercania;
                grupo = i;
            }
        }
        return grupo;
    }
    public int getNumClusters() {
        return numClusters;
    }
    public int getNumIterations() {
        return numIterations;
    }
    public long getSeed() {
        return seed;
    }
    public List<Row> getRepresentantes() {
        return Representantes;
    }
    private Map<Integer,List<Row>> getGrupos() {
        return Grupos;
    }
}
