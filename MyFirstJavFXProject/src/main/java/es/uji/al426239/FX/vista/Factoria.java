package es.uji.al426239.FX.vista;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.List;

public interface Factoria {
    Text Texto(String texto1);
    ToggleButton Botones(String nombre , ToggleGroup alfa);
    void Evenbotones(List<Toggle> alfa,TiposDeEvento tipo);
}
