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
 * This class models a singly linked list of boxes which can be used to store and manage inventory
 * 
 * @author Ethan
 *
 */
public class InventoryList {

  private LinkedBox head;
  private LinkedBox tail;
  private int size;
  private int yellowCount;
  private int brownCount;
  private int blueCount;

  /**
   * Adds a new blue box at the top of blue boxes if the list contains any blue box. Blue boxes must
   * be added at the button of yellow boxes and at the top of all the brown boxes if any. This means
   * that a new blue box must be added at index yellowCount.
   * 
   * @param blueBox New box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if blueBox is
   *                                            null or if it does not have a Color.BLUE color
   */
  public void addBlue(Box blueBox) {
    if (blueBox == null || blueBox.getColor() != Color.BLUE) {
      throw new IllegalArgumentException("addBlue() Error: Must input blue colored boxes");
    }
    LinkedBox newNode = new LinkedBox(blueBox);
    LinkedBox prevNode = head; // Used to loop through list

    if (size == 0) {
      head = newNode;
      tail = newNode;
      // Must update head and tail accordingly if list empty
    } else if (yellowCount == 0) {
      newNode.setNext(head);
      head = newNode;
      // Sets box to head if no yellow boxes before it but there are some boxes after
    } else {
      for (int i = 0; i < yellowCount - 1; i++) {
        // Must be yellowCount - 1 since prevNode is head before the first iteration
        // If not, will go too far and run getNext() on a null prevNode
        prevNode = prevNode.getNext();
      } // Locates the last yellow box so that blueBox can be added after it
      newNode.setNext(prevNode.getNext());
      prevNode.setNext(newNode);
      if (brownCount == 0 && blueCount == 0) {
        tail = newNode;
      } // Updates tail accordingly if no boxes after newNode
    }
    size++;
    blueCount++;
  }

  /**
   * Adds a brown box at the end of this inventory list
   * 
   * @param brownBox New brown box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if brownBox is
   *                                            null or if the color of the specific brownBox is not
   *                                            equal to Color.BROWN
   */
  public void addBrown(Box brownBox) {
    if (brownBox == null || brownBox.getColor() != Color.BROWN) {
      throw new IllegalArgumentException("addBrown() Error: Must input brown colored boxes");
    }

    LinkedBox newNode = new LinkedBox(brownBox);
    if (size == 0) {
      head = newNode;
      tail = newNode;
      // Updates head and tail accordingly if list empty
    } else {
      tail.setNext(newNode);
      tail = newNode;
      // Updates tail when adding brownBox normally
    }
    size++;
    brownCount++;
  }

  /**
   * Adds a new yellow box at the head of this list
   * 
   * @param yellowBox New box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if yellowBox is
   *                                            null or if its color does not equal to Color.YELLOW
   */
  public void addYellow(Box yellowBox) {
    if (yellowBox == null || yellowBox.getColor() != Color.YELLOW) {
      throw new IllegalArgumentException("addYellow() Error: Must input yellow colored boxes");
    }

    LinkedBox newNode = new LinkedBox(yellowBox, head);
    head = newNode; // Sets head to newNode and newNode's next to what used to be head
    if (size == 0) {
      tail = head; // Updates tail as well as head if list is empty
    }
    size++;
    yellowCount++;
  }

  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  public void clear() {
    // Sets all count fields to 0 and removes head and tail values
    head = null;
    tail = null;
    size = 0;
    blueCount = 0;
    brownCount = 0;
    yellowCount = 0;
  }

