package es.uji.al426239;

import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.RowWithLabels;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.rowytable.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTemplatest {
    private ReaderTemplate Lector;
    private TableWithLabels tablaConEtiquetas;
    private TableWithLabels tableSinEtiquetas;
    private String separator;
    private Set<String> ConjuntoEtiquetas;
    private Table tableVacia;
    private Table tablaLLena;

    @BeforeEach
    void inicioClase() {
        separator = System.getProperty("file.separator");
        ArrayList<String> lineas = new ArrayList<>();
        tablaConEtiquetas = new TableWithLabels();
        String headers = "sepal length,sepal width,petal length,petal width,class";
        lineas.add("5.1,3.5,1.4,0.2,Iris-setosa");
        lineas.add("7.0,3.2,4.7,1.4,Iris-versicolor");
        lineas.add("7.7,2.6,6.9,2.3,Iris-virginica");
        ConjuntoEtiquetas = new HashSet<>();
        ArrayList<String> cabezeras = new ArrayList<>(Arrays.asList(headers.split(",")));
        for (int i = 0; i < lineas.size(); i++) {
            RowWithLabels Fila = new RowWithLabels(i);
            String[] linea = lineas.get(i).split(",");
            for (int j = 0; j < linea.length - 1; j++) {
                Fila.setData(Double.valueOf(linea[j]));
            }
            tablaConEtiquetas.SetKey(linea[linea.length - 1]);
            tablaConEtiquetas.setRow(Fila);
            ConjuntoEtiquetas.add(linea[linea.length - 1]);
        }
        tablaConEtiquetas.setHeaders(cabezeras);
        tablaLLena = new Table();
        tableVacia = new Table();
        lineas.clear();
        lineas.add("1211,1802");
        lineas.add("1345,2405");
        lineas.add("1422,2005");
        for (String s : lineas) {
            Row Fila = new Row();
            String[] linea = s.split(",");
            for (String string : linea) {
                Fila.setData(Double.valueOf(string));
            }
            tablaLLena.setRow(Fila);
        }
    }

    @Test
    @DisplayName("LecturaHeaders")
    void prueba1() throws FileNotFoundException {
        Lector = new CSVLabeledFileReader("." + separator + "FicheroPrueba.csv");
        tableSinEtiquetas = (TableWithLabels) Lector.readTableFromSource();
        assertEquals(tablaConEtiquetas.getHeaders(), tableSinEtiquetas.getHeaders());
    }

    @Test
    @DisplayName("LecturaRows")
    void prueba2() throws FileNotFoundException {
        Lector = new CSVLabeledFileReader("." + separator + "FicheroPrueba.csv");
        tableSinEtiquetas = (TableWithLabels) Lector.readTableFromSource();
        for (int i = 0; i < tablaConEtiquetas.getRow().size(); i++) {
            assertEquals(tablaConEtiquetas.getRow(i).getData(), tableSinEtiquetas.getRow(i).getData());
        }
        for (String string : ConjuntoEtiquetas) {
            assertEquals(tablaConEtiquetas.GetKey(string), tableSinEtiquetas.GetKey(string));
        }

    }

    @Test
    @DisplayName("LecturaRows")
    void prueba4() throws FileNotFoundException {
        Lector = new CSVUnlabeledFileReader("." + separator + "FicheroPrueba2.csv");
        tableVacia = Lector.readTableFromSource();
        for (int i = 0; i < tablaLLena.getRow().size(); i++) {
            assertEquals(tablaLLena.getRow().get(i).getData(), tableVacia.getRow().get(i).getData());
        }
    }

}