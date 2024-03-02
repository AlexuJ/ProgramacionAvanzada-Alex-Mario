package es.uji;

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
            String[] linea = sc.nextLine().split(",");
            TablaSinEtiquetas.addFilaSinEtiquetas(linea);
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
            String[] linea = sc.nextLine().split(",");
            TablaConEtiquetas.addFilaConEtiqueta(linea, TablaConEtiquetas.lebelsToIndex(fichero));
        }
        sc.close();
        return TablaConEtiquetas;
    }
}