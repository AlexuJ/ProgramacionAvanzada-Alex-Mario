package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class CSVTest {
    private CSV Lector;
    private ArrayList<String> Ficheros;
    @BeforeAll
    void  inicioClase(){
        Lector = new CSV();
        Ficheros.add("./Practica1/miles_dolars.txt");
        Ficheros.add("./Practica1/iris.txt");
        Ficheros.add("RedBunny");
    }
    @Test
     @DisplayName("Metodo readTable sin exception ")
    void prueba1(){

    }
    @Test
    @DisplayName("Metodo readTable con exception ")
    void prueba2(){

    }
}