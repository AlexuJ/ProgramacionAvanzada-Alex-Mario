package es.uji.al426239.algoritmos;

import es.uji.al426239.distance.Distance;
import es.uji.al426239.metodos.Convertidor;
import es.uji.al426239.rowytable.TableWithLabels;
import java.util.List;

public class KNN implements Algorithm<TableWithLabels, List<Double>, Integer> {
    private TableWithLabels TablaEntrenamiento;
    private Distance distance;

    public KNN(Distance distance) {
        this.TablaEntrenamiento = new TableWithLabels();
        this.distance = distance;
    }

    // Método para aplicar a la tabla de entrenamiento los datos en cuestión
    @Override
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }

    // Método para estimar el número de la clase al que pertenece esos datos
    @Override
    public Integer estimate(List<Double> data) {
        Convertidor convertidor = new Convertidor();
        int Estimacion = 0;
        double MenorAproximacion = Double.MAX_VALUE;
        for (int i = 0; i < TablaEntrenamiento.getRow().size(); i++) {
            double MetricaEuclidiana = distance.calculateDistance(data,
                    convertidor.convertirADouble(TablaEntrenamiento.getRow(i).getData()));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRow(i).getNumberClass();
            }
        }
        return Estimacion;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
