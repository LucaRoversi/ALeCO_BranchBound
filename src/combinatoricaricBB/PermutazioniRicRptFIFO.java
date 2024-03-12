package combinatoricaricBB;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;

import supportoBB.*;

public class PermutazioniRicRptFIFO {
  
  public static int conta = 0;

  public static void risposte(Integer[] a, List<ArrIntInt> spazioStati) {

    ArrIntInt eNode = eNode(spazioStati);

    if (!completo(a, eNode)) {
      if (!rifiuta(eNode)) {
        spazioStati = espande(a, eNode, spazioStati);
        while (spazioStati.iterator().hasNext())
          risposte(a, spazioStati);
      } else {
        System.out.println("Rifiuta " + toString(eNode) + " " + ++conta);
      }
    } else {
      if (accetta()) {
        System.out.println("Accetta " + toString(eNode) + " " + ++conta);
      } else {
        System.out.println("Non accetta " + toString(eNode) + " " + ++conta);
      }
    }
  }

  private static ArrIntInt eNode(List<ArrIntInt> soluzioni) {
    ArrIntInt eNode = soluzioni.get(0);
    soluzioni.remove(0);
    return eNode;
  }

  public static boolean completo(Integer[] a, ArrIntInt eNode) {
    return a.length == eNode.pi1();
  }

  public static boolean rifiuta(ArrIntInt eNode) {
    return false;
  }

  private static List<ArrIntInt> espande(Integer[] a, ArrIntInt eNode, List<ArrIntInt> spazioStati) {

    Integer[] soluzione = eNode.pi0();
    int j = eNode.pi1();

    int i = 0;
    while (i < a.length) {
      
      soluzione[j] = a[i];
      swap(a, i, j);
      Integer[] soluzioneFiglio = Arrays.copyOf(soluzione, soluzione.length);
      spazioStati.add(spazioStati.size(), // FIFO
          new ArrIntInt(soluzioneFiglio, j + 1));
      swap(a, i, j);

      i = i + 1;
    }
    return spazioStati;
  }

  public static boolean accetta() {
    return true;
  }

  private static void swap(Integer[] s, int i, int j) {
    int tmp = s[i];
    s[i] = s[j];
    s[j] = tmp;
  }

  public static String toString(ArrIntInt risposta) {
    return Arrays.deepToString(risposta.pi0());
  }
}