package es.uji.al426239.algoritmos;

import es.uji.al426239.rowytable.Table;

public interface Algorithm<T extends Table,U,W> {
    void train(T Tabla) throws Comparator, TablaVacia, FilaVacia;
    W estimate(U datos) throws FilaVacia;
}
