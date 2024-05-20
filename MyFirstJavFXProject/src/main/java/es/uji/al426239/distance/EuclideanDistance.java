package es.uji.al426239.distance;

import javax.xml.namespace.QName;
import java.util.List;

public class EuclideanDistance implements Distance {

    public EuclideanDistance(){

    }
    @Override
    public double calculateDistance(List<Double> data, List<Double> data2) {
        if (data.size() != data2.size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
        double MetricaEuclidiana = 0.0;
        for (int i = 0; i < data.size(); i++) {
            MetricaEuclidiana += Math.pow(data.get(i) - data2.get(i), 2);
        }
        return Math.sqrt(MetricaEuclidiana);
    }


}
