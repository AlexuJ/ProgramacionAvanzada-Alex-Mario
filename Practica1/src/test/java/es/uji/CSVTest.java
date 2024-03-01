package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CSVTest {
    private CSV Lector;
    private ArrayList<String> Ficheros;

    @BeforeAll
    void inicioClase() {
        Lector = new CSV();
        Ficheros = new ArrayList<>(); // Inicializa la lista de archivos
        Ficheros.add("./Practica1/miles_dolars.txt");
        Ficheros.add("./Practica1/iris.txt");
        Ficheros.add("RedBunny");
    }

    public List<Row> datosMillion() {
        int[][] datos = {
                { 1211, 1802 },
                { 1345, 2405 },
                { 1422, 2005 },
                { 1687, 2511 },
                { 1849, 2332 },
                { 2026, 2305 },
                { 2133, 3016 },
                { 2253, 3385 },
                { 2400, 3090 },
                { 2468, 3694 },
                { 2699, 3371 },
                { 2806, 3998 },
                { 3082, 3555 },
                { 3209, 4692 },
                { 3466, 4244 },
                { 3643, 5298 },
                { 3852, 4801 },
                { 4033, 5147 },
                { 4267, 5738 },
                { 4498, 6420 },
                { 4533, 6059 },
                { 4804, 6426 },
                { 5090, 6321 },
                { 5233, 7026 },
                { 5439, 6964 }
        };
        List<Row> filas = new ArrayList<>();
        for (int[] filaDatos : datos) {
            Row fila = new Row();
            fila.setData(filaDatos);
            filas.add(fila);
        }
        return filas;
    }

    @Test
    @DisplayName("Lectura de archivo existente")
    void prueba1() throws FileNotFoundException {
        Table table = Lector.readerTable(Ficheros.get(0));
        List<Row> lista = table.getRows();
        List<Row> listaEsperada = datosMillion();
        for (int i = 0; i < listaEsperada.size(); i++) {
            assertArrayEquals(lista.get(i).getData().toArray(), lista.get(i).getData().toArray());
        }

    }

    @Test
    @DisplayName("Lectura de archivo inexistente")
    void prueba2() {
        // Asegúrate de que la lectura de un archivo inexistente lance una excepción
        // Puedes usar try-catch para manejar la excepción y asegurarte de que la prueba
        // falle si no se lanza la excepción esperada
        try {
            Lector.readerTable("archivo_inexistente.txt");
            // Si no se lanza ninguna excepción, la prueba debe fallar
            fail("Se esperaba una excepción FileNotFoundException");
        } catch (FileNotFoundException e) {
            // Se espera una excepción FileNotFoundException, la prueba pasa
        }
    }
}