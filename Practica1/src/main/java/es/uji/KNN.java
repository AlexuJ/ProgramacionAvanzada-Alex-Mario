package es.uji;

import java.io.FileNotFoundException;
import java.util.List;

public class KNN {
    private static final String fichero = "./iris.csv";
    private TableWithLabels TablaEntrenamiento;
    public KNN() throws FileNotFoundException {
        CSV Lector = new CSV();
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        train(TablaDatos);
    }
    public TableWithLabels getTablaEntrenamiento() {
        return TablaEntrenamiento;
    }
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }
    public Integer estimate(List<Double> data) {
        int Estimacion = 0;
        double MenorAproximacion = 20.0;
        for (int i=0; i<TablaEntrenamiento.datos.size(); i++) {
            Double MetricaEuclidiana = CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRowAt(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                System.out.println(TablaEntrenamiento.getRowAt(i).data);
                Estimacion = TablaEntrenamiento.getRowAt(i).getNumberClass();
            }
        }
        return Estimacion;
    }
    public Double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        if (data.size() != fila.data.size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
        double MetricaEuclidiana = 0.0;
        for (int i=0; i<data.size(); i++) {
            MetricaEuclidiana+=Math.pow(data.get(i)-fila.data.get(i).doubleValue(),2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }
}
