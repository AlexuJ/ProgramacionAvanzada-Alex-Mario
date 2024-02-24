package es.uji;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface LectorTabla {
    public List<String> Headers (String fichero) throws FileNotFoundException;
    public List<Row> DatosSinEtiqueta (String fichero) throws FileNotFoundException;
    public List<RowWithLabels> DatosConEtiqueta (String fichero, Map<String,Integer> Etiquetas) throws FileNotFoundException;
}
