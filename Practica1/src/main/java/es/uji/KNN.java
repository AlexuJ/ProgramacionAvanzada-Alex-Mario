package es.uji;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KNN {
    private TableWithLabels TablaEntrenamiento;
    public KNN() {
        TablaEntrenamiento = new TableWithLabels();
    }
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }
    public Integer estimate(List<Double> data) throws FileNotFoundException {
        CSV Lector = new CSV();
        String fichero = "./Practica1/iris.csv";
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        train(TablaDatos);
        int Estimacion = 0;
        double MenorAproximacion = 3.0;
        for (int i=0; i<TablaEntrenamiento.datos.size(); i++) {
            double Aproximacion = (double) TablaDatos.getRowAt(i).getNumberClass() - CalcularMetricaEuclidiana(data, TablaDatos.datos.get(i));
            if (Aproximacion < 0) {
                Aproximacion*=-1;
            }
            if (Aproximacion < MenorAproximacion) {
                MenorAproximacion = Aproximacion;
                Estimacion = TablaDatos.getRowAt(i).getNumberClass();
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
        datos.add(5.2);
        datos.add(3.7);
        datos.add(1.2);
        datos.add(0.5);
        System.out.println(prueba.estimate(datos));
    }
}
