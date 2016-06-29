/*
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * Atv class handles the decision making.
 * Atv can move over any terrain other than walls. 
 * Atv dies to taxis and trucks.
 * 
 * @author noxor
 * @version 1.0
 */
public final class Atv extends AbstractVehicle {
    
    /**
     * Amount of turns until ATV revives. 
     */
    private static final int ATV_DEATH = 20;
    
    
    /**
     * @param theX the X value of the atv.
     * @param theY the Y value of the atv.
     * @param theDir the direction of the atv.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = ATV_DEATH;
        
    }
    

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (myDeathTime <= 0) {
            myDeathTime = ATV_DEATH;
        }
        return theTerrain != Terrain.WALL;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) { 
        Direction newDir = Direction.random();       
        if (newDir == myDir.reverse()) {
            newDir = myDir.right();
        }
        return newDir;
    }  
}
