package es.uji.al426239.KMeans;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.Excepciones.Comparator;
import es.uji.al426239.Interfaz.Algorithm;
import es.uji.al426239.KNN.KNN;

import java.util.*;

public class KMeans implements Algorithm<Table,List<Number>,Integer> {
    private final int numClusters;
    private final int numIterations;
    private final List<Row> Representantes;
    private final long seed;
    private final Map<Integer,List<Row>> Grupos;
    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Representantes = new ArrayList<>();
        this.Grupos = new HashMap<>();
    }
    @Override
    public void train(Table datos) throws Comparator {
        inicializar(datos);
        for (int i=0; i < numIterations; i++) {
            for (Row fila : datos.getRow()) {
                Grupos.get(estimate(fila.getData())).add(fila);
            }
            calcularCentroides(datos);
        }
    }
    private void inicializar(Table datos) {
        Random random = new Random(seed);
        for (int i=0; i < numClusters; i++) {
            int fila = random.nextInt(datos.getRow().size());
            if (!Representantes.contains(datos.getRow(fila))) {
                Representantes.add(datos.getRow(fila));
            }
            Grupos.put(i, new ArrayList<>());
        }
    }
    private void calcularCentroides(Table datos) throws Comparator {
        if (numClusters > datos.getRow().size()) {
            throw new Comparator(numClusters,datos.getRow().size());
        } else {
            Representantes.clear();
            SumarYDividir();
        }
    }
    @Override
    public Integer estimate(List<Number> dato) {
        KNN calculador = new KNN();
        double menor = Double.MAX_VALUE;
        int grupo = 0;
        for (int i=0; i < Representantes.size(); i++) {
            double cercania = calculador.CalcularMetricaEuclidiana(convertirADouble(dato), Representantes.get(i));
            if (cercania < menor) {
                menor = cercania;
                grupo = i;
            }
        }
        return grupo;
    }
    private List<Double> convertirADouble(List<Number> datos) {
        List<Double> datoDoubles = new ArrayList<>();
        for (Number num : datos) {
            datoDoubles.add(num.doubleValue());
        }
        return datoDoubles;
    }
    private void SumarYDividir() {
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
}
