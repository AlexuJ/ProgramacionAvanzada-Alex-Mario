package es.uji;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {

    private float[] datos = {1.0F, 2.0F, 3.0F, 4.0F, 5.0F};

    @Test
    void mediaAritmetica() {
        Estadistica estadistica = new Estadistica();
        float valorObtenido = estadistica.mediaAritmetica(datos);
        float valorEsperado = 3F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void varianza() {
        Estadistica estadistica = new Estadistica();
        float valorObtenido = estadistica.varianza(datos);
        float valorEsperado = 2F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void desviacionTipica() {
        Estadistica estadistica = new Estadistica();
        float valorObtenido = estadistica.desviacionTipica(datos);
        float valorEsperado = 1.4142135F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void suma() {
        Estadistica estadistica = new Estadistica();
        float primer_numero = datos[0];
        float segundo_numero = datos[1];
        float valorObtenido = estadistica.suma(primer_numero, segundo_numero);
        float valorEsperado = 3F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void resta() {
        Estadistica estadistica = new Estadistica();
        float primer_numero = datos[2];
        float segundo_numero = datos[3];
        float valorObtenido = estadistica.resta(primer_numero, segundo_numero);
        float valorEsperado = -1F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void multiplicacion() {
        Estadistica estadistica = new Estadistica();
        float primer_numero = datos[2];
        float segundo_numero = datos[3];
        float valorObtenido = estadistica.multiplicacion(primer_numero, segundo_numero);
        float valorEsperado = 12.0F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void division() {
        Estadistica estadistica = new Estadistica();
        float primer_numero = datos[2];
        float segundo_numero = datos[3];
        float valorObtenido = estadistica.division(primer_numero, segundo_numero);
        float valorEsperado = 0.75F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }

    @Test
    void getUltimoResultado() {
        Estadistica estadistica = new Estadistica();
        float valorObtenido = estadistica.getUltimoResultado();
        float valorEsperado = 0.0F;
        System.out.println("Valor obtenido: " + valorObtenido + " Valor esperado: " + valorEsperado);
        assertEquals(valorObtenido, valorEsperado);
    }
}