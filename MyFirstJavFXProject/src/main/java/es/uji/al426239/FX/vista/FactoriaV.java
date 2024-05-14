package es.uji.al426239.FX.vista;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.List;

public interface FactoriaV {
    public Text Ftexto(String texto1);
    public Toggle Cbotones(String nombre , ToggleGroup alfa);
    public void Evenbotones(List<Toggle> alfa,TiposDeEvento tipo);
}
