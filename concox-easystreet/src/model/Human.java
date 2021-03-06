/*
 * TCSS 305 - Easy Street
 * Spring 2016
 */
package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Human class that extends AbstractVehicle. Handles decision making and
 * restrictions for the human vehicles. 
 * 
 * @author concox
 * @version 1.0
 *
 */
public final class Human extends AbstractVehicle {

    /**
     * Amount of turns it takes for the human to revive.
     */
    private static final int HUMAN_DEATH = 50;

    /**
     * Constructs a human, with x,y and a direction.
     * 
     * @param theX The X value of the human.
     * @param theY The Y value of the human.
     * @param theDir The initial direction of the human.
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = HUMAN_DEATH;
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean isPassing = false;
        if (theTerrain == Terrain.GRASS) {
            isPassing = true;
        } 
        if (theTerrain == Terrain.CROSSWALK) {
            if (theLight == Light.GREEN) {
                isPassing = false;
            } else {
                isPassing = true;
            }
        }
        return isPassing;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newDir = myDir;
        Direction crossDir = newDir;
        boolean crossPresent = false;
        myValidDir = new ArrayList<>();
        for (final Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (entry.getValue() == Terrain.CROSSWALK && entry.getKey() != myDir.reverse()) {
                crossPresent = true;
                crossDir = entry.getKey();
            }
            if (entry.getValue() == Terrain.GRASS && entry.getKey() != myDir.reverse()) { 
                myValidDir.add(entry.getKey());
            }
        }
        if (myValidDir.size() > 0 && !crossPresent) {
            newDir = myValidDir.get(myRandGen.nextInt(myValidDir.size()));            
        } else {
            newDir = myDir.reverse();
        }
        if (crossPresent) {
            newDir = crossDir;
        }
        return newDir;
        
    }
    
}
