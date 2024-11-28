package nz.ac.auckland.se281;
// LifePolicy.java lets users create basePremium for the life policy
public class LifePolicy extends Policy {
  // Calculates basePremium for life policy
  public int basePremium(int userAge, String sumInsured, int checker) {
    double sumInsInt = Double.valueOf(sumInsured);
    double calculate = (1.00 + (userAge / 100.00)) / 100;
    // Gets the calculated value of (1.xx% * sumInsured)
    int value = (int) Math.round(calculate * sumInsInt);
    return value;
  }
}
