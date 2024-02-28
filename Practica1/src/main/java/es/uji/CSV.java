package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV implements LectorTabla {
    public Table readTable(String fichero)throws FileNotFoundException {
        Table TablaSinEtiquetas = new Table();
        TablaSinEtiquetas.headers = Headers(fichero);
        TablaSinEtiquetas.datos = DatosTabla(fichero);
        return TablaSinEtiquetas;
    }
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.headers = Headers(fichero);
        TablaConEtiquetas.datos = DatosTabla(fichero);
        return TablaConEtiquetas;
    }
    @Override
    public List<String> Headers(String fichero) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split(",");
        return new ArrayList<>(Arrays.asList(linea));
    }
}
