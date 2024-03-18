package es.uji;

import es.uji.CarpetaRow.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private Row fila;
    private List<Number> datos;

    // una funcion que añade numeros a las listas para poder trabajar
    public List<Number> Creadatos(List<Number> datos) {
        for (double i = 0; i < 5; i++) {
            datos.add(i);
        }
        System.out.println(datos);
        return datos;
    }

    // lo que se iniciara al principio y es comun a todos los test se elige
    // beforeEach para no usar el static
    @BeforeEach
    void inicioClase() {
        fila = new Row();
        datos = new ArrayList<>();
        fila.setData(datos);
        fila.setData(Creadatos(fila.getData()));
        datos = Creadatos(datos);
    }

    @Test
    @DisplayName("getData")
    void Prueba1() {
        assertArrayEquals(datos.toArray(), fila.getData().toArray(), "fila no esta almacenando datos");
    }

    @Test
    @DisplayName("SetData")
    void Prueba3() {
        List<Number> nuevosDatos = new ArrayList<>();
        nuevosDatos.add(10);
        nuevosDatos.add(20);
        nuevosDatos.add(30);
        fila.setData(nuevosDatos);
        assertArrayEquals(nuevosDatos.toArray(), fila.getData().toArray(),
                "Los datos no se han establecido correctamente");
    }
}