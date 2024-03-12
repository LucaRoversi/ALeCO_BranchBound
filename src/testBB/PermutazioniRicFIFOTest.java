package testBB;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import combinatoricaricBB.*;
import supportoBB.*;

class PermutazioniRicFIFOTest {

  @Test
  void testPermuta2() {
    PermutazioniRicFIFO.conta = 0;
    int k = 2; // n.ro elementi da permutare
    List<ArrIntInt> liveNodes = 
        new ArrayList<ArrIntInt>();

    Integer[] soluzione = initSoluzione(k);
    int j = 0;
    liveNodes.add(new ArrIntInt(soluzione, j));
    PermutazioniRicFIFO.risposte(liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta3() {
    PermutazioniRicFIFO.conta = 0;
    int k = 3; // n.ro elementi da permutare
    List<ArrIntInt> liveNodes = 
        new ArrayList<ArrIntInt>();

    Integer[] soluzione = initSoluzione(k);
    int j = 0;
    liveNodes.add(new ArrIntInt(soluzione, j));
    PermutazioniRicFIFO.risposte(liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta4() {
    PermutazioniRicFIFO.conta = 0;
    int k = 4; // n.ro elementi da permutare
    List<ArrIntInt> liveNodes = 
        new ArrayList<ArrIntInt>();

    Integer[] soluzione = initSoluzione(k);
    int j = 0;
    liveNodes.add(new ArrIntInt(soluzione, j));
    PermutazioniRicFIFO.risposte(liveNodes);
    System.out.println("%---------------------------------");
  }

  private Integer[] initSoluzione(int n) {
    Integer[] soluzione = new Integer[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = i + 1;
    return soluzione;
  }
}