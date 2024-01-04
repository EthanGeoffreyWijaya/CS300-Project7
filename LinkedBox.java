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
 * This class models a linked box node which can be used to create a singly linked list of boxes.
 * 
 * @author Ethan
 *
 */
public class LinkedBox {
  private Box box;
  private LinkedBox next;

  /**
   * Creates a new LinkedBox with a specified box and null as next field
   * 
   * @param box the box carried by this linked box
   */
  public LinkedBox(Box box) {
    this.box = box;
    next = null;
  }

  /**
   * Creates a new LinkedBox node with given box and next fields
   * 
   * @param box  the box carried by this linked box
   * @param next reference to the next linked box in the list
   */
  public LinkedBox(Box box, LinkedBox next) {
    this.box = box;
    this.next = next;
  }

  /**
   * Returns the next linked box node
   * 
   * @return the next
   */
  public LinkedBox getNext() {
    return next;
  }

  /**
   * Sets the link to the next linked box node
   * 
   * @param next the next to set
   */
  public void setNext(LinkedBox next) {
    this.next = next;
  }

  /**
   * Returns the data field box
   * 
   * @return the box
   */
  public Box getBox() {
    return box;
  }

  /**
   * Returns a String representation of this Linked box. 
   * This String will be : box.toString() + " -> " if next field is not null 
   * box.toString() + " -> END" if next field is null For instance,
   * LinkedBox box1 = new LinkedBox(new Box(Color.BLUE)); 
   * LinkedBox box2 = new LinkedBox(new Box(Color.YELLOW), box1); 
   * box1.toString() returns "BLUE 1 -> END" 
   * box2.toString() returns "YELLOW 2 -> "
   * 
   * @return a String representation of this Linked MegaBlock object
   */
  @Override
  public String toString() {
    if (next != null) {
      return box.toString() + " -> ";
    } else {
      return box.toString() + " -> END";
    }
  }

}
