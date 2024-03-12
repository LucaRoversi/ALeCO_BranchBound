package knapsackBB;

/** Algoritmo iterativo Branch&Bound per risolvere KP in accordo con
 * politiche FIFO sia di accodamento dei live nodes e sia di scelta 
 * dell'eNode in uno spazio degli stati che rappresenta sottoinsiemi. 
 * 
 * Il problema e' una terna:
 * < W[0..n) di pesi
 * , P[0..n) di profitti 
 * , valore c di capacita' massima > .
 * */

import java.util.Arrays;
import java.util.List;

import supportoBB.*;

public class KPFIFOBB {
  
  public void risposte( IstanzaKP iKPBB
                      , ArrBoolInt rispostaCorrente
                      , List<ArrBoolInt> liveNodes) {

    /* Stampa la prima soluzione, ottenuta con l'algoritmo Greedy,
     * per stabilire il primo lower bound al profitto ottimo globale
     * che stiamo cercando.                                           */
    System.out.print  ("A [0..r) " 
       + toStringENode(iKPBB, rispostaCorrente));
    System.out.println(toStringValori(" con", iKPBB, rispostaCorrente));    
    
    while (liveNodes.iterator().hasNext()) {

      ArrBoolInt eNode = eNode(iKPBB, rispostaCorrente, liveNodes);

      if (!rifiuta(iKPBB, rispostaCorrente, eNode)) {

        if (accetta(iKPBB, rispostaCorrente, eNode)) {
          rispostaCorrente = eNode;
          System.out.print  ("A eNode " + toStringENode(iKPBB, eNode));
          System.out.println(toStringValori(" con", iKPBB, eNode));
//          System.out.print  (" e [x..r) " + toStringENode(iKPBB, rispostaCorrente));
//          System.out.println(toStringValori(" con", iKPBB, rispostaCorrente));
        }

        if (!completo(iKPBB, rispostaCorrente, eNode)) 
          liveNodes = espande(eNode, liveNodes);
        else {
          // non espande l'attuale E-node
          System.out.print  ("C eNode " + toStringENode(iKPBB, eNode));
          System.out.println(toStringValori(" con", iKPBB, eNode));
//          System.out.print  (" e [x..r) " + toStringENode(iKPBB, rispostaCorrente));
//          System.out.println(toStringValori(" con", iKPBB, rispostaCorrente));          
        }

      } else {
        System.out.print  ("R eNode " + toStringENode(iKPBB, eNode));
        System.out.println(toStringValori(" con", iKPBB, eNode));
//        System.out.print  (" e [x..r) " + toStringENode(iKPBB, rispostaCorrente));
//        System.out.println(toStringValori(" con", iKPBB, rispostaCorrente));
      }
    }
  }

  /* Restituisce un eNode, estratto in accordo con la politica implementata
   * dal metodo indiceENode. Prima dell'estrazione, stampa la lista dei
   * live nodes.                                                           */
  private ArrBoolInt eNode(IstanzaKP iKPBB, ArrBoolInt rispostaCorrente, List<ArrBoolInt> liveNodes) {
    
    /* Stampa live nodes.                                       */
//    System.out.print("Live nodes: " + toStringLiveNodes(iKPBB, liveNodes));
    
    int indiceENode = indiceENode(iKPBB, liveNodes); 
    ArrBoolInt eNode = liveNodes.get(indiceENode);
    liveNodes.remove(indiceENode);    
            
    System.out.print  ("->" + toStringENode(iKPBB, eNode));
    System.out.println(toStringValori(" con", iKPBB, eNode));
//    System.out.print  (" e [x..r) " + toStringENode(iKPBB, rispostaCorrente));
//    System.out.println(toStringValori(" con", iKPBB, rispostaCorrente));

    return eNode;
  }

  /* Restituisce l'indice al live node con profitto stimato migliore,
   * cioe' inferiore, se preso con segno '-', a quello di tutti gli altri
   * liveNodes. Per ogni liveNode, la stima del profitto ottimale usa
   * il profitto assicurato dal nodo stesso e raccolto in profittoAssicurato.      */
  int indiceENode(IstanzaKP iKPBB, List<ArrBoolInt> liveNodes) {  
    int indiceEnode = 0 ;
    return indiceEnode;
  }
  
  /* Rifiuta eNode se non e' una soluzione: il suo peso supera quello
   * massimo ammissibile.                                              */
  boolean rifiuta( IstanzaKP iKPBB
                 , ArrBoolInt rispostaCorrente
                 , ArrBoolInt eNode  ) {
    
    IntFloat    fHENode = fH(iKPBB, eNode); 
    Integer   pesoENode = fHENode.pi0(); // costo noto di eNode

    Integer c = iKPBB.getC(); // capacita' massima

    return pesoENode > c; // eNode non e' soluzione
  }

