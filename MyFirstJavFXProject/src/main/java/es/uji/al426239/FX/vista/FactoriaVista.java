package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FactoriaVista implements Factoria {
    private final Controlador controlador;
    public FactoriaVista(Controlador controlador) {
        this.controlador = controlador;
    }
    @Override
    public Text Texto(String texto1) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD, 15));
        return tiporecomendacion;
    }
    @Override
    public RadioButton Botones(String nombre, ToggleGroup alfa) {
        RadioButton radioButton1 = new RadioButton(nombre);
        radioButton1.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD, 10));
        radioButton1.setToggleGroup(alfa);
        return radioButton1;
    }
    public void Evenbotones(RadioButton radioButton,TiposDeEvento tipo) {
        if (tipo == TiposDeEvento.Algoritm && radioButton.getText().equals("Recommend based on songs features")) {
            AlgorithmBotton(radioButton,1);
        } else if (tipo == TiposDeEvento.Distance && radioButton.getText().equals("Euclidean")) {
            DistanceBotton(radioButton,1);
        } else if (tipo == TiposDeEvento.Algoritm && radioButton.getText().equals("Recommend based on guessed genre")) {
            AlgorithmBotton(radioButton,2);
        } else if (tipo == TiposDeEvento.Distance && radioButton.getText().equals("Manhattan")) {
            DistanceBotton(radioButton,2);
        }
    }
    private void  AlgorithmBotton(RadioButton radioButton, int a) {
        radioButton.setOnAction(value -> controlador.EventAlgorithm(a));
    }
    private void  DistanceBotton(RadioButton botones, int a) {
        botones.setOnAction(value -> controlador.EventAlgorithm(a));
    }
}
