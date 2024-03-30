package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.RowWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {
    private Map<String, Integer> lebelsToIndex;
    public void RellenarMapaEtiquetas(String etiqueta) {
        if (!lebelsToIndex.containsKey(etiqueta)) {
            lebelsToIndex.put(etiqueta,0);
        }
    }
    public RowWithLabels getRowAt(int rowNumber) {
        return (RowWithLabels) super.getRowAt(rowNumber);
    }
    public Map<String, Integer> getLebelsToIndex() {
        return lebelsToIndex;
    }
}
