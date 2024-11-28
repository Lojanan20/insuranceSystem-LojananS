package nz.ac.auckland.se281;
// CarPolicy.java lets users create basePremium for the car's policy
public class CarPolicy extends Policy {
  private int userAge;
  private String sumInsured;
  private int checker;

  public CarPolicy(int userAge, String sumInsured, int checker) {
    this.userAge = userAge;
    this.sumInsured = sumInsured;
    this.checker = checker;
  }
  // Calculates basePremium for car policy
  public int basePremium(int userAge, String sumInsured, int checker) {
    int sumInsInt = Integer.valueOf(sumInsured);
    if (userAge < 25) {
      // If user age under 24, then the basePremium will get 15% of the sumInsured
      Double value = 0.15 * sumInsInt;
      if (checker == 1) {
        // Adds 80 if mechanical breakdown
        return value.intValue() + 80;
      } else {
        return value.intValue();
      }
    } else {
      // If user age over 24, basePremium will get 10% of sumInsured
      Double value = 0.1 * sumInsInt;
      if (checker == 1) {
        // Adds 80 if mechanical breakdown
        return value.intValue() + 80;
      } else {
        return value.intValue();
      }
    }
  }
}
