package es.uji.al426239.CSV;

import es.uji.al426239.CarpetaRow.RowWithLabels;
import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.CarpetaTable.TableWithLabels;
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
            String[] linea = sc.nextLine().split(",");
            RowWithLabels fila = new RowWithLabels();
            for (int i = 0 ; i < linea.length-1; i++){
                fila.setUnicData(Double.valueOf(linea[i]));
            }
            TablaConEtiquetas.RellenarMapaEtiquetas(linea[linea.length-1]);
            fila.setNumberClass(TablaConEtiquetas.getLebelsToIndex().get(linea[linea.length-1]));
            TablaConEtiquetas.setRow(fila);
        }
        sc.close();
        return TablaConEtiquetas;
    }
}