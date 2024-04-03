package es.uji.al426239.Interfaz;

import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.Excepciones.Comparator;

import java.util.List;

public interface Algorithm<T extends Table,U extends List<? extends Number>,W extends Integer> {
    void train(T Table) throws Comparator;
    W estimate(U datos);
}
