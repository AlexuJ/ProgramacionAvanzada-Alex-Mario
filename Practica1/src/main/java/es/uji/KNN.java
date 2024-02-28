package es.uji;

import java.io.FileNotFoundException;
import java.util.List;

public class KNN {
    private final String fichero1 = "./Practica1/iris.csv";
    public void train(TableWithLabels data) throws FileNotFoundException {
        CSV Lector = new CSV();
        data = Lector.readTableWithLabels(fichero1);
    }

    public Integer estimate(List<Double> data) throws FileNotFoundException {
        TableWithLabels TablaDeDatos = new TableWithLabels();
        train(TablaDeDatos);
    }
}
