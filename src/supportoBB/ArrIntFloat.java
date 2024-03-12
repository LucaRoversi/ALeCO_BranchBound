package supportoBB;

public class ArrIntFloat {

  private GenPair<Integer[], Float> coppia;

  public ArrIntFloat(Integer[] a, Float b) {
    this.coppia = new GenPair<Integer[], Float>(a, b);
  }

  public Integer[] pi0() {
    return this.coppia.pi0();
  }

  public Float pi1() {
    return this.coppia.pi1();
  }

  public String toString() {
    return "(" + pi0() + "," + pi1() + ")";
  }
}
