package es.uji.al426239.Excepciones;

public class Comparador extends Exception {
    public Comparador(int K, int N) {
        super("El número de grupos "+K+" es mayor que el número de filas "+N);
    }
}
