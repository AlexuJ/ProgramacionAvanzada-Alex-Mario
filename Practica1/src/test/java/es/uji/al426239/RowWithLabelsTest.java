package es.uji.al426239;

import es.uji.al426239.rowytable.RowWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowWithLabelsTest {
    // inicio prueba 1 de test
    private RowWithLabels filaAcabada;
    private int valorInicio;
    private int valorSustituto;

    // una funcion que añade numeros a las listas para poder trabajar
    // lo que se iniciara al principio y es comun a todos los test se elige
    // beforeEach para no usar el static
    @BeforeEach
    void inicioClase() {
        valorInicio = Integer.MAX_VALUE;
        valorSustituto = Integer.MIN_VALUE;
        filaAcabada = new RowWithLabels(valorInicio);
    }

    @Test
    @DisplayName("getNumber")
    void Prueba1() {
        assertEquals(valorInicio, filaAcabada.getNumberClass());
    }

    @Test
    @DisplayName("setNumber")
    void Prueba2() {
        filaAcabada.setNumberClass(valorSustituto);
        assertEquals(valorSustituto, filaAcabada.getNumberClass(), "No funciona el SetData");
    }

}