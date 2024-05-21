package es.uji.al426239.FX.modelo;

import es.uji.al426239.algoritmos.*;
import es.uji.al426239.sistemaderecomendacion.RecSys;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
<<<<<<< HEAD
=======
import javafx.scene.control.ListView;

>>>>>>> ae2dfad86e5bacd37abceab72c4973d444bc073c
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Modelo implements AskModelo {
    private int numeroRecomendaciones;
    private String cancionRecomendada;
    private ObservableList<String> recomendaciones;
    private HashMap<String,HashMap<String,RecSys>> Recomendador;
    private RecSys recomendadorActual;
    private IntFactoriasAl factoriaAlgoritmos;
    private List<String> Algoritmo;
    private List<String> Distancias;

    public Modelo(IntFactoriasAl factoria) {
        this.Recomendador = new HashMap<>();
        this.numeroRecomendaciones = 5;
        factoriaAlgoritmos = factoria;
        Algoritmo = factoriaAlgoritmos.GetListaAlgoritmos();
        Distancias = factoriaAlgoritmos.GetListaDistancias();
        this.recomendaciones = FXCollections.observableArrayList();
    }
    public void setRecomendaciones(String Algorithm,String Distance) throws FilaVacia, IOException, TablaVacia, Comparator {
        RecSys recSys;
        if (Recomendador.containsKey(Algorithm)){
            HashMap<String,RecSys> auxiliar = Recomendador.get(Algorithm);
            if (auxiliar.containsKey(Distance)){
                recSys = auxiliar.get(Distance);
            }else {
                recSys = factoriaAlgoritmos.Selecion(Algorithm,Distance);
                auxiliar.put(Distance,factoriaAlgoritmos.Selecion(Algorithm,Distance));
                Recomendador.put(Algorithm,auxiliar);
            }
        }else {
            HashMap<String,RecSys> auxiliar = new HashMap<>();
            recSys = factoriaAlgoritmos.Selecion(Algorithm,Distance);
            auxiliar.put(Distance,recSys);
            Recomendador.put(Algorithm,auxiliar);
        }
<<<<<<< HEAD
=======
        System.out.println(cancionRecomendada);
>>>>>>> ae2dfad86e5bacd37abceab72c4973d444bc073c
        recomendadorActual = recSys;
        List<String> nuevasRecomendaciones = recSys.recommend(cancionRecomendada ,numeroRecomendaciones);
        recomendaciones.setAll(nuevasRecomendaciones); // Actualiza la ObservableList con las nuevas recomendaciones
    }

    public void reset(){
        recomendadorActual.reset();
        recomendaciones = FXCollections.observableArrayList();
    }

    public void setNumeroRecomendaciones(int numeroRecomendaciones) {
        this.numeroRecomendaciones = numeroRecomendaciones;
    }
<<<<<<< HEAD

=======
    public ListView<String> anyadircanciones() throws FileNotFoundException {
        ListView<String> listacanciones = new ListView<>();
        String sep = FileSystems.getDefault().getSeparator();
        String fichero = "." + sep + "data" + sep + "songs_train_names.csv";
        Scanner sc = new Scanner(new File(fichero));
        while (sc.hasNextLine()) {
            listacanciones.getItems().add(sc.nextLine());
        }
        return listacanciones;
    }
>>>>>>> ae2dfad86e5bacd37abceab72c4973d444bc073c
    public String getCancionRecomendada() {
        return cancionRecomendada;
    }

    public void setCancionRecomendada(String cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }

    public int getNumeroRecomendaciones() {
        return numeroRecomendaciones;
    }

    public ObservableList<String> getRecomendaciones() {
        return recomendaciones;
    }

    @Override
    public List<String> GetAlgoritmos(){
        return Algoritmo;
    }

    @Override
    public List<String> GetDistancias(){
        return Distancias;
    }

}
