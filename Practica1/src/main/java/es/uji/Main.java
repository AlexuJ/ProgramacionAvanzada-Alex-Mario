package es.uji;

import java.io.FileNotFoundException;

public class Main {
    public static final String fichero1 = "./Practica1/miles_dolars.txt";
    public static final String fichero2 = "./Practica1/iris.txt";
    public static void main(String[] args) throws FileNotFoundException {
        CSV prueba = new CSV();
        Table tabla = prueba.readTable(fichero1);
        System.out.println(tabla.headers);
        for (Row fila : tabla.datos) {
            System.out.println(fila.getData());
        }
        TableWithLabels TablaconEtiquetas = prueba.readTableWithLabels(fichero2);
        System.out.println(TablaconEtiquetas.headers);
        for (RowWithLabels fila : TablaconEtiquetas.DatosConEtiquetas) {
            System.out.println(fila.getData()+" "+fila.getNumberClass());
        }
    }
}
