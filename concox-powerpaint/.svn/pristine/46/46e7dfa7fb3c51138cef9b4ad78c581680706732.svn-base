package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * The Tool that draws in a straight line between starting and ending point.
 * @author concox
 * @version 1.0
 *
 */
public class LineTool implements ToolInterface {
    
    /**
     * 
     */
    private Line2D myLine;
    
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
    public LineTool() {
        super();
        myX = 0;
        myY = 0;
        myLine = new Line2D.Double();       
    }

    @Override
    public Shape start(final int theX, final int theY) {
        myX = theX;
        myY = theY;
        myLine = new Line2D.Double(myX, myY, myX, myY);
        return (Shape) myLine.clone();
    }

    @Override
    public Shape move(final int theX, final int theY) {
        myLine.setLine(myX, myY, theX, theY);
        return (Shape) myLine.clone();
    }

    @Override
    public Shape end(final int theX, final int theY) {
        myLine.setLine(myX, myY, theX, theY);
        return (Shape) myLine.clone();
    }

    @Override
    public String getName() {
        return "Line";
    } 

}
