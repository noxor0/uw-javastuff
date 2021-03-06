package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import model.ShoppingCart;

import org.junit.Before;
import org.junit.Test;

/**
 * This isn't graded, right? I'm not going to do comments. 
 * 
 * @author concox
 * @version 1.1
 */
public class ShoppingCartTest {

    /**
     * 
     */
    private ShoppingCart myCart;
    /**
     * 
     */
    private final Item myBananaItem = new Item("Banana", new BigDecimal("2.99"));
    /**
     * 
     */
    private final Item myTomatoItem = new Item("Tomato", new BigDecimal("1.99"), 
                                      3, new BigDecimal(".59"));
    /**
     * 
     */
    private final ItemOrder myBananaOrder = new ItemOrder(myBananaItem, 3);
    /**
     * 
     */
    private final ItemOrder myTomatoOrder = new ItemOrder(myTomatoItem, 6);
    
    /**
     * 
     */
    @Before
    public void setUp() {
//        myCart.setMembership(true);
        myCart = new ShoppingCart();
        myCart.add(myBananaOrder);
        myCart.add(myTomatoOrder);
    }
    
    /**
     * 
     */
    @Test
    public void testAdd() {
        myCart.clear();
        assertEquals(0, myCart.size());
        myCart.add(myBananaOrder);
        assertEquals(1, myCart.size());
    }

    /**
     * adds duplicate.
     */
    @Test
    public void testAdd1() {
        myCart.add(myBananaOrder);
        assertEquals(2, myCart.size());
    }
    /**
     * 
     */
    @Test
    public void testisMembership() {
        myCart.setMembership(true);
        assertTrue(myCart.isMembership());
    }

    /**
     * 
     */
    @Test
    public void testCalculateTotal() {
        myCart.setMembership(true);
        assertEquals(new BigDecimal("10.15"), myCart.calculateTotal());
        
    }
    
    /**
     * 
     */
    @Test
    public void testCalculateTotal1() {
        myCart.setMembership(false);
        assertEquals(new BigDecimal("20.91"), myCart.calculateTotal());
        
    }
    

    /**
     * 
     */
    @Test
    public void testClear() {
        myCart.clear();
        assertEquals(0, myCart.size());
    }
    
    /**
     * 
     */
    @Test
    public void testClear1() {
        myCart.clear();
        myCart.clear();
    }

    /**
     * 
     */
    @Test
    public void testToString() {
        assertEquals("My Cart: \nBanana, $2.99:3\nTomato, $1.99 (3 for $0.59):6\n", 
                     myCart.toString());
        
    }

}
