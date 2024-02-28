package es.uji;

import java.util.Date;
import java.util.List;

public class RowWithLabels extends Row {
    // etiqueta convertida en numero
    public int numberClass;

    // getNumberClass returnea numberClass
    public int getNumberClass() {
        return numberClass;
    }
    @Override
    public List<Double> getData() {
        return super.getData();
    }
    // creo el set de NumberClass
    public void setNumberClass(int number) {
        numberClass = number;
    }
}
