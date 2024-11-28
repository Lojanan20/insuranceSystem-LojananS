package nz.ac.auckland.se281;
// HomePolicy.java lets users calculate the base premium for their home's policy
public class HomePolicy extends Policy {
  private int userInput;
  private String sumInsured;

  public HomePolicy(int userInput, String sumInsured) {
    this.userInput = userInput;
    this.sumInsured = sumInsured;
  }
  // Calculates the basePremium for home policy
  public int basePremium(int userInput, String sumInsured, int checker) {
    int sumInsInt = Integer.valueOf(sumInsured);
    if (userInput == 1) {
      // If the user typed yes
      Double value = 0.02 * sumInsInt;
      return value.intValue();
    } else {
      // If the user typed no
      Double value = 0.01 * sumInsInt;
      return value.intValue();
    }
  }
}
