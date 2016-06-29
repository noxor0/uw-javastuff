/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;


/**
 * Path tool that creates a path that follows the user's cursor.
 * 
 * @author noxor
 * @version 1.0
 *
 */
public final class PencilTool implements ToolInterface {
    /**
     * Creates the path to be edited.
     */
    private Path2D myPath;
    /**
     * The starting X of the path.
     */
    private int myX;
    /**
     * The starting Y of the path. 
     */
    private int myY;
    /**
     * Creates the PencilTool.
     */
    public PencilTool() {
        super();
        myX = 0;
        myY = 0;
    }
    @Override
    public Shape start(final int theX, final int theY) {
        myPath = new GeneralPath();
        myPath.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        myX = theX;
        myY = theY;
        myPath.moveTo(myX, myY);
        return (Shape) myPath.clone();
    }
    @Override
    public Shape move(final int theX, final int theY) {
        myPath.lineTo(theX, theY);
        return (Shape) myPath.clone();
    }
    @Override
    public Shape end(final int theX, final int theY) {
        myPath.lineTo(theX, theY);
        return (Shape) myPath.clone();
    }
    @Override
    public String getName() {
        return "Pencil";
    }
    @Override
    public String getImagePath() {
        return "/icons/pencil.png";
    }
}
