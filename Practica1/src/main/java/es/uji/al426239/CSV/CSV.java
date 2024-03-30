package es.uji.al426239.CSV;

import es.uji.al426239.CarpetaRow.Row;
import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table TablaSinEtiquetas = new Table();
        TablaSinEtiquetas.setHeaders(TablaSinEtiquetas.Cabeceras(fichero));
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] linea = sc.next().split(",");
            Row fila = new Row();
            for (int i = 0 ;i < linea.length-1 ;i++) {
                fila.setUnicData(Double.valueOf(linea[i]));
            }
            TablaSinEtiquetas.setRow(fila);

        }
        sc.close();
        return TablaSinEtiquetas;
    }

    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.setHeaders(TablaConEtiquetas.Cabeceras(fichero));
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] linea = sc.next().split(",");
            TablaConEtiquetas.SetKey(linea[linea.length-1]);
            Row fila = new RowWithLabels( TablaConEtiquetas.GetKey(linea[linea.length-1]));
            for (int i = 0 ;i < linea.length-1 ;i++) {
                fila.setUnicData(Double.valueOf(linea[i]));
            }
            TablaConEtiquetas.setRow(fila);
            
        }
        sc.close();
        return TablaConEtiquetas;
    }
}