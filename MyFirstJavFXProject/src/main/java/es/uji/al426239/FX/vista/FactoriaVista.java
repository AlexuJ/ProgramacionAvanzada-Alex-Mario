package es.uji.al426239.FX.vista;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FactoriaVista implements FactoriaV {
    @Override
    public Text Texto(String texto1) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD,15));
        return tiporecomendacion;
    }
    @Override
    public RadioButton Botones(String nombre , ToggleGroup alfa){
        RadioButton radioButton1 = new RadioButton(nombre);
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton1.setToggleGroup(alfa);
        return radioButton1;
    }
}