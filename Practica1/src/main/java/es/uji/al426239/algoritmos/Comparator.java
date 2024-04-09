package es.uji.al426239.algoritmos;

public class Comparator extends Exception {
    public Comparator(int K, int N) {
        super("El número de grupos " + K + " es mayor que el número de filas " + N);
    }
}