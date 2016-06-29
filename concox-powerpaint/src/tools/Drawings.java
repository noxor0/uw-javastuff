/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;


/**
 * Drawing class that holds values of the completed shape.
 * 
 * @author concox
 * @version 1.0
 */
public final class Drawings {
    /**
     * The shape shape which is drawn.
     */
    private final Shape myShape;
    
    /**
     * The color of the shape.
     */
    private final Color myColor;
    /**
     * The fill color of the shape.
     */
    private final Color myFillColor;
    /**
     * The stroke width of the pen used to make the drawing.
     */
    private final Stroke myStroke;
    /**
     * If the shape has been filed or not.
     */
    private boolean myIsShapeFilled;
    /**
     * Constructor to create a Drawing.Stores 3 different properties of the drawing.
     * @param theShape the Shape of the drawing.
     * @param theColor the Color of the drawing.
     * @param theStroke the Stroke width of the pen use to make the drawing.
     */
    public Drawings(final Shape theShape, final Color theColor, final float theStroke) {
        myShape = theShape;
        myColor = theColor;            
        myFillColor = Color.WHITE;
        myStroke = new BasicStroke(theStroke); 
    }
    /**
     * Overloaded constructor to create a Drawing with a fill color.
     * @param theShape the Shape of the drawing.
     * @param theColor the Color of the drawing.
     * @param theFillColor the Fill color of the drawing.
     * @param theStroke the Stroke width of the pen use to make the drawing.
     */
    public Drawings(final Shape theShape, final Color theColor,
                    final Color theFillColor, final float theStroke) {
        myShape = theShape;
        myColor = theColor;
        myFillColor = theFillColor;            
        myStroke = new BasicStroke(theStroke); 
        myIsShapeFilled = true;
    }
    /**
     * Return the Shape that was created by user.
     * @return current Shape.
     */
    public Shape getShape() {
        return myShape;
    }
    /**
     * Return what color the shape was made in..
     * @return current color.
     */
    public Color getColor() {
        return new Color(myColor.getRGB());
    }
    /**
     * Returns which color the shape was filled with.
     * @return the fill color.
     */
    public Color getFill() {
        return new Color(myFillColor.getRGB());
    }
    /**
     * Return current pen width.
     * @return current Stroke.
     */
    public Stroke getStroke() {
        return myStroke;
    }
    /**
     * Shape is stored with a filled or not boolean.
     * @return if the shape is filled.
     */
    public boolean isShapeFilled() {
        return myIsShapeFilled;
    }
}
