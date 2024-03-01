package es.uji;

public class RowWithLabels extends Row {
    // etiqueta convertida en numero
    public int numberClass;

    // getNumberClass returnea numberClass
    @Override
    public int getNumberClass() {
        return numberClass;
    }

    // creo el set de NumberClass
    public void setNumberClass(int number) {
        numberClass = number;
    }
}
