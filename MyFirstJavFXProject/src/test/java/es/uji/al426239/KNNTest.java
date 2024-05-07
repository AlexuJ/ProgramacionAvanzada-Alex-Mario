package es.uji.al426239;

import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.metodos.Convertidor;
import es.uji.al426239.rowytable.TableWithLabels;
import es.uji.al426239.algoritmos.KNN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class KNNTest {
        private TableWithLabels tablaEntrenamiento;
        private KNN Estimador;
        private Distance distance;
        private List<Double> Datos1, Datos2, Datos3, Datos4, Datos5;
        private List<Integer> Datos6;

        @BeforeEach
        void inicioClase() {
                try {
                        distance = new EuclideanDistance();
                        String separator = System.getProperty("file.separator");
                        ReaderTemplate lector = new CSVLabeledFileReader("." + separator + "iris.csv");
                        Estimador = new KNN(distance);
                        tablaEntrenamiento = (TableWithLabels) lector.readTableFromSource();
                        Estimador.train(tablaEntrenamiento);
                        Datos1 = new ArrayList<>(List.of(4.2, 2.6, 1.2, 0.7));
                        Datos2 = new ArrayList<>(List.of(7.2, 5.6, 2.5, 1.9));
                        Datos3 = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0));
                        Datos4 = new ArrayList<>(List.of(10.2, 7.6, 4.2, 2.7));
                        Datos5 = new ArrayList<>(List.of(6.2, 4.5, 3.5, 1.5));
                        Datos6 = new ArrayList<>(List.of(1, 2, 3, 4));
                } catch (FileNotFoundException e) {
                        // Manejar la excepción adecuadamente
                        e.printStackTrace();
                }
        }

        @DisplayName("Devuelve TablaEntrenamiento")
        @Test
        void getTablaEntramiento() {
                // variable de error
                double delta = 0.001;
                Convertidor convertidor = new Convertidor();
                Assertions.assertEquals(0.592, distance.calculateDistance(Datos1, convertidor.convertirADouble(tablaEntrenamiento.getRow(41).getData())),
                                delta, "Debería dar 0.592");
                Assertions.assertEquals(4.019, distance.calculateDistance(Datos2, convertidor.convertirADouble(tablaEntrenamiento.getRow(1).getData())),
                                delta, "Debería dar 4,019");
                Assertions.assertEquals(7.246, distance.calculateDistance(Datos3, convertidor.convertirADouble(tablaEntrenamiento.getRow(79).getData())),
                                delta, "Debería dar 7,246");
                Assertions.assertEquals(6.722, distance.calculateDistance(Datos4, convertidor.convertirADouble(tablaEntrenamiento.getRow(121).getData())),
                                delta, "Debería dar 6,722");
                Assertions.assertEquals(2.341, distance.calculateDistance(Datos5, convertidor.convertirADouble(tablaEntrenamiento.getRow(147).getData())),
                                delta, "Debería dar 2,341");
        }

        @DisplayName("Estimación")
        @Test
        void estimate() {
                Assertions.assertEquals(Datos6.get(0), Estimador.estimate(Datos1));
                Assertions.assertEquals(Datos6.get(0), Estimador.estimate(Datos3));
                Assertions.assertEquals(Datos6.get(2), Estimador.estimate(Datos4));
                Assertions.assertEquals(Datos6.get(1), Estimador.estimate(Datos5));
        }
}