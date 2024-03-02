package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Table {
    // usamos las interfaces List y los datos deberan ser String y Row
    private List<String> headers;
    private List<Row> datos;

    // constructor para inicializar listas
    public Table() {
        headers = new ArrayList<>();
        datos = new ArrayList<>();
    }
    //Método para añadir filas sin etiquetas
    public void addFilaSinEtiquetas(String[] linea) {
        Row FilaSinEtiqueta = new Row();
        for (String dato : linea) {
            FilaSinEtiqueta.getData().add(Double.valueOf(dato));
        }
        datos.add(FilaSinEtiqueta);
    }
    //Método para añadir las cabeceras independientemente del fichero
    public List<String> Cabeceras(String fichero) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split(",");
        return new ArrayList<>(Arrays.asList(linea));
    }
    // metodo para consultar lo que hay en esta parte de la tabla
    public Row getRowAt(int rowNumber) {
        return datos.get(rowNumber);
    }

    public List<Row> getRows() {
        return datos;
    }

    // te permite añadir una fila al final
    public void setRow(Row fila) {
        setRowAt(fila, datos.size());
    }

    public void setRows(List<Row> filas) {
        datos = filas;
    }

    // te permite insertar una fila donde tu quieras hay que lanzar una excepcion si
    // indice es mayor que el tamaño que la tabla
    public void setRowAt(Row fila, int indice) {
        if (indice > datos.size()) {
            throw new IndexOutOfBoundsException("El indice proporcionado es mas grande que el tamaño de la tabla");
        }
        datos.add(indice, fila);
    }

    // esto se usa para obtener la cabezera
    public List<String> getHeaders() {
        return headers;
    }

    // esto se usa para setear la cabecera
    public void setHeaders(List<String> encabezados) {
        headers = encabezados;
    }
}
