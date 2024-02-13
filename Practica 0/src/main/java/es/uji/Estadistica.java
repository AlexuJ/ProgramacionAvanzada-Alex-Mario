package es.uji;

public class Estadistica {
    private float ultimoResultado;
    public float mediaAritmetica(float[] datos) {
        float suma_total=sumador(datos);
        float total_de_numeros=datos.length;
        return division(suma_total,total_de_numeros);
    }
    public float varianza(float[] datos) {
        if (datos.length>0) {
            float total_de_numeros=datos.length;
            float suma_total = 0.0F;
            for (int i=0;i< datos.length;i++) {
                suma_total+= (float) Math.pow(suma(datos[i],-mediaAritmetica(datos)),2);
            }
            return suma_total/total_de_numeros;
        }
        return 0.0F;
    }
    public float desviacionTipica(float[] datos) {
        return (float) Math.sqrt(varianza(datos));
    }
    protected float suma(float primerSumando, float segundoSumando) {
        return ultimoResultado = primerSumando + segundoSumando;
    }
    protected float resta(float minuendo, float sustraendo) {
        return ultimoResultado = minuendo - sustraendo;
    }
    protected float multiplicacion(float primerFactor, float segundoFactor) {
        return ultimoResultado = primerFactor * segundoFactor;
    }
    protected float division(float dividendo, float divisor) {
        if (divisor!=0) {
            return ultimoResultado = dividendo / divisor;
        }
        return 0.0F;
    }
    protected float getUltimoResultado() {
        return ultimoResultado;
    }
    public  float sumador (float [] datos){
        float  sumatotal = 0;
        for (int i = 0 ;i<datos.length;i++){
            sumatotal = suma(sumatotal,datos[i]);
        }
        return  ultimoResultado = sumatotal;
    }
}