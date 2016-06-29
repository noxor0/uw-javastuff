package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;


/**Test for the Item class.
 * 
 * @author concox
 * @version 1
 */
public class ItemTest {    
    /**
     * @param QUANTITY_TWO  Limit to get bulk price.
     */
    private static final int QUANTITY_TWO = 2;
    /**
     * @param itemBulk Bulk Item instantiated for testing.
     */
    private Item myItemBulk;
    /**
     * @param itemNormal Normal / non-bulk Item instantiated for testing.
     */
    private Item myItemNormal;
    /**
     * @param myPriceItemBulk The price for the bulk item.
     */
    private final BigDecimal myPriceItemBulk = new BigDecimal("3.99");
    /**
     * @param myPriceItemNormal The price for non-bulk item.
     */
    private final BigDecimal myPriceItemNormal = new BigDecimal("4.959");
    /**
     * @param myPriceWhenBulk The price when bulk quantity reached.
     */
    private final BigDecimal myPriceWhenBulk = new BigDecimal("1.99");
    
    /**
     * Creates two item objects for testing.
     */
    @Before
    public void setUp() {
        myItemBulk = new Item("Tomato", myPriceItemBulk, QUANTITY_TWO, myPriceWhenBulk);
        myItemNormal = new Item("Banana", myPriceItemNormal);
    }

    /**  
     * Testing default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals(myItemNormal, new Item("Banana", myPriceItemNormal));
    }
    
    /**  
     * Testing default constructor with incorrect parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDefaultConstructor1() {
        assertEquals(myItemNormal, new Item(null, myPriceItemNormal));
    }
    
    /**  
     * Testing default constructor with incorrect parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDefaultConstructor2() {
        assertEquals(myItemNormal, new Item("Banana", BigDecimal.ZERO));
    }
    
    
    /**
     * Passing in null name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        new Item(null, myPriceItemNormal);
    }
    
    /**
     * Passing in invalid price. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        new Item("Carrot", BigDecimal.ZERO);
    }
    
    /**
     * Testing overloaded constructor.
     */
    @Test
    public void testOverloadedConstructor() {
        assertEquals(myItemBulk, new Item("Tomato", myPriceItemBulk,
                                          QUANTITY_TWO, myPriceWhenBulk));
    }
    
    /**
     * Passing in invalid name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructor1() {
        new Item(null, myPriceItemBulk, QUANTITY_TWO, myPriceWhenBulk);
    }
    
    /**
     * Passing in invalid price.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructor2() {
        new Item("Carrot", new BigDecimal("-2.00"), QUANTITY_TWO, myPriceWhenBulk);
    }
    
    /**
     * Passing in invalid quantity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructor3() {
        new Item("Carrot", myPriceItemBulk, 0, myPriceWhenBulk);
    }
    
    /**
     * Passing in invalid bulk price.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructor4() {
        new Item("Carrot", myPriceItemBulk, QUANTITY_TWO, BigDecimal.ZERO);
    }

    /**
     * Testing the get Price method. 
     */
    @Test
    public void testGetPrice() {
        assertEquals(myPriceItemBulk, myItemBulk.getPrice());
    }

    /**
     * Testing the getBulkQuantity method.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals(QUANTITY_TWO, myItemBulk.getBulkQuantity());
    }

    /**
     * Testing the getBulkPrice method.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals(myPriceWhenBulk, myItemBulk.getBulkPrice());
    }

    /**
     * Testing the isBulk method. Should only return true when using overloaded constructor.
     */
    @Test
    public void testIsBulk() {
        assertTrue(myItemBulk.isBulk());
    }

    /**
     * Testing toString Method.
     */
    @Test
    public void testToString() {
        assertEquals("Banana, 4.96", myItemNormal.toString());
        assertEquals("Tomato, 3.99 (2 for 1.99)", myItemBulk.toString());
    }

    /**
     * Testing overridden equals method.
     */
    //Coverage says 100%
    @Test
    public void testEqualsObject() {
        assertEquals(myItemBulk, new Item("Tomato", myPriceItemBulk));
    }
    
    /**
     * Testing overridden equals method.
     */
    @Test
    public void testEqualsObject1() {
        assertEquals(myItemBulk, myItemBulk);
    }
    
    /**
     * Testing overridden equals method.
     */
    @Test
    public void testEqualsObject2() {
        assertNotEquals(new Item("Tomato", BigDecimal.TEN), myItemBulk);
        assertNotEquals(new Item("Tomato", BigDecimal.ONE), myItemBulk);
        assertNotEquals(new Item("Banana", myPriceItemBulk), myItemBulk);
        assertNotEquals(null, myItemBulk);

    }

    /**
     * Testing HashCode method. Should return the this value.
     */
    @Test
    public void testHashCode() {
        assertEquals(522415402, myItemBulk.hashCode());
    }
}
