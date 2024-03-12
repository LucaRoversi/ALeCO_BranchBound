package subsetsumBB;

/** Risolve il problema Subsetsum aggiungendo una aggiungendo una fase 
 * di bound all'algorimto FIFO per Subsetsum i S3FIFO.
 * 
 * Il problema e' una coppia:
 * < X[0..n) di elementi di cui formare sottoinsiemi
 * , valore S da eguagliare, sommando elementi di un sottoinsieme 
 *   X[0..j) di X[0..n), se X[0..j) esiste> .                      */

import java.util.List;

import supportoBB.*;

public class S3FIFOBB extends S3FIFO {
   
  @Override
  boolean rifiuta(int[] insieme, int s, ArrBoolInt eNode, List<ArrBoolInt> liveNodes) {
    
    int fHDiENode  = fCompostoH(insieme, s, eNode);
    int costoPerDifetto = stimaCostoPerDifetto(insieme, fHDiENode, s, eNode);
    int costoPerEccesso = stimaCostoPerEccesso(insieme, fHDiENode, s, eNode);
    
    return !( costoPerDifetto <= s && s <= costoPerEccesso);
  }
}