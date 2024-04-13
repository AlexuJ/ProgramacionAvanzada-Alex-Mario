package es.uji.al426239;

import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.TablaVacia;
import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class KMeansTest {
    private KMeans kmeans;
    private Table tabla;
    private Integer resultado;

    @BeforeEach
    void inicioClase() throws FileNotFoundException {
        String separator = System.getProperty("file.separator");
        CSV lector = new CSV();
        tabla = lector.readTable("." + separator + "FicheroPrueba3.csv");
        kmeans = new KMeans(3, 20, 4321);
        resultado = 0;
    }

    @Test
    void train() throws TablaVacia, FilaVacia {
        kmeans.train(tabla);
        for (List<Row> grupo : kmeans.getGrupos().values()) {
            for (Row fila : grupo) {
                System.out.println(fila.getData());
                System.out.println(kmeans.estimate(fila.getData()));
                Assertions.assertEquals(resultado, kmeans.estimate(fila.getData()));
            }
            resultado++;
        }
    }
}