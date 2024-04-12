package es.uji.al426239;

import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {
    private KMeans kmeans ;
    private List<List<Number>> parametrosprueba;
    private List<Integer> respuestas;
    private String ficheroprueba ;
    private  Integer numeroclusters;
    private  Integer iteraciones;
    private  Long semilla;
    private Table tabla;
    private CSV lector;

    @BeforeEach
    void    inicioClase() throws FileNotFoundException {
       String separator = System.getProperty("file.separator");
       ficheroprueba = "." + separator + "FicheroPrueba3.csv";
       lector = new CSV();
        tabla = lector.readTable(ficheroprueba);
        parametrosprueba = new ArrayList<>();
        semilla = 4321L;
        numeroclusters = 8;
        iteraciones = 200;
        kmeans = new KMeans(numeroclusters, iteraciones, semilla);



    }
    @Test
    void train() throws Comparator {
        kmeans.train(tabla);
        Row fila = tabla.getRow(2);
        System.out.println(fila.getData());
        System.out.println(kmeans.estimate(fila.getData()));

    }

}