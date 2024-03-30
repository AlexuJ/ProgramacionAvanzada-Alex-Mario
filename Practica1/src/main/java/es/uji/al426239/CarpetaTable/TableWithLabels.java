package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.RowWithLabels;
import java.util.*;

public class TableWithLabels extends Table {
    private final Map<String, Integer> etiquetas;

    // Constructor clase TableWithlabels
    public TableWithLabels() {
        etiquetas = new HashMap<>();
    }

    // Metodo para agregar claves al dicionario
    public void SetKey (String etiqueta) {
        int valor = 1;
        if (!etiquetas.containsKey(etiqueta)) {
            etiquetas.put(etiqueta, etiquetas.size() + valor);
        }
    }
    public int GetKey(String etiqueta) {
        return etiquetas.get(etiqueta);
    }
    public Map<String, Integer> getEtiquetas() {
        return etiquetas;
    }
    public RowWithLabels getRowAt(int i) {
        return (RowWithLabels) super.getRowAt(i);
    }
}


