package es.uji.al426239;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Maneras de mejorar los test una clase con funciones y parametros que vayamos a usar para las pruebas
class TableWithLabelsTest {
    // inicio prueba 1 de test
    private List<Row> FilasPrueba;
    private List<String> headersPrueba;
    private TableWithLabels tablallena;
    private TableWithLabels tablaVacia;
    private int numeroFilas;
    private String etiquetas;

    @BeforeEach
    void inicio() {
        tablallena = new TableWithLabels();
        tablaVacia = new TableWithLabels();
        numeroFilas = 5;
        headersPrueba = new ArrayList<>();
        etiquetas = "alfa,beta,gamma,fi,pi,omega,";
        SetKeys(tablallena);

    }

    public void CreadorFilas(List<Row> filas) {
        for (int i = 0; i < numeroFilas; i++) {
            Row fila = new RowWithLabels(i);

            filas.add(fila);
        }

    }

    public void SetKeys(TableWithLabels tabla) {
        for (String etiqueta : etiquetas.split(",")) {
            tabla.SetKey(etiqueta);
        }
    }

    @Test
    @DisplayName("GetKey y el set Key")
    void GetSetTablaWithLabels() {
        for (int i = 0; i < etiquetas.split(",").length - 1; i++) {
            assertEquals(i + 1, tablallena.GetKey(etiquetas.split(",")[i]));
        }
    }

}