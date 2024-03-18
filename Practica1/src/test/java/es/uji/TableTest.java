package es.uji;

import es.uji.CarpetaRow.Row;
import es.uji.CarpetaTable.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    // inicio prueba 1 de test
    private Table tabla;
    private List<String> headersPrueba;

    @BeforeEach
    void inicio() {
        tabla = new Table();
        List<Row> filasPrueba = new ArrayList<>();
        filasPrueba = CreadorFilas(filasPrueba, 5);
        headersPrueba = new ArrayList<>();

    }

    // funcion que añade un caracter por string que se le pasa
    public List<String> CreadorHeaders(List<String> encabezado, String palabra) {
        for (char i : palabra.toCharArray()) {
            encabezado.add(String.valueOf(i));
        }
        return encabezado;
    }

    // un bucle doble que genera filas y las almacena con numeros aleatorios
    public List<Row> CreadorFilas(List<Row> filas, int numeroFilas) {
        Random random = new Random();
        for (int i = 0; i < numeroFilas; i++) {
            Row fila = new Row();
            for (int j = 0; j < 5; j++) {
                fila.setUnicData(random.nextDouble(1, 10));
            }
            filas.add(fila);
        }
        return filas;
    }

    @Test
    @DisplayName("SetRowTest")
    void SetRow() {
        List<Row> ListaFilas = new ArrayList<>();
        ListaFilas = CreadorFilas(ListaFilas, 1);
        Row fila = ListaFilas.get(0);
        tabla.setRow(fila);
        List<Number> filaClase = tabla.getRows().get(0).getData();
        List<Number> filaPrueba = fila.getData();
        assertArrayEquals(filaClase.toArray(), filaPrueba.toArray(), "Las filas no coinciden");
    }

    @Test
    @DisplayName("GetRowAtTest")
    void GetRow() {
        List<Row> ListaFilas = new ArrayList<>();
        ListaFilas = CreadorFilas(ListaFilas, 5);
        for (Row listaFila : ListaFilas) {
            tabla.setRow(listaFila);
        }
        for (int j = 0; j < ListaFilas.size(); j++) {
            List<Number> filaPrueba = ListaFilas.get(j).getData();
            List<Number> filaTabla = tabla.getRowAt(j).getData();
            assertArrayEquals(filaTabla.toArray(), filaPrueba.toArray(), "Las filas no coinciden ");

        }
    }

    @Test
    @DisplayName("SetHeaderTest && GetHeaderTest")
    void SetHeader() {
        headersPrueba = CreadorHeaders(headersPrueba, "MARIO");
        tabla.setHeaders(CreadorHeaders(tabla.getHeaders(), "MARIO"));
        assertEquals(headersPrueba, tabla.getHeaders(), "Los Headers No coinciden");

    }

}