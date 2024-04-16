package es.uji.al426239.algoritmos;

import es.uji.al426239.rowytable.Table;

public class TablaVacia extends Exception {
    public TablaVacia(Table tabla) {
        super("La tabla "+ tabla +" está vacía");
    }
}
