package es.uji.al426239.FX.vista;

import es.uji.al426239.FX.controlador.Controlador;
import es.uji.al426239.distance.Distance;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class FactoriaVista implements FactoriaV {
   private Controlador controlador ;
    public FactoriaVista(){

    }
    public void getControlador(Controlador control){
        controlador = control;
    }
    @Override
    public  Text Ftexto(String texto1) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif", FontWeight.SEMI_BOLD,15));
        return tiporecomendacion;
    }
    @Override
    public Toggle Cbotones(String nombre , ToggleGroup alfa){
        RadioButton radioButton1 = new RadioButton(nombre);
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton1.setToggleGroup(alfa);
        return  radioButton1;
    }
    @Override
    public void Evenbotones(List<Toggle> alfa,TiposDeEvento tipo){
        //cambiar por un switch case
        for (int i = 0 ; i < alfa.size();i++){
            Toggle boton = alfa.get(i);
            RadioButton radioButton = (RadioButton) boton;
            if (tipo == TiposDeEvento.Algoritm){
                AlgorithmBotton(radioButton,i+1);
            }else if(tipo == TiposDeEvento.Distance){
                DistanceBotton(radioButton,i+1);
            }
        }

    }
    private void  AlgorithmBotton(RadioButton radioButton,int a){
        radioButton.setOnAction(value -> controlador.EventAlgorithm(a));

    }

    private void  DistanceBotton(RadioButton botones,int a){
        botones.setOnAction(value -> controlador.EventAlgorithm(a));
    }
}
