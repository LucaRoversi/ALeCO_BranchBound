package testBB;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import combinatoricaricBB.*;
import supportoBB.*;

class SottoinsiemiRicLIFOTest {

  @Test
  void testPermuta2() {
    SottoinsiemiRicLIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 2;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicLIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta3() {
    SottoinsiemiRicLIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 3;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicLIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testPermuta4() {
    SottoinsiemiRicLIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 4;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiRicLIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  private Boolean[] init(int n) {
    Boolean[] a = new Boolean[n];
    for (int i = 0; i < a.length; i++)
      a[i] = false;
    return a;
  }
}