
/*
 * Name: Alex Miller
 * Date: Mar 13 2023
 * Self Grade: 90(One day late)
 * Description: This program is a social media app that allows you to create a list of the people you follow. It allows you to add a follower, unfollow someone, follow someone, search for a follower, update followers, list all followers, display the number of followers, and display the number of the people following you.
 */
import java.util.*;

public class InstaMiller {
}

class User implements Comparable {
  private String first, last, username;
  private boolean followBack;

  // parameterized constructor
  public User(String first, String last, String username, boolean followBack) {
    // initializing instance variables
    this.first = first;
    this.last = last;
    this.username = username;
    this.followBack = followBack;
  }

  // getter for the followBack attribute
  public boolean getFollow() {
    return followBack;
  }

  // changes followBack to false
  public void unfollow() {
    followBack = false;
  }

  // changes followback to true
  public void follow() {
    followBack = true;
  }

  // getter for first name
  public String getFirst() {
    return first;
  }

  // getter for last name
  public String getLast() {
    return last;
  }

  // setter for first name
  public void setFirst(String newFirst) {
    first = newFirst;
  }

  // setter for last name
  public void setLast(String newLast) {
    last = newLast;
  }

  // getter for username
  public String getUsername() {
    return username;
  }

  // overrides the compareTo() method
  public int compareTo(Object o) {
    User other = (User) o;
    return this.username.compareTo(other.username);
  }

  // overrides the equals() method
  public boolean equals(User other) {
    return this.first.equalsIgnoreCase(other.first) && this.last.equalsIgnoreCase(other.last);
  }

  // returns the string representation of the User object
  public String toString() {
    String info = "Username: " + username + "\nName: " + first + "\nLast name: " + last;
    if (followBack) {
      info += " \"You are following this person";
    } else {
      info += " \"You are not following this person";
    }
    return info;
  }
}

class SocialMedia {
  // stores the list of users
  private ArrayList<User> app;

  // default constructor
  public SocialMedia() {
    app = new ArrayList<User>();
  }

  // method to follow back the given user
  public void followBack(String first, String last) {
    String s1 = first.trim() + " " + last.trim();
    for (int i = 0; i < app.size(); i++) {
      String s2 = app.get(i).getFirst() + " " + app.get(i).getLast();
      if (s2.equalsIgnoreCase(s1)) {
        app.get(i).follow();
      }
    }
  }

  // method to add a new user to the arraylist
  public boolean follow(String first, String last, String userfirst, boolean followBack) {
    if (search(first.trim(), last.trim()))
      return false;

    // create a new User object with the inputted details
    User newUser = new User(first, last, userfirst, followBack);
    boolean inserted = false;

    // loop through the ArrayList app
    for (int i = 0; i < app.size(); i++) {
      // if the current object is not less than the given object
      if (app.get(i).compareTo(newUser) >= 0) {
        // insert the new user at the found index
        app.add(i, newUser);
        // set inserted to true and break the loop
        inserted = true;
        break;
      }
    }
    if (!inserted)
      // add the user at the end
      app.add(newUser);
    return true;
  }

  /*
   * This method removes the person from the list meaning that they are not
   * following you
   * and you are not following them
   */
  public boolean remove(String first, String last) {
    String s1 = first.trim() + " " + last.trim();
    // loop through the ArrayList app
    for (int i = 0; i < app.size(); i++) {
      // combine the first and last name of the current user
      String s2 = app.get(i).getFirst() + " " + app.get(i).getLast();
      // if the current user has the given first & last name
      if (s2.equalsIgnoreCase(s1)) {
        // remove it from the list & return true
        app.remove(i);
        return true;
      }
    }
    return false;
  }

  public boolean search(String first, String last) {
    String s1 = first.trim() + " " + last.trim();
    // loop through the ArrayList app
    for (int i = 0; i < app.size(); i++) {
      // combine the first and last name of the current user
      String s2 = app.get(i).getFirst() + " " + app.get(i).getLast();
      // return true if the current user has the given first & last name
      if (s2.equalsIgnoreCase(s1))
        return true;
    }
    return false;
  }

  public ArrayList<User> getList() {
    return app;
  }

  public int followerCounts() {
    return app.size();// must change
  }

  public int followingCounts() {
    int count = 0;
    // loop through the ArrayList app
    for (int i = 0; i < app.size(); i++) {
      // if this person is following the current user
      if (app.get(i).getFollow())
        // increment the count by 1
        count++;
    }
    // return the computed count
    return count;
  }

  public String toString() {
    String info = "";
    // loop through the ArrayList app
    for (int i = 0; i < app.size(); i++) {
      // append the current user to the info string
      info += (app.get(i).toString() + "\n***************************************************************\n");
    }
    // return the info string
    return info;
  }

