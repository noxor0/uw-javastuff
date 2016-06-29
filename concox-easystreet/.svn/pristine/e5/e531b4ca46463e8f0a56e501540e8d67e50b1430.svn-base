/*
 *TCSS 305 - Easy Street 
 *Spring 2016
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Test;

/**
 * @author concox
 * @version 1.0
 *
 */
public class TruckTest {
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 100;
    /**
     *  Test method for Truck constructor. 
     */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        assertEquals("Truck x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.NORTH, t.getDirection());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }
    
    /**
     * Test method for {@link model.Truck#canPass(model.Terrain, model.Light)}.
     */
    @Test
    public void testCanPass() {
        final Truck truck = new Truck(10, 11, Direction.NORTH);
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);
        for (final Terrain t : Terrain.values()) {
            for (final Light l : Light.values()) {
                if (t == Terrain.STREET) {
                    assertTrue("Truck should pass on STREET with light " 
                               + l, truck.canPass(t, l));
                } else if (t == Terrain.LIGHT) {
                    assertTrue("Truck should pass on LIGHT with light " 
                                    + l, truck.canPass(t, l));
                } else if (t == Terrain.CROSSWALK) {
                    if (l == Light.GREEN || l == Light.YELLOW) {
                        assertTrue("Truck should pass on STREET with light " 
                                        + l, truck.canPass(t, l));   
                    } else {
                        assertFalse("Truck should not pass on STREET with light " 
                                        + l, truck.canPass(t, l));  
                    }
                } else if (!validTerrain.contains(t)) {
                    assertFalse("Truck should not pass on" + t + " with light " 
                                    + l, truck.canPass(t, l));  
                }
                
            }
            
        }
    }

    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionAllValid() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck truck = new Truck(10, 11, Direction.NORTH);
        
        for (int i = 0; i < TRIES_FOR_RANDOMNESS; i++) {
            final Direction d = truck.chooseDirection(neighbors);
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }
        assertTrue("Truck chooseDirection() fails to select randomly "
                        + "among all possible valid choices!",
                        seenWest && seenNorth && seenEast); 
        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                         seenSouth);
        
    }
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                assertEquals("Truck chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                             Direction.SOUTH, truck.chooseDirection(neighbors));
            }
                
        }
    }
}
