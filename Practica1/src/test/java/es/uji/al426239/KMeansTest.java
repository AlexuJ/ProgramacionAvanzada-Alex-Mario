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
    private Table tabla;

    @BeforeEach
    void inicioClase() throws FileNotFoundException {
        String separator = System.getProperty("file.separator");
        CSV lector = new CSV();
<<<<<<< HEAD
        tabla = lector.readTable("." + separator + "data" + separator + "songs_train_withoutnames.csv");
        kmeans = new KMeans(15, 200, 4321);
=======
        String rutaFicheroPrueba = "." + separator + "FicheroPrueba3.csv";
        tabla = lector.readTable(rutaFicheroPrueba);
        parametrosprueba = new ArrayList<>();
        kmeans = new KMeans(3, 2000, 4321);
>>>>>>> 424aba1e9d82d3169532f8d4077dd6ea3b5c2007
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