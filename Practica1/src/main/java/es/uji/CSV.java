package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//esta clase implementa la interfaz LectorTabla
public class CSV implements LectorTabla {
    //Metodo para leer la tabla sin etiquetas lanza una excepcion si no encuentra el fichero
    //se me a ocurrido usar una interfaz para tabla y para filas de esta forma podrias aplicar el poliformismo y siplificar tu codigo
    public Table readTable(String fichero) throws FileNotFoundException {
        //Mejoras para esta funcion crear una funcion vacia que haga el trabajo de asignar los headers y los datos
        //crea una nueva tabla
        Table tabla = new Table();
        //crea los headers  atraves de HeadersSinEtiqueta
        tabla.setHeaders(HeadersSinEtiqueta(fichero));
        //coje los datos sin etiqueta y manda return de la tabla
        tabla.setRows(DatosSinEtiqueta(fichero));
        return tabla;
    }
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        TablaConEtiquetas.headers=HeadersConEtiqueta(fichero);
        TablaConEtiquetas.DatosConEtiquetas=DatosConEtiqueta(fichero,TablaConEtiquetas.rellenarMapaEtiquetas(fichero));
        return TablaConEtiquetas;
    }
    //soy yo o esto estas usando un  metodo exactamente igules para obtener los headers lo cual se simplifica añadiendo un unico metodo
    @Override
    public List<String> HeadersSinEtiqueta(String fichero) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split("\\s+");
        List<String> cabeceras = new ArrayList<>(List.of(linea));
        sc.close();
        return cabeceras;
    }
    @Override
    public List<String> HeadersConEtiqueta(String fichero) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split(",");
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
            String[] linea = sc.nextLine().split("\\s+");
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
