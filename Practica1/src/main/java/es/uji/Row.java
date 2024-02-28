package es.uji;

import java.util.ArrayList;
import java.util.List;

public class Row {
    public List<Double> data;
    public Row() {
        data = new ArrayList<>();
    }
    public List<Double> getData() {
        return data;
    }
}
