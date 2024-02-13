package es.uji.delaHorraBuga;
    public class Estadistica extends Aritmetica {
        private float ultimoResultado;


        public float mediaAritmetica(float[] datos) {
            return divividir(sumador(datos),datos.length);
        }
        public float varianza(float[] datos) {
            float media = mediaAritmetica(datos);
            float[] numeros = new float[datos.length];
            for (int i = 0 ;i<datos.length;i++) {
                numeros[i] = multiplicar(sumar(datos[i],-media),sumar(datos[i],-media));
            }
            return  divividir(sumador(numeros),datos.length);
        }
        public double desviacionTipica(float[] datos) {
           return eleva(varianza(datos),0.5f);
        }
    }

