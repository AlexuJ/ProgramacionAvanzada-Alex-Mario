package es.uji.CSV;

import es.uji.CarpetaTable.Table;
import es.uji.CarpetaTable.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    //Método para leer archivo sin etiquetas
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

    //Método para leer archivos con etiquetas
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.setHeaders(TablaConEtiquetas.Cabeceras(fichero));
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            TablaConEtiquetas.addFila(sc.nextLine().split(","));
        }
        sc.close();
        return TablaConEtiquetas;
    }
}