package es.uji.al426239;

import com.beust.ah.A;
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
    private List<Double> data1, data2, data3, data4, data5, data6, data7, data8;
    private Row fila1, fila2, fila3, fila4;
    // parametros del CalcularCentroides
    private Table tabla;
    private Integer numeroclusters;
    private Map<Integer, List<Row>> grupos;
    private List<Row> representantes;

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
        data4 = Arrays.asList(1.0, 2.0, 3.0);
        data1 = Arrays.asList(1.0, 2.0, 3.0);
        data2 = Arrays.asList(1.0, 1.0, 1.0);
        data3 = Arrays.asList(1.0, 2.0, 3.0);
        // variables para el test calcular centroides
        String separator = System.getProperty("file.separator");
        String rutaFicheroPrueba = "." + separator + "FicheroPrueba3.csv";
        CSV Lector = new CSV();
        tabla = new Table();
        tabla = Lector.readTable(rutaFicheroPrueba);
        numeroclusters = 3;
    }

    @Test
    void MetricaEuclidiana() {
        double resultadoEsperado1 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 5.0, 2) + Math.pow(3.0 - 6.0, 2));
        assertEquals(resultadoEsperado1, calculador.CalcularMetricaEuclidiana(data1, fila1));
        // Caso de prueba con listas del mismo tamaño y todos los elementos iguales
        assertEquals(0.0, calculador.CalcularMetricaEuclidiana(data2, fila2));

        // Caso de prueba con listas del mismo tamaño y todos los elementos diferentes
        double resultadoEsperado3 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 6.0, 2) + Math.pow(3.0 - 8.0, 2));
        assertEquals(resultadoEsperado3, calculador.CalcularMetricaEuclidiana(data3, fila3));

        // Caso de prueba con listas de diferentes tamaños
        assertThrows(IllegalArgumentException.class, () -> calculador.CalcularMetricaEuclidiana(data4, fila4));
    }

    @Test
    void calcularCentroides() {
        List<Row> representantesADevolver = new ArrayList<>();
        grupos = new HashMap<>();
        System.out.println(calculador.calcularCentroides(tabla, numeroclusters, grupos, new ArrayList<>()));
    }
}
