package es.uji;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    public Table selectorTabla(String distingidor, String linea, String fichero) {
        Table table;
        if (linea.contains(distingidor)) {
            table = new TableWithLabels();
            table.SetFichero(fichero);
        } else {
            table = new Table();
        }

        return table;
    }

    public Table readerTable(String ficher) throws FileNotFoundException {
        String separador = ",";
        String distigidor = "-";
        Scanner scanner = new Scanner(ficher);
        String headers = scanner.nextLine();
        String Fila1 = scanner.nextLine();
        Table table = selectorTabla(distigidor, Fila1, ficher);
        table.setHeadersList(headers, separador);
        scanner.close();

        return table;
    }

}