  /* Accetta se il profitto dell'eNode migliora quello offerto 
   * da rispostaCorrente.                                              */
  boolean accetta( IstanzaKP iKPBB
                 , ArrBoolInt rispostaCorrente
                 , ArrBoolInt eNode) {
    
    IntFloat fHRispostaCorrente = fH(iKPBB, rispostaCorrente);
    Float profittoRispostaCorrente = fHRispostaCorrente.pi1(); 

    IntFloat fHENode = fH(iKPBB, eNode);    
    Float profittoENode = fHENode.pi1(); 
    
    // Usando '<' al posto di '<=' non elenca tutte le risposte
    return profittoRispostaCorrente < profittoENode;
  }

  /* Certamente eNode e' completo se rappresenta un intero ramo dello 
   * spazio degli stati. 
   * Tuttavia, e' completo anche quando la stima per *eccesso* del 
   * profitto offerto da eNode non supera il profitto certo di 
   * rispostaCorrente. In tal caso non vale la pena esplorare il 
   * sottoalbero di cui e' radice alla ricerca di una soluzione 
   * della migliore trovata sinora, ovvero rispostaCorrente.    */
  boolean completo(IstanzaKP iKPBB
      , ArrBoolInt rispostaCorrente
      , ArrBoolInt eNode) {
    
    Boolean[] soluzione = eNode.pi0();
    int               j = eNode.pi1();
    
    boolean completo = soluzione.length == j;
    
    if (!completo) {  
      
      IntFloat fHENode = fH(iKPBB, eNode);
      Float profittoStimatoENode = stimaCostoPerEccesso(iKPBB, fHENode, eNode);

      IntFloat fHRispostaCorrente = fH(iKPBB, rispostaCorrente);
      Float profittoRispostaCorrente = fHRispostaCorrente.pi1();

      /* Completo se rispostaCorrente assicura un profitto non peggiore
       * del miglior profitto ottenibile da eNode che approssima per
       * eccesso z*.                                                    */
      completo = profittoRispostaCorrente >= profittoStimatoENode;
      /*                   fh.z                   zLP o fh.z+g.z        */

    }
    return completo;  
  }

  /* Restituisce peso e profitto assicurati dal liveNode, 
   * cioe' dal sottoinsieme in soluzione[0..j).                */
  IntFloat fH(IstanzaKP iKPBB, ArrBoolInt liveNode) {
    
    Boolean[] soluzione = liveNode.pi0(); 
    Integer  j = liveNode.pi1(); 
    
    Integer[] w = iKPBB.getW();
    Float[]   p = iKPBB.getP();

    int pesoLiveNode = 0;
    for (int i = 0; i < j; i++) {
      pesoLiveNode += (soluzione[i]) ? w[i] : 0;
    }

    float profittoLiveNode = 0.0f;
    for (int i = 0; i < j; i++) {
      profittoLiveNode += (soluzione[i]) ? p[i] : 0.0f;
    }

    return new IntFloat(pesoLiveNode, profittoLiveNode);
  }  
  
  /* Restituisce il valore fH.z(soluzione[0..j)+g.z(soluzione[0..j)), 
   * cioe' somma il profitto certo di soluzione[0..j) 
   * con i profitti di tutti gli elementi non ancora inclusi in soluzione,
   * che, se aggiunti, non sforano il limite c, ovvero i profitti di tutti
   * gli elementi con indice nell'intervallo [j..split).
   * 
   * Ispirato sia a Bound, cioe' Algoritmo 7.11 di Horowitz,
   * sia a Greedy-LKP, descritto da Pisinger. */
  Float stimaCostoPerEccesso( IstanzaKP iKPBB
                            , IntFloat fHLiveNode
                            , ArrBoolInt liveNode) {

    Integer[]  w = iKPBB.getW(); 
    Float[]    p = iKPBB.getP(); 
    Integer    c = iKPBB.getC(); 

    Integer     pesoLiveNode = fHLiveNode.pi0(); 
    Float   profittoLiveNode = fHLiveNode.pi1(); 

    Boolean[] soluzione = liveNode.pi0(); 
    Integer           j = liveNode.pi1(); 
    
    int       pesoStimato = pesoLiveNode;     // peso sicuro
    Float profittoStimato = profittoLiveNode; // profitto sicuro
    int split = j;
    boolean incrementa = true;
    while (split < soluzione.length && incrementa) {
      incrementa = (pesoStimato + w[split] <= c);
      if (incrementa) { // incremento intero
            pesoStimato = pesoStimato + w[split];
        profittoStimato = profittoStimato + p[split];
        split = split + 1;
      } else { 
        profittoStimato = profittoStimato 
            + (float)(c - pesoStimato)*(p[split]/w[split]);
      }
    }

    return profittoStimato;
  }
  
