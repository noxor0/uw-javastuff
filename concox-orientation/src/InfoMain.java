/*
 * TCSS 305 - Spring 2016
 * Assignment 0 - Orientation
 */
/**
 * A tiny program that displays information about a person.
 * 
 * @author Connor Cox
 * @version 1 March 2016
 */
public final class InfoMain {
    /**
    * Prevent default constructor. 
    */
    private InfoMain() {
    }
     /**
     * Main method of the program.
     * @param theArgs ignored in this program
     */
    public static void main(final String[] theArgs) {
        final Person connor = new Person("Connor", 19);
        System.out.println(connor);
        connor.suggestion();
    }
}
