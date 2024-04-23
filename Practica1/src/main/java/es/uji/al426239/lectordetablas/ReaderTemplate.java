package es.uji.al426239.lectordetablas;

import es.uji.al426239.rowytable.Table;

public abstract class ReaderTemplate {
    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();
    public final Table readTableFromSource() {

    }
}
