package nz.ac.auckland.se281;
// Policy.java is an abstract class containing the basePremium so that it can be changed in all the
// subclasses; HomePolicy.java, CarPolicy.java & LifePolicy.java
public abstract class Policy {

  public abstract int basePremium(int userInput, String userValue, int checker);
}
