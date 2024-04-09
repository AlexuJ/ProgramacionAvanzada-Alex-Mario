package es.uji.al426239.algoritmos;

import es.uji.al426239.row_table.Table;

public interface Algorithm<T extends Table,U,W> {
    void train(T Tabla) throws Comparator;
    W estimate(U datos);
}
