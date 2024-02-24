package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    //inicio prueba 1 de test
    private  Row fila ;
    private List<Double> datos;
    public List<Double> Creadatos(List<Double> datos){
        for (double i = 0 ; i<5;i++){
            datos.add(i);
        }
        return datos;
    }
    @BeforeAll
    void  inicioClase(){
        fila = new Row();
        datos = Creadatos(datos);
    }

    @Test
    @DisplayName("Almacenamiento")
    void Prueba1(){
        fila.data = Creadatos(fila.data);
        assertArrayEquals(datos.toArray(),fila.data.toArray(),"Se esperava que los arrays coincidieran");
    }
    @Test
    @DisplayName("GET DATA")
    void Prueba2(){
        fila.data = Creadatos(fila.data);
        assertArrayEquals(fila.getData().toArray(),fila.data.toArray());

    }
    @Test
    @DisplayName("Excepcion Contra tipos de datos")
    void Prueba3(){

        fila.data = Creadatos(fila.data);
        assertArrayEquals(fila.getData().toArray(),fila.data.toArray());

    }


}