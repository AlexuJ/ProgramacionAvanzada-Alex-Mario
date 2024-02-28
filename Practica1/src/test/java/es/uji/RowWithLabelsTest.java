package es.uji;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RowWithLabelsTest {
    //inicio prueba 1 de test
    private RowWithLabels fila;
    private List<Number> datos;
    //una funcion que añade numeros a las listas para poder trabajar
    //lo que se iniciara al principio y es comun a todos los test se elige beforeEach para no usar el static
    @BeforeEach
    void inicioClase() {
        datos = new ArrayList<>();
        fila = new RowWithLabels();
    }
    public List<Number> generador(int cantidad){
        Random random = new Random();
        List<Number> lista = new ArrayList<>();
        for (int i = 0 ; i < cantidad ;i++){
            lista.add(random.nextDouble(1,10));
        }
        return lista;
    }


    @DisplayName("get set NumberOfClass")
    void GetSetNumberOfClass(){
        List<Number> listaPrueba = new ArrayList<>();
        listaPrueba = generador(2);
        Number alfa = listaPrueba.get(0).intValue();
        fila.setNumberClass(alfa.intValue());
        assertEquals(alfa,fila.getNumberClass());

    }
    @Test
    @DisplayName("get set RowWithLabels")
    void GetSetRowithLabels(){
        List<Number> listaPrueba = new ArrayList<>();
        listaPrueba = generador(2);
        fila.setData(listaPrueba);
        assertArrayEquals(listaPrueba.toArray(),fila.getData().toArray());

    }



}