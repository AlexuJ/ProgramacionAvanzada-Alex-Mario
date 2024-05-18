package es.uji.al426239.distance;

import java.util.ArrayList;
import java.util.List;

public class FactoryDistance implements intFactoriasDis{
    private List<String> Distancias;
    public FactoryDistance(){

        this.Distancias =new ArrayList<>(List.of("MANHATTHAM","EUCLIDEAN"));

    }

    public Distance Selecion(String distance) {
        Distance distancia ;
        distance = distance.toUpperCase();
        switch (distance) {
            case "MANHATTHAM":
                distancia = isMahattam();
                break;
            case "EUCLIDEAN":
                distancia = isEuclidean();
                break;

            default:
                throw  new IllegalArgumentException("Parametro no reconocido");
        }
        return distancia;
    }
    public Distance isMahattam(){
        return new ManhattanDistance();
    }
    public Distance isEuclidean(){
        return new EuclideanDistance();
    }
    @Override
    public List<String> GetLista(){
        return Distancias;
    }

}
