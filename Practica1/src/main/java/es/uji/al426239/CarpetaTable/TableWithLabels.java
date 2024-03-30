package es.uji.al426239.CarpetaTable;

import es.uji.al426239.CarpetaRow.RowWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {
    private Map<String, Integer> lebelsToIndex;
    public void RellenarMapaEtiquetas(String etiqueta) {
        if (!lebelsToIndex.containsKey(etiqueta)) {
            lebelsToIndex.put(etiqueta, 0);
            public Map<String, Integer> etiquetas;
        }
    }

            // Constructor clase TableWithlabels
    public TableWithLabels() {
                etiquetas = new HashMap<String, Integer>();
    }
            // Método para crear un mapa que guarde las etiquetas con sus números de clase
            public Map<String, Integer> lebelsToIndex (String fichero) throws FileNotFoundException {
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
            }

            public RowWithLabels getRowAt ( int rowNumber){
                return (RowWithLabels) super.getRowAt(rowNumber);
            }

            public Map<String, Integer> getLebelsToIndex () {
                return lebelsToIndex;
            }

            // Método para añadir filas con etiquetas
            @Override
            public void addFila (String[]linea) throws FileNotFoundException {
                RowWithLabels FilaConEtiqueta = new RowWithLabels();
                for (int i = 0; i < linea.length - 1; i++) {
                    FilaConEtiqueta.getData().add(Double.valueOf(linea[i]));
                }
                FilaConEtiqueta.setNumberClass(lebelsToIndex("iris.csv").get(linea[linea.length - 1]));
                getRows().add(FilaConEtiqueta);
            }

            // Método para conseguir un fila con etiquetas
            public RowWithLabels getRowAt ( int rowNumber){
                return (RowWithLabels) super.getRowAt(rowNumber);
            }

            // Metodo para agregar claves al dicionario
            public void SetKey (String etiqueta){
                // cuanto cuenta cada etiqueta
                Integer valor = 1;
                // compruebo si existe la key de la etiqueta si existe sumo valor a la cantidad
                // de la entrada
                // si no añado la entrada y el valor
                if (etiquetas.containsKey(etiqueta)) {
                    Integer Cantidad = etiquetas.get(etiqueta) + valor;
                    etiquetas.put(etiqueta, Cantidad);
                } else {
                    etiquetas.put(etiqueta, valor);
                }
            }

            // metodo para consultar los valores del mapa falta añadirle una excepcion por
            // si alguien le consulta
            // una etiqueta inexistente
            public Integer Consulta (String etiqueta){
                if (etiquetas.containsKey(etiqueta)) {
                    return etiquetas.get(etiqueta);
                } else {
                    return null;
                }
            }
        }
}
