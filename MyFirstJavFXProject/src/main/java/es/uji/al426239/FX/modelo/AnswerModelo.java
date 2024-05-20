package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.FilaVacia;
import es.uji.al426239.algoritmos.TablaVacia;

import java.io.IOException;

public interface AnswerModelo {
    void setRecomendaciones(String Algorithm,String Distance) throws FilaVacia, IOException, TablaVacia, Comparator;
    void reset();
}
