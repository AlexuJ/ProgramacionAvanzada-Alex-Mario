package es.uji.al426239.lectordetablas;

import java.io.FileNotFoundException;
import java.util.Scanner;
import es.uji.al426239.rowytable.Table;

public abstract class ReaderTemplate {
    protected String source;
    protected Table table;
    protected Scanner sc;

    public ReaderTemplate(String source) {
        this.source = source;
        this.table = createTable();
    }

    abstract void openSource(String source) throws FileNotFoundException;

    abstract void processHeaders(String headers);

    abstract void processData(String data);

    abstract void closeSource();

    abstract boolean hasMoreData();

    abstract String getNextData();

    abstract Table createTable();

    public final Table readTableFromSource() throws FileNotFoundException {
        openSource(source);
        if (hasMoreData()) {
            processHeaders(getNextData());
            while (hasMoreData()) {
                processData(getNextData());
            }
            closeSource();
        } else {
            // lanza excepcion
        }
        return table;
    }
}
