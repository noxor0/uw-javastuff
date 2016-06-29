/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A tool that creates a rectangle that follows the user's cursor.
 * 
 * @author noxor
 * @version 1.0
 *
 */
public final class RectangleTool implements ToolInterface {
    /**
     * Creates a rectangle to be edited.
     */
    private final Rectangle2D myRectangle;
    /**
     * Starting X value of the rectangle.
     */
    private int myX;
    /**
     * Starting Y value of the rectangle.
     */
    private int myY;
    /**
     * Creates the rectangle tool.
     */
    public RectangleTool() {
        super();
        myX = 0;
        myY = 0;
        myRectangle = new Rectangle2D.Double();
    }
    @Override
    public Shape start(final int theX, final int theY) {
        myX = theX;
        myY = theY;
        myRectangle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myRectangle.clone();
    }
    @Override
    public Shape move(final int theX, final int theY) {
        myRectangle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myRectangle.clone();
    }
    @Override
    public Shape end(final int theX, final int theY) {
        myRectangle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myRectangle.clone();
    }
    @Override
    public String getName() {
        return "Rectangle";
    }
    @Override
    public String getImagePath() {
        return "/icons/rectangle.png";
    }
    
}
