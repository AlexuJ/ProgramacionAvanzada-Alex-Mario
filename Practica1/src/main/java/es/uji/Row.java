package es.uji;

import java.util.List;

public class Row {
    public List<Double> data;
    public Row() {
        this.data=getData();
    }
    public List<Double> getData() {
        return data;
    }
}
