package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table tabla = new Table();
        Scanner sc = new Scanner(new File(fichero));
        String linea_headers = sc.nextLine();
        tabla.headers.add(linea_headers);
        while (sc.hasNextLine()) {
            Row fila = new Row();
            String[] valores = sc.nextLine().split("\\s+");
            for (String valor : valores) {
                fila.data.add(Double.valueOf(valor));
            }
            tabla.datos.add(fila);
        }
        sc.close();
        return tabla;
    }
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException{
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        Scanner sc = new Scanner(new File(fichero));
        String linea_headers = sc.nextLine();
        TablaConEtiquetas.headers.add(linea_headers);
        while (sc.hasNextLine()) {
            RowWithLabels fila = new RowWithLabels();
            String[] valores =sc.nextLine().split(",");
            for (String valor : valores) {
                if (TablaConEtiquetas.rellenarMapaEtiquetas(fichero).containsKey(valor)) {
                    fila.numberClass=TablaConEtiquetas.rellenarMapaEtiquetas(fichero).get(valor);
                } else {
                    fila.data.add(Double.valueOf(valor));
                }
            }
            TablaConEtiquetas.DatosConEtiquetas.add(fila);
        }
        sc.close();
        return TablaConEtiquetas;
    }
}
