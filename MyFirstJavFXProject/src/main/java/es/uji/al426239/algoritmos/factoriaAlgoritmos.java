package es.uji.al426239.algoritmos;

import es.uji.al426239.distance.FactoryDistance;
import es.uji.al426239.distance.intFactoriasDis;
import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.sistemaderecomendacion.RecSys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class factoriaAlgoritmos implements IntFactoriasAl {

        private intFactoriasDis Factoriadistancias;


        private  int numeroIteracion;
        private  int numeroClusters;
        private List<String> Algoritmos;
        private List<String> Distancias;
        private String ruta;
        private  String ficheroLabel;
        private String ficheroUnlabel;
        private String ficheroTestUnlabeled;
    private String ficheroTestlabeled;
        private ReaderTemplate lector;

        private  int seed;
        public factoriaAlgoritmos(intFactoriasDis distacias) {
            Factoriadistancias = distacias;
            this.numeroIteracion = 200;
            this.numeroClusters = 15;
            this.seed = 4321;
            Distancias = Factoriadistancias.GetLista();
            Algoritmos = new ArrayList<>(List.of("Recommend based on songs features","Recommend based on guessed genre"));
            String sep = FileSystems.getDefault().getSeparator();
            ruta = "." + sep + "data"+ sep;
            ficheroLabel =ruta+"songs_train.csv";
            ficheroUnlabel = ruta+"songs_train_withoutnames.csv";
            ficheroTestlabeled = ruta + "songs_test.csv";
            ficheroTestUnlabeled = ruta + "songs_test_withoutnames.csv";
            ruta = "." + sep + "data"+ sep;
        }
        @Override
        public RecSys Selecion(String elecion, String distance) throws IOException, FilaVacia, TablaVacia, Comparator {
            RecSys recomendador;
            switch (elecion) {
                case "Recommend based on songs features":
                    System.out.println("estos en is Knn");
                    recomendador = IsKnn(distance);
                    break;
                case "Recommend based on guessed genre":
                    System.out.println("estos en is KMEANS");
                     recomendador = IsKmeans(distance);
                    break;

                default:
                    throw  new IllegalArgumentException("Parametro no reconocido");
            }
            System.out.println("Vuelta");
            return recomendador;
        }
        private RecSys IsKnn(String disce) throws IOException, FilaVacia, TablaVacia, Comparator {
            lector = new CSVLabeledFileReader(ficheroLabel);
            System.out.println(" se leer");
            Algorithm Algoritmo = new KNN(Factoriadistancias.Selecion(disce));
            System.out.println("nuevo algoritmo");
            RecSys recomendador = new RecSys(Algoritmo);
            Table tabla = lector.readTableFromSource();
            recomendador.train(tabla);
            lector.ChangeFile(ficheroTestlabeled);
            recomendador.run(lector.readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
            return recomendador;

        }

        private RecSys IsKmeans(String disce) throws IOException, FilaVacia, TablaVacia, Comparator {
            lector = new CSVLabeledFileReader(ficheroUnlabel);
            Algorithm Algoritmo =  new KMeans(numeroClusters,numeroIteracion,seed,Factoriadistancias.Selecion(disce));
            RecSys recomendador = new RecSys(Algoritmo);
            Table tabla = lector.readTableFromSource();
            recomendador.train(tabla);
            lector.ChangeFile(ficheroTestUnlabeled);
            recomendador.run(lector.readTableFromSource(),readNames(ruta+"songs_train_names.csv"));
            return recomendador;

        }
        @Override
        public List<String> GetListaAlgoritmos(){
            return Algoritmos;
        }
        @Override
        public List<String> GetListaDistancias(){
            return Distancias;
        }
        private List<String> readNames(String fileOfItemNames) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
            String line;
            List<String> names = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                names.add(line);
            }
            br.close();
            return names;
        }


}
