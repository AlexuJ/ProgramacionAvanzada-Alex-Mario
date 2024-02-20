package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.DoubleStream;

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
}
