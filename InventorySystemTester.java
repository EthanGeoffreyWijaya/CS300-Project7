//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: a descriptive title
// Course: CS 300 Spring 2021
//
// Author: Ethan Geoffrey Wijaya
// Email: egwijaya@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
/**
 * Includes tester methods for the behaviors of the InventoryList class
 * 
 * @author Ethan
 *
 */
public class InventorySystemTester {

  /**
   * Checks for the correctness of the InventoryList.clear() method
   * 
   * @return true if all tests pass
   */
  public static boolean testClear() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();
    list.addBlue(new Box(Color.BLUE));
    list.addYellow(new Box(Color.YELLOW));
    list.addBlue(new Box(Color.BLUE));
    list.clear();

    // 1) Size test
    if (list.size() != 0) {
      System.out.println("Error 1| Size should be 0 after clear(). size: " + list.size());
      return false;
    }

    // 2) Color count tests
    if (list.getYellowCount() != 0) {
      System.out.println("Error 2.1| yellowCount should be 0 after clear(). yellowCount: "
          + list.getYellowCount());
      return false;
    }
    if (list.getBlueCount() != 0) {
      System.out.println(
          "Error 2.2| blueCount should be 0 after clear(). blueCount: " + list.getBlueCount());
      return false;
    }
    if (list.getBrownCount() != 0) {
      System.out.println(
          "Error 2.3| brownCount should be 0 after clear(). brownCount: " + list.getBrownCount());
      return false;
    }

    // 3) isEmpty() test
    if (!list.isEmpty()) {
      System.out.println("Error 3| isEmpty() should not return false after clear().");
      return false;
    }

    // 4) Test if list still has contents
    try {
      list.get(0);
      System.out.println("Error 4| List should have no contents. List:\n" + list);
    } catch (Exception e) {

    }

