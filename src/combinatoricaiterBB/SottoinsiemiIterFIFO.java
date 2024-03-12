package combinatoricaiterBB;

/** Genera tutti i sottoinsiemi di una collezione di elementi
 * con un processo iterativo, producendo uno spazio degli stati 
 * in accordo con una visita in ampiezza dell'albero che lo 
 * rappresenta.
 * 
 * La visita in ampiezza segue dall'accodare (politica FIFO)
 * tutti i figli di un eNode alla coda dei liveNodes.         */

import java.util.Arrays;
import java.util.List;

import supportoBB.*;

public class SottoinsiemiIterFIFO {

  public static int conta = 0;

  /* Visita iterativamente l'elenco dei liveNodes, finche'
   * non si vuota. La terminazione e' assicurata dalla finitezza
   * dello spazio degli stati. Si assume un elenco iniziale di
   * liveNodes composto da un nodo che corrisponde alla radice
   * dello spazio degli stati.                                    */
  public static void risposte(List<ArrBoolInt> liveNodes) {

    while (liveNodes.iterator().hasNext()) {
      
      ArrBoolInt eNode = eNode(liveNodes);

      if (!completo(eNode)) {
        if (!rifiuta(eNode)) {
          liveNodes = espande(eNode, liveNodes);
        } else {
          System.out.println("Rifiuta " + toString(eNode) 
                                + " " + ++conta);
        }
      } else {
        if (accetta()) {
          System.out.println("Accetta " + toString(eNode) 
                                + " " + ++conta);
        } else {
          System.out.println("Non accetta " + toString(eNode) 
                                + " " + ++conta);
        }
      }
    }
  }

  /* Preleva sempre il primo elemento disponibile nello spazio
   * degli stati.                                                */
  private static ArrBoolInt eNode(List<ArrBoolInt> liveNodes) {
    ArrBoolInt eNode = liveNodes.get(0);
    liveNodes.remove(0);
    return eNode;
  }

  /* Un eNode corrisponde and una soluzione completa se, la 
   * sua componente, corrispondente ad una soluzione, e',
   * appunto, completa.                                        */
  private static boolean completo(ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();
    return soluzione.length == j;
  }

  /* Generando tutto lo spazio dei sottoinsiemi, non
   * si rifiuta mai . */
  private static boolean rifiuta(ArrBoolInt eNode) {
    return false;
  }

  /* Implementa l'invariante di una visita in ampiezza dello spazio
   * degli stati. In particolare, tratta l'elenco dei liveNodes
   * come una coda, accodando i figli dell'attuale eNode.          */
  private static List<ArrBoolInt> espande(ArrBoolInt eNode,
      List<ArrBoolInt> liveNodes) {

    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    Boolean[] nuovaSoluzione = null;
    for (boolean nuovoElementoInSoluzione : new boolean[] { soluzione[j], !soluzione[j] }) {
      nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
      nuovaSoluzione[j] = nuovoElementoInSoluzione;
      ArrBoolInt nuovoLiveNode = new ArrBoolInt(nuovaSoluzione, j + 1);
      liveNodes.add(liveNodes.size(), // FIFO
                      nuovoLiveNode);
    }
    return liveNodes;
  }

  /* Generando tutto lo spazio delle permutazioni, non appena
   * una soluzione e' completa non possiamo far altro che accettare. */
  private static boolean accetta() {
    return true;
  }

  private static String toString(ArrBoolInt eNode) {
    Boolean[] risposta = eNode.pi0();
    return Arrays.deepToString(risposta);
  }
}