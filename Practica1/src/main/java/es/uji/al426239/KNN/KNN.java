package es.uji.al426239.KNN;

import es.uji.al426239.CSV.CSV;
import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaTable.TableWithLabels;

import java.io.FileNotFoundException;
import java.util.List;

public class KNN {
    private TableWithLabels TablaEntrenamiento;
    private  String fichero;

    //Constructor para inicializar una tabla de datos y modificar la tabla de entrenamiento
    public KNN(String fichero) throws FileNotFoundException {
        CSV Lector = new CSV();
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        train(TablaDatos);
    }

    //Método para devolver la tabla de entrenamiento
    public TableWithLabels getTablaEntrenamiento() {
        return TablaEntrenamiento;
    }

    //Método para aplicar a la tabla de entrenamiento los datos en cuestión
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }

    //Método para estimar el número de la clase al que pertenece esos datos
    public Integer estimate(List<Double> data) {
        int Estimacion = 0;
        double MenorAproximacion = 20.0;
        for (int i=0; i<TablaEntrenamiento.getRows().size(); i++) {
            Double MetricaEuclidiana = CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRowAt(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRowAt(i).getNumberClass();
            }
        }
        return Estimacion;
    }

    //Método para calcular la métrica Euclidiana
    public Double CalcularMetricaEuclidiana(List<Double> data, Row fila) {
        if (data.size() != fila.getData().size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
        double MetricaEuclidiana = 0.0;
        for (int i=0; i<data.size(); i++) {
            MetricaEuclidiana+=Math.pow(data.get(i)-fila.getData().get(i).doubleValue(),2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }
}
