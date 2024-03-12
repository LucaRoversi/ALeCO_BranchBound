package testBB;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import combinatoricaiterBB.*;
import supportoBB.*;

class SottoinsiemiIterFIFOTest {

  @Test
  void testSott2() {
    SottoinsiemiIterFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 2;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiIterFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testSott3() {
    SottoinsiemiIterFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 3;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiIterFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  @Test
  void testSott4() {
    SottoinsiemiIterFIFO.conta = 0;
    List<ArrBoolInt> spazioStati = new ArrayList<ArrBoolInt>();
    int k = 4;
    
    ArrBoolInt soluzione = new ArrBoolInt(init(k), 0); 
    spazioStati.add(soluzione);
    SottoinsiemiIterFIFO.risposte(spazioStati);
    System.out.println("%---------------------------------");
  }

  private Boolean[] init(int n) {
    Boolean[] soluzione = new Boolean[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = false;
    return soluzione;
  }
}