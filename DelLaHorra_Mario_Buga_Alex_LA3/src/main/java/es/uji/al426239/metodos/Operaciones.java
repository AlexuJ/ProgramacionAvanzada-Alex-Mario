package es.uji.al426239.metodos;

import es.uji.al426239.rowytable.Row;
import java.util.List;
import java.util.Map;

public class Operaciones {
    public double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        if (data.size() != fila.getData().size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
        double MetricaEuclidiana = 0.0;
        for (int i = 0; i < data.size(); i++) {
            MetricaEuclidiana += Math.pow(data.get(i) - fila.getData().get(i).doubleValue(), 2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }

    public List<Row> calcularCentroides(Map<Integer, List<Row>> Grupos, List<Row> representantes) {
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
            representantes.add(centroide);
        }
        return representantes;
    }
}
