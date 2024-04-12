package es.uji.al426239;

import es.uji.al426239.metodos.MetricaEuclidiana;
import es.uji.al426239.row_table.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MetricaEuclidianaTest {
    @BeforeEach
    void inicio(){

    }
    //hay que comprobar que se lance la excepcion y que funciona la operacion
    @Test
    void calcularMetricaEuclidiana() {

            MetricaEuclidiana metricaEuclidiana = new MetricaEuclidiana();

            // Caso de prueba con listas del mismo tamaño
            List<Double> data1 = Arrays.asList(1.0, 2.0, 3.0);
            Row fila1 = new Row();
            fila1.setData(Arrays.asList(4.0, 5.0, 6.0));
            double resultadoEsperado1 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 5.0, 2) + Math.pow(3.0 - 6.0, 2));
            assertEquals(resultadoEsperado1, metricaEuclidiana.CalcularMetricaEuclidiana(data1, fila1));

            // Caso de prueba con listas del mismo tamaño y todos los elementos iguales
            List<Double> data2 = Arrays.asList(1.0, 1.0, 1.0);
            Row fila2 = new Row();
            fila2.setData(Arrays.asList(1.0, 1.0, 1.0));
            assertEquals(0.0, metricaEuclidiana.CalcularMetricaEuclidiana(data2, fila2));

            // Caso de prueba con listas del mismo tamaño y todos los elementos diferentes
            List<Double> data3 = Arrays.asList(1.0, 2.0, 3.0);

            Row fila3 = new Row();
            fila3.setData(Arrays.asList(4.0, 6.0, 8.0));
            double resultadoEsperado3 = Math.sqrt(Math.pow(1.0 - 4.0, 2) + Math.pow(2.0 - 6.0, 2) + Math.pow(3.0 - 8.0, 2));
            assertEquals(resultadoEsperado3, metricaEuclidiana.CalcularMetricaEuclidiana(data3, fila3));

            // Caso de prueba con listas de diferentes tamaños
            List<Double> data4 = Arrays.asList(1.0, 2.0, 3.0);
            Row fila4 = new Row();
            fila4.setData(Arrays.asList(4.0, 5.0, 6.0, 7.0));// fila4 tiene un elemento más
            assertThrows(IllegalArgumentException.class, () -> metricaEuclidiana.CalcularMetricaEuclidiana(data4, fila4));
        }
    }
    
