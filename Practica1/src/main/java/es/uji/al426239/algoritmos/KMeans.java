package es.uji.al426239.algoritmos;

import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import es.uji.al426239.metodos.Convertidor;
import es.uji.al426239.metodos.MetricaEuclidiana;
import java.util.*;

public class KMeans implements Algorithm<Table,List<Number>,Integer> {
    private int numClusters;
    private final int numIterations;
    private  final List<Row> Representantes;
    private  final long seed;
    private  final Map<Integer,List<Row>> Grupos;
    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Representantes = new ArrayList<>();
        this.Grupos = new HashMap<>();
    }
    @Override
    public void train(Table datos) {
        inicializar(datos);
        for (int i=0; i < numIterations; i++) {
            Grupos.clear();
            for (Row fila : datos.getRow()) {
                Grupos.computeIfAbsent(estimate(fila.getData()), k -> new ArrayList<>()).add(fila);
            }
            Representantes.clear();
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
    private void calcularCentroides(Table datos) {
        if (numClusters > datos.getRow().size()) {
            numClusters = datos.getRow().size();
        }
        SumarYDividir();
    }
    @Override
    public Integer estimate(List<Number> dato) {
        MetricaEuclidiana calculador = new MetricaEuclidiana();
        Convertidor convertidor = new Convertidor();
        double menor = Double.MAX_VALUE;
        int grupo = 0;
        for (int i=0; i < Representantes.size(); i++) {
            double cercania = calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(dato), Representantes.get(i));
            if (cercania < menor) {
                menor = cercania;
                grupo = i;
            }
        }
        return grupo;
    }
    private void SumarYDividir() {
        for (List<Row> grupo : Grupos.values()) {
            Row centroide = new Row();
            centroide.inicializarTamanyo(grupo.get(0).size());
            for (Row fila : grupo) {
                for (int j = 0; j < fila.getData().size(); j++) {
                    centroide.sumeData(j, fila.getData().get(j).doubleValue());
                }
            }
            for (int k = 0; k < centroide.size(); k++) {
                centroide.splitData(k, grupo.size());
            }
            Representantes.add(centroide);
        }
    }
    public Map<Integer, List<Row>> getGrupos() {
        return Grupos;
    }
}