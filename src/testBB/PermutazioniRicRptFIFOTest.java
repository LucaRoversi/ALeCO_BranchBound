package testBB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import combinatoricaricBB.*;
import supportoBB.*;

class PermutazioniRicRptFIFOTest {

  @Test
  void testPermuta2() {
    PermutazioniRicRptFIFO.conta = 0;
    List<ArrIntInt> liveNodes = new ArrayList<ArrIntInt>();
    int k = 2;
    
    ArrIntInt soluzione = new ArrIntInt(new Integer[k], 0);
    liveNodes.add(soluzione);
    PermutazioniRicRptFIFO.risposte(initCollezione(k), liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta3() {
    PermutazioniRicRptFIFO.conta = 0;
    List<ArrIntInt> liveNodes = new ArrayList<ArrIntInt>();
    int k = 3;

    ArrIntInt soluzione = new ArrIntInt(new Integer[k], 0);
    liveNodes.add(soluzione);
    PermutazioniRicRptFIFO.risposte(initCollezione(k), liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta4() {
    PermutazioniRicRptFIFO.conta = 0;
    List<ArrIntInt> liveNodes = new ArrayList<ArrIntInt>();
    int k = 4;

    ArrIntInt soluzione = new ArrIntInt(new Integer[k], 0);
    liveNodes.add(soluzione);
    PermutazioniRicRptFIFO.risposte(initCollezione(k), liveNodes);
    System.out.println("%---------------------------------");
  }

  private Integer[] initCollezione(int n) {
    Integer[] collezione = new Integer[n];
    for (int i = 0; i < collezione.length; i++)
      collezione[i] = i + 1;
    return collezione;
  }
}