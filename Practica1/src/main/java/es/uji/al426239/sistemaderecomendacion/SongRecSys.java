package es.uji.al426239.sistemaderecomendacion;

import es.uji.al426239.distance.Distance;
import es.uji.al426239.distance.EuclideanDistance;
import es.uji.al426239.lectordetablas.CSVLabeledFileReader;
import es.uji.al426239.lectordetablas.CSVUnlabeledFileReader;
import es.uji.al426239.lectordetablas.ReaderTemplate;
import es.uji.al426239.rowytable.Table;
import es.uji.al426239.algoritmos.Algorithm;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.algoritmos.KMeans;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SongRecSys {
    SongRecSys(String method) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "." + sep +"Practica1" + sep + "data"; //HABRÁ QUE CAMBIAR LA RUTA PARA SVEN

        // File names (could be provided as arguments to the constructor to be more
        // general)
        Map<String, String> filenames = new HashMap<>();
        filenames.put("knn" + "train", ruta + sep + "songs_train.csv");
        filenames.put("knn" + "test", ruta + sep + "songs_test.csv");
        filenames.put("kmeans" + "train", ruta + sep + "songs_train_withoutnames.csv");
        filenames.put("kmeans" + "test", ruta + sep + "songs_test_withoutnames.csv");

        // Algorithms
        Distance distance = new EuclideanDistance();
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn", new KNN(distance));
        algorithms.put("kmeans", new KMeans(15, 200, 4321, distance));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String[] stages = { "train", "test" };

        ReaderTemplate csvunlabeled, csvlabeled;

        for (String stage : stages) {
            csvlabeled = new CSVLabeledFileReader(filenames.get("knn" + stage));
            csvunlabeled = new CSVUnlabeledFileReader((filenames.get("kmeans" + stage)));
            tables.put("knn" + stage,  csvlabeled.readTableFromSource());
            tables.put("kmeans" + stage, csvunlabeled.readTableFromSource());
        }

        // Names of items
        List<String> names = readNames(ruta + sep + "songs_test_names.csv");

        // Start the RecSys
        RecSys recsys = new RecSys(algorithms.get(method));
        recsys.train(tables.get(method + "train"));
        recsys.run(tables.get(method + "test"), names);

        // Given a liked item, ask for a number of recomendations
        String liked_name = "Lootkemia";
        List<String> recommended_items = recsys.recommend(liked_name, 5);

        // Display the recommendation text (to be replaced with graphical display with
        // JavaFX implementation)
        reportRecommendation(liked_name, recommended_items);
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

    private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \"" + liked_name + "\" then you might like:");
        for (String name : recommended_items) {
            System.out.println("\t * " + name);
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys("knn");
        new SongRecSys("kmeans");
    }
}