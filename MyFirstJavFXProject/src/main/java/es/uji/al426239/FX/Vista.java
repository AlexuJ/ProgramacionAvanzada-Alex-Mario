package es.uji.al426239.FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Vista extends Application {
    private Controlador controlador;
    private Modelo modelo;
    private Stage escenario;
    private Scene escenaCanciones;
    private Scene escenaRecomendaciones;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.controlador = new Controlador();
        this.modelo = new Modelo();
        this.escenario = primaryStage;
        this.escenaCanciones = escenaListaCanciones();
        this.escenaRecomendaciones = escenaRecomendarTitulos();
        escenario.setScene(escenaCanciones);
        escenario.show();
    }
    //Escena canciones y sus métodos
    private Scene escenaListaCanciones() throws FileNotFoundException {
        VBox vBox = new VBox();
        crearelecciones(vBox,"Recommendation Type", "Recommend based on songs features", "Recommend based on guessed genre");
        crearelecciones(vBox,"Distance Type","Euclidean","Manhattan");
        crearlistacanciones(vBox);
        botonRecomendar(vBox);
        return new Scene(vBox,300,600);
    }
    private void crearelecciones(VBox vBox, String texto1, String texto2, String texto3) {
        Text tiporecomendacion = new Text(texto1);
        tiporecomendacion.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,15));
        RadioButton radioButton1 = new RadioButton(texto2);
        RadioButton radioButton2 = new RadioButton(texto3);
        radioButton1.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        radioButton2.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,10));
        ToggleGroup radioGroupRecommendation = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroupRecommendation);
        radioButton2.setToggleGroup(radioGroupRecommendation);
        vBox.getChildren().addAll(tiporecomendacion,radioButton1,radioButton2);
    }
    private void crearlistacanciones(VBox vBox) throws FileNotFoundException {
        Text titulolistacanciones = new Text("Song Titles");
        titulolistacanciones.setFont(Font.font("Bree Serif",FontWeight.SEMI_BOLD,20));
        ListView<String> listacanciones = modelo.anyadircanciones();
        vBox.getChildren().addAll(titulolistacanciones,listacanciones);
    }
    private void botonRecomendar(VBox vBox) {
        //Este botón pasa a la otra escena, la de recomendaciones
        Button button = new Button("Recommend");
        vBox.getChildren().addAll(button);
        button.setOnAction(value -> {
            escenario.setScene(escenaRecomendaciones);
            escenario.show();
        });
    }
    //Escena recomendaciones y sus métodos
    private Scene escenaRecomendarTitulos() throws FileNotFoundException {
        HBox hBox = anyadirNumeroRecomendaciones(new HBox());
        VBox vBox = ensenyaRecomendaciones(hBox, new VBox());
        botonClose(vBox);
        return new Scene(vBox, 350, 300);
    }
    private HBox anyadirNumeroRecomendaciones(HBox hBox) {
        Text texto = new Text("Number of recommendations:");
        Spinner<Integer> stringSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1,1));
        hBox.getChildren().addAll(texto,stringSpinner);
        return hBox;
    }
    private VBox ensenyaRecomendaciones(HBox hBox, VBox vBox) throws FileNotFoundException {
        // ↓ Este string habrá que hacer de alguna forma que guarde lo que se selecciona de la otra escena
        String cancionLiked = "|DARK|HARD|TECHNO";
        Text text = new Text("If you liked "+cancionLiked+" you might like");
        //Habrá que buscar la forma de lanzar aquí las recomendaciones
        ListView<String> listarecomendaciones = modelo.anyadircanciones();
        vBox.getChildren().addAll(hBox,text,listarecomendaciones);
        return vBox;
    }
    private void botonClose(VBox vBox) {
        //Este botón cierra el escenario
        Button button = new Button("Close");
        vBox.getChildren().addAll(button);
        button.setOnAction(value -> {
            escenario.close();
        });
    }
}
