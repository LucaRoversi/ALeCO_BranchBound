package combinatoricaricBB;

/** Genera permutazioni di una collezione di elementi
 * con un processo ricorsivo, producendo uno spazio degli stati 
 * in accordo con una visita casuale dell'albero che lo 
 * rappresenta.
 * 
 * La generazione sceglie casualmente: il prossimo eNode tra
 * i liveNodes.
 * 
 * Sperimenta anche la potatura casuale di un sottoalbero, 
 * rifiutatndo, casualmente, l'attuale eNode.
 * 
 * Che tutti i figli di un eNode in via di espansione siano 
 * accodati (politica FIFO) ai liveNodes, o impilati (politica 
 * LIFO) ai liveNodes, diventa irrilevante.                     */

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import supportoBB.*;

public class PermutazioniRicRandom {

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

  private static ArrIntInt eNode(List<ArrIntInt> liveNodes) {
    int indiceENode = nodoCasuale(liveNodes); 
    ArrIntInt eNode = liveNodes.get(indiceENode);
    liveNodes.remove(indiceENode);
    return eNode;
  }

  /* Sceglie un nodo a caso tra i liveNodes.                 */
  private static int nodoCasuale(List<ArrIntInt> liveNodes) {
    return new Random().nextInt(liveNodes.size()); // [0..liveNodes.size())
  }

  /* Scompone eNode nelle sue due componenti:
   * -) soluzione;
   * -) lunghezza effettiva della soluzione. 
   * Quindi, verifica se soluzione e' completa.              */
  private static boolean completo(ArrIntInt eNode) {
    Integer[] soluzione = eNode.pi0();
    int j = eNode.pi1();
    return soluzione.length == j;
  }

  /* Scarta un nodo in base ad una scelta casuale.            */
  private static boolean rifiuta(ArrIntInt eNode) {
    return (new Random()).nextBoolean(); // true o false
  }

  /* Implementa l'invariante di una visita in ampiezza dello spazio
   * degli stati. Puo', indifferentemente trattare l'elenco dei 
   * liveNodes come una coda, o come pila.                        */
  private static List<ArrIntInt> espande(ArrIntInt eNode,
      List<ArrIntInt> liveNodes) {

    Integer[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    int i = j;
    while (i < soluzione.length) {

      swap(soluzione, i, j);
      Integer[] nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
      ArrIntInt nuovoLiveNode = new ArrIntInt(nuovaSoluzione, j + 1);
      liveNodes.add(0, // LIFO
//      spazioStati.add(spazioStati.size(), // FIFO
                      nuovoLiveNode);
      swap(soluzione, i, j);

      i += 1;
    }
    return liveNodes;
  }

  /* Non ci sono argomenti per non accettare.
   * Le scelte casuali sono gia' state operate per scegliere
   * l'eNode, o per scartarlo. Arrivati a questo punto si accetta.  */
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