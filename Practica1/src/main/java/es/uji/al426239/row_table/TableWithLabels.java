package es.uji.al426239.row_table;

import java.util.*;

public class TableWithLabels extends Table {
    private Map<String, Integer> etiquetas;

    // Constructor clase TableWithlabels
    public TableWithLabels() {
        etiquetas = new HashMap<>();
    }

    // Metodo para agregar claves al dicionario
    public void SetKey(String etiqueta) {
        int valor = 1;
        if (!etiquetas.containsKey(etiqueta)) {
            etiquetas.put(etiqueta, etiquetas.size() + valor);
        }
    }

    public Integer GetKey(String etiqueta) {
        if (!etiquetas.containsKey(etiqueta)) {
            SetKey(etiqueta);
        }
        return etiquetas.get(etiqueta);
    }

    @Override
    public RowWithLabels getRow(int i) {
        return (RowWithLabels) super.getRow(i);
    }

}
