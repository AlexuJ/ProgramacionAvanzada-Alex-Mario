package es.uji.al426239.lectordetablas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import es.uji.al426239.rowytable.Row;
import es.uji.al426239.rowytable.Table;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    @Override
    Table createTable() {
        return new Table();
    }

    @Override
    void openSource(String source) throws FileNotFoundException {
        sc = new Scanner(new File(source));
    }

    @Override
    void processHeaders(String headers) {
        table.setHeaders(new ArrayList<>(Arrays.asList(headers.split(","))));
    }

    @Override
    void processData(String data) {
        String[] linea = data.split(",");
        Row fila = new Row();
        for (String s : linea) {
            fila.setData(Double.valueOf(s));
        }
        table.setRow(fila);
    }

    @Override
    void closeSource() {
        sc.close();
    }

    @Override
    boolean hasMoreData() {
        return sc.hasNextLine();
    }

    @Override
    String getNextData() {
        return sc.nextLine();
    }
}
