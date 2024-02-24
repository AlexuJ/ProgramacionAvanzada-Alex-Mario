package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {
    public List<RowWithLabels> DatosConEtiquetas;
    public TableWithLabels() {
        DatosConEtiquetas = new ArrayList<>();
    }
    public Map<String,Integer> rellenarMapaEtiquetas(String fichero) throws FileNotFoundException {
        Map<String,Integer> Etiquetas = new HashMap<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        int ClaseEtiqueta = 0;
        while (sc.hasNextLine()) {
            String[] valores = sc.nextLine().split(",");
            if (Etiquetas.containsKey(valores[valores.length-1])) {
                Etiquetas.put(valores[valores.length-1],ClaseEtiqueta);
            } else {
                Etiquetas.put(valores[valores.length-1],ClaseEtiqueta++);
            }
        }
        return Etiquetas;
    }
    public RowWithLabels getRowAt(int rowNumber) {
        return DatosConEtiquetas.get(rowNumber);
    }
}
