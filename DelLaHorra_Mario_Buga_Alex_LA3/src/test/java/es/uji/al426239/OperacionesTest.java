package es.uji.al426239;

import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.lectordetablas.CSV;
import es.uji.al426239.metodos.Convertidor;
import es.uji.al426239.metodos.Operaciones;
import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;
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
    private Integer numeroclusters, iteraciones, seed;
    private Map<Integer, List<Row>> grupos;
    private List<List<Number>> resultados;

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
        iteraciones = 10;
        seed = 4321;
        resultados = new ArrayList<>();
        List<Number> resultados1 = Arrays.asList(1.0, 2.0, 3.0);
        List<Number> resultados2 = Arrays.asList(1.0, 1.0, 1.0);
        List<Number> resultados3 = Arrays.asList(1.0, 3.0, 4.0);
        List<Number> resultados4 = Arrays.asList(2.0, 2.0, 3.0);
        List<Number> resultados5 = Arrays.asList(2.0, 3.0, 4.0);
        List<Number> resultados6 = Arrays.asList(5.0, 2.0, 5.0);
        List<Number> resultados7 = Arrays.asList(4.0, 6.0, 7.0);
        resultados.add(resultados1);
        resultados.add(resultados2);
        resultados.add(resultados3);
        resultados.add(resultados4);
        resultados.add(resultados5);
        resultados.add(resultados6);
        resultados.add(resultados7);
    }

    @Test
    void MetricaEuclidiana() {
        double resultadoEsperado1 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 5.0, 2) + Math.pow(3.0 - 6.0, 2));
        assertEquals(resultadoEsperado1,
                calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data1), fila1));
        // Caso de prueba con listas del mismo tamaño y todos los elementos iguales
        assertEquals(0.0, calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data2), fila2));

        // Caso de prueba con listas del mismo tamaño y todos los elementos diferentes
        double resultadoEsperado3 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(3.0 - 6.0, 2) + Math.pow(4.0 - 8.0, 2));
        assertEquals(resultadoEsperado3,
                calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data3), fila3));

        // Caso de prueba con listas de diferentes tamaños
        assertThrows(IllegalArgumentException.class,
                () -> calculador.CalcularMetricaEuclidiana(convertidor.convertirADouble(data4), fila4));
    }

    @Test
    void calcularCentroides() throws FilaVacia {
        KMeans algoritmo = new KMeans(numeroclusters, iteraciones, seed);
        int contador = 0;
        for (Row datos : tabla.getRow()) {
            grupos.computeIfAbsent(algoritmo.estimate(datos.getData()), k -> new ArrayList<>()).add(datos);
        }
        for (List<Row> grupo : grupos.values()) {
            for (Row fila : grupo) {
                assertEquals(fila.getData(), resultados.get(contador));
                contador++;

            }
        }
    }
}