  /* METODO LASCIATO PER COMPLETEZZA E COMODITA', MA INUTILE.
   * 
   * Incrementa quanto piu' possibile il profitto generato da 
   * soluzione[0..j), estendendo soluzione[0..j) con ogni 
   * elemento possibile, sino allo split escluso.
   * 
   * Il motivo dell'inutilita' dovrebbe essere chiaro:
   * siamo sempre e solo interessati al profitto reale di un nodo
   * o al suo al profitto stimato.
   * Nel primo caso non siamo interessati ad arrivare allo split
   * item, ma solo all'ultimo item effettivamente inserito in 
   * soluzione[0..j). Nel secondo, trovato lo split item dobbiamo 
   * "riempire" lo spazio rimasto e calcolare il profitto ottimale, 
   * relativo al nodo che stiamo valutando. 
   * 
   * E' l'Algoritmo 7.11, cioe' Bound di Horowitz, quindi una
   * generalizzazione di Greedy-split in Pisinger.                */
  Float stimaCostoPerDifetto( IstanzaKP iKPBB
                            , IntFloat fHLiveNode
                            , ArrBoolInt liveNode) {

    Integer[]  w = iKPBB.getW(); 
    Float[]    p = iKPBB.getP(); 
    Integer    c = iKPBB.getC(); 

    Integer     pesoLiveNode = fHLiveNode.pi0(); 
    Float   profittoLiveNode = fHLiveNode.pi1(); 

    Boolean[] soluzione = liveNode.pi0(); 
    Integer           j = liveNode.pi1(); 

    int i = j;
    while (i < soluzione.length) {
      if (pesoLiveNode + w[i] <= c) {
            pesoLiveNode = pesoLiveNode + w[i];
        profittoLiveNode = profittoLiveNode + p[i];
      }
      i = i + 1;
    }
     
    return profittoLiveNode;
  }
  
  /* Estende l'elenco dei liveNodes con due figli di eNode. */ 
  private static List<ArrBoolInt> espande( ArrBoolInt eNode
                                         , List<ArrBoolInt> liveNodes) {

    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    for (boolean nuovoElementoSoluzione : new boolean[] { soluzione[j]
                                                        , !soluzione[j] }) {
      Boolean[] nuovaSoluzione = Arrays.copyOf(soluzione, soluzione.length);
      nuovaSoluzione[j] = nuovoElementoSoluzione;
      ArrBoolInt nuovoLiveNode = new ArrBoolInt(nuovaSoluzione, j + 1);
      liveNodes.add(liveNodes.size(), // FIFO
                    nuovoLiveNode);
    }

    return liveNodes;
  }

  private String toStringValori(String intestazione, IstanzaKP iKPBB, ArrBoolInt stato) {
    
    IntFloat    fHStato = fH(iKPBB, stato);
    Integer fHdotWStato = fHStato.pi0();
    Float   fHdotZStato = fHStato.pi1();
    
    Float costoPerDifettoStato = stimaCostoPerDifetto(iKPBB, fHStato, stato);
    Float costoPerEccessoStato = stimaCostoPerEccesso(iKPBB, fHStato, stato);
       
    return intestazione
        + " fh.w=" + fHdotWStato
        + " fh.z=" + fHdotZStato
//        + " zG="   + costoPerDifettoStato
        + " zLP="  + costoPerEccessoStato;
  }
   
  private String toStringLiveNodes(IstanzaKP iKPBB, List<ArrBoolInt> liveNodes) {
    String stringa = "{";
    
    for (int i = 0; i < liveNodes.size(); i++) {
      ArrBoolInt liveNode = liveNodes.get(i);
      stringa += toStringENode(iKPBB, liveNode);
    }
    
    return stringa + "}";
  }
  
  private String toStringENode(IstanzaKP iKPBB, ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int               j = eNode.pi1();
    
    Integer[] w = iKPBB.getW();
    Float[]   p = iKPBB.getP();

    String stringa = "[";
    for (int i = 0; i < j; i++)
      stringa += (soluzione[i]) 
      ? " " + p[i] + "," 
      : "!" + p[i] + ",";

    return ((",".equalsIgnoreCase(stringa.substring(stringa.length()-1,stringa.length()))) 
        ? stringa.substring(0,stringa.length()-1) : stringa) + "]";
  }
  
  /* La prima soluzione per KP ha tutti i valori inizializzati a true. */
  public Boolean[] initSoluzione(int n) {
    Boolean[] soluzione = new Boolean[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = true;
    return soluzione;
  }
 
  /* Algoritmo Greedy di Pisinger. 
   * Produce la prima risposta per una data istanza.
   * Ovvero, realizza il passo base dell'invariante alla
   * base del Branch&Bound.                                    */
  public ArrBoolInt initRisposta(IstanzaKP iKPBB) {

    Integer[]  w = iKPBB.getW(); 
    Float[]    p = iKPBB.getP(); 
    Integer    c = iKPBB.getC(); 

    Boolean[] risposta = new Boolean[w.length]; 

    Integer     pesoLiveNode = 0; 
    Float   profittoLiveNode = 0.0f; 

    int i = 0;
    boolean incrementa = true;
    while (i < w.length) { // greedy
//    while (i < w.length && incrementa) { // greedy split
      incrementa = (pesoLiveNode + w[i] <= c);
      if (incrementa) {
        risposta[i] = true;
        pesoLiveNode = pesoLiveNode + w[i];
        profittoLiveNode = profittoLiveNode + p[i];
      } else {
        risposta[i] = false;
      }

      i = i + 1;
    }

    return new ArrBoolInt(risposta,i);
  }
}