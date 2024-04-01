package es.uji.al426239.CarpetaRow;

import java.util.ArrayList;
import java.util.List;

public class Row {
    // se almacena una list de cualquier dato numerico que necesitemos
    // usamos la interfaz Number y List para poder obtener flexivilidad en codigo
    private List<Number> data;

    // constructor de la clase crea una ArrayList
    public Row() {
        data = new ArrayList<>();
    }

    // devuelve data
    public List<Number> getData() {
        return data;
    }

    // pasar una lista al atributo data
    public void setData(List<Number> newData) {
        data = newData;
    }

    // Podria ser que este metodo no fuera Necesario
    // Aviso Metodo Peligroso Necesario Lanzar Una Execpecion si i supera el tamaño
    public void sumeData(int i, Number newData) {
        data.set(i, data.get(i).doubleValue() + newData.doubleValue());
    }

    public void splitData(int i, Number Splitter) {
        data.set(i, data.get(i).doubleValue() / Splitter.doubleValue());
    }

    public void setData(Number nuevoNumero) {
        data.add(nuevoNumero);
    }

    public int size() {
        return data.size();
    }
}
