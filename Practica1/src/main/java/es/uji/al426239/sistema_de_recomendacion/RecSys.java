package es.uji.al426239.sistema_de_recomendacion;

import es.uji.al426239.row_table.Table;
import es.uji.al426239.algoritmos.Comparator;
import es.uji.al426239.algoritmos.Algorithm;
import java.util.*;

public class RecSys {
   private final Algorithm<Table, List<Number>, Integer> algorithm;
   private Table testData;
   private  List<String> testItemNames;
   private final List<String> selectedItems;
   private final Map<Integer, Integer> estimatedLabels;
   public RecSys(Algorithm<Table, List<Number>, Integer> algorithm) {
       this.algorithm = algorithm;
       this.selectedItems = new ArrayList<>();
       this.estimatedLabels = new HashMap<>();
   }
   public void train(Table trainData) throws Comparator {
       algorithm.train(trainData);
   }
   public void run(Table testData, List<String> testItemNames) {
       this.testData = testData;
       this.testItemNames = testItemNames;
       estimate();
   }
   public List<String> recommend(String nameLikedItem, int numRecommendations) {
       int idx = findName(nameLikedItem);
       int likedItemLabel = algorithm.estimate(testData.getRow().get(idx).getData());
       selectItems(idx, likedItemLabel, numRecommendations);
       return getNamesSelectedItems();
   }

    /* Para la parte de run() encargada de estimar la etiqueta de todo el conjunto de test */
   private void estimate() {
       for (int i=0; i < testData.getRow().size(); i++) {
           estimatedLabels.put(i,algorithm.estimate(testData.getRow().get(i).getData()));
       }
   }

    /* Devuelve el identificador de clase/grupo de nameItem, para el paso 1a de recommend() */
    private int findName(String nameItem) {
       return testItemNames.indexOf(nameItem);
   }

   /* Para el paso 3a de recommend() */
    private void selectItems(int idxLikedItem, int labelLikedItem, int numRec) {
       int count = 0;
       for (int i = 0; i < testData.getRow().size() && count < numRec; i++) {
           if (estimatedLabels.get(i) == labelLikedItem && i != idxLikedItem) {
               selectedItems.add(testItemNames.get(i));
               count++;
           }
       }
   }

    /* Para el paso 3b de recommend() */
    private List<String> getNamesSelectedItems() {
       return selectedItems;
   }
}
