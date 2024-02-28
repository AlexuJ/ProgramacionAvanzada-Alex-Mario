package es.uji;

import org.junit.jupiter.api.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;

public class TodosLosTests {
    private static final String FicheroSinEtiquetas = "./Practica1/miles_dolars.txt";
    private static final String FicheroConEtiquetas = "./Practica1/iris.txt";
    private static Table TablaSinEtiquetas;
    private static TableWithLabels TablaConEtiquetas;
    private static Map<String,Integer> MapaConEtiquetas;

    @BeforeAll
    static void inicioTest() throws FileNotFoundException {
        CSV LectorFichero = new CSV();
        TablaSinEtiquetas = LectorFichero.readTable(FicheroSinEtiquetas);
        TablaConEtiquetas = LectorFichero.readTableWithLabels(FicheroConEtiquetas);
        MapaConEtiquetas = TablaConEtiquetas.rellenarMapaEtiquetas(FicheroConEtiquetas);
    }
    @DisplayName("Nº filas")
    @Test
    void ComprobarFilas() {
        Assertions.assertEquals(26, TablaSinEtiquetas.datos.size()+1, "Tienen que haber 26 filas");
        Assertions.assertEquals(151,TablaConEtiquetas.DatosConEtiquetas.size()+1,"Tienen que haber 151 filas");
    }
    @DisplayName("Nº columnas")
    @Test
    void ComprobarColumnas() {
        Assertions.assertEquals(2,TablaSinEtiquetas.headers.size(),"Tienen que haber 2 columnas");
        Assertions.assertEquals(5,TablaConEtiquetas.headers.size(),"Tienen que haber 5 columnas");
    }
    @DisplayName("Nombre etiquetas")
    @Test
    void NombreEtiquetas() {
        String[] CabecerasSinEtiquetas = {"Miles", "Dolars"};
        String[] CabecerasConEtiquetas = {"sepal length", "sepal width", "petal length", "petal width", "class"};
        Assertions.assertEquals(CabecerasSinEtiquetas,TablaSinEtiquetas.headers,"Tiene que ser: "+ Arrays.toString(CabecerasSinEtiquetas));
        Assertions.assertEquals(CabecerasConEtiquetas,TablaConEtiquetas.headers,"Tiene que ser: "+ Arrays.toString(CabecerasConEtiquetas));
    }
    @DisplayName("Nº fila")
    @Test
    void NumeroFila() {
        Integer[] NumerosEsperados = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
        int iterador = 0;
        for (String etiqueta : MapaConEtiquetas.keySet()) {
            int ResultadoEsperado = NumerosEsperados[iterador];
            Assertions.assertEquals(NumerosEsperados[iterador++],MapaConEtiquetas.get(etiqueta),"Tiene que ser: "+ResultadoEsperado);
        }
    }
    @DisplayName("Comprobar filas añadidas")
    @Test
    void ComprobarFilasAnyadidas() {
        Row FilanyadidaSinEtiqueta = AnyadirFilaTablaSinEtiquetas();
        RowWithLabels FilaAnyadidaConEtiqueta = AnyadirFilaTablaConEtiquetas();
        Assertions.assertEquals(FilanyadidaSinEtiqueta,TablaSinEtiquetas.getRowAt(26),"Tiene que ser: "+FilanyadidaSinEtiqueta.getData());
        Assertions.assertEquals(FilaAnyadidaConEtiqueta,TablaConEtiquetas.getRowAt(151),"Tiene que ser: "+FilaAnyadidaConEtiqueta.getData());
    }
    private Row AnyadirFilaTablaSinEtiquetas() {
        Row FilaSinEtiqueta = new Row();
        FilaSinEtiqueta.data.add(1057.0);
        FilaSinEtiqueta.data.add(1598.0);
        TablaSinEtiquetas.datos.add(FilaSinEtiqueta);
        return FilaSinEtiqueta;
    }
    private RowWithLabels AnyadirFilaTablaConEtiquetas() {
        RowWithLabels FilaConEtiqueta = new RowWithLabels();
        FilaConEtiqueta.data.add(7.1);
        FilaConEtiqueta.data.add(4.0);
        FilaConEtiqueta.data.add(5.7);
        FilaConEtiqueta.data.add(2.5);
        FilaConEtiqueta.numberClass=3;
        MapaConEtiquetas.put("Iris-virginica",3);
        TablaConEtiquetas.DatosConEtiquetas.add(FilaConEtiqueta);
        return FilaConEtiqueta;
    }
}
