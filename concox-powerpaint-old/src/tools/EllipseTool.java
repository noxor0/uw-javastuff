package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * CIRCLES.
 * 
 * @author noxor
 * @version 1.0
 */
public class EllipseTool implements ToolInterface {
    /**
     * 
     */
    private final Ellipse2D myCircle;
    /**
     * 
     */
    private int myX;
    /**
     * 
     */
    private int myY;
    /**
     * 
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
}
