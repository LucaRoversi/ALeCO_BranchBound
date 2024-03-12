package subsetsumBB;

/** Risolve il problema Subsetsum aggiungendo una fase di bound
 * all'algorimto Least-Cost search per Subsetsum in S3LC.
 * 
 * Il problema e' una coppia:
 * < X[0..n) di elementi di cui formare sottoinsiemi
 * , valore S da eguagliare, sommando elementi di un sottoinsieme 
 *   X[0..j) di X[0..n), se X[0..j) esiste> .
 * 
 * In S3LC abbiamo sintetizzato la relazione:
 * 
 *   FH(x[0..j))+G(x[0..j))-X[split]
 *           <= S <= FH(x[0..j))+G(x[0..j)) 
 * 
 * che determina un intervallo entro il quale S, valore cercato,
 * debba trovarsi, in relazione alle seguenti quantita':
 * -) il costo noto di x[0..j), fornito da FH(x[0..j)), 
 * -) il costo necessario per la minima approssimazione per
 * eccesso di S, fornito da G(x[0..j)),
 * -) il costo necessario per la massima approssimazione per
 * difetto di S, fornito da G(x[0..j))-X[split]
 *
 * Se S non puo' stare nell'intervallo che un nodo x[0..j),
 * allora non c'e' speranza di usare x[0..j) per ottenere S.
 * */

import java.util.List;
import supportoBB.*;

public class S3LCBB extends S3LC {
   
  @Override
  boolean rifiuta(int[] insieme, int s, ArrBoolInt eNode, List<ArrBoolInt> liveNodes) {
    
    int fHDiENode  = fCompostoH(insieme, s, eNode);
    int costoPerDifetto = stimaCostoPerDifetto(insieme, fHDiENode, s, eNode);
    int costoPerEccesso = stimaCostoPerEccesso(insieme, fHDiENode, s, eNode);
    
    return !( costoPerDifetto <= s && s <= costoPerEccesso);
  }
}