package es.uji.al426239.algoritmos;

import es.uji.al426239.metodos.Operaciones;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import es.uji.al426239.metodos.Convertidor;
import java.util.*;

public class KMeans implements Algorithm<Table, List<Number>, Integer> {
    private int numClusters;
    private int numIterations;
    private List<Row> Representantes;
    private long seed;
    private Map<Integer, List<Row>> Grupos;

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
        for (int i = 0; i < numIterations; i++) {
            Grupos.clear();
            for (Row fila : datos.getRow()) {
                // si el resultado del stimate esta añade no hace nada si no existe la clave
                // añade la fila
                Grupos.computeIfAbsent(estimate(fila.getData()), k -> new ArrayList<>()).add(fila);
            }
            Representantes.clear();
            Operaciones calculador = new Operaciones();
            Representantes = calculador.calcularCentroides(datos, numClusters, Grupos, Representantes);
        }
    }

    private void inicializar(Table datos) {
        Random random = new Random(seed);
        for (int i = 0; i < numClusters; i++) {
            int fila = random.nextInt(datos.getRow().size());
            if (!Representantes.contains(datos.getRow(fila))) {
                Representantes.add(datos.getRow(fila));
            }
            Grupos.put(i, new ArrayList<>());
        }
    }

    @Override
    public Integer estimate(List<Number> dato) {
        Operaciones calculador = new Operaciones();
        Convertidor convertidor = new Convertidor();
        double menor = Double.MAX_VALUE;
        int grupo = 0;
        for (int i = 0; i < Representantes.size(); i++) {
            double cercania = calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(dato),
                    Representantes.get(i));
            if (cercania < menor) {
                menor = cercania;
                grupo = i;
            }
        }
        return grupo;
    }

    public Map<Integer, List<Row>> getGrupos() {
        return Grupos;
    }
}