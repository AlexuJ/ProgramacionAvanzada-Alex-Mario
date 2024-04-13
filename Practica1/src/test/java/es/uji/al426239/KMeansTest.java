package es.uji.al426239;

import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class KMeansTest {
    private KMeans kmeans;
    private List<List<Number>> parametrosprueba;
    private Table tabla;

    @BeforeEach
    void inicioClase() throws FileNotFoundException {
        String separator = System.getProperty("file.separator");
        CSV lector = new CSV();
        String rutaFicheroPrueba = "." + separator + "FicheroPrueba3.csv";
        tabla = lector.readTable(rutaFicheroPrueba);
        parametrosprueba = new ArrayList<>();
        kmeans = new KMeans(3, 2000, 4321);
    }

    @Test
    void train() {
        kmeans.train(tabla);
        for (List<Row> grupo : kmeans.getGrupos().values()) {
            for (Row fila : grupo) {
                System.out.println(fila.getData());
                System.out.println(kmeans.estimate(fila.getData()));

            }
        }
    }
}