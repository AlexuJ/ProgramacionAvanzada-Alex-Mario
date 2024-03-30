package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.RowWithLabels;
import java.util.*;

public class TableWithLabels extends Table {
    private final Map<String, Integer> etiquetas;

    // Constructor clase TableWithlabels
    public TableWithLabels() {
        etiquetas = new HashMap<>();
    }
    // Método para añadir filas con etiquetas
    @Override
    public void addFila (String[] linea) {
        RowWithLabels FilaConEtiqueta = new RowWithLabels();
        for (int i = 0; i < linea.length - 1; i++) {
            FilaConEtiqueta.getData().add(Double.valueOf(linea[i]));
        }
        FilaConEtiqueta.setNumberClass(etiquetas.get(linea[linea.length-1]));
        getRows().add(FilaConEtiqueta);
    }
    // Metodo para agregar claves al dicionario
    public void SetKey (String etiqueta) {
        int valor = 1;
        if (!etiquetas.containsKey(etiqueta)) {
            etiquetas.put(etiqueta, etiquetas.size() + valor);
        }
    }
    public Map<String, Integer> getEtiquetas() {
        return etiquetas;
    }
    public RowWithLabels getRowAt(int i) {
        return (RowWithLabels) super.getRowAt(i);
    }
}

