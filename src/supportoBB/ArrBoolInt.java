/** Quadrupla, essenzialmente per permutazioni con ripetizione FIFO. */
package supportoBB;

public class ArrBoolInt {
  private GenPair<Boolean[], Integer> coppia;

  public ArrBoolInt(Boolean[] a, Integer b) {
    this.coppia = new GenPair<Boolean[], Integer>(a, b);
  }

  public Boolean[] pi0() {
    return this.coppia.pi0();
  }

  public Integer pi1() {
    return this.coppia.pi1();
  }

  public String toString() {
    return "(" + pi0() + "," + pi1() + ")";
  }

}
