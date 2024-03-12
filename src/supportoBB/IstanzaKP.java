package supportoBB;

public class IstanzaKP {

  private GenPair<GenPair<Integer[], Float[]>, Integer> terna;

  public IstanzaKP(Integer[] w, Float[] p, Integer c) {
    this.terna = new GenPair< GenPair<Integer[], Float[]>
                            , Integer>( new GenPair<Integer[], Float[]>(w, p)
                            , c);
  }

  public Integer[] getW() {
    return this.terna.pi0().pi0();
  }

  public Float[] getP() {
    return this.terna.pi0().pi1();
  }

  public Integer getC() {
    return this.terna.pi1();
  }

  public String toString() {
    return "(" + getW() + "," + getP() + "," + getC() + ")";
  }
}
