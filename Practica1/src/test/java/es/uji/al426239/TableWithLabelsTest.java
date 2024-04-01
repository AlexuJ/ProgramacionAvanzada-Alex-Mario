package es.uji.al426239;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Maneras de mejorar los test una clase con funciones y parametros que vayamos a usar para las pruebas
class TableWithLabelsTest {
    // inicio prueba 1 de test
    public List<Row> FilasPrueba;

    public List<String> headersPrueba;

    public Table tabla;

    @BeforeEach
    void inicio() {
        tabla = new TableWithLabels();
        FilasPrueba = new ArrayList<>();
        FilasPrueba = CreadorFilas(FilasPrueba, 5);
        headersPrueba = new ArrayList<>();

    }

    public List<Row> CreadorFilas(List<Row> filas, int numeroFilas) {
        Random random = new Random();
        for (int i = 0; i < numeroFilas; i++) {
            Row fila = new RowWithLabels(0);
            for (int j = 0; j < 5; j++) {
                fila.setData(random.nextDouble(1, 10));
            }
            filas.add(fila);
        }
        return filas;
    }

    @Test
    @DisplayName("GetSetTabla")
    void GetSetTablaWithLabels() {
        tabla.setRow(FilasPrueba); // Establecer las filas de prueba en la tabla
        List<Row> filasObtenidas = tabla.getRow(); // Obtener las filas de la tabla

        // Verificar que el número de filas es el mismo
        assertEquals(FilasPrueba.size(), filasObtenidas.size());

        // Verificar que cada fila de prueba se encuentra en la tabla
        for (int i = 0; i < FilasPrueba.size(); i++) {
            assertEquals(FilasPrueba.get(i), filasObtenidas.get(i));
        }
    }
}