package subsetsumBB;

/** Offre il nucleo di un algoritmo iterativo che genera un spazio 
 * degli stati a sottoinsiemi, risolvere Subsetsum in accordo con
 * diverse politiche di gestione dei live nodes e di scelta dell'eNode.
 * 
 * Il problema e' una coppia:
 * < X[0..n) di elementi di cui formare sottoinsiemi
 * , valore S da eguagliare, sommando elementi di un sottoinsieme 
 *   X[0..j) di X[0..n), se X[0..j) esiste> .
 *
 * In particolare, definisce le funzioni su cui basare una LC-search, 
 * per Subsetsum, la cui misura, relativa ad un nodo x[0..j), fornisce
 * la migliore approssimazione per difetto e per eccesso del valore S.  */

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import supportoBB.*;

public class S3Rand {
  
  public void risposte(int[] insieme, int s, List<ArrBoolInt> liveNodes) {

    while (liveNodes.iterator().hasNext()) {
      
      ArrBoolInt eNode = eNode(insieme, s, liveNodes);
      
      if (!completo(insieme, s, eNode)) {
        if (!rifiuta(insieme, s, eNode, liveNodes)) {
          
          liveNodes = espande(eNode, liveNodes);
          
        } else { // mai percorso
          System.out.println("R: " + toStringENode(insieme, eNode));
        }
      } else {
        if (accetta(insieme, s, eNode)) {
          System.out.println("A: " + toStringENode(insieme, eNode));
        } else {
          System.out.println("N: " + toStringENode(insieme, eNode));
        }
      }
    }
  }

  /* Restituisce un eNode, estratto in accordo con la politica implementata
   * dal metodo indiceENode. Prima dell'estrazione, stampa la lista dei
   * live nodes.                                                           */
  private ArrBoolInt eNode(int[] insieme, int s, List<ArrBoolInt> liveNodes) {
    
    /* Stampa dei live nodes.                                       */
    System.out.print("Live nodes: " + toStringLiveNodes(insieme, liveNodes));
    
    int indiceENode = indiceENode(insieme, s, liveNodes); 
    ArrBoolInt eNode = liveNodes.get(indiceENode);
    liveNodes.remove(indiceENode);    
        
    int fH = fCompostoH(insieme, s, eNode);
    System.out.println(" ---> " + toStringENode(insieme, eNode) 
        + " Costo : " + fH
        + ", Difetto: " + stimaCostoPerDifetto(insieme, fH, s, eNode) 
        + ", Eccesso: " + stimaCostoPerEccesso(insieme, fH, s, eNode));      

    return eNode;
  }

  /* L'eNode e' estratto a caso dai live nodes.  */
  int indiceENode(int[] insieme, int s, List<ArrBoolInt> liveNodes) {
    int indiceLiveNode = new Random().nextInt(liveNodes.size());
    return indiceLiveNode;
  }

  boolean completo(int[] insieme, int s, ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();
    return soluzione.length == j;
  }

  boolean rifiuta(int[] insieme, int somma, ArrBoolInt eNode, List<ArrBoolInt> liveNodes) {
    return false;
  }

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

  boolean accetta(int[] insieme, int somma, ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    int sommaENode = 0;
    for (int i = 0; i < j; i++) {
      sommaENode += (soluzione[i])? insieme[i] : 0;
    }
    return sommaENode == somma;
  }

  String toStringENode(int[] insieme, ArrBoolInt eNode) {
    Boolean[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    String stringa = "[";
    for (int i = 0; i < j; i++)
      stringa += (soluzione[i]) 
                  ? " " + insieme[i] + "," 
                  : "!" + insieme[i] + ",";

    return ((",".equalsIgnoreCase(stringa.substring(stringa.length()-1,stringa.length()))) 
        ? stringa.substring(0,stringa.length()-1) : stringa) + "]";
  }

  /* Restituisce il costo noto di soluzione[0..j) in liveNode, ovvero
   * l'analogo di FH(soluzione[0..j)).                                       */
  int fCompostoH(int[] insieme, int s, ArrBoolInt liveNode) {
    
    Boolean[] soluzione = liveNode.pi0(); 
    Integer  j = liveNode.pi1(); 
    
    int fCompostoH = 0;
    for (int i = 0; i < j; i++) {
      fCompostoH += (soluzione[i]) ? insieme[i] : 0;
    }

    return fCompostoH;
  }  
 
  /* Restituisce il valore G(soluzione[0..j))-insieme[split], 
   * cioe' somma a soluzione[0..j) tutti gli elementi in 
   * insieme[j..split)
   * dove split e' il minimo indice per cui 
   * aggiungendo insieme[j..split) non si supera S, mentre
   * aggiungendo insieme[j..split] si ottiene almeno S.
   *                  
   * E' ispirato all'Algoritmo 8.1 di Horowitz di nome UBound. */
  int stimaCostoPerDifetto(int[] insieme, int fH, int s, ArrBoolInt liveNode) {

    Integer  j = liveNode.pi1(); 

    int costoLiveNode = fH;
    int i = j;
    boolean incrementa = true;
    while (i < insieme.length && incrementa) {
      incrementa = (costoLiveNode + insieme[i] < s);
      if (incrementa) {
        costoLiveNode = costoLiveNode + insieme[i];
        i = i + 1;
      }
    }
     
    return costoLiveNode;
  }
    
  /* Restituisce il valore G(soluzione[0..j)), cioe' somma
   * a soluzione[0..j) tutti gli elementi in insieme[j..split]
   * dove split e' il minimo indice per cui 
   * aggiungendo insieme[j..split) non si supera S, mentre
   * aggiungendo insieme[j..split] ottiene almeno S.
   *                  
   * E' ispirato all'Algoritmo 7.11 di Horowitz di nome Bound.
   * La differenza e' che non si fa alcun rilassamento lineare
   * perche' conosciamo il limite S da ottenere.                */
  int stimaCostoPerEccesso(int[] insieme, int fH, int s, ArrBoolInt liveNode) {

    Integer  j = liveNode.pi1(); 

    int costoLiveNode = fH;
    int i = j;
    boolean incrementa = true;
    while (i < insieme.length && incrementa) {
      incrementa = costoLiveNode < s;
      if (incrementa) {
        costoLiveNode = costoLiveNode + insieme[i];
        i = i + 1;
      }
    }
    return costoLiveNode;
  }
  
  String toStringLiveNodes(int[] insieme, List<ArrBoolInt> liveNodes) {
    String stringa = "{";
    
    for (int i = 0; i < liveNodes.size(); i++) {
      ArrBoolInt liveNode = liveNodes.get(i);
      stringa += toStringENode(insieme, liveNode);
    }
    
    return stringa + "}";
  }
}