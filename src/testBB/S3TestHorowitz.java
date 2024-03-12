package testBB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import subsetsumBB.*;
import supportoBB.*;

class S3TestHorowitz {

  @Test
  void testLC() { // 24
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;
    
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3LC s3LC = new S3LC();
    s3LC.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testFIFO() { //29
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;
    
    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3FIFO s3FIFO = new S3FIFO();
    s3FIFO.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testLIFO() { //24 
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;

    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3LIFO s3LIFO = new S3LIFO();
    s3LIFO.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  @Test
  void testRand() {
    int[] insieme = new int[] { 24, 11, 13, 7 };
    int somma = 31;

    List<ArrBoolInt> liveNodes = new ArrayList<ArrBoolInt>();
    Boolean[] soluzione = initSoluzione(insieme.length);
    ArrBoolInt radice = new ArrBoolInt(soluzione, 0);
    liveNodes.add(radice);
    
    S3Rand s3Rand = new S3Rand();
    s3Rand.risposte(insieme, somma, liveNodes);
    System.out.println("%---------------------------------");
  }

  private Boolean[] initSoluzione(int n) {
    Boolean[] soluzione = new Boolean[n];
    for (int i = 0; i < soluzione.length; i++)
      soluzione[i] = true;
    return soluzione;
  }
}