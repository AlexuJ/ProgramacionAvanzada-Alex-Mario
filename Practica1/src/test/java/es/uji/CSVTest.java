package es.uji;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    private CSV Lector;
    private ArrayList<String> Ficheros;

    @BeforeEach
    void inicioClase() {
        Lector = new CSV();
        Ficheros = new ArrayList<>();
        Ficheros.add("./miles_dolars.csv");
        Ficheros.add("./iris.csv");
        Ficheros.add("RedBunny");
    }

    @Test
    @DisplayName("Dimensiones readTable")
    void prueba1() throws FileNotFoundException {
        String ruta = Ficheros.get(0);
        Table table = Lector.readTable(ruta);
        assertEquals(25, table.getRows().size(), "Las filas no coinciden");
        assertEquals(2, table.getRowAt(1).data.size(), "Las columnas no coinciden");
    }

    @Test
    @DisplayName("TestCabezeras Tabla")
    void prueba2() throws FileNotFoundException {
        Table Tabla = Lector.readTable(Ficheros.get(0));
        List<String> heders = new ArrayList<>(List.of("Miles","Dollars"));
        assertEquals(Tabla.getHeaders(), heders);
    }

    @Test
    @DisplayName("TestCabezeras TablaWithHeders")
    void prueba3() throws FileNotFoundException {
        TableWithLabels Tabla = Lector.readTableWithLabels(Ficheros.get(1));
        List<String> heders = new ArrayList<>(List.of("sepal length", "sepal width", "petal length", "petal width", "class"));
        assertEquals(Tabla.getHeaders(), heders);
    }

    @Test
    @DisplayName("Dimensiones TableWithLabels")
    void prueba4() throws FileNotFoundException {
        TableWithLabels Tabla = Lector.readTableWithLabels(Ficheros.get(1));

        assertEquals(Tabla.getRows().size(), 150);
        assertEquals(Tabla.getRowAt(1).data.size(), 4);
    }

    @Test
    @DisplayName("Contenido de Filas")
    void prueba5() throws FileNotFoundException {
        Table table = Lector.readTable(Ficheros.get(0));
        ArrayList<Number> array = new ArrayList<>();
        array.add(3209.0);
        array.add(4692.0);
        Row fila = new Row();
        fila.setData(array);
        Row filaTabla = table.getRowAt(13);
        for (int i = 0; i < fila.getData().size(); i++) {
            assertEquals(fila.getData().get(i), filaTabla.getData().get(i));
        }
    }

    @Test
    @DisplayName("Contenido de Filas")
    void prueba6() throws FileNotFoundException {
        Table tabla = Lector.readTableWithLabels(Ficheros.get(1));
        ArrayList<Number> array = new ArrayList<>();
        array.add(4.3);
        array.add(3.0);
        array.add(1.1);
        array.add(0.1);
        Row fila = new Row();
        fila.setData(array);
        Row filaTabla = tabla.getRowAt(13);
        for (int i = 0; i < fila.getData().size(); i++) {
            assertEquals(fila.getData().get(i), filaTabla.getData().get(i));
        }
    }

    @Test
    @DisplayName("Contenido de Filas")
    void prueba7() throws FileNotFoundException {
        TableWithLabels table = Lector.readTableWithLabels(Ficheros.get(1));
        RowWithLabels Row1 = new RowWithLabels();
        Row1.setNumberClass(1);
        assertEquals(Row1.getNumberClass(), table.getRowAt(1).getNumberClass());
        Row1.setNumberClass(2);
        assertEquals(table.getRowAt(70).getNumberClass(), Row1.getNumberClass());
        Row1.setNumberClass(3);
        assertEquals(table.getRowAt(130).getNumberClass(), Row1.getNumberClass());
    }
}