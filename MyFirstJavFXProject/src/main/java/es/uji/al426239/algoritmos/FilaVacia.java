package es.uji.al426239.algoritmos;

import java.util.List;

public class FilaVacia extends Exception {
    public FilaVacia(List<Number> datos) {
        super("La fila de datos "+ datos +" está vacía");
    }
}
