package es.uji.al426239.Excepciones;

public class EtiquetaNoEncontrada extends Exception {
    public EtiquetaNoEncontrada(String etiqueta) {
        super("La etiqueta "+etiqueta+" no está en el diccionario");
    }
}
