package es.uji.al426239;

import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.algoritmos.TablaVacia;
import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.List;

class KMeansTest {
    private KMeans kmeans;
    private Table tabla;
    private Integer resultado;

    @BeforeEach
    void inicioClase() throws FileNotFoundException {
        String separator = System.getProperty("file.separator");
        ReaderTemplate lector = new CSVUnlabeledFileReader("." + separator + "FicheroPrueba3.csv");
        tabla = lector.readTableFromSource();
        Distance distance = new EuclideanDistance();
        kmeans = new KMeans(3, 20, 4321, distance);
        resultado = 0;
    }

    @Test
    void train() throws TablaVacia, FilaVacia {
        kmeans.train(tabla);
        for (List<Row> grupo : kmeans.getGrupos().values()) {
            for (Row fila : grupo) {
                Assertions.assertEquals(resultado, kmeans.estimate(fila.getData()));
            }
            resultado++;
        }
    }
}