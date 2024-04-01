package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.Row;
import java.util.ArrayList;
import java.util.List;

public class Table {
    // usamos las interfaces List y los datos deberan ser String y Row
    private List<String> headers;
    private List<Row> datos;

    // constructor para inicializar listas
    public Table() {
        headers = new ArrayList<>();
        datos = new ArrayList<>();
    }

    // metodo para consultar lo que hay en esta parte de la tabla
    public Row getRow(int rowNumber) {
        return datos.get(rowNumber);
    }

    public List<Row> getRow() {
        return datos;
    }

    // te permite añadir una fila al final
    public void setRow(Row fila) {
        setRow(fila, datos.size());
    }

    public void setRow(List<Row> filas) {
        datos = filas;
    }

    // te permite insertar una fila donde tu quieras hay que lanzar una excepcion si
    // indice es mayor que el tamaño que la tabla
    public void setRow(Row fila, int indice) {
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