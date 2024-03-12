package combinatoricaricBB;

/** Genera tutte le permutazioni di una collezione di elementi
 * con un processo ricorsivo, producendo uno spazio degli stati 
 * in ampiezza dell'albero che lo rappresenta.
 * 
 * La visita in ampiezza segue dall'accodare (politica FIFO)
 * tutti i figli di un eNode alla coda dei liveNodes.          */

import java.util.Arrays;
import java.util.List;
import supportoBB.*;

public class PermutazioniRicFIFO {

  public static int conta = 0;

  /* Visita ricorsivamente l'elenco dei liveNodes, finche'
   * non si vuota. La terminazione e' assicurata dalla finitezza
   * dello spazio degli stati. Si assume un elenco iniziale di
   * liveNodes composto da un nodo che corrisponde alla radice
   * dello spazio degli stati.                                    */
  public static void risposte(List<ArrIntInt> liveNodes) {

    ArrIntInt eNode = eNode(liveNodes);

    if (!completo(eNode)) {
      if (!rifiuta(eNode)) {
        
        liveNodes = espande(eNode, liveNodes);
        
        while (liveNodes.iterator().hasNext())
          risposte(liveNodes);
        
      } else {
        System.out.println("Rifiuta " + toString(eNode) 
                              + " " + ++conta);
      }
    } else {
      if (accetta()) { 
        System.out.println("Accetta " + toString(eNode) 
                              + " " + ++conta);
      } else { // mai percorso
        System.out.println("Non accetta " + toString(eNode) 
                              + " " + ++conta);
      }
    }
  }

  /* Preleva sempre il primo elemento disponibile nello spazio
   * degli stati.                                                */
  private static ArrIntInt eNode(List<ArrIntInt> spazioStati) {
    ArrIntInt eNode = spazioStati.get(0);
    spazioStati.remove(0);
    return eNode;
  }

  /* Un eNode corrisponde and una soluzione completa se, la 
   * sua componente, corrispondente ad una soluzione, e',
   * appunto, completa.                                        */
  private static boolean completo(ArrIntInt eNode) {
    Integer[] soluzione = eNode.pi0();
    int j = eNode.pi1();
    return soluzione.length == j;
  }

  /* Generando tutto lo spazio delle permutazioni, non
   * si rifiuta mai . */
  private static boolean rifiuta(ArrIntInt eNode) {
    return false;
  }

  /* Implementa l'invariante di una visita in ampiezza dello spazio
   * degli stati. In particolare, tratta l'elenco dei liveNodes
   * come una coda, accodando i figli dell'attuale eNode.          */
  private static List<ArrIntInt> espande(ArrIntInt eNode,
      List<ArrIntInt> liveNodes) {

    Integer[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    int i = j;
    while (i < soluzione.length) {

      swap(soluzione, i, j);
      Integer[] nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
      ArrIntInt nuovoLiveNode = new ArrIntInt(nuovaSoluzione, j + 1);
      liveNodes.add(liveNodes.size(), // FIFO
                      nuovoLiveNode);
      swap(soluzione, i, j);

      i += 1;
    }
    return liveNodes;
  }

  /* Generando tutto lo spazio delle permutazioni, non appena una 
   * soluzione e' completa non possiamo far altro che accettare.       */
  private static boolean accetta() {
    return true;
  }

  private static void swap(Integer[] soluzione, int i, int j) {
    int tmp = soluzione[i];
    soluzione[i] = soluzione[j];
    soluzione[j] = tmp;
  }

  private static String toString(ArrIntInt eNode) {
    Integer[] risposta = eNode.pi0();
    return Arrays.deepToString(risposta);
  }
}