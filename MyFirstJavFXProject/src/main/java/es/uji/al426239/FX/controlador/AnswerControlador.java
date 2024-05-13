package es.uji.al426239.FX.controlador;

import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;

import java.io.FileNotFoundException;

public interface AnswerControlador {
    void EventAlgorithm(int caso) throws FileNotFoundException, FilaVacia, TablaVacia, Comparator;
    void EventDistance(int caso);
}
