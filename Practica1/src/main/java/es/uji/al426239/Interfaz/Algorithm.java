package es.uji.al426239.Interfaz;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.Excepciones.Comparator;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface Algorithm<T extends Table,U extends List<? extends Number>,W extends Integer> {
    void train(T Tabla) throws Comparator;
    W estimate(U datos);
    default double CalcularMetricaEuclidiana(@NotNull List<Double> data, Row fila) {
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
