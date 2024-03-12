package supportoBB;

public class IntFloat {
  private GenPair<Integer, Float> coppia;

  public IntFloat(Integer a, Float b) {
    this.coppia = new GenPair<Integer, Float>(a, b);
  }

  public Integer pi0() {
    return coppia.pi0();
  }

  public Float pi1() {
    return coppia.pi1();
  }

  public String toString() {
    return "(" + pi0() + "," + pi1() + ")";
  }
}