  /**
   * Returns the element stored at position index of this list without removing it.
   * 
   * @param index Position within this list
   * @return The box stored at position index of this list
   * @throws java.lang.IndexOutOfBoundsException - with a descriptive error message if the index is
   *                                             out of bounds (index < 0 || index >= size())
   */
  public Box get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("get() Error: Invalid index");
    }
    LinkedBox currNode = head;
    for (int i = 0; i < index; i++) {
      currNode = currNode.getNext();
      // Loops through list using getNext() until specified index reached
      // uses i < index because currNode is head before first iteration, technically i should be 1
    }

    return currNode.getBox();
  }

  /**
   * Returns the number of blue boxes stored in this list
   * 
   * @return the blueCount
   */
  public int getBlueCount() {
    return blueCount;
  }

  /**
   * Returns the number of brown boxes stored in this list
   * 
   * @return the brownCount
   */
  public int getBrownCount() {
    return brownCount;
  }

  /**
   * Returns the number of yellow boxes stored in this list
   * 
   * @return the yellowCount
   */
  public int getYellowCount() {
    return yellowCount;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Removes and returns a box given its inventory number from this list
   * 
   * @param inventoryNumber inventory number of the box to be removed from this list
   * @return a reference to the removed element
   * @throws java.util.NoSuchElementException - with a descriptive error message if no box is found
   *                                          with the provided inventory number in the list.
   */
  public Box removeBox(int inventoryNumber) {
    if (size == 0) {
      throw new java.util.NoSuchElementException("removeBox() Error: Invalid size");
    }
    LinkedBox currNode = head;
    LinkedBox tempNode;

    if (currNode.getBox().getInventoryNumber() == inventoryNumber) {
      // Checks if head has inventoryNumber
      head = currNode.getNext();
      if (size == 1) {
        tail = head; 
        // If only one element in the list, removing would set tail to null as well as head
      }
      switch (currNode.getBox().getColor()) {
        case YELLOW:
          yellowCount--;
          break;
        case BLUE:
          blueCount--;
          break;
        case BROWN:
          brownCount--;
          break;
      }
      size--;
      return currNode.getBox();
    }

    for (int i = 0; i < size - 1; i++) {
      // Loops through the list to search for inventoryNumber
      // Algorithm must look ahead of currNode so that it's next value can be altered
      tempNode = currNode.getNext();
      if (tempNode.getBox().getInventoryNumber() == inventoryNumber) {
        currNode.setNext(tempNode.getNext());
        if (tempNode.equals(tail)) {
          // Tail must be updated if the tail node has inventoryNumber and must be removed
          tail = currNode;
        }
        switch (tempNode.getBox().getColor()) {
          case YELLOW:
            yellowCount--;
            break;
          case BLUE:
            blueCount--;
            break;
          case BROWN:
            brownCount--;
            break;
        }
        size--;
        return tempNode.getBox();
      }
      currNode = currNode.getNext();
    }
    // If inventoryNumber wasn't found, the exception must be thrown
    throw new java.util.NoSuchElementException(
        "removeBox() Error: Box " + inventoryNumber + " not found");
  }

  /**
   * Removes and returns the element at the tail of this list if it has a brown color
   * 
   * @return a reference to the removed element
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list does
   *                                          not contain any brown box (brownCount == 0)
   */
  public Box removeBrown() {
    LinkedBox currNode = tail;
    LinkedBox prevNode = head;
    if (brownCount == 0) {
      throw new java.util.NoSuchElementException("removeBrown() Error: No brown boxes exist");
    }

    if (size == 1) { // If last element of the list is to be removed, head and tail must be null
      head = null;
      tail = null;
    } else {
      // Iterates through the list to find the node right before tail and alter its next value
      for (int i = 0; i < size - 1; i++) {
        if (prevNode.getNext().equals(tail)) {
          prevNode.setNext(null);
          tail = prevNode; // Tail is updated to the node before it
          break;
        }
        prevNode = prevNode.getNext();
      }
    }
    brownCount--;
    size--;
    return currNode.getBox();
  }

  /**
   * Removes and returns the box at the head of this list if its color is yellow
   * 
   * @return a reference to the removed box
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list does
   *                                          not contain any yellow boxes
   */
  public Box removeYellow() {
    LinkedBox currNode = head;
    if (yellowCount == 0) {
      throw new java.util.NoSuchElementException("removeYellow() Error: No yellow boxes exist");
    }

    if (size == 1) {
      tail = null; // If last element in the list is to be removed, head and tail must be null
    }
    // Removes the old head node by shifting head to the next node
    head = currNode.getNext();
    yellowCount--;
    size--;
    return currNode.getBox();
  }

  /**
   * Returns the size of this list
   * 
   * @return the size of this LinkedBoxList
   */
  public int size() {
    return size;
  }

  /**
   * Returns a String representation of the contents of this list
   * 
   * @return a String representation of the content of this list. If this list is empty, an empty
   *         String ("") will be returned.
   */
  @Override
  public String toString() {
    String finalStr = "";
    if (size == 0) {
      return finalStr;
    }
    LinkedBox currNode = head;

    for (int i = 0; i < size; i++) {
      finalStr += currNode.toString();
      currNode = currNode.getNext();
    }
    return finalStr;
  }
}
