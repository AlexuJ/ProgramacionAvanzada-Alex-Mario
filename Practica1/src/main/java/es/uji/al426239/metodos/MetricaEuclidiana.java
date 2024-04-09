package es.uji.al426239.metodos;

import es.uji.al426239.row_table.Row;
import java.util.List;
public class MetricaEuclidiana {
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
}
