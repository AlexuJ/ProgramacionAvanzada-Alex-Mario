package es.uji;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    private CSV Lector;
    private ArrayList<String> Ficheros;
    @BeforeEach
    void inicioClase() {
        Lector = new CSV();
        Ficheros = new ArrayList<>();
        Ficheros.add("./miles_dolars.csv");
<<<<<<< HEAD
        Ficheros.add("./iris.csv");
=======
        Ficheros.add("./Practica1/iris.txt");
>>>>>>> 8607944fd1c1ec2c85e4fa30902170b9976ca2c9
        Ficheros.add("RedBunny");
    }

    @Test
    @DisplayName("TestReadtable")
    void prueba1() throws FileNotFoundException {
        String ruta = Ficheros.get(0);
        Table table = Lector.readTable(ruta);
        assertEquals(25, table.getRows().size(), "Las filas no coinciden");
    }
}