package es.uji.al426239.CSV;

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
            TablaSinEtiquetas.addFila(sc.nextLine().split(","));
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
            String[] linea = sc.nextLine().split(",");
            TablaConEtiquetas.SetKey(linea[linea.length-1]);
            TablaConEtiquetas.addFila(linea);
        }
        sc.close();
        return TablaConEtiquetas;
    }
}