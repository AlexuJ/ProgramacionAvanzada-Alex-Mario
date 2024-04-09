package es.uji.al426239.Convertidor;

import java.util.ArrayList;
import java.util.List;
public class Convertidor {
    public List<Double> convertirADouble(List<Number> datos) {
        List<Double> datoDoubles = new ArrayList<>();
        for (Number num : datos) {
            datoDoubles.add(num.doubleValue());
        }
        return datoDoubles;
    }
}
