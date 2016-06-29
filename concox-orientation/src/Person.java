/*
 * TCSS 305 - Spring 2016
 * Assignment 0 - Orientation
 */
/**
 * Class to make a person, this person's info can then be viewed through InfoMain.
 * @author Connor Cox
 * @version 1 March 2016
 */
public class Person {
    /**
     * @param myName the name of the person
     * @param myAge the age of the person
     */
    private final String myName;
    private final int myAge;
    /**
     * Class constructor.
     * @param theName string parameter passed in from constructor 
     * @param theAge  number parameter passed in from the constructor  
     */
    public Person(final String theName, final int theAge) {
        myName = theName;
        myAge = theAge;
    } 
    /**
     * A to string method to give quick info about the person created.
     * This method uses the two parameters passed in.
     * @return quick string used as an overview
     */
    public String toString() {
        return "Hello my name is " + myName + " and I am " + myAge + ".";
    }
    /**
     * A simple print line to suggest a great movie, regardless of who you are.
     */
    public void suggestion() {
        System.out.println("You should watch Ex-Machina, its a great sci-fi movie!");
    }
}
