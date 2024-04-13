package es.uji.al426239.algoritmos;

import es.uji.al426239.metodos.Operaciones;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import es.uji.al426239.metodos.Convertidor;
import java.util.*;

public class KMeans implements Algorithm<Table, List<Number>, Integer> {
    private int numClusters;
    private final int numIterations;
    private List<Row> Representantes;
    private final long seed;
    private final Map<Integer, List<Row>> Grupos;
    private final Operaciones calculador;

    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Representantes = new ArrayList<>();
        this.Grupos = new HashMap<>();
        this.calculador = new Operaciones();
    }

    @Override
    public void train(Table datos) throws TablaVacia, FilaVacia {
        if (datos.getRow().isEmpty()) {
            throw new TablaVacia(datos);
        } else {
            inicializar(datos);
            for (int i = 0; i < numIterations; i++) {
                Grupos.clear();
                for (Row fila : datos.getRow()) {
                    // si existe una lista de filas en el mapa para la estimación, la devuelve, si no, la crea
                    // En cualquier caso añade la nueva fila
                    Grupos.computeIfAbsent(estimate(fila.getData()), k -> new ArrayList<>()).add(fila);
                }
                Representantes.clear();
                Representantes = calculador.calcularCentroides(Grupos, Representantes);
            }
        }
    }

    private void inicializar(Table datos) {
        Random random = new Random(seed);
        if (numClusters > datos.getRow().size()) {
            numClusters = datos.getRow().size();
        }
        for (int i = 0; i < numClusters; i++) {
            int fila = random.nextInt(datos.getRow().size());
            if (!Representantes.contains(datos.getRow(fila))) {
                Representantes.add(datos.getRow(fila));
            }
            Grupos.put(i, new ArrayList<>());
        }
    }

    @Override
    public Integer estimate(List<Number> dato) throws FilaVacia {
        if (dato.isEmpty()) {
            throw new FilaVacia(dato);
        }
        Convertidor convertidor = new Convertidor();
        double menor = Double.MAX_VALUE;
        int grupo = 0;
        for (int i = 0; i < Representantes.size(); i++) {
            double cercania = calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(dato), Representantes.get(i));
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