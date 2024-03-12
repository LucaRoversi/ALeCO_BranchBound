package supportoBB;

public class IntInt {
  private GenPair<Integer, Integer> coppia;

  public IntInt(Integer a, Integer b) {
    this.coppia = new GenPair<Integer, Integer>(a, b);
  }

  public Integer pi0() {
    return coppia.pi0();
  }

  public Integer pi1() {
    return coppia.pi1();
  }

  public String toString() {
    return "(" + pi0() + "," + pi1() + ")";
  }
}
