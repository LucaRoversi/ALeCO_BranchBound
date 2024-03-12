package knapsackBB;

/** Algoritmo iterativo Branch&Bound per risolvere KP in accordo con
 * una politica FIFO per l'accodamento dei live nodes e di una politica
 * Least Cost per la scelta dell'eNode in uno spazio degli stati che 
 * rappresenta sottoinsiemi. 
 * 
 * Il problema e' una terna:
 * < W[0..n) di pesi
 * , P[0..n) di profitti 
 * , valore c di capacita' massima > .
 * 
 * La funzione costo e' l'approssimazione per eccesso del lavoro da compiere
 * per produrre una eventuale risposta, partendo dal live node considerato.
 * Quindi, essa valuta il lavoro noto, usando soluzione[0..j) cui aggiunge 
 * sia il costo di tutti gli elementi di indice [j..split), sia la porzione
 * del costo p_split, proporzionale al peso w_split che serve per saturare
 * la capacita'.  */

import java.util.List;

import supportoBB.*;

public class KPLCBB extends KPFIFOBB {
  
  @Override
  /* Restituisce l'indice al live node con profitto stimato migliore,
   * cioe' inferiore, se preso con segno '-', a quello di tutti gli altri
   * liveNodes. Per ogni liveNode, la stima del profitto ottimale usa
   * il profitto assicurato dal nodo stesso e raccolto in profittoAssicurato.      */
  int indiceENode(IstanzaKP iKPBB, List<ArrBoolInt> liveNodes) {
    
    /* Assume che il primo liveNode di liveNodes sia a costo minimo. */
    int indiceLiveNodeMigliore = 0;
    ArrBoolInt liveNode = liveNodes.get(indiceLiveNodeMigliore);
    
    IntFloat fH = fH(iKPBB, liveNode);
    Float migliorProfittoMinimo = -stimaCostoPerEccesso(iKPBB, fH, liveNode); 
 
    /* Cerca il liveNode a costo minimo. */
    for (int indice = 1; indice < liveNodes.size(); indice++) {
      
      liveNode = liveNodes.get(indice);
      
      fH = fH(iKPBB, liveNode);
      Float stimaProfittoLiveNode = -stimaCostoPerEccesso(iKPBB, fH, liveNode);
      
      if (stimaProfittoLiveNode < migliorProfittoMinimo) {
        indiceLiveNodeMigliore = indice;
        migliorProfittoMinimo = stimaProfittoLiveNode;        
      }
    }
    
    liveNode = liveNodes.get(indiceLiveNodeMigliore);    
    return indiceLiveNodeMigliore;
  }
}