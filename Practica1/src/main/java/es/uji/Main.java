package es.uji;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CSV prueba = new CSV();
        Table tabla = prueba.readTable("./Practica1/miles_dolars.txt");
        System.out.println(tabla.headers);
        for (Row fila : tabla.datos) {
            System.out.println(fila.data);
        }
    }
}
