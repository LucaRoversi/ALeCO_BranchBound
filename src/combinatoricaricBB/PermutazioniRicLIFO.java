package combinatoricaricBB;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;

import supportoBB.*;

public class PermutazioniRicLIFO {
  
  public static int conta = 0;

  public static void risposte(List<ArrIntInt> spazioStati) {

    ArrIntInt eNode = eNode(spazioStati);

    if (!completo(eNode)) {
      if (!rifiuta(eNode)) {
        spazioStati = espande(eNode, spazioStati);
        while (spazioStati.iterator().hasNext())
          risposte(spazioStati);
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

  public static boolean completo(ArrIntInt eNode) {
    return eNode.pi0().length == eNode.pi1();
  }

  public static boolean rifiuta(ArrIntInt eNode) {
    return false;
  }

  private static List<ArrIntInt> espande(ArrIntInt eNode,
      List<ArrIntInt> spazioStati) {

    Integer[] a = eNode.pi0();
    int j = eNode.pi1();

    int i = j;
    while (i < a.length) {

      swap(a, i, j);
      Integer[] aFiglio = Arrays.copyOf(a, a.length);
      spazioStati.add(0, // LIFO
          new ArrIntInt(aFiglio, j + 1));
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