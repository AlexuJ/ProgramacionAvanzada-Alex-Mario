package es.uji.al426239.FX.vista;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public interface Factoria {
    Text Texto(String texto1);
    RadioButton Botones(String nombre , ToggleGroup alfa);
}
