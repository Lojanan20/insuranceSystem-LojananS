package nz.ac.auckland.se281;

public class Person {

  private String userName;
  private String userAge;

  public Person(String userName, String userAge) {
    this.userName = userName;
    this.userAge = userAge;
  }

  public String name() {
    // COnverts the string characters to lower case
    String lowname = userName.toLowerCase();
    // Only first letter will be capital
    String firstCap = lowname.substring(0, 1).toUpperCase();
    // Returns the title cased name
    return userName = firstCap + lowname.substring(1);
  }

  public void createUser() {
    MessageCli.PROFILE_CREATED.printMessage(userName, userAge);
  }

  public String invalidAge(String userAge) {
    if ((Integer.parseInt(userAge)) < 0) {
      // If age is below 0, then returns blank
      MessageCli.INVALID_AGE.printMessage(userAge, userName);
      return "";
    } else {
      this.userAge = userAge;
      //   If the age is valid, then it will return that age
      return this.userAge;
    }
  }

  public String longUser(String userName) {
    if (userName.length() < 3) {
      // Prints username too short and returns blank string if name shorter than 3 characters
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      return "";
    } else {
      // Returns the user if it isn't short
      this.userName = userName;
      return this.userName;
    }
  }

  public void duplicateUser() {
    MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
  }

  public void loadUser() {
    MessageCli.PROFILE_LOADED.printMessage(userName);
  }

  public void notFound() {
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
  }

  public void deletePerson() {
    MessageCli.PROFILE_DELETED.printMessage(userName);
  }
}
