package testBB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import subsetsumBB.*;
import supportoBB.*;

class S3BBTestHorowitz {

  @Test
  void testLCBB() { //12
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;

    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3LCBB s3LCBB = new S3LCBB();
    s3LCBB.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testFIFOBB() { //17
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;
    
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3FIFOBB s3FIFOBB = new S3FIFOBB();
    s3FIFOBB.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testLIFOBB() { //12
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;
    
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3LIFOBB s3LIFOBB = new S3LIFOBB();
    s3LIFOBB.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  private Boolean[] initSoluzione(int n) {
    Boolean[] soluzione = new Boolean[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = true;
    return soluzione;
  }
}