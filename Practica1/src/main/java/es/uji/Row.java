package es.uji;

import java.util.ArrayList;
import java.util.List;

public class Row {

    //Atributo
    public List<Double> data;
    //constructor
    public Row() {
        data = new ArrayList<>();
    }
    public List<Double> getData() {
        return data;
    }
    public void setData(List<Double> numero){
       data = numero ;
    }
    public Double ObtenerDato(int numero){
        if (numero>data.size()-1){
            return data.get(numero);
        }else {
            throw new  IndexOutOfBoundsException("Indice fuera de los limites");
        }
    }
}
