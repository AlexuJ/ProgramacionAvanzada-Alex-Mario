package es.uji.al426239.lector_de_tablas;

import es.uji.al426239.row_table.Row;
import es.uji.al426239.row_table.RowWithLabels;
import es.uji.al426239.row_table.Table;
import es.uji.al426239.row_table.TableWithLabels;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSV {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table TablaSinEtiquetas = new Table();
        Scanner sc = new Scanner(new File(fichero));
        Cabeceras(sc.nextLine().split(","), TablaSinEtiquetas);
        while (sc.hasNextLine()) {
            String[] linea = sc.nextLine().split(",");
            Row fila = new Row();
            for (String s : linea) {
                fila.setData(Double.valueOf(s));
            }
            TablaSinEtiquetas.setRow(fila);

        }
        sc.close();
        return TablaSinEtiquetas;
    }

    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        Scanner sc = new Scanner(new File(fichero));
        Cabeceras(sc.nextLine().split(","), TablaConEtiquetas);
        while (sc.hasNextLine()) {
            String[] linea = sc.nextLine().split(",");
            System.out.println(linea);
            Row fila = new RowWithLabels(TablaConEtiquetas.GetKey(linea[linea.length - 1]));
            for (int i = 0; i < linea.length - 1; i++) {
                fila.setData(Double.valueOf(linea[i]));
            }
            TablaConEtiquetas.setRow(fila);
        }
        sc.close();
        return TablaConEtiquetas;
    }

    // metodo que setea a las tablas las cabeceras
    private void Cabeceras(String[] linea, Table tabla) {
        ArrayList<String> cabezeras = new ArrayList<>(Arrays.asList(linea));
        tabla.setHeaders(cabezeras);
    }

}