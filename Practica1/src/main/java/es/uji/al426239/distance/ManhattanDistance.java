package es.uji.al426239.distance;

import java.util.List;

public class ManhattanDistance implements Distance {
    @Override
    public double calculateDistance(List<Double> data, List<Double> data2) {
        if (data.size() != data2.size()) {
            throw new IllegalArgumentException("Las listas deben tener la misma longitud");
        }
        double distanceManhattan = 0.0;
        for (int i = 0; i < data.size(); i++) {
            distanceManhattan += data.get(i) - data2.get(i);
        }
        return distanceManhattan;
    }
}
