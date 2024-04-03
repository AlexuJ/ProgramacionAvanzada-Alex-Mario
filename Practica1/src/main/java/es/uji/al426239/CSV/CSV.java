package es.uji.al426239.CSV;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSV {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table TablaSinEtiquetas = new Table();
        Scanner sc = new Scanner(new File(fichero));
        String[] alfa = sc.nextLine().split(",");
        Cabeceras(alfa, TablaSinEtiquetas);
        Cabeceras(alfa, TablaSinEtiquetas);
        while (sc.hasNextLine()) {
            String[] linea = sc.next().split(",");
            Row fila = new Row();
            for (int i = 0; i < linea.length - 1; i++) {
                fila.setData(Double.valueOf(linea[i]));
            }
            TablaSinEtiquetas.setRow(fila);

        }
        sc.close();
        return TablaSinEtiquetas;
    }

    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        Scanner sc = new Scanner(new File(fichero));
        String[] alfa = sc.nextLine().split(",");
        Cabeceras(alfa, TablaConEtiquetas);
        while (sc.hasNextLine()) {
            String[] linea = sc.next().split(",");
            Row fila = new RowWithLabels(TablaConEtiquetas.GetKey(linea[linea.length - 1]));
            for (int i = 0; i < linea.length - 1; i++) {
                System.out.println(Double.valueOf(linea[i]));
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