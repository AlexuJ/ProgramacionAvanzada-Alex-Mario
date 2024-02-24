package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
//version del CSV TEST 0.1
class CSVTest {
    //Atributo de la clase para hacer el test
    private CSV csvLector;
    private ArrayList<String> Ficheros;
    //Inicio la clase para las pruebas
    @BeforeAll
    void  inicioClase(){
        CSV csvLector = new CSV();
        Ficheros.add("./Practica1/miles_dolars.txt");
        Ficheros.add("./Practica1/iris.txt");
        Ficheros.add("RedBunny");
    }
    @Test
    //Me dispongo a probar el metodo de la tabla sin tratar de que tenga excepcion
     @DisplayName("Metodo readTable sin  exception ")
    void prueba1(){

    }
    @Test
    @DisplayName("Metodo readTable con  exception ")
    void suma(){

    }
}