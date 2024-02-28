package es.uji;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KNN {
    private TableWithLabels TablaEntrenamiento;
    private final String fichero = "./Practica1/iris.csv";
    public KNN() {
        TablaEntrenamiento = new TableWithLabels();
    }
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }
    public Integer estimate(List<Double> data) throws FileNotFoundException {
        CSV Lector = new CSV();
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        train(TablaDatos);
        for (Row fila : TablaEntrenamiento.datos) {
            CalcularMetricaEuclidiana(data, fila);
        }
        return 0;
    }
    private void CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        double MetricaEuclidiana = 0.0;
        for (int i=0; i<data.size(); i++) {
            MetricaEuclidiana+=Math.pow(data.get(i)-fila.data.get(i),2);
        }
        System.out.println(Math.sqrt(MetricaEuclidiana));
    }
    public static void main(String[] args) throws FileNotFoundException {
        KNN prueba = new KNN();
        List<Double> datos = new ArrayList<>();
        datos.add(5.2);
        datos.add(3.7);
        datos.add(1.2);
        datos.add(0.5);
        System.out.println(prueba.estimate(datos));
    }
}
