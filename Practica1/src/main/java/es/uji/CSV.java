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
            String linea_datos = sc.nextLine();
            Row fila = new Row();
            String[] valores = linea_datos.split("\\s+");
            for (String valor : valores) {
                fila.data.add(Double.parseDouble(valor));
            }
            tabla.datos.add(fila);
        }
        sc.close();
        return tabla;
    }
}
