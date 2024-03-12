/** Quadrupla, essenzialmente per permutazioni con ripetizione FIFO. */
package supportoBB;

public class ArrIntInt {

  private GenPair<Integer[], Integer> coppia;

  public ArrIntInt(Integer[] a, Integer b) {
    this.coppia = new GenPair<Integer[], Integer>(a, b);
  }

  public Integer[] pi0() {
    return this.coppia.pi0();
  }

  public Integer pi1() {
    return this.coppia.pi1();
  }

  public String toString() {
    return "(" + pi0() + "," + pi1() + ")";
  }
}
