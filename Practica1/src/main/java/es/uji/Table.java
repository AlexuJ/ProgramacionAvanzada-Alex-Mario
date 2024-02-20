package es.uji;

import java.util.Date;
import java.util.List;

public class Table {
    public List<String> headers;
    public List<Row> datos;
    public Row getRowAt(int rowNumber) {
        return datos.get(rowNumber);
    }
}
