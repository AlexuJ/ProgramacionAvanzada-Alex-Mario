package es.uji.al426239;

import es.uji.al426239.algoritmos.Algorithm;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.KMeans;
import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.Table;
<<<<<<< HEAD
import es.uji.al426239.sistema_de_recomendacion.RecSys;
=======
>>>>>>> 3d9dddb1a5e281a1a39581f2712fb5f0f3e2b6c1
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class KMeansTest {
    private KMeans kmeans ;
    private List<List<Number>> parametrosprueba;
    private  Integer numeroclusters;
    private  Integer iteraciones;
    private  Long semilla;
    private Table tabla;
    private CSV lector;

    @BeforeEach
    void inicioClase() throws FileNotFoundException {
       String separator = System.getProperty("file.separator");
       lector = new CSV();
       tabla = lector.readTable("."+ separator + "data"+ separator +"songs_train_withoutnames.csv");
       parametrosprueba = new ArrayList<>();
       semilla = 4321L;
       numeroclusters = 15;
       iteraciones = 200;
       kmeans = new KMeans(numeroclusters, iteraciones, semilla);
    }
    @Test
    void train() {
        kmeans.train(tabla);
        for (List<Row> grupo : kmeans.getGrupos().values()) {
            for (Row fila : grupo) {
                System.out.println(kmeans.estimate(fila.getData()));
                System.out.println(kmeans.getGrupos().get(kmeans.estimate(fila.getData())));
            }
        }
    }
}