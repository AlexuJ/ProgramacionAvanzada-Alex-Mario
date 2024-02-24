package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSV implements LectorTabla {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table tabla = new Table();
        tabla.headers=Headers(fichero);
        tabla.datos=DatosSinEtiqueta(fichero);
        return tabla;
    }
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.headers=Headers(fichero);
        TablaConEtiquetas.DatosConEtiquetas=DatosConEtiqueta(fichero,TablaConEtiquetas.rellenarMapaEtiquetas(fichero));
        return TablaConEtiquetas;
    }
    @Override
    public List<String> Headers(String fichero) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split("\\s+");
        List<String> cabeceras = new ArrayList<>(List.of(linea));
        sc.close();
        return cabeceras;
    }
    @Override
    public List<Row> DatosSinEtiqueta(String fichero) throws FileNotFoundException {
        List<Row> DatosSinEtiqueta = new ArrayList<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            Row fila = new Row();
            String[] linea = sc.nextLine().split(",");
            for (String dato : linea) {
                fila.data.add(Double.valueOf(dato));
            }
            DatosSinEtiqueta.add(fila);
        }
        sc.close();
        return DatosSinEtiqueta;
    }
    @Override
    public List<RowWithLabels> DatosConEtiqueta(String fichero, Map<String, Integer> Etiquetas) throws FileNotFoundException {
        List<RowWithLabels> DatosConEtiqueta = new ArrayList<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            RowWithLabels fila = new RowWithLabels();
            String[] linea = sc.nextLine().split(",");
            for (String dato : linea) {
                if (Etiquetas.containsKey(dato)) {
                    fila.numberClass=Etiquetas.get(dato);
                } else {
                    fila.data.add(Double.valueOf(dato));
                }
            }
            DatosConEtiqueta.add(fila);
        }
        sc.close();
        return DatosConEtiqueta;
    }
}
