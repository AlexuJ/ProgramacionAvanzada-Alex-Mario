package es.uji.al426239.FX.vista;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.List;

public interface Factoria {
    Text Texto(String texto1);
    RadioButton Botones(String nombre , ToggleGroup alfa);
    void Evenbotones(RadioButton radioButton,TiposDeEvento tipo);
}
