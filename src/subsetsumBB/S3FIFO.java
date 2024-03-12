package subsetsumBB;

import java.util.Arrays;

/** Risolve il problema Subsetsum aggiungendo una gestione a
 * coda dei live nodes, partendo dall'algoritmo per Subsetsum 
 * in S3Rand.
 * 
 * Il problema e' una coppia:
 * < X[0..n) di elementi di cui formare sottoinsiemi
 * , valore S da eguagliare, sommando elementi di un sottoinsieme 
 *   X[0..j) di X[0..n), se X[0..j) esiste> .                      */

import java.util.List;

import supportoBB.*;

public class S3FIFO extends S3Rand {
  
  @Override
  /* Estende l'elenco dei liveNodes con due figli di eNode. */ 
  List<ArrBoolInt> espande(ArrBoolInt eNode,
      List<ArrBoolInt> liveNodes) {

    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    for (boolean nuovoElementoSoluzione : new boolean[] { soluzione[j], !soluzione[j] }) {
      Boolean[] nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
      nuovaSoluzione[j] = nuovoElementoSoluzione;
      ArrBoolInt nuovoLiveNode = new ArrBoolInt(nuovaSoluzione, j + 1);
      liveNodes.add(liveNodes.size(), // FIFO
                    nuovoLiveNode);
    }

    return liveNodes;
  }

  /* Estrae il primo nodo di liveNodes.                                  */
  @Override
  int indiceENode(int[] insieme, int s, List<ArrBoolInt> liveNodes) {
    int indiceEnode = 0;
    return indiceEnode;
  }
}