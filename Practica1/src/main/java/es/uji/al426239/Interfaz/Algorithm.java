package es.uji.al426239.Interfaz;

public interface Algorithm<T,U,W> {
    public void train(T Table);
    public W estimate(U datos);
}
