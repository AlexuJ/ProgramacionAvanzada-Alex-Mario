package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TableWithLabels extends Table {
    public Map<String,Integer> rellenarMapaEtiquetas(String fichero) throws FileNotFoundException {
        Map<String,Integer> Etiquetas = new HashMap<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        int ClaseEtiqueta = 1;
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
}
