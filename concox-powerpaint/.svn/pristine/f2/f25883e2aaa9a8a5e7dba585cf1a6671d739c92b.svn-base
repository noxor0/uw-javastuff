package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Rectangle Tool.
 * 
 * @author noxor
 * @version 1.0
 *
 */
public class RectangleTool implements ToolInterface {
    /**
     * 
     */
    private final Rectangle2D myRectangle;
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
        // TODO Auto-generated method stub
        return "Rectangle";
    }
    
}
