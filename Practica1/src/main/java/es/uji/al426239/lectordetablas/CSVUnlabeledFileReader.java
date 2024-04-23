package es.uji.al426239.lectordetablas;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    public CSVUnlabeledFileReader(String source, String headers, String data) {
        super(source, headers, data);
    }
    @Override
    void openSource(String source) {

    }

    @Override
    void processHeaders(String headers) {

    }

    @Override
    void processData(String data) {

    }

    @Override
    void closeSource() {

    }

    @Override
    boolean hasMoreData() {
        return false;
    }

    @Override
    String getNextData() {
        return null;
    }
}
