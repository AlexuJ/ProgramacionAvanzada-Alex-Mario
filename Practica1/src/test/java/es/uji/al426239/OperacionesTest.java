package es.uji.al426239;

import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.metodos.Convertidor;
import es.uji.al426239.metodos.Operaciones;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class OperacionesTest {
    private Operaciones calculador;
    private Convertidor convertidor;
    private List<Number> data1, data2, data3, data4;
    private Row fila1, fila2, fila3, fila4;
    // parametros del CalcularCentroides
    private Table tabla;
    private Integer numeroclusters;
    private Map<Integer, List<Row>> grupos;

    @BeforeEach
    void inicio() throws FileNotFoundException {
        // variable para el test de metricaeuclidiana
        calculador = new Operaciones();
        convertidor = new Convertidor();
        fila1 = new Row();
        fila2 = new Row();
        fila3 = new Row();
        fila4 = new Row();
        fila1.setData(Arrays.asList(4.0, 5.0, 6.0));
        fila2.setData(Arrays.asList(1.0, 1.0, 1.0));
        fila3.setData(Arrays.asList(4.0, 6.0, 8.0));
        fila4.setData(Arrays.asList(1.0, 2.0, 3.0, 1.0, 2.0, 3.0));
        data1 = Arrays.asList(1.0, 2.0, 3.0);
        data2 = Arrays.asList(1.0, 1.0, 1.0);
        data3 = Arrays.asList(1.0, 3.0, 4.0);
        data4 = Arrays.asList(2.0, 2.0, 3.0);
        // variables para el test calcular centroides
        String separator = System.getProperty("file.separator");
        CSV Lector = new CSV();
        grupos = new HashMap<>();
        tabla = Lector.readTable("." + separator + "FicheroPrueba4.csv");
        numeroclusters = 3;
    }

    @Test
    void MetricaEuclidiana() {
        double resultadoEsperado1 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 5.0, 2) + Math.pow(3.0 - 6.0, 2));
        assertEquals(resultadoEsperado1, calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data1), fila1));
        // Caso de prueba con listas del mismo tamaño y todos los elementos iguales
        assertEquals(0.0, calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data2), fila2));

        // Caso de prueba con listas del mismo tamaño y todos los elementos diferentes
        double resultadoEsperado3 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(3.0 - 6.0, 2) + Math.pow(4.0 - 8.0, 2));
        assertEquals(resultadoEsperado3, calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data3), fila3));

        // Caso de prueba con listas de diferentes tamaños
        assertThrows(IllegalArgumentException.class, () -> calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data4), fila4));
    }

    @Test
    void calcularCentroides() throws FilaVacia {
        KMeans algoritmo = new KMeans(numeroclusters,10,4321);
        for (Row datos : tabla.getRow()) {
            System.out.println(algoritmo.estimate(datos.getData()));
            grupos.computeIfAbsent(algoritmo.estimate(datos.getData()), k -> new ArrayList<>()).add(datos);
        }
        for (List<Row> grupo : grupos.values()) {
            for (Row fila : grupo) {
                System.out.println(fila.getData());
            }
        }
    }
}
