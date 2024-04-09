package es.uji.al426239.KNN;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import es.uji.al426239.Interfaz.Algorithm;
import java.util.List;

public class KNN implements Algorithm<TableWithLabels, List<Double>, Integer> {
    private TableWithLabels TablaEntrenamiento;

    public KNN() {
        this.TablaEntrenamiento = new TableWithLabels();
    }

    // Método para aplicar a la tabla de entrenamiento los datos en cuestión
    @Override
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }

    // Método para estimar el número de la clase al que pertenece esos datos
    @Override
    public Integer estimate(List<Double> data) {
        int Estimacion = 0;
        double MenorAproximacion = Double.MAX_VALUE;
        for (int i = 0; i < TablaEntrenamiento.getRow().size(); i++) {
            Double MetricaEuclidiana = CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRow(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRow(i).getNumberClass();
            }
        }
        return Estimacion;
    }

    // Método para devolver la tabla de entrenamiento
    public TableWithLabels getTablaEntrenamiento() {
        return TablaEntrenamiento;
    }
}
