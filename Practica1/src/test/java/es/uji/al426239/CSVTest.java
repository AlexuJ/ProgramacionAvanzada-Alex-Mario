package es.uji.al426239;

import es.uji.al426239.CSV.CSV;
import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    private CSV Lector;
    private ArrayList<String> Ficheros;
    private ArrayList<String> cabezeras;
    private ArrayList<String> Lineas;
    private TableWithLabels tablaConEtiquetas;
    private TableWithLabels tableSinEtiquetas;
    private String separator;
    private Set<String> ConjuntoEtiquetas;
    private Table tableVacia;
    private Table tablaLLena;

    @BeforeEach
    void inicioClase() {
        Lector = new CSV();
        Ficheros = new ArrayList<>();
        separator = System.getProperty("file.separator");
        System.out.println(separator);
        Lineas = new ArrayList<>();
        cabezeras = new ArrayList<>();
        tablaConEtiquetas = new TableWithLabels();
        String headers = "sepal length,sepal width,petal length,petal width,class";
        Lineas.add("5.1,3.5,1.4,0.2,Iris-setosa");
        Lineas.add("7.0,3.2,4.7,1.4,Iris-versicolor");
        Lineas.add("7.7,2.6,6.9,2.3,Iris-virginica");
        ConjuntoEtiquetas = new HashSet<>();
        for (String header : headers.split(",")) {
            cabezeras.add(header);
        }
        for (int i = 0; i < Lineas.size(); i++) {
            RowWithLabels Fila = new RowWithLabels(i);
            String linea[] = Lineas.get(i).split(",");
            for (int j = 0; j < linea.length - 1; j++) {
                Fila.setData(Double.valueOf(linea[j]));
            }
            tablaConEtiquetas.SetKey(linea[linea.length - 1]);
            tablaConEtiquetas.setRow(Fila);
            ConjuntoEtiquetas.add(linea[linea.length - 1]);
        }
        tablaConEtiquetas.setHeaders(cabezeras);
        Ficheros.add("." + separator + "FicheroPrueba.csv");
        tablaLLena = new Table();
        tableVacia = new Table();
        Lineas.clear();
        Lineas.add("1211,1802");
        Lineas.add("1345,2405");
        Lineas.add("1422,2005");
        for (int i = 0; i < Lineas.size(); i++) {
            Row Fila = new Row();
            String linea[] = Lineas.get(i).split(",");
            for (int j = 0; j < linea.length - 1; j++) {
                Fila.setData(Double.valueOf(linea[j]));
            }
            tablaLLena.setRow(Fila);
        }
        Ficheros.add("." + separator + "FicheroPrueba2.csv");

    }

    @Test
    @DisplayName("LecturaHeaders")
    void prueba1() throws FileNotFoundException {

        tableSinEtiquetas = Lector.readTableWithLabels("." + separator + "FicheroPrueba.csv");
        assertEquals(tablaConEtiquetas.getHeaders(), tableSinEtiquetas.getHeaders());

    }

    @Test
    @DisplayName("LecturaRows")
    void prueba2() throws FileNotFoundException {
        tableSinEtiquetas = Lector.readTableWithLabels("." + separator + "FicheroPrueba.csv");
        for (int i = 0; i < tablaConEtiquetas.getRow().size(); i++) {
            System.out.println("Hola");
            System.out
                    .println(tablaConEtiquetas.getRow(i).getData() + " " + tableSinEtiquetas.getRow().get(i).getData());
            assertEquals(tablaConEtiquetas.getRow(i).getData(), tableSinEtiquetas.getRow(i).getData());
        }
        for (String string : ConjuntoEtiquetas) {
            assertEquals(tablaConEtiquetas.GetKey(string), tableSinEtiquetas.GetKey(string));
        }

    }

    @Test
    @DisplayName("LecturaRows")
    void prueba4() throws FileNotFoundException {
        tableVacia = Lector.readTable("." + separator + "FicheroPrueba2.csv");
        for (int i = 0; i < tablaLLena.getRow().size(); i++) {
            assertEquals(tablaLLena.getRow().get(i).getData(), tableVacia.getRow().get(i).getData());
        }
    }

}