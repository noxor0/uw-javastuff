package tools;

import java.awt.Shape;

/**
 * Interface for tool functionality.
 * @author concox
 * @version 1.0
 */
public interface ToolInterface {

    /**
     * Creates the initial shape made by the drawing tool.
     * @param theX the initial x position 
     * @param theY the initial y position 
     * @return a shape that was initially created
     */
    Shape start(final int theX, final int theY);
    
    /**
     * The change in the shape during mouse movement.
     * @param theX the X coordinate during the shape movement
     * @param theY the y coordinate during the shape movement
     * @return the change in the shape
     */
    Shape move(final int theX, final int theY);
    
    /**
     * Ending, or final shape of the drawing.
     * @param theX the X coordinate at the end of shape movement
     * @param theY the Y coordinate at the end of shape movement
     * @return the completed shape
     */
    Shape end(final int theX, final int theY);
    
    /**
     * Name of the tool used.
     * @return the name of the Tool.
     */
    String getName();

}
