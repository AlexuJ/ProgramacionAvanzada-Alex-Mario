package es.uji;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

public class TodasLasPruebasTest {
    private static final String FicheroSinEtiquetas = "./Practica1/miles_dolars.txt";
    private static final String FicheroConEtiquetas = "./Practica1/iris.txt";
    private static Table TablaSinEtiquetas;
    private static TableWithLabels TablaConEtiquetas;


    @BeforeAll
    static void inicioTest() throws FileNotFoundException {
        CSV LectorFichero = new CSV();
        TablaSinEtiquetas = LectorFichero.readTable(FicheroSinEtiquetas);
        TablaConEtiquetas = LectorFichero.readTableWithLabels(FicheroConEtiquetas);
    }

    @DisplayName("Comprobar nº filas")
    @Test
    void ComprobarFilas() {
        Assertions.assertEquals(26, TablaSinEtiquetas.datos.size()+1, "Tienen que haber un total de 26 filas");
    }
}
