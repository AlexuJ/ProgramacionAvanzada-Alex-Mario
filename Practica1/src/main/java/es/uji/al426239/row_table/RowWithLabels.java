 package es.uji.al426239.row_table;

public class RowWithLabels extends Row {
    // etiqueta convertida en numero
    private int numberClass;
    public RowWithLabels(int numero) {
        numberClass = numero;
    }
    // getNumberClass returnea numberClass
    public int getNumberClass() {
        return numberClass;
    }

    // creo el set de NumberClass
    public void setNumberClass(int number) {
        numberClass = number;
    }
}