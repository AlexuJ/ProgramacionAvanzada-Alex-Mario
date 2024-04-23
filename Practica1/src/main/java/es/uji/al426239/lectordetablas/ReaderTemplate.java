package es.uji.al426239.lectordetablas;

import es.uji.al426239.rowytable.Table;

public abstract class ReaderTemplate {
    private String source;
    private String headers;
    private String data;
    public ReaderTemplate(String source, String headers, String data) {
        this.source = source;
        this.headers = headers;
        this.data = data;
    }
    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();
    public final Table readTableFromSource() {

    }
}
