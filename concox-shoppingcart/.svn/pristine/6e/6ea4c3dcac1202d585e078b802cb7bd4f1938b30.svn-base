/*
 * TCSS 305
 * Assignment 2 - Shopping Cart
 */

package model;

/**The Item Order class for the shopping cart GUI.
 * Holds the item and quantity. 
 * 
 * @author Concox
 * @version 1.0
 */

public final class ItemOrder {
    
    /**
     * @param myItem The name of the item, saved as a string.
     */
    private final Item myItem;
    /**
     * @param myQuantity The amount of items ordered. Used to give bulk prices.
     */
    private int myQuantity;
 
    /**
     * Basic constructor for the class. 
     * Same question about the NullPointerException, I got a warning.
     * I'm sure its not a big deal.
     * 
     * @param theItem The name of the item, saved as a string.
     * @param theQuantity The amount of items ordered. Used to give bulk prices.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theItem == null || theQuantity < 0) {
            throw new IllegalArgumentException("Invalid name or Quantity");
        } else {
            myQuantity = theQuantity;
            myItem = theItem;
            
        }
    }

    /**
     * @return the item that was ordered.
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * @return returns the amount of items ordered.
     */
    public int getQuantity() {
        return myQuantity;
    }
    
    /**
     * Adds a value to the quantity of an order.
     * 
     * @param theAdd value to be added
     */
    public void addQuantity(final int theAdd) {
        myQuantity += theAdd;
    }

    @Override
    public String toString() {
        return  myItem.toString() + ':' + myQuantity;
    }

}
