package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSV implements LectorTabla {
    public Table readTable(String fichero) throws FileNotFoundException {
        Table tabla = new Table();
        tabla.headers=Headers(fichero, "\\s+");
        tabla.datos=DatosTabla(fichero, "\\s+");
        return tabla;
    }
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.headers=Headers(fichero, ",");
        TablaConEtiquetas.DatosConEtiquetas=TratarDatosConEtiqueta(fichero, TablaConEtiquetas.rellenarMapaEtiquetas(fichero));
        return TablaConEtiquetas;
    }
    @Override
    public List<String> Headers(String fichero, String Separador) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split(Separador);
        List<String> cabeceras = new ArrayList<>(List.of(linea));
        sc.close();
        return cabeceras;
    }
    @Override
    public List<Row> DatosTabla (String fichero, String Separador) throws FileNotFoundException {
        List<Row> DatosSinEtiqueta = new ArrayList<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            Row fila = new Row();
            String[] linea = sc.nextLine().split(Separador);
            int fin = linea.length;
            if (Separador.equals(",") && fichero.equals("./Practica1/iris.txt")) {
                fin-=1;
            }
            for (int i=0; i<fin; i++) {
                fila.data.add(Double.valueOf(linea[i]));
                DatosSinEtiqueta.add(fila);
            }
        }
        sc.close();
        return DatosSinEtiqueta;
    }
    private List<RowWithLabels> TratarDatosConEtiqueta(String fichero, Map<String, Integer> Etiquetas) throws FileNotFoundException {
        List<RowWithLabels> DatosConEtiqueta = new ArrayList<>();
        List<Row> DatosATratar = DatosTabla(fichero, ",");
        int iterador = 0;
        for (String etiqueta : Etiquetas.keySet()) {
            RowWithLabels LineaConEtiqueta = new RowWithLabels();
            LineaConEtiqueta.data = DatosATratar.get(iterador++).data;
            LineaConEtiqueta.numberClass = Etiquetas.get(etiqueta);
            DatosConEtiqueta.add(LineaConEtiqueta);
        }
        return DatosConEtiqueta;
    }
}
