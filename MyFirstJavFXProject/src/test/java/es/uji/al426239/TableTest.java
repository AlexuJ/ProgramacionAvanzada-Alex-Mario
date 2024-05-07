package es.uji.al426239;

import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    // inicio prueba 1 de test
    private Table tablaLLena;
    private Table tablaVacia;
    private List<String> headersPrueba;
    private List<String> headersPrueba2;
    private int numeroFilas;
    private int numeroDatos;
    private List<Row> filasPrueba;
    private List<Row> filasPrueba2;

    @BeforeEach
    void inicio() {
        tablaLLena = new Table();
        numeroFilas = 5;
        numeroDatos = 5;
        filasPrueba = new ArrayList<>();
        filasPrueba2 = new ArrayList<>();
        CreadorFilas(filasPrueba);
        CreadorFilas(filasPrueba2);
        tablaVacia = new Table();
        tablaLLena.setRow(filasPrueba);
        headersPrueba = new ArrayList<>();
        headersPrueba2 = new ArrayList<>();
        String encabezados = "sepal length,sepal width,petal length,petal width,class";
        for (String encabezado : encabezados.split(",")) {
            headersPrueba.add(encabezado);
            headersPrueba2.add(encabezado);
        }
        tablaLLena.setHeaders(headersPrueba);

    }

    // un bucle doble que genera filas y las almacena con numeros aleatorios
    public void CreadorFilas(List<Row> filas) {
        for (int i = 0; i < numeroFilas; i++) {
            Row fila = new Row();
            for (int j = 0; j < numeroDatos; j++) {
                fila.setData(j);
            }
            filas.add(fila);
        }
    }

    public void ComparadorFilas(List<Row> Lista1, List<Row> Lista2) {
        for (int j = 0; j < numeroFilas; j++) {
            assertEquals(Lista1.get(j).getData(), Lista2.get(j).getData());
        }
    }

    public void Filas(Row Lista1, Row Lista2) {
        for (int j = 0; j < numeroDatos; j++) {
            assertEquals(Lista1.getData(), Lista2.getData());
        }
    }

    @Test
    @DisplayName("SetRowTest")
    void SetRow() {

        for (int i = 0; i < numeroFilas; i++) {
            tablaVacia.setRow(filasPrueba2.get(i));
            Filas(filasPrueba.get(i), tablaVacia.getRow(i));
        }
        ComparadorFilas(filasPrueba, filasPrueba2);
        tablaVacia.setRow(filasPrueba);
        ComparadorFilas(filasPrueba2, tablaVacia.getRow());

    }

    @Test
    @DisplayName("GetHeaders")
    void prueba2() {
        ComparadorFilas(filasPrueba2, tablaLLena.getRow());
        for (int j = 0; j < numeroFilas; j++) {
            Filas(tablaLLena.getRow(j), filasPrueba2.get(j));
        }

    }

    @Test
    @DisplayName("GetRowAtTest")
    void GetHeaders() {
        assertEquals(headersPrueba2, tablaLLena.getHeaders());
    }

    @Test
    @DisplayName("GetRowAtTest")
    void setHeaders() {
        tablaVacia.setHeaders(headersPrueba2);
        assertEquals(headersPrueba, tablaVacia.getHeaders());
    }

}