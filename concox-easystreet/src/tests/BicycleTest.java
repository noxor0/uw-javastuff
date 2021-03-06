/*
 * TCSS 305 - Easy Street
 * Spring 2016
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.Bicycle;
import model.Direction;
import model.Terrain;
import org.junit.Test;


/**
 * @author noxor
 * @version 1.0
 */
public class BicycleTest {

    /**
     * 
     */
    @Test
    public void testChooseDirection() {
        final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.TRAIL);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);
        
        assertEquals(Direction.EAST, bike.chooseDirection(neighbors));
        
    }
    
    /**
     * 
     */
    @Test
    public void testChooseDirection1() {
        final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.TRAIL);
        
        assertNotEquals(Direction.SOUTH, bike.chooseDirection(neighbors));
        
    }
    
    /**
     * 
     */
    @Test
    public void testChooseDirection2() {
        final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        assertEquals(Direction.SOUTH, bike.chooseDirection(neighbors));
        
    }

}
