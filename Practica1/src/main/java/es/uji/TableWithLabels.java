package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {
    // atributos que tenemos una lista de Row
    public List<Row> DatosConEtiquetas;

    // constructor Vacio
    public TableWithLabels() {
        DatosConEtiquetas = new ArrayList<>();
    }

    // metodo no revisado
    public Map<String, Integer> rellenarMapaEtiquetas(String fichero) throws FileNotFoundException {
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

}
