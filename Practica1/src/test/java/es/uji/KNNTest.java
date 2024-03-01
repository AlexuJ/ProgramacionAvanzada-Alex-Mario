package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

class KNNTest {
    private static final String fichero = "./iris.csv";
    private static TableWithLabels TablaEntramiento;

    @BeforeAll
    static void inicioClase() throws FileNotFoundException {
        CSV Lector = new CSV();
        KNN Estimador = new KNN();
        TableWithLabels TablaDatos = Lector.readTableWithLabels(fichero);
        Estimador.train(TablaDatos);
        TablaEntramiento = Estimador.getTablaEntrenamiento();
    }

    @DisplayName("Devuelve TablaEntrenamiento")
    @Test
    void getTablaEntramiento() {

    }

    @DisplayName("Distancia euclidiana")
    @Test
    void CalcularMetricaEuclidiana() {
    }

    @DisplayName("Estimación")
    @Test
    void estimate() {
    }
}