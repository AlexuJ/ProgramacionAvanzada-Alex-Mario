package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.RowWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {

    //Método para crear un mapa que guarde las etiquetas con sus números de clase
    public Map<String, Integer> lebelsToIndex(String fichero) throws FileNotFoundException  {
        Map<String, Integer> Etiquetas = new HashMap<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        int ClaseEtiqueta = 0;
        while (sc.hasNextLine()) {
            String[] valores = sc.nextLine().split(",");
            if (Etiquetas.containsKey(valores[valores.length - 1])) {
                Etiquetas.put(valores[valores.length - 1], ClaseEtiqueta);
            } else {
                Etiquetas.put(valores[valores.length - 1], ClaseEtiqueta++);
            }
        }
        sc.close();
        return Etiquetas;
    }

    //Método para añadir filas con etiquetas
    @Override
    public void addFila(String[] linea) throws FileNotFoundException {
        RowWithLabels FilaConEtiqueta = new RowWithLabels();
        for (int i=0; i<linea.length-1; i++) {
            FilaConEtiqueta.getData().add(Double.valueOf(linea[i]));
        }
        FilaConEtiqueta.setNumberClass(lebelsToIndex("iris.csv").get(linea[linea.length-1]));
        getRows().add(FilaConEtiqueta);
    }

    //Método para conseguir un fila con etiquetas
    public RowWithLabels getRowAt(int rowNumber) {
        return (RowWithLabels) super.getRowAt(rowNumber);
    }
}
