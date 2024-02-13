package es.uji.delaHorraBuga;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Aritmetica {
    public Aritmetica(){

    }
    private float ultimoResultado ;
    //Sumar esta pensado para que se pueda utilizar como restar lo unico que hay que hacer es pasarle los numeros en negativo
    public float sumar(float primerNumero ,float segundoNumero){
        return  ultimoResultado = primerNumero + segundoNumero;
    }
    public  float multiplicar(float primerNumero ,float segundoNumero){
        return  ultimoResultado = primerNumero*segundoNumero;
    }
    //hay un caso de riesgo de que el valor sea 0 y de error en ese caso divisor pasa a ser 1
    public float divividir  (float dividendo , float divisor) {
        if (divisor == 0.0f) {
            System.out.println("Divisor igual ha 0 ");
             divisor = 1;
        }
        return  ultimoResultado = dividendo/divisor;
    }
    public  float sumador (float [] Numeros){
        float  sumatotal = 0;
        for (int i = 0 ;i<Numeros.length;i++){
            sumatotal = sumar(sumatotal,Numeros[i]);
        }
        return  ultimoResultado = sumatotal;
    }
    public float getUltimoResultado(){
        return  ultimoResultado;
    }
    //preguntar a en clase
    public  double eleva(float numero,float potencia){
        return Math.pow(numero,potencia);
    }
}
