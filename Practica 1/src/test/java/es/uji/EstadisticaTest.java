package es.uji;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {
    private final Estadistica estadistica = new Estadistica();
    private final float[] datos1 = {1, 2, 3, 4};
    private final float[] datos2 = {5, 5, 5, 5};
    private final float[] datos3= {0, 0, 0, 0};
    private final float[] datos4 = {8};
    private final float[] datos5 = {-2, -1, 1, 2};
    private final float[] datos6 = {};
    @Test
    void mediaAritmetica() {
        assertEquals(2.5F,estadistica.mediaAritmetica(datos1),"Tiene que dar 2.5");
        assertEquals(5F,estadistica.mediaAritmetica(datos2),"Tiene que dar 5");
        assertEquals(0.0F,estadistica.mediaAritmetica(datos3),"Tiene que dar 0");
        assertEquals(8F,estadistica.mediaAritmetica(datos4),"Tiene que dar 8");
        assertEquals(0.0F,estadistica.mediaAritmetica(datos5),"Tiene que dar 0");
        assertEquals(0.0F,estadistica.mediaAritmetica(datos6),"Tiene que dar 0");
    }
    @Test
    void varianza() {
        assertEquals(1.25F,estadistica.varianza(datos1),"Tiene que dar 1.25");
        assertEquals(0.0F,estadistica.varianza(datos2),"Tiene que dar 0");
        assertEquals(0.0F,estadistica.varianza(datos3),"Tiene que dar 0");
        assertEquals(0.0F,estadistica.varianza(datos4),"Tiene que dar 0");
        assertEquals(2.5F,estadistica.varianza(datos5),"Tiene que dar 2.5");
        assertEquals(0.0F,estadistica.varianza(datos6),"Tiene que dar 0");

    }
    @Test
    void desviacionTipica() {
        assertEquals(1.1180339887499F,estadistica.desviacionTipica(datos1),"Tiene que dar 1.11");
        assertEquals(0.0F,estadistica.desviacionTipica(datos2),"Tiene que dar 0.0");
        assertEquals(0.0F,estadistica.desviacionTipica(datos3),"Tiene que dar 0.0");
        assertEquals(0.0F,estadistica.desviacionTipica(datos4),"Tiene que dar 0.0");
        assertEquals(01.5811388F,estadistica.desviacionTipica(datos5),"Tiene que dar 1.5811388");
        assertEquals(0.0F,estadistica.desviacionTipica(datos6),"Tiene que dar 0.0");

    }
    @Test
    void suma() {
        assertEquals(3F,estadistica.suma(datos1[0],datos1[1]),"Tiene que dar 3");
        assertEquals(10F,estadistica.suma(datos2[1],datos2[2]),"Tiene que dar 10");
        assertEquals(0.0F,estadistica.suma(datos3[2],datos3[3]),"Tiene que dar 0");
        assertEquals(0.0F,estadistica.suma(datos5[2],datos5[1]),"Tiene que dar 0.0");
    }
    @Test
    void resta() {
        assertEquals(-1F,estadistica.resta(datos1[0],datos1[1]),"Tiene que dar -1");
        assertEquals(0.0F,estadistica.resta(datos2[1],datos2[2]),"Tiene que dar 0");
        assertEquals(0.0F,estadistica.resta(datos3[2],datos3[3]),"Tiene que dar 0");
        assertEquals(2.0F,estadistica.resta(datos5[2],datos5[1]),"Tiene que dar 2");
    }
    @Test
    void multiplicacion() {
        assertEquals(2F,estadistica.multiplicacion(datos1[0],datos1[1]),"Tiene que dar 2");
        assertEquals(25F,estadistica.multiplicacion(datos2[1],datos2[2]),"Tiene que dar 25");
        assertEquals(0.0F,estadistica.multiplicacion(datos3[2],datos3[3]),"Tiene que dar 0");
        assertEquals(-1.0F,estadistica.multiplicacion(datos5[2],datos5[1]),"Tiene que dar -1.0");
    }
    @Test
    void division() {
        assertEquals(0.5F,estadistica.division(datos1[0],datos1[1]),"Tiene que dar 0.5");
        assertEquals(1F,estadistica.division(datos2[1],datos2[2]),"Tiene que dar 1");
        assertEquals(0.0F,estadistica.division(datos3[2],datos3[3]),"Tiene que dar 0");
        assertEquals(-1.0F,estadistica.division(datos5[2],datos5[1]),"Tiene que dar -1.0");
    }
}