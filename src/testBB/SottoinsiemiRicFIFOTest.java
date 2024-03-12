package testBB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import combinatoricaiterBB.SottoinsiemiIterFIFO;

import java.util.ArrayList;
import java.util.List;
import combinatoricaricBB.*;
import supportoBB.*;

class SottoinsiemiRicFIFOTest {

  @Test
  void testPermuta2() {
    SottoinsiemiRicFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 2;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta3() {
    SottoinsiemiRicFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 3;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta4() {
    SottoinsiemiRicFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 4;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  private Boolean[] init(int n) {
    Boolean[] soluzione = new Boolean[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = false;
    return soluzione;
  }
}