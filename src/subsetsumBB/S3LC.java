package subsetsumBB;

/** Propone una riflessione di natura didattica sulla soluzione al
 * problema Subsetsum tramite un algoritmo Least-Cost search.
 * 
 * Il problema e' una coppia:
 * < X[0..n) di elementi di cui formare sottoinsiemi
 * , valore S da eguagliare, sommando elementi di un sottoinsieme 
 *   X[0..j) di X[0..n), se X[0..j) esiste> .
 * 
 * Implementiamo l'euristica Least Cost-search su uno spazio degli
 * stati che rappresenta sottoinsiemi x[0..j) di indici {0..n-1} 
 * dell'insieme X[0..n).
 * 
 * 1) Definiamo il costo FH(x[0..j)) di costruire x[0..j) come:
 * 
 *    FH(x[0..j)) = x[0]*X[0]+..+x[j-1]*X[j-1]  
 *          
 * in cui x[k]==1 codifica true e x[k]==0 codifica false.
 * 
 * 2) Partendo dal valore *noto* FH(x[0..j)), lo scopo e'ottenere S
 * con gli elementi che, alla fine, x[0..j) conterra'. Con uno sforzo
 * computazionale accettabile, possiamo chiederci se sia possibile
 * estendere x[0..j) con elementi opportuni, che esso non contenga
 * ancora, ed approssimare per eccesso, nel modo migliore, il valore S
 * come segue:  
 * 
 *   S <= FH(x[0..j))+(X[j]+..+X[split-1]+X[split]) 
 *        
 * dato un indice split opportuno. Analogamente, l'esstensione di 
 * x[0..j) puo' essere "fermata un passo prima" ed ottenere la migliore
 * approssimazione per difetto di S, come segue:
 * 
 * 
 *  FH(x[0..j))+(X[j]+..+X[split-1]) <= S  .
 * 
 * Se definiamo: 
 * 
 *       G(x[0..j)) = X[j]+..+X[split-1]+X[split] 
 *       
 * identificare l'intervallo:
 *   
 *      FH(x[0..j))+(X[j]+..+X[split-1]) 
 *           <= S <= FH(x[0..j))+(X[j]+..+X[split-1]+X[split])
 *      
 * equivale a trovare, con costo computazionale oggettivamente poco 
 * rilevante, un indice split opportuno per cui:
 * 
 *      FH(x[0..j))+G(x[0..j))-X[split-1] 
 *                <= S <= FH(x[0..j))+G(x[0..j))  .
 *
 * Invertendo i segni, possiamo usare parte della relazione appena 
 * scritta:
 * 
 *      -(FH(x[0..j))+G(x[0..j))) <= -S 
 *      
 * come funzione costo approssimata per scegliere l'eNode, 
 * che sara' quello, tra i live nodes, con:
 * 
 *      -(FH(x[0..j))+G(x[0..j)))
 * 
 * a valore minimo. 
 * 
 * OSSERVAZIONE 
 * Vale la pena insistere sul fatto che l'identificazione di ogni istanza 
 * di x[0..j) procede per tentativi e determina la complessita' dell'intero 
 * processo di ricerca.
 *  
 * Il calcolo di G(X[0..j)) e' molto meno costoso perche' non richiede 
 * ricerche, ma aggiunge ad x[0..j) gli elementi fino a "giusto prima", o ad
 * "appena dopo" il valore S.
 * 
 * NOTA
 * La definizione della funzione costo approssimata appena sintetizzata
 * non e' tra quelle canoniche che la letteratura offre, volendo risolvere il 
 * Subsetsum con un algoritmo LC-search.
 * Pero', la funzione appena definita imposta un discorso che dovrebbe 
 * semplificare il passaggio ad algoritmi Branch&Bound per il Knapsack, di cui 
 * Subsetsum e' un caso particolare.
 * Inoltre, la funzione appena definita offre spunti per derivare altre funzioni
 * costo e sperimentarne l'efficacia.
 * 
 * NOTA 
 * Nel codice che segue soluzione[0..j) gioca il ruolo di x[0..j) 
 * e insieme[0..n) il ruolo di X[0..n).                                 */

import java.util.List;
import supportoBB.*;

public class S3LC extends S3Rand {

  @Override
  /* Restituisce l'indice al live node con costo minimo
   * e stampa l'eNode estratto con costo e costo stimato.             */
  int indiceENode(int[] insieme, int s, List<ArrBoolInt> liveNodes) {
    
    /* Assume che il primo liveNode di liveNodes sia a costo minimo. */
    int indiceLiveNodeCostoMinimo = 0;
    ArrBoolInt liveNode = liveNodes.get(indiceLiveNodeCostoMinimo);
    
    int fH = fCompostoH(insieme, s, liveNode);
    int costoMinimo = -stimaCostoPerEccesso(insieme, fH, s, liveNode); 
 
    /* Cerca il liveNode a costo minimo. */
    for (int indice = 1; indice < liveNodes.size(); indice++) {
      
      liveNode = liveNodes.get(indice);
      
      fH = fCompostoH(insieme, s, liveNode);
      int costoLiveNode = -stimaCostoPerEccesso(insieme, fH, s, liveNode);
      
      if (costoLiveNode < costoMinimo) {
        indiceLiveNodeCostoMinimo = indice;
        costoMinimo = costoLiveNode;        
      }
    }
    
    liveNode = liveNodes.get(indiceLiveNodeCostoMinimo);    
    return indiceLiveNodeCostoMinimo;
  }
}