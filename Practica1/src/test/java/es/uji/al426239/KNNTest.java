package es.uji.al426239;

import es.uji.al426239.CSV.CSV;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import es.uji.al426239.KNN.KNN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class KNNTest {
    private static TableWithLabels TablaEntramiento;
    private static KNN Estimador;
    private static String separator;
    private static List<Double> Datos1, Datos2, Datos3, Datos4, Datos5;
    @BeforeAll
    static void inicioClase() throws FileNotFoundException {
        CSV Lector = new CSV();
        Estimador = new KNN();
        separator = System.getProperty("file.separator");
        System.out.println(separator);
        Estimador.train(Lector.readTableWithLabels("iris.csv"));
        TablaEntramiento = Estimador.getTablaEntrenamiento();
        Datos1 = new ArrayList<>(List.of(4.2,2.6,1.2,0.7));
        Datos2 = new ArrayList<>(List.of(7.2,5.6,2.5,1.9));
        Datos3 = new ArrayList<>(List.of(0.0,0.0,0.0,0.0));
        Datos4 = new ArrayList<>(List.of(10.2,7.6,4.2,2.7));
        Datos5 = new ArrayList<>(List.of(6.2,4.5,3.5,1.5));
    }

    @DisplayName("Devuelve TablaEntrenamiento")
    @Test
    void getTablaEntramiento() {
       Assertions.assertEquals(Estimador.getTablaEntrenamiento(),TablaEntramiento,"Debe devolver la TablaEntrenamiento");
    }
    @DisplayName("Distancia euclidiana")
    @Test
    void CalcularMetricaEuclidiana() {
        DecimalFormat df = new DecimalFormat("#0.000");
        Assertions.assertEquals("0,592",df.format(Estimador.CalcularMetricaEuclidiana(Datos1,TablaEntramiento.getRowAt(41))),"Debería dar 0,592");
        Assertions.assertEquals("4,019",df.format(Estimador.CalcularMetricaEuclidiana(Datos2,TablaEntramiento.getRowAt(1))),"Debería dar 4,019");
        Assertions.assertEquals("7,246",df.format(Estimador.CalcularMetricaEuclidiana(Datos3,TablaEntramiento.getRowAt(79))),"Debería dar 7,246");
        Assertions.assertEquals("6,722",df.format(Estimador.CalcularMetricaEuclidiana(Datos4,TablaEntramiento.getRowAt(121))),"Debería dar 6,722");
        Assertions.assertEquals("2,341",df.format(Estimador.CalcularMetricaEuclidiana(Datos5,TablaEntramiento.getRowAt(147))),"Debería dar 2,341");
    }
    @DisplayName("Estimación")
    @Test
    void estimate() {
        Assertions.assertEquals(1,Estimador.estimate(Datos1),"Debe dar clase 1");
        Assertions.assertEquals(1,Estimador.estimate(Datos2),"Debe dar clase 1");
        Assertions.assertEquals(1,Estimador.estimate(Datos3),"Debe dar clase 1");
        Assertions.assertEquals(3,Estimador.estimate(Datos4),"Debe dar clase 3");
        Assertions.assertEquals(2,Estimador.estimate(Datos5),"Debe dar clase 2");
    }
}