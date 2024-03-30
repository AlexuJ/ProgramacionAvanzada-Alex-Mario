package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    // le un fichero y lo añade a la tabla
    public Table readTable(String fichero) throws FileNotFoundException {
        Table TablaSinEtiquetas = new Table();
        if (Vacio(fichero) == True) {
            TablaSinEtiquetas.headers = TablaSinEtiquetas.Cabeceras(fichero);
            Scanner sc = new Scanner(new File(fichero));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(",");
                TablaSinEtiquetas.addFilaSinEtiquetas(linea);
            }
            sc.close();
            return TablaSinEtiquetas;
        } else {
            return Table;
        }
    }

    public Boolean Vacio(String Fichero) {
        File archivo = new File(Fichero);
        if (archivo.lenght() == 0) {
            return False;
        } else {
            return True;
        }
    }

    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        if (Vacio(fichero) == True) {
            TablaConEtiquetas.headers = TablaConEtiquetas.Cabeceras(fichero);
            Scanner sc = new Scanner(new File(fichero));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(",");
                TablaConEtiquetas.addFilaConEtiqueta(linea, TablaConEtiquetas.lebelsToIndex(fichero));
                sc.close();
            }
            return TablaConEtiquetas;

        }
    }
}