  /* create your own driver here */
  class MyDriver {
    public static void main(String[] args) {
      SocialMedia myInsta = new SocialMedia();
      Scanner kb = new Scanner(System.in);
      myInsta.follow("Josh", "Thomas", "JThomas", true);
      myInsta.follow("Mila", "Kuang", "SmhMila", true);
      myInsta.follow("John", "Skelly", "Johnskell", false);
      myInsta.follow("Ben", "Steensland", "BSteezy", true);
      myInsta.follow("Trevor", "Hart", "TrevorH", false);
      /* Displaying your followers */
      System.out.println("Your followers informations\n");
      System.out.println(myInsta);
      /* Unfollowing a user */
      System.out.println("Enter the first name of someone you want to unfollow: ");
      String first = kb.next();
      System.out.println("Enter their last name: ");
      String last = kb.next();
      myInsta.remove(first, last);
      /* Displaying the list */
      System.out.println("List of followers after removing " + first + " " + last);
      System.out.println(myInsta);
      /* adding a new follower */
      System.out.println("Enter the first name of someone you want to follow: ");
      first = kb.next();
      System.out.println("Enter their last name: ");
      last = kb.next();
      System.out.println("Adding " + first + " " + last + " to your list of followers");
      System.out.println("What is their username?");
      String username = kb.next();
      myInsta.follow(first, last, username, true);
      /* Dipslying the followers */
      System.out.println("List of your followers:");
      System.out.println(myInsta);
      /* Searching for a follower */
      System.out.println("Who do you want to search for?");
      System.out.println("First name: ");
      first = kb.next();
      System.out.println("Last name: ");
      last = kb.next();
      System.out.println("Searching for " + first + " " + last + " in your followers list");
      if (myInsta.search(first, last) == false) {
        System.out.println("They are not in your list of followers");
        System.out.println("\n***************************");
      } else {
        System.out.println("They are on your list of followers.");
      }
      System.out.println("You are following " + myInsta.followerCounts() + " people");
      System.out.println("You have " + myInsta.followingCounts() + " followers");
      System.out.println(myInsta);
      System.out.println("Enter the name of the person that you want to follow back: ");
      System.out.println("First name: ");
      first = kb.next();
      System.out.println("Last name: ");
      last = kb.next();
      myInsta.followBack(first, last);
      System.out.println("You are now following " + first + " " + last + ".");
      System.out.println(myInsta);
    }
  }
  /*
   * below is a sample driver. Do not remove this driver from your code when
   * submitting it
   */
  // class Driver {
  // public static void main(String[] args) {
  // SocialMedia myInsta = new SocialMedia();
  // /* Adding followers to your list */
  // /* the boolean field indicates whether you want to follow them back */
  // myInsta.follow("Matthew", "Philips", "MatPhil", true);
  // myInsta.follow("Gary", "Kane", "GKane", false);
  // myInsta.follow("Robert", "Kenny", "RKenny", true);
  // myInsta.follow("Bill", "Fitch", "BillF", true);
  // myInsta.follow("Trevor", "Schlulz", "TrevorS", false);
  // /* Displaying your followers */
  // System.out.println("Your followers informations\n");
  // System.out.println(myInsta);
  // /* Unfollowing a user */
  // System.out.println("Removing Robert Kenny from your followers list");
  // myInsta.remove("Robert", "Kenny");
  // /* Displaying the list */
  // System.out.println("List of followers after removing Robert Kenny");
  // System.out.println(myInsta);
  // /* adding a new follower */
  // System.out.println("Adding Elon Musk to your list of followers");
  // myInsta.follow("Elon", "Musk", "ElonM", true);
  // /* Dipslying the followers */
  // System.out.println("List of your followers:");
  // System.out.println(myInsta);
  // /* Searching for a follower */
  // System.out.println("Searching for Stonewall Jackson(StonW) in your followers
  // list");
  // if (myInsta.search("Jackson", "Stonewall") == false) {
  // System.out.println("Stonewall Jackson is not in your list of followers");
  // System.out.println("\n***************************");
  // System.out.println("You are following " + myInsta.followerCounts() +
  // "people");
  // System.out.println("You have " + myInsta.followingCounts() + "followers");
  // System.out.println(myInsta);
  // Scanner kb = new Scanner(System.in);
  // System.out.println("Enter the username of the person that you want to follow
  // back: ");
  // String first = kb.next();
  // String last = kb.next();
  // myInsta.followBack(first, last);
  // System.out.println(myInsta);
  // }
  // }
  // }
}
