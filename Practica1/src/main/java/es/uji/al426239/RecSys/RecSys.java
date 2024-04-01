package es.uji.al426239.RecSys;

import es.uji.al426239.CarpetaTable.Table;
import es.uji.al426239.Excepciones.Comparador;
import es.uji.al426239.Interfaz.Algorithm;
import java.util.*;

public class RecSys {
   private final Algorithm algorithm;
   private Table testData;
   private  List<String> testItemNames;
   private final List<String> selectedItems;
   private final Map<Integer, List<String>> labelToItems;
   public RecSys(Algorithm algorithm) {
       this.algorithm = algorithm;
       this.selectedItems = new ArrayList<>();
       this.labelToItems = new HashMap<>();
   }
   public void train(Table trainData) {
       this.testData = trainData;
   }
   public void run(Table testData, List<String> testItemNames) throws Comparador {
       this.testData = testData;
       this.testItemNames = testItemNames;
       algorithm.train(testData);
       estimate();
   }
   public List<String> recommend(String nameLikedItem, int numRecommendations) {
       //1. a) obtengo el índice del elemento que ha gustado
       int idx = findName(nameLikedItem);
       //1. b) - 2, se obtiene el dato de la fila con el índice anterior y se estima directamente
       int likedItemLabel = algorithm.estimate(testData.getRow().get(idx).getData());
       //3. obtengo las etiquetas de elementos parecidos
       selectItems(idx, likedItemLabel, numRecommendations);
       //4. devuelvo la lista de recomendaciones
       return getNamesSelectedItems();
   }
   private void estimate() {
       for (int i=0; i < testData.getRow().size(); i++) {
           List<Number> datos = testData.getRow().get(i).getData();
           algorithm.estimate(datos);
       }
   }
   private int findName(String nameItem) {
       return testItemNames.indexOf(nameItem);
   }
   private void selectItems(int idxLikedItem, int labelLikedItem, int numRec) {
       int count = 0;
       for (int i = 0; i < testData.getRow().size(); i++) {
           if (algorithm.estimate(testData.getRow().get(i).getData()) == labelLikedItem && i != idxLikedItem) {
               selectedItems.add(testItemNames.get(i));
               List<String> items = labelToItems.computeIfAbsent(labelLikedItem, k -> new ArrayList<>());
               items.add(testItemNames.get(i));
               count++;
               if (count == numRec) {
                   break;
               }
           }
       }
   }
   private List<String> getNamesSelectedItems() {
       return selectedItems;
   }
}
