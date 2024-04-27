package es.uji.al426239;

import org.junit.jupiter.api.Test;

import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.distance.ManhattanDistance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

class ManhattanDistanceTest {
    private Distance manhattan;
    private Double resultado1;
    private Double resultado2;
    private Double resultado3;
    private Double resultado4;
    private List<Double> resultados;
    private List<Double> parametros1;
    private List<Double> parametros2;
    private List<Double> parametros3;
    private List<Double> parametros4;

    private Double error;

    @BeforeEach
    void inicioClase() {
        manhattan = new ManhattanDistance();
        // parametros
        error = 0.1;
        parametros1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        parametros2 = Arrays.asList(5.0, 4.0, 3.0, 2.0, 1.0);
        parametros3 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        parametros4 = Arrays.asList(4.0, 2.0, 3.0, 1.0, 2.0);

        // resultados
        resultado1 = 0.0;
        resultado2 = 0.0;
        resultado3 = -3.0;
        resultado4 = 0.0;
        resultados = Arrays.asList(resultado1, resultado2, resultado3, resultado4);

    }

    @Test
    void calculateDistance() throws IllegalArgumentException {
        // comprobamos que da 0 si se le pasan dos datos como parametros
        assertEquals(resultados.get(0), manhattan.calculateDistance(parametros1, parametros1));
        // comprobamos que da si invertimos la lista de parametros que me tenga que da
        // 0.0 pero ajustado con un error de 0.1
        assertEquals(resultados.get(1), manhattan.calculateDistance(parametros2, parametros1));
        // listas diferentes
        assertEquals(resultados.get(2), manhattan.calculateDistance(parametros4, parametros1));

        // excepcion
        assertThrows(IllegalArgumentException.class, () -> {
            manhattan.calculateDistance(parametros1, parametros3);
        }, "Debería lanzar IllegalArgumentException si los parámetros son incorrectos");

    }
}