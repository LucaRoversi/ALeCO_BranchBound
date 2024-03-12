package combinatoricaricBB;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;

import supportoBB.*;

public class SottoinsiemiRicLIFO {
  
  public static int conta = 0;

  public static void risposte(List<ArrBoolInt> spazioStati) {

    ArrBoolInt eNode = eNode(spazioStati);

    if (!completo(eNode)) {
      if (!rifiuta(eNode)) {
        spazioStati = espande(eNode, spazioStati);
        while (spazioStati.iterator().hasNext())
          risposte(spazioStati);
      } else {
        System.out.println("Rifiuta " + toString(eNode) + " " + ++conta);
      }
    } else {
      if (accetta()) {
        System.out.println("Accetta " + toString(eNode) + " " + ++conta);
      } else {
        System.out.println("Non accetta " + toString(eNode) + " " + ++conta);
      }
    }
  }

  private static ArrBoolInt eNode(List<ArrBoolInt> liveNodes) {
    ArrBoolInt eNode = liveNodes.get(0);
    liveNodes.remove(0);
    return eNode;
  }

  public static boolean completo(ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();
    return soluzione.length == j;
  }

  public static boolean rifiuta(ArrBoolInt eNode) {
    return false;
  }

  private static List<ArrBoolInt> espande(ArrBoolInt eNode,
      List<ArrBoolInt> liveNodes) {

    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    Boolean[] nuovaSoluzione = null;
    ArrBoolInt nuovoLiveNode = null;

    nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
    nuovoLiveNode = new ArrBoolInt(nuovaSoluzione, j + 1); 
    liveNodes.add(0, // LIFO
                nuovoLiveNode);
    
    nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
    nuovaSoluzione[j] = !nuovaSoluzione[j];
    nuovoLiveNode = new ArrBoolInt(nuovaSoluzione, j + 1); 
    liveNodes.add(0, // LIFO
                nuovoLiveNode);

    return liveNodes;
  }

  public static boolean accetta() {
    return true;
  }

  public static String toString(ArrBoolInt eNode) {
    Boolean[] risposta = eNode.pi0();
   return Arrays.deepToString(risposta);
  }
}