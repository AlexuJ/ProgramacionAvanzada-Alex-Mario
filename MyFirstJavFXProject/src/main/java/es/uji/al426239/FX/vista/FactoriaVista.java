package es.uji.al426239.FX.vista;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;

public class FactoriaVista implements Factoria {
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
    @Override
    public void Evenbotones (List < Toggle > alfa, TiposDeEvento tipo) {
        if (tipo == TiposDeEvento.Algoritm) {
            AlgorithmBotton(alfa);
        }
    }
    private void AlgorithmBotton (List < Toggle > botones) {
        for (Toggle boton : botones) {
            RadioButton radioButton = (RadioButton) boton;
        }
    }
}
