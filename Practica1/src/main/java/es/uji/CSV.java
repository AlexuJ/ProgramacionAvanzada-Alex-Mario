package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSV implements LectorTabla {
    //Método para leer fichero sin etiquetas
    public Table readTable(String fichero) throws FileNotFoundException {
        Table tabla = new Table();
        //El separador es "\\s+" para el fichero si etiquetas
        tabla.headers=Headers(fichero, "\\s+");
        tabla.datos=DatosTabla(fichero, "\\s+");
        return tabla;
    }
    //Método para leer fichero con etiquetas
    public TableWithLabels readTableWithLabels(String fichero) throws FileNotFoundException {
        TableWithLabels TablaConEtiquetas = new TableWithLabels();
        //El separador es "," para el fichero con etiquetas
        TablaConEtiquetas.headers=Headers(fichero, ",");
        //Le paso también el mapa para coger las etiquetas
        TablaConEtiquetas.datos=TratarDatosConEtiqueta(fichero, TablaConEtiquetas.rellenarMapaEtiquetas(fichero));
        return TablaConEtiquetas;
    }
    //Método para procesar cabeceras de cualquier fichero
    @Override
    public List<String> Headers(String fichero, String Separador) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fichero));
        String[] linea = sc.nextLine().split(Separador);
        List<String> cabeceras = new ArrayList<>(List.of(linea));
        sc.close();
        return cabeceras;
    }
    //Método para procesar Datos de cualquier tabla
    @Override
    public List<Row> DatosTabla (String fichero, String Separador) throws FileNotFoundException {
        List<Row> DatosSinEtiqueta = new ArrayList<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        while (sc.hasNextLine()) {
            Row fila = new Row();
            String[] linea = sc.nextLine().split(Separador);
            int fin = linea.length;
            //Si el separador que se pasa es "," significa que se pasa el fichero con etiquetas,
            //por lo que las etiquetas son procesadas en el mapa y las obtengo del método privado
            if (Separador.equals(",")) {
                fin-=1;
            }
            //proceso únicamente los datos, no las etiquetas
            for (int i=0; i<fin; i++) {
                fila.data.add(Double.valueOf(linea[i]));
                DatosSinEtiqueta.add(fila);
            }
        }
        sc.close();
        return DatosSinEtiqueta;
    }
    //Método para tratar datos con etiquetas
    private List<Row> TratarDatosConEtiqueta(String fichero, Map<String, Integer> Etiquetas) throws FileNotFoundException {
        List<Row> DatosConEtiqueta = new ArrayList<>();
        List<Row> DatosATratar = DatosTabla(fichero, ",");
        int iterador = 0;
        //Itero sobre el mapa para coger el numberClass de cada etiqueta
        for (String etiqueta : Etiquetas.keySet()) {
            RowWithLabels LineaConEtiqueta = new RowWithLabels();
            LineaConEtiqueta.data = DatosATratar.get(iterador++).data;
            LineaConEtiqueta.numberClass = Etiquetas.get(etiqueta);
            DatosConEtiqueta.add(LineaConEtiqueta);
        }
        return DatosConEtiqueta;
    }
}
