package es.uji;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AritmeticaTest {




    @Test
        //en el test contemplo que funciona la suma que se pueda restar y que se pueden usar enteros
    void sumar() {
        Aritmetica aritmetica = new Aritmetica();
        assertEquals(aritmetica.sumar(1.2f,2.3f),3.5f,"1.2 + 2.3 = 3.5");
        assertEquals(aritmetica.sumar(1,1),2.0f,"1+1=2.0");
        assertEquals(aritmetica.sumar(2,-2),0,"2-2 = 0");
        assertEquals(aritmetica.sumar(-2,-2),-4,"-2-2 = 0");
    }
    //en el test contemplo que funciona la multiplicacion de decimales el multiplicar por 0  y los cambios de signo ademas de multiplicar enteros
    @Test
    void multiplicar() {
        Aritmetica aritmetica = new Aritmetica();
        assertEquals(aritmetica.multiplicar(2.2f,3.4f),7.4800004959106445,"2.2 * 3.4 = 7.4800004959106445");
        assertEquals(aritmetica.multiplicar(0,1),0,"0 * 1 = 0");
        assertEquals(aritmetica.multiplicar(-2,3),-6,"-2 * 3 = -6");
        assertEquals(aritmetica.multiplicar(-5,-4),20,"-5 * -4 = 20");
    }
    //en el test contemplo que la division  funcione con enteros y decimales que evite el dividir por 0 dividiendo por 1 y los casos con distintos signos
    //y que el divisor sea mas grande que el dividendo
    @Test
    void divividir() {
        Aritmetica aritmetica = new Aritmetica();
        assertEquals(aritmetica.divividir(5f,2.0f),2.5f,"5/2,5 = 2,5");
        assertEquals(aritmetica.divividir(2,20),0.10000000149011612,"2/2 = 1");
        assertEquals(aritmetica.divividir(30 ,0),30,"30/0 = error");
        assertEquals(aritmetica.divividir(-4,-2),2,"-4/-2=2");
        assertEquals(aritmetica.divividir(4,-2),-2,"-4/-2=2");
    }
    //El uso de sumador es para sumar muchos numero rapido funciona con positivos como con negativos y numeros decimales caso extremo
    //donde el vector es de un digito y el caso de que sea un vector vacio
    @Test
    void sumador() {
        Aritmetica aritmetica = new Aritmetica();
        float[] vector1 ={1,2,3};
        float[] vector2 ={-1,2,3};
        float[] vector3 ={1,2,3.9f};
        float[] vector4 = {0};
        float[] vector5 = {};
        assertEquals(aritmetica.sumador(vector1),6,"1+2+3=6.9");
        assertEquals(aritmetica.sumador(vector2),4,"-1+2+3=4");
        assertEquals(aritmetica.sumador(vector3),6.900000095367432,"1+2+3.9=6.9");
        assertEquals(aritmetica.sumador(vector4),0,"0=0");
        assertEquals(aritmetica.sumador(vector5),0,"-1+2+3=4");

    }
    //Test de eleva
}