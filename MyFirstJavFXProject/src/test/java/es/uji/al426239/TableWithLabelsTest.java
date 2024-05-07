package es.uji.al426239;

import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.rowytable.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

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
        headersPrueba = new ArrayList<>();

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