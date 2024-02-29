package es.uji;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KNN {
    private static final String fichero = "./Practica1/iris.csv";
    private CSV Lector = new CSV();
    private TableWithLabels TablaDatos;
    private TableWithLabels TablaEntrenamiento;
    public KNN() throws FileNotFoundException {
        TablaDatos = Lector.readTableWithLabels(fichero);
        TablaEntrenamiento = new TableWithLabels();
    }
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }
    public Integer estimate(List<Double> data) {
        train(TablaDatos);
        int Estimacion = 0;
        double MenorAproximacion = 3.0;
        for (int i=0; i<TablaEntrenamiento.datos.size(); i++) {
            Double MetricaEuclidiana = CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRowAt(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRowAt(i).getNumberClass();
            }
        }
        return Estimacion;
    }
    private Double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        double MetricaEuclidiana = 0.0;
        for (int i=0; i<data.size(); i++) {
            MetricaEuclidiana+=Math.pow(data.get(i)-fila.data.get(i),2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }
    public static void main(String[] args) throws FileNotFoundException {
        KNN prueba = new KNN();
        List<Double> datos = new ArrayList<>();
        datos.add(10.4);
        datos.add(8.7);
        datos.add(5.4);
        datos.add(3.2);
        System.out.println(prueba.estimate(datos));
    }
}
