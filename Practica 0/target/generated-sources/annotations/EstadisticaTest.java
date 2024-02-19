package es.uji.delaHorraBuga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {

    @Test
    //contempla los casos de que la media todos lo numeros sean igual que contenga solo un elemento y que se le de un vector vacio
    void mediaAritmetica() {
        Estadistica estadistica = new Estadistica();
        float[] vector1 ={5,5,5,5};
        float[] vector2 ={4};
        float[] vector3 ={};
        assertEquals(estadistica.mediaAritmetica(vector1),5,"La media de 5,5,5,5 es 5 ");
        assertEquals(estadistica.mediaAritmetica(vector2),4,"La media de 4 es 5 ");
        assertEquals(estadistica.mediaAritmetica(vector3),0,"La media de {} es 0 ");


    }

    //contempla los casos de que la media todos lo numeros sean igual que contenga solo un elemento y que se le de un vector vacio
    @Test
    void varianza() {
        Estadistica estadistica = new Estadistica();
        float[] vector1 ={5,5,5,5};
        float[] vector2 ={4};
        float[] vector3 ={};
        float[] vector4 = {3, 6, 9, 12, 15};
        assertEquals(estadistica.varianza(vector1),0,"La varianza de 5 5 5 5 es 0");
        assertEquals(estadistica.varianza(vector2),0,"La varianza de 4 es 0");
        assertEquals(estadistica.varianza(vector3),0,"La varianza de {} es 0");
        assertEquals(estadistica.varianza(vector4),18,"La varianza de 3, 6, 9, 12, 15 es 90 ");

    }
    //Test de la desviacion critica hemos tenido encuenta los casos criticos y que funcione bien
    @Test
    void desviacionTipica() {
        Estadistica estadistica = new Estadistica();
        float[] vector1 ={5,5,5,5};
        float[] vector2 ={4};
        float[] vector3 ={};
        float[] vector4 = {3, 6, 9, 12, 15};
        assertEquals(estadistica.desviacionTipica(vector1),0,"La desviacion tipica de 5,5,5,5 es 0  ");
        assertEquals(estadistica.desviacionTipica(vector2),0,"La desviacion tipica de 4 es 0  ");
        assertEquals(estadistica.desviacionTipica(vector3),0,"La desviacion tipica de {} es 0  ");
        assertEquals(estadistica.desviacionTipica(vector4),Math.sqrt(18),"La desviacion tipica de 5,5,5,5 es 0  ");


    }
}