package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

/* InsuranceSystem.java controls the insurance system program which lets users
create policies for people. These policies are home, car and life which are determined
using the profile's factors including sum insured and age
Author: Lojanan Sivanantharuban */

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  // The arraylists for the profiles
  ArrayList<Integer> positionList = new ArrayList<>();
  ArrayList<String> personList = new ArrayList<>();
  ArrayList<String> ageList = new ArrayList<>();
  ArrayList<Integer> policyList = new ArrayList<>();
  ArrayList<String> homeList = new ArrayList<>();
  ArrayList<String> carList = new ArrayList<>();
  ArrayList<String> lifeList = new ArrayList<>();
  ArrayList<String> rankList = new ArrayList<>();
  ArrayList<Boolean> oneLife = new ArrayList<>();

  // This stores the value of the loaded profile. Default is -1
  int[] selectedProfile = {-1};
  // Checks if verified for policies
  int[] verify = {-1};
  // These store values to be used outside the if else statement for policies
  String[] homeStr = {"", ""};
  int[] homeInt = {0, 0};

  String[] carUseStr = {"", ""};
  int[] carUseInt = {0, 0};

  String[] lifeStr = {""};
  int[] lifeInt = {0, 0};

  // This orders the ranking of the policy
  // x is home, y is car and z is life
  String rankPolicy = "xyz";

  // Print database prints the profiles and their respective database. There are no outputs as it is
  // a void method
  public void printDatabase() {
    // Converts the size of position list to a string. This will allow to determine
    // the amount of profiles in database
    String n = Integer.toString(positionList.size());
    int j = 0;
    if (positionList.size() == 0) {
      // If no value, it will print the DB Policy count message from MessageCli file
      // with 0 profile
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (positionList.size() == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
      if (selectedProfile[0] == 0) {
        if (policyList.get(selectedProfile[0]) == 1) {
          // Loaded profile with only 1 policy
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
              "*** ",
              "1",
              personList.get(j),
              ageList.get(j),
              Integer.toString(policyList.get(j)),
              "y");
        } else {
          // Loaded profile with multiple policies
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
              "*** ",
              "1",
              personList.get(j),
              ageList.get(j),
              Integer.toString(policyList.get(j)),
              "ies");
        }
      } else {
        // Not loaded profile with one policy
        if (policyList.get(j) == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
              "", "1", personList.get(j), ageList.get(j), Integer.toString(policyList.get(j)), "y");
        } else {
          // Not loaded profile with multiple policies
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
              "",
              "1",
              personList.get(j),
              ageList.get(j),
              Integer.toString(policyList.get(j)),
              "ies");
        }
      }
      // Prints out the policies for the profile at index j
      for (int k = 1; k < 4; k++) {
        String homeRank = rankList.get(j).substring(0, 1);
        String carRank = rankList.get(j).substring(1, 2);
        String lifeRank = rankList.get(j).substring(2, 3);
        if (homeRank.equals(Integer.toString(k))) {
          System.out.println(homeList.get(j));
        } else if (carRank.equals(Integer.toString(k))) {
          System.out.println(carList.get(j));
        } else if (lifeRank.equals(Integer.toString(k))) {
          System.out.println(lifeList.get(j));
        }
      }
    } else {
      // If there is more than 1 profile, then the message will be different
      System.out.println("Database has " + n + " profiles:");
      // A for loop will begin to print the profiles in the database with their
      // corresponding rank, name and age
      for (int i = 0; i < positionList.size(); i++) {
        j++;
        if (i == selectedProfile[0]) {
          // Only one policy for selected profile
          if ((policyList.get(i)) == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
                "*** ",
                Integer.toString(j),
                personList.get(i),
                ageList.get(i),
                Integer.toString(policyList.get(i)),
                "y");
          } else {
            // Multiple policies for selected profile
            MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
                "*** ",
                Integer.toString(j),
                personList.get(i),
                ageList.get(i),
                Integer.toString(policyList.get(i)),
                "ies");
          }
        } else {
          // Only one policy for not selected profiles
          if (policyList.get(i) == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
                "",
                Integer.toString(j),
                personList.get(i),
                ageList.get(i),
                Integer.toString(policyList.get(i)),
                "y");
          } else {
            // Multiple policies for not selected profiles
            MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage(
                "",
                Integer.toString(j),
                personList.get(i),
                ageList.get(i),
                Integer.toString(policyList.get(i)),
                "ies");
          }
        }
        // Prints out the policies in order of creation
        for (int k = 1; k < 4; k++) {
          String homeRank = rankList.get(i).substring(0, 1);
          String carRank = rankList.get(i).substring(1, 2);
          String lifeRank = rankList.get(i).substring(2, 3);
          if (homeRank.equals(Integer.toString(k))) {
            System.out.println(homeList.get(i));
          } else if (carRank.equals(Integer.toString(k))) {
            System.out.println(carList.get(i));
          } else if (lifeRank.equals(Integer.toString(k))) {
            System.out.println(lifeList.get(i));
          }
        }
      }
    }
  }
  // Create new profile method allows the user to create a new profile for people by inputting their
  // first name and age
  public void createNewProfile(String userName, String age) {
    // This code will check if no profile is loaded
    if (selectedProfile[0] != -1) {
      // If a profile is loaded already, it will tell the user which profile is loaded
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(personList.get(selectedProfile[0]));
      return;
    }
    // Sets variable pos to 0 to track the rank of profile
    int pos = 0;
    // Creates person object and uses the Person class titleName constructor to set
    // name tot title case. The count will get the length of username
    Person p = new Person(userName, age);
    String titleName = p.name();
    // int count = p.count();
    // This loop checks if the user is in the personList already
    for (int i = 0; i < positionList.size(); i++) {
      if (titleName.equals(personList.get(i))) {
        // If the user is found, it will display that it cannot create the profile and
        // returns without performing anything
        p.duplicateUser();
        return;
      }
    }
    // This used to be two if loops. I coded longUser and invalidAge on the Person class.
    // The longUser checks the length of username and invalidAge ensures that the age isn't below 0
    if ((titleName.equals(p.longUser(titleName))) && (age.equals(p.invalidAge(age)))) {
      // If both the conditions are met, positionList will add one and the user creation will be
      // displayed
      positionList.add(pos++);
      p.createUser();
      // The users name and age will be added to their respective arraysLists
      personList.add(titleName);
      ageList.add(age);
      // Adds policy related lists for the profile
      policyList.add(0);
      rankList.add(rankPolicy);
      homeList.add("");
      carList.add("");
      lifeList.add("");
      oneLife.add(false);
    }
  }
  // Load profile method lets users load a profile to then be used for other methods
  public void loadProfile(String userName) {
    // Gets the persons name in title case
    Person p = new Person(userName, "");
    String titleName = p.name();
    // A loop that checks whether that profile is within the personList
    for (int i = 0; i < positionList.size(); i++) {
      String name = personList.get(i);
      if (titleName.equals(name)) {
        // If the name is a match, it will display the loaded user message
        p.loadUser();
        // This array will change the value from -1 to the selected profile position
        selectedProfile[0] = i;
        return;
      }
    }
    // If no user is found with that name, then it will display user not found and
    // won't change the selectedProfile
    p.notFound();
  }
  // Unload profile lets users unload the loaded profile for use of other methods
  public void unloadProfile() {
    for (int i = 0; i < positionList.size(); i++) {
      if (selectedProfile[0] == i) {
        // This code prints the profile unoaded message and shows the name of the
        // profile
        MessageCli.PROFILE_UNLOADED.printMessage(personList.get(selectedProfile[0]));
        // Resets the selectedProfile to -1 after unloading. Then returns to ensure that
        // the profile was unloaded
        selectedProfile[0] = -1;
        return;
      }
    }
    // If after the loop, the user isn't returned, then that means no profile was
    // loaded and a message will show that
    MessageCli.NO_PROFILE_LOADED.printMessage();
  }
  // Delete profile deletes the profile of the username and it's existing arraylists
  public void deleteProfile(String userName) {
    // Gets the title case version of user input
    Person p = new Person(userName, "");
    String titleName = p.name();
    // Will not delete profile when there is a value other than -1 for
    // selectedProfile array as this means a profile is loaded
    if (selectedProfile[0] != -1) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(titleName);
      return;
    }
    // This loop checks if a profile exists with the user inputted name
    for (int i = 0; i < positionList.size(); i++) {
      String profile = personList.get(i);
      if (titleName.equals(profile)) {
        // Prints the delete person message and removes the profile details from all the
        // lists then returns the user to insurance system
        p.deletePerson();
        positionList.remove(i);
        personList.remove(i);
        ageList.remove(i);
        policyList.remove(i);
        rankList.remove(i);
        homeList.remove(i);
        carList.remove(i);
        lifeList.remove(i);
        oneLife.remove(i);
        return;
      }
    }
    // Will print no profile to delete if a profile isn't found
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(titleName);
  }
  // Create policy lets users create policies for the loaded profile which can then be displayed on
  // the print database
  public void createPolicy(PolicyType type, String[] options) {
    // Will print if no profile loaded (selectedProfile is set to -1)
    if (selectedProfile[0] == -1) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    } else {
      // Gets the age string from ageList and converts to integer
      String ageStr = ageList.get(selectedProfile[0]);
      int ageInt = Integer.valueOf(ageStr);
      if (type == PolicyType.HOME) {
        // Checks if user input was yes and sets the verify array accordingly
        if (options[2].startsWith("y")) {
          verify[0] = 1;
        } else {
          verify[0] = 0;
        }
        // Sets the homeStr array to the user's inputs. Gets the home policy class and basePremium
        homeStr[0] = options[0];
        homeStr[1] = options[1];
        HomePolicy h = new HomePolicy(verify[0], homeStr[0]);
        homeInt[0] = h.basePremium(verify[0], homeStr[0], 0);

        // Increases the amount of policies the user has
        int positioning = policyList.get(selectedProfile[0]) + 1;
        policyList.set(selectedProfile[0], positioning);
        // Gets ranking of the policy for printing
        String current = rankList.get(selectedProfile[0]);
        rankList.set(selectedProfile[0], (current.replace("x", Integer.toString(positioning))));
        // Prints that the person's home policy was created
        MessageCli.NEW_POLICY_CREATED.printMessage("home", personList.get(selectedProfile[0]));
      } else if (type == PolicyType.CAR) {
        // Stores the car variables in the array for use later
        carUseStr[0] = options[0];
        carUseStr[1] = options[1];
        // Checks if the user input is a yes
        if (options[3].startsWith("y")) {
          verify[0] = 1;
        } else {
          verify[0] = 0;
        }
        CarPolicy c = new CarPolicy(ageInt, carUseStr[0], verify[0]);
        // Gets the car basePremium and stores it in car integer array
        carUseInt[0] = c.basePremium(ageInt, carUseStr[0], verify[0]);
        // Increases the amount of policies there is
        int positioning = policyList.get(selectedProfile[0]) + 1;
        policyList.set(selectedProfile[0], positioning);

        String current = rankList.get(selectedProfile[0]);
        // Sets the ranking of the policy
        rankList.set(selectedProfile[0], (current.replace("y", Integer.toString(positioning))));
        // Prints the car policy created
        MessageCli.NEW_POLICY_CREATED.printMessage("car", personList.get(selectedProfile[0]));
      } else if (type == PolicyType.LIFE) {
        // Checks if the persons age is above 100 and prints over age message
        if (ageInt > 100) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(personList.get(selectedProfile[0]));
          return;
        } else if (oneLife.get(selectedProfile[0]) == true) {
          // If a person already has a life policy, it won't allow them to create a new one
          MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(personList.get(selectedProfile[0]));
          return;
        } else {
          // Gets the user sumInsured and basePremium and stores into array
          lifeStr[0] = options[0];
          LifePolicy l = new LifePolicy();
          lifeInt[0] = l.basePremium(ageInt, lifeStr[0], verify[0]);
          // Increases the policies that the person has
          int positioning = policyList.get(selectedProfile[0]) + 1;
          policyList.set(selectedProfile[0], positioning);

          String current = rankList.get(selectedProfile[0]);
          // Sets the ranking of the policy
          rankList.set(selectedProfile[0], (current.replace("z", Integer.toString(positioning))));
          // Once the life policy is created, the person's element is set to true so they cannot
          // create more life policy
          oneLife.get(selectedProfile[0]);
          oneLife.set(selectedProfile[0], true);
          // Prints the life policy created message
          MessageCli.NEW_POLICY_CREATED.printMessage("life", personList.get(selectedProfile[0]));
        }
      }
    }
    // Counts the amount of policies there are
    int amountOfPolicies = 0;
    for (int k = 1; k < 4; k++) {
      String homeRank = rankList.get(selectedProfile[0]).substring(0, 1);
      String carRank = rankList.get(selectedProfile[0]).substring(1, 2);
      String lifeRank = rankList.get(selectedProfile[0]).substring(2, 3);
      if (homeRank.equals(Integer.toString(k))) {
        amountOfPolicies++;
      } else if (carRank.equals(Integer.toString(k))) {
        amountOfPolicies++;
      } else if (lifeRank.equals(Integer.toString(k))) {
        amountOfPolicies++;
      }
    }
    // If the amount of policies is 2, then 10% discount
    if (amountOfPolicies == 2) {
      double disc1 = (90.00 / 100.00);
      carUseInt[1] = (int) (carUseInt[0] * disc1);
      homeInt[1] = (int) ((homeInt[0]) * disc1);
      lifeInt[1] = (int) (lifeInt[0] * disc1);
    } else if (amountOfPolicies > 2) {
      // If amount of policies is more than 2, then 20% discount
      double disc2 = (80.00 / 100.00);
      carUseInt[1] = (int) (carUseInt[0] * disc2);
      homeInt[1] = (int) (homeInt[0] * disc2);
      lifeInt[1] = (int) (lifeInt[0] * disc2);
    } else {
      // If amount of policies is 1, then it equals basePremium
      homeInt[1] = homeInt[0];
      carUseInt[1] = carUseInt[0];
      lifeInt[1] = lifeInt[0];
    }
    // Gets the message as a string and sets it in their respective arraylist
    String homeStore =
        MessageCli.PRINT_DB_HOME_POLICY.getMessage(
            homeStr[1], homeStr[0], Integer.toString(homeInt[0]), Integer.toString(homeInt[1]));
    homeList.set(selectedProfile[0], homeStore);

    String carStore =
        MessageCli.PRINT_DB_CAR_POLICY.getMessage(
            carUseStr[1],
            carUseStr[0],
            Integer.toString(carUseInt[0]),
            Integer.toString(carUseInt[1]));
    carList.set(selectedProfile[0], carStore);

    String lifeStore =
        MessageCli.PRINT_DB_LIFE_POLICY.getMessage(
            options[0], Integer.toString(lifeInt[0]), Integer.toString(lifeInt[1]));
    lifeList.set(selectedProfile[0], lifeStore);
  }
}