    return true;
  }

  /**
   * checks for the correctness of the InventoryList.addYellow(), InventoryList.addBlue(), and
   * InventoryList.addBrown() methods
   * 
   * @return True if all tests pass
   */
  public static boolean testAddBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();

    // 1) Basic add test
    list.addBlue(new Box(Color.BLUE));
    list.addYellow(new Box(Color.YELLOW));
    list.addBrown(new Box(Color.BROWN));
    if (!list.toString().equals("YELLOW 2 -> BLUE 1 -> BROWN 3 -> END") || list.size() != 3
        || list.getYellowCount() != 1 || list.getBlueCount() != 1 || list.getBrownCount() != 1) {
      System.out.println("Error 1| Boxes weren't added properly."
          + "\nExpected: YELLOW 2 -> BLUE 1 -> BROWN 3 -> END" + "\nActual: " + list);
      System.out.print("Expected: Size: 3; yellowCount: 1; blueCount: 1; brownCount: 1\nActual: ");
      displaySizeCounts(list);
      return false;
    }

    // 2) addYellow behavior test (Makes sure yellow boxes added to head)
    list.addYellow(new Box(Color.YELLOW));
    if (!list.toString().equals("YELLOW 4 -> YELLOW 2 -> BLUE 1 -> BROWN 3 -> END")) {
      System.out.println("Error 2| Improper adding of yellow boxes."
          + "\nExpected: YELLOW 4 -> YELLOW 2 -> BLUE 1 -> BROWN 3 -> END" + "\nActual: " + list);
      return false;
    }

    // 3) addBrown behavior test (Makes sure brown boxes added to tail)
    list.addBrown(new Box(Color.BROWN));
    if (!list.toString().equals("YELLOW 4 -> YELLOW 2 -> BLUE 1 -> BROWN 3 -> BROWN 5 -> END")) {
      System.out.println("Error 3| Improper adding of brown boxes."
          + "\nExpected: YELLOW 4 -> YELLOW 2 -> BLUE 1 -> BROWN 3 -> BROWN 5 -> END" + "\nActual: "
          + list);
      return false;
    }

    // 4) addBlue behavior test (Makes sure blue boxes added after yellow and before brown)
    list.addBlue(new Box(Color.BLUE));
    if (!list.toString()
        .equals("YELLOW 4 -> YELLOW 2 -> BLUE 6 -> BLUE 1 -> BROWN 3 -> BROWN 5 -> " + "END")) {
      System.out.println("Error 4| Improper adding of blue boxes."
          + "\nExpected: YELLOW 4 -> YELLOW 2 -> BLUE 6 -> BLUE 1 -> BROWN 3 -> BROWN 5 -> END"
          + "\nActual: " + list);
      return false;
    }

    // 5) Improper color input tests
    try {// 1. Blue
      list.addBlue(new Box(Color.YELLOW));
      System.out.println("Error 5.1.1| No exception thrown for incorrect color in addBlue()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 5.1.2| Wrong exception thrown for incorrect color in addBlue()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    try {// 2. Yellow
      list.addYellow(new Box(Color.BLUE));
      System.out.println("Error 5.2.1| No exception thrown for incorrect color in addYellow()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 5.2.2| Wrong exception thrown for incorrect color in addYellow()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    try {// 3. Brown
      list.addBrown(new Box(Color.YELLOW));
      System.out.println("Error 5.3.1| No exception thrown for incorrect color in addBrown()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 5.3.2| Wrong exception thrown for incorrect color in addBrown()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    // 6) Null value input tests
    try {// 1. Blue
      list.addBlue(null);
      System.out.println("Error 6.1.1| No exception thrown for null input in addBlue()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 6.1.2| Wrong exception thrown for null input in addBlue()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    try {// 2. Yellow
      list.addYellow(null);
      System.out.println("Error 6.2.1| No exception thrown for null input in addYellow()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 6.2.2| Wrong exception thrown for null input in addYellow()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    try {// 3. Brown
      list.addBrown(null);
      System.out.println("Error 6.3.1| No exception thrown for null input in addBrown()");
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      System.out.println("Error 6.3.2| Wrong exception thrown for null input in addBrown()"
          + "\nException details: " + e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * checks for the correctness of the InventoryList.removeBox() InventoryList.removeYellow(), and
   * InventoryList.remove Brown() methods
   * 
   * @return True if all tests pass
   */
  public static boolean testRemoveBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();
    list.addBlue(new Box(Color.BLUE));
    list.addBlue(new Box(Color.BLUE));
    list.addBlue(new Box(Color.BLUE));
    list.addYellow(new Box(Color.YELLOW));
    list.addBrown(new Box(Color.BROWN));
    Box box;

    // 1) removeBox() test for Box removal and return value
    box = list.removeBox(1);
    if (list.get(3).getInventoryNumber() == 1 || list.size() != 4 || list.getYellowCount() != 1
        || list.getBlueCount() != 2 || list.getBrownCount() != 1) {
      System.out.print("Error 1.1| removeBox() failed to remove item. "
          + "\nExpected: Size: 4; yellowCount: 1; blueCount: 2; brownCount: 1\nActual: ");
      displaySizeCounts(list);
      return false;
    }
    if (box == null || box.getColor() != Color.BLUE || box.getInventoryNumber() != 1) {
      System.out.println("Error 1.2| removeBox() returned the wrong value. Expected: BLUE 1."
          + " Actual: " + box == null ? "null" : box.toString());
      return false;
    }

    // 2) removeYellow() test for Box removal and return value
    box = list.removeYellow();
    if (list.get(0).getInventoryNumber() == 4 || list.size() != 3 || list.getYellowCount() != 0
        || list.getBlueCount() != 2 || list.getBrownCount() != 1) {
      System.out.print("Error 2.1| removeYellow() failed to remove item. "
          + "\nExpected: Size: 3; yellowCount: 0; blueCount: 2; brownCount: 1\nActual: ");
      displaySizeCounts(list);
      return false;
    }
    if (box == null || box.getColor() != Color.YELLOW || box.getInventoryNumber() != 4) {
      System.out.println("Error 2.2| removeYellow() returned the wrong value. Expected: YELLOW 3."
          + " Actual: " + box == null ? "null" : box.toString());
      return false;
    }

    // 3) removeBrown() test for Box removal and return value
    box = list.removeBrown();
    if (list.size() != 2 || list.get(1).getInventoryNumber() == 5 || list.getYellowCount() != 0
        || list.getBlueCount() != 2 || list.getBrownCount() != 0) {
      System.out.print("Error 3.1| removeBrown() failed to remove item. "
          + "\nExpected: Size: 2; yellowCount: 0; blueCount: 2; brownCount: 0\nActual: ");
      displaySizeCounts(list);
      return false;
    }
    if (box == null || box.getColor() != Color.BROWN || box.getInventoryNumber() != 5) {
      System.out.println("Error 3.2| removeBrown() returned the wrong value. Expected: BROWN 5."
          + " Actual: " + box == null ? "null" : box.toString());
      return false;
    }

    // 4) Exception tests for removeBox(), removeYellow(), and removeBrown()
    try {// 1. removeBox()
      list.removeBox(1000);
      System.out.println("Error 4.1.1| No exception thrown for improper input in removeBox()");
      return false;
    } catch (java.util.NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("Error 4.1.2| Wrong exception thrown for improper input in removeBox()"
          + "\nException details: " + e.getMessage());
      return false;
    }
    try {// 2. removeYellow()
      list.removeYellow();
      System.out.println("Error 4.2.1| No exception thrown when no yellow boxes exist");
      return false;
    } catch (java.util.NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("Error 4.2.2| Wrong exception thrown when no yellow boxes exist"
          + "\nException details: " + e.getMessage());
      return false;
    }
    try {// 3. removeBrown()
      list.removeBrown();
      System.out.println("Error 4.3.1| No exception thrown when no brown boxes exist");
      return false;
    } catch (java.util.NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("Error 4.3.2| Wrong exception thrown when no brown boxes exist"
          + "\nException details: " + e.getMessage());
      return false;
    }

    // 5) removeBox() test for removing at head
    try {
      list.removeBox(3);
      if (!list.toString().equals("BLUE 2 -> END")) {
        System.out.println("Error 5.1| Incorrect removal of head node. Expected: BLUE 2 -> END."
            + "Actual: " + list);
        return false;
      }
    } catch (Exception e) {
      System.out
          .println("Error 5.2| Something went wrong when removing a head node: " + e.getMessage());
      return false;
    }

    // 6) removeBox() test for removing at tail
    list.addYellow(new Box(Color.YELLOW));
    try {
      list.removeBox(2);
      if (!list.toString().equals("YELLOW 6 -> END")) {
        System.out.println("Error 6.1| Incorrect removal of tail node. Expected: YELLOW 6 -> END."
            + "Actual: " + list);
        return false;
      }
    } catch (Exception e) {
      System.out
          .println("Error 6.2| Something went wrong when removing a tail node: " + e.getMessage());
      return false;
    }

    // 7) removeBox() test for removing last node in list
    try {
      list.removeBox(6);
      if (!list.toString().equals("")) {
        System.out.println(
            "Error 7.1| List should be empty after removing last node." + "Actual: " + list);
        return false;
      }
    } catch (Exception e) {
      System.out
          .println("Error 7.2| Something went wrong when removing last node: " + e.getMessage());
      return false;
    }
    try {
      list.addYellow(new Box(Color.YELLOW));
      if (!list.toString().equals("YELLOW 7 -> END")) {
        System.out.println(
            "Error 7.3| List incorrectly altered. Expected: YELLOW 7 -> END." + "Actual: " + list);
        return false;
      }
      list.removeBox(7);
      list.addBlue(new Box(Color.BLUE));
      if (!list.toString().equals("BLUE 8 -> END")) {
        System.out.println(
            "Error 7.3| List incorrectly altered. Expected: BLUE 8 -> END." + "Actual: " + list);
        return false;
      }
      list.removeBox(8);
      list.addBrown(new Box(Color.BROWN));
      if (!list.toString().equals("BROWN 9 -> END")) {
        System.out.println(
            "Error 7.3| List incorrectly altered. Expected: BROWN 9 -> END." + "Actual: " + list);
        return false;
      }
      list.removeBox(9);
      if (!list.toString().equals("")) {
        System.out.println("Error 7.3| List incorrectly altered. Should be empty");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error 7.4| Incorrectness in removing final node. List no longer"
          + " functions properly: " + e.getMessage());
      return false;
    }

    // 8) removeYellow() test for removing final value
    list.addYellow(new Box(Color.YELLOW));
    try {
      list.removeYellow();
      list.addBlue(new Box(Color.BLUE));
      list.addBrown(new Box(Color.BROWN));
      if (!list.toString().equals("BLUE 11 -> BROWN 12 -> END")) {
        System.out.println("Error 8.1| List no longer functions properly after removeYellow()"
            + "Expected list: BLUE 11 -> BROWN 12 -> END. Actual: " + list);
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error 8.2| Exception thrown. List no longer functions properly after"
          + " removeYellow(): " + e.getMessage());
    }

    // 9) removeBrown() test for removing final value
    list.removeBox(11);
    try {
      list.removeBrown();
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(new Box(Color.BLUE));
      if (!list.toString().equals("YELLOW 13 -> BLUE 14 -> END")) {
        System.out.println("Error 9.1| List no longer functions properly after removeBrown()"
            + " Expected list: YELLOW 13 -> BLUE 14 -> END. Actual: " + list);
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error 9.2 Exception thrown. List no longer functions properly after"
          + " removeBrown(): " + e);
      return false;
    }


    return true;
  }

  /**
   * Checks for the correctness of the InventoryList.get() method
   * 
   * @return True if all tests pass
   */
  public static boolean testGetBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();
    list.addBrown(new Box(Color.BROWN));
    list.addYellow(new Box(Color.YELLOW));
    list.addBlue(new Box(Color.BLUE));
    Box box;

    // 1) Checks if returned correct box
    box = list.get(1);
    if (!box.toString().equals("BLUE 3")) {
      System.out
          .println("Error 1| get() returned an incorrect value. Expected: BLUE 3. Actual: " + box);
      return false;
    }

    // 2) Checks if list unchanged
    if (!list.toString().equals("YELLOW 2 -> BLUE 3 -> BROWN 1 -> END")) {
      System.out.println("Error 2| get() is not supposed to alter the list. "
          + "Expected: YELLOW 2 -> BLUE 3 -> BROWN 1 -> END. Actual: " + list);
      return false;
    }

    // 3) Exception checks (index >= size)
    try {
      list.get(50);
      System.out.println("Error 3.1| No exception thrown for overly large index in get().");
      return false;
    } catch (IndexOutOfBoundsException e) {

    } catch (Exception e) {
      System.out.println("Error 3.2| Wrong exception thrown for overly large index in get().");
      return false;
    }

    // 4) Exception checks (index < 0)
    try {
      list.get(-1);
      System.out.println("Error 4.1| No exception thrown for negative index in get().");
      return false;
    } catch (IndexOutOfBoundsException e) {

    } catch (Exception e) {
      System.out.println("Error 4.2| Wrong exception thrown for negative index in get().");
      return false;
    }

    return true;
  }

  /**
   * A test suite method to run all your test methods
   * 
   * @return True if all test methods pass
   */
  public static boolean runAllTests() {
    Box.restartNextInventoryNumber();

    if (testClear() && testAddBoxes() && testRemoveBoxes() && testGetBoxes()) {
      return true;
    }
    return false;
  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * 
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(InventoryList list) {
    System.out.println("  Size: " + list.size() + ", yellowCount: " + list.getYellowCount()
        + ", blueCount: " + list.getBlueCount() + ", brownCount: " + list.getBrownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   * 
   */
  public static void demo() {
    // Create a new empty InventoryList object
    InventoryList list = new InventoryList();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a blue box to an empty list
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 1
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 2 at the head of the list
    System.out.println(list); // prints list's content
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 3
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 4
    System.out.println(list); // prints list's content
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 5 at the head of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more boxes to list and display its contents
    list.addBrown(new Box(Color.BROWN)); // adds BROWN 6 at the end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBrown(new Box(Color.BROWN)); // adds BROWN 7 at the end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 7 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBlue(new Box(Color.BLUE)); // adds BLUE 8
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 6 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeYellow(); // removes YELLOW 5
    System.out.println(list); // prints list's content
    list.removeBox(3); // removes BLUE 3 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeBox(25); // tries to remove box #25
    } catch (java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all yellow boxes
    while (list.getYellowCount() != 0) {
      list.removeYellow();
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(1); // removes BLUE 1 from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addBrown(new Box(Color.BROWN)); // adds BROWN 9 to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(8); // removes BLUE 8 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBrown(); // removes BROWN 9 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 10 to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeBox(10); // removes YELLOW 10 from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }

  /**
   * Main method to run test methods or demo()
   * 
   * @param args
   */
  public static void main(String[] args) {
    demo();
    //System.out.println(runAllTests());
  }

}
