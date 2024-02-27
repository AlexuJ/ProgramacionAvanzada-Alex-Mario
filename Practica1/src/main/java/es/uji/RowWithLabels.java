package es.uji;

import java.util.List;

public class RowWithLabels extends Row {
    //etiqueta convertida en numero
    public int numberClass;
    //getNumberClass returnea numberClass
    public int getNumberClass() {
        return numberClass;
    }
    //getData te da la lista de numeros
    @Override
    public List<Number> getData() {
        return super.getData();
    }
    //se invoca al metododo setData de la clase madre
    @Override
    public void setData(List<Number> NewData){
        super.setData(NewData);
    }
    //creo el set de NumberClass
    public void  setNumberClass(int number){
        numberClass = number ;
    }

}
