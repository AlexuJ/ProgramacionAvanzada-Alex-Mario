package es.uji.al426239.algoritmos;

import es.uji.al426239.distance.Distance;
import es.uji.al426239.metodos.CalcularCentroides;
import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.metodos.Convertidor;
import java.util.*;

public class KMeans implements Algorithm<Table, List<Number>, Integer> {
    private int numClusters;
    private final int numIterations;
    private List<Row> Representantes;
    private final long seed;
    private final Map<Integer, List<Row>> Grupos;
    private Distance distance;
    private final CalcularCentroides calculador;

    public KMeans(int numClusters, int numIterations, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.Representantes = new ArrayList<>();
        this.Grupos = new HashMap<>();
        this.distance = distance;
        this.calculador = new CalcularCentroides();
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
            double cercania = distance.calculateDistance(convertidor.convertirADouble(dato), convertidor.convertirADouble(Representantes.get(i).getData()));
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
    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}