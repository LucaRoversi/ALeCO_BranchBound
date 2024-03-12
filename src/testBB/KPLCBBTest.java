package testBB;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import knapsackBB.*;
import supportoBB.*;

class KPLCBBTest {

  @Test
  void testHorowitzEx8punto2() { // Identico a Ex8punto3
    KPLCBB iKPLCBB = new KPLCBB();

    Integer[]     pesi = new Integer[] {   2,   4,   6,   9 };
    Float[]   profitti = new Float[]   { 10f, 10f, 12f, 18f };
    Integer   capacita = 15;
    IstanzaKP  iKPBB = new IstanzaKP(pesi, profitti, capacita);
    
    ArrBoolInt rispostaIniziale = iKPLCBB.initRisposta(iKPBB);

    Boolean[] soluzione = iKPLCBB.initSoluzione(pesi.length);
    ArrBoolInt   radice = new ArrBoolInt(soluzione, 0);
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    liveNodes.add(radice);
    
    iKPLCBB.risposte(iKPBB, rispostaIniziale, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testValutazioniMini() { 
    KPLCBB iKPLCBB = new KPLCBB();
    Integer[] pesi = new Integer[] { 2, 5, 4, 2 };
    Float[] profitti = new Float[] { 2.0f, 4.6f, 3.9f, 2.0f };
    Integer capacita = 9;
    IstanzaKP iKPBB = new IstanzaKP(pesi, profitti, capacita);

    ArrBoolInt rispostaIniziale = iKPLCBB.initRisposta(iKPBB);

    Boolean[] soluzione = iKPLCBB.initSoluzione(pesi.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    liveNodes.add(radice);

    iKPLCBB.risposte(iKPBB, rispostaIniziale, liveNodes);
    System.out.println("%---------------------------------");
  }   

  @Test
  void testPisingerExPag16() { 
    KPLCBB iKPLCBB = new KPLCBB();
    Integer[]     pesi = new Integer[] {  2,  3,  6,  7,  5,  9,  4 };
    Float[]   profitti = new Float[]   { 6f, 5f, 8f, 9f, 6f, 7f, 3f };
    Integer   capacita = 9;
    IstanzaKP  iKPBB = new IstanzaKP(pesi, profitti, capacita);
    
    ArrBoolInt rispostaIniziale = iKPLCBB.initRisposta(iKPBB);

    Boolean[] soluzione = iKPLCBB.initSoluzione(pesi.length);
    ArrBoolInt   radice = new ArrBoolInt(soluzione, 0);
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    liveNodes.add(radice);
    
  iKPLCBB.risposte(iKPBB, rispostaIniziale, liveNodes);
    System.out.println("%---------------------------------");
  }
  
  @Test
  /* E' istruttivo fornire un elenco di coppie non ordinato */
  void testValutazioni() { 
    KPLCBB iKPLCBB = new KPLCBB();
    Integer[] pesi = new Integer[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        10,
        10,
        4,
        2, 2, 2,
        3, 3, 3, 3, 3,
        3, 3, 2, 2, };
    Float[] profitti = new Float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f,
        2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f,
        1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
        8.0f,
        6.0f,
        2.0f,
        1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
        0.0f, 0.0f, 0.0f, 0.0f, };
    Integer capacita = 100;
    IstanzaKP iKPBB = new IstanzaKP(pesi, profitti, capacita);

    ArrBoolInt rispostaIniziale = iKPLCBB.initRisposta(iKPBB);

    Boolean[] soluzione = iKPLCBB.initSoluzione(pesi.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    liveNodes.add(radice);

    iKPLCBB.risposte(iKPBB, rispostaIniziale, liveNodes);
    System.out.println("%---------------------------------");
  }
}