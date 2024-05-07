package es.uji.al426239.lectordetablas;

import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.RowWithLabels;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.rowytable.TableWithLabels;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader {
    public CSVLabeledFileReader(String source) {
        super(source);
    }

    @Override
    Table createTable() {
        return new TableWithLabels();
    }

    @Override
    void processData(String data) {
        String[] linea = data.split(",");
        Row fila = new RowWithLabels(((TableWithLabels) table).GetKey(linea[linea.length - 1]));
        for (int i = 0; i < linea.length - 1; i++) {
            fila.setData(Double.valueOf(linea[i]));
        }
        table.setRow(fila);
    }
}