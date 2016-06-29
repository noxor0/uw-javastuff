/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Creates an ellipse that follows the users cursor.
 * 
 * @author noxor
 * @version 1.0
 */
public final class EllipseTool implements ToolInterface {
    /**
     * Creates an ellipse to be used.
     */
    private final Ellipse2D myCircle;
    /**
     * The starting x of the circle.
     */
    private int myX;
    /**
     * The starting y of the circle. 
     */
    private int myY;
    /**
     * Constructs the ellipse tool.
     */
    public EllipseTool() {
        super();
        myX = 0;
        myY = 0;
        myCircle = new Ellipse2D.Double();
    }
    @Override
    public Shape start(final int theX, final int theY) {
        myX = theX;
        myY = theY;
        myCircle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myCircle.clone();
    }
    @Override
    public Shape move(final int theX, final int theY) {
        myCircle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myCircle.clone();
    }
    @Override
    public Shape end(final int theX, final int theY) {
        myCircle.setFrameFromDiagonal(myX, myY, theX, theY);
        return (Shape) myCircle.clone();
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Ellipse";
    }
    @Override
    public String getImagePath() {
        return "/icons/ellipse.png";
    }
}
