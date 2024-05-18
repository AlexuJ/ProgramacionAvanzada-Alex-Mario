package es.uji.al426239.algoritmos;

import es.uji.al426239.sistemaderecomendacion.RecSys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IntFactoriasAl {
    RecSys Selecion(String elecion, String distance) throws IOException, FilaVacia, TablaVacia, Comparator;
    List<String> GetListaAlgoritmos();
    List<String> GetListaDistancias();
}
