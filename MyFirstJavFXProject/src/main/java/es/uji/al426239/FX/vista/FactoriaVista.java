package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;

public class FactoriaVista implements Factoria {

    public FactoriaVista() {

    }
    @Override
    public Text Texto(String texto1) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD, 15));
        return tiporecomendacion;
    }
    @Override
    public ToggleButton Botones(String nombre, ToggleGroup botones) {
        RadioButton radioButton1 = new RadioButton(nombre);
        radioButton1.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD, 10));
        radioButton1.setToggleGroup(botones);
        return radioButton1;
    }
    public void Evenbotones(List<Toggle> alfa,TiposDeEvento tipo) {
        for (int i = 0 ; i < alfa.size();i++){
            Toggle boton = alfa.get(i);
            RadioButton radioButton = (RadioButton) boton;
            if (tipo == TiposDeEvento.Algoritm) {

            }else if(tipo == TiposDeEvento.Distance) {

            }
        }
    }

}
