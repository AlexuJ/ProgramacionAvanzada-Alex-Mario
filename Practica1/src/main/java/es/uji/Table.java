package es.uji;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Table {
    // usamos las interfaces List y los datos deberan ser String y Row
    public List<String> headers;
    public List<Row> datos;
    public String fichero;

    // constructor crea un array list al cual se
    public Table() {

    }

    public void SetFichero(String ficher) {
        this.fichero = ficher;
    }

    // metodo para añadir filas atraves del fichero
    protected void addFilaSinEtiquetas(List<String> linea) {
        Row filaSinEtiqueta = new Row();
        for (String dato : linea) {
            filaSinEtiqueta.setUnicData(Double.valueOf(dato));
        }
        setRow(filaSinEtiqueta);
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

    public List<String> setHeadersList(String divisor, String lista) {
        List<String> encabezados = new ArrayList<>();
        encabezados = Arrays.asList(lista.split(divisor));
        return encabezados;
    }

    public void SetTableFichero(Scanner scanner, String separador) throws FileNotFoundException {
        setHeadersList(scanner.nextLine(), separador);
        while (scanner.hasNextLine()) {
            addFilaSinEtiquetas(Arrays.asList(scanner.nextLine().split(separador)));
        }
        scanner.close();
    }
}
