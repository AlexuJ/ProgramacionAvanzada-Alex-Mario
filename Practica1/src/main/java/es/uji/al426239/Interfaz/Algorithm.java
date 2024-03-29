package es.uji.al426239.Interfaz;

import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.Excepciones.Comparador;

import java.util.List;

public interface Algorithm<T extends Table,U extends List<? extends Number>,W extends Integer> {
    public void train(T Table) throws Comparador;
    public W estimate(U datos);
}
