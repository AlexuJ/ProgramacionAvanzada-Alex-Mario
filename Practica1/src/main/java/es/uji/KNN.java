package es.uji;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KNN {
    private static final String fichero = "./Practica1/iris.csv";
    private CSV Lector = new CSV();
    private TableWithLabels TablaDatos;
    private TableWithLabels TablaEntrenamiento;
<<<<<<< Updated upstream
    public KNN() throws FileNotFoundException {
        TablaDatos = Lector.readTableWithLabels(fichero);
        TablaEntrenamiento = new TableWithLabels();
=======

    protected KNN() throws FileNotFoundException {
        CSV Lector = new CSV();
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        train(TablaDatos);
    }

    public TableWithLabels getTablaEntrenamiento() {
        return TablaEntrenamiento;
>>>>>>> Stashed changes
    }

    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }

    public Integer estimate(List<Double> data) {
        train(TablaDatos);
        int Estimacion = 0;
        double MenorAproximacion = 20.0;
        for (int i = 0; i < TablaEntrenamiento.datos.size(); i++) {
            Double MetricaEuclidiana = CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRowAt(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRowAt(i).getNumberClass();
            }
        }
        return Estimacion;
    }
<<<<<<< Updated upstream
    private Double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
=======

    public Double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        if (data.size() != fila.data.size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
>>>>>>> Stashed changes
        double MetricaEuclidiana = 0.0;
        for (int i = 0; i < data.size(); i++) {
            MetricaEuclidiana += Math.pow(data.get(i) - fila.getData().get(i).doubleValue(), 2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }
}
