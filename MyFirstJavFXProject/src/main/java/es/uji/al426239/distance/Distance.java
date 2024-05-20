package es.uji.al426239.distance;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q);
     String getName();
}
