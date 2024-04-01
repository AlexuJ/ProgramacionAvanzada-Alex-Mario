package es.uji.al426239;

import es.uji.al426239.CarpetaRow.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private Row filallena;
    private Row filavacia;
    private List<Number> datos;
    private int valor;

    // una funcion que añade numeros a las listas para poder trabajar

    // lo que se iniciara al principio y es comun a todos los test se elige
    // beforeEach para no usar el static
    @BeforeEach
    void inicioClase() {
        filavacia = new Row();
        datos = new ArrayList<>();
        filallena = new Row();
        valor = 5;
        for (Double i = 0.0; i < valor; i++) {
            filallena.setData(i);
            datos.add(i);
        }
    }

    @Test
    @DisplayName("getData")
    void Prueba1() {

        assertEquals(datos.toArray().length, filallena.getData().toArray().length,
                "Tamaños diferentes datos " + datos.toArray().length + "filallena " + filallena.size());
        assertEquals(filallena.getData(), datos, "Contenidos distintos");
    }

    @Test
    @DisplayName("Size")
    void Prueb2() {

        assertEquals(valor, filallena.getData().toArray().length,
                "Tamaños esperado " + valor + "filallena " + filallena.size());
    }

    @Test
    @DisplayName("SetData(List<Number>)")
    void Prueba3() {
        // Asigna la lista de datos a filavacia
        filavacia.setData(filallena.getData());

        // Verifica que las listas de datos de filallena y filavacia sean iguales
        assertEquals(filallena.getData(), filavacia.getData(), "Las listas de datos deben ser iguales");
    }

    @Test
    @DisplayName("SetData(Number)")
    void Prueba4() {
        for (double i = 0.0; i < valor; i++) {
            filavacia.setData(i);
        }
        assertEquals(datos, filavacia.getData(), "El metodo SetData(Number) No esta asignando bien");
    }

    @Test
    @DisplayName("sumeData")
    void Prueba5() {
        for (int i = 0; i < valor; i++) {
            datos.set(i, datos.get(i).doubleValue() + i);
            filallena.sumeData(i, i);
        }
        assertEquals(datos, filallena.getData(), "Las listas de datos deben ser iguales");
    }

    @Test
    @DisplayName("splitData")
    void Prueba6() {
        for (int i = 0; i < valor; i++) {
            datos.set(i, datos.get(i).doubleValue() / i);
            filallena.splitData(i, i);
        }
        assertEquals(datos, filallena.getData(), "Las listas de datos deben ser iguales");
    }
}