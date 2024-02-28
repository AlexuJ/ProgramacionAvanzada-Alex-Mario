package es.uji;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public List<String> headers;
    public List<Row> datos;
    public Table() {
        headers = new ArrayList<>();
        datos = new ArrayList<>();
    }
    public Row getRowAt(int rowNumber) {
        return datos.get(rowNumber);
    }
}
