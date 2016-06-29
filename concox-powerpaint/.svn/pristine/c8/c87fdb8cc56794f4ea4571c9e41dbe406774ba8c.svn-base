/*
 *TCSS 305 - PowerPaint Spring 2016 
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;

/**
 * Creates a 16x16 color icon with a 1 pixel black border. 
 * Color is determined by whatever Color is passed in, can be changed.
 * Roughly based off of the Color Icon found on:
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/ColorIcon.htm
 * 
 * @author Andrea Carboni
 * @author concox
 * @version 1.0
 *
 */
public final class UtilityIcon implements Icon {
    
    /**
     * Width of the Icon.
     */
    private static final int COLOR_WIDTH = 16;
    /**
     * Height of the Icon.
     */
    private static final int COLOR_HEIGHT = 16;
    /**
     * Colors the border of the icon, set to black.
     */
    private final Color myBorder;
    /**
     * The inset that surrounds the icon.
     */
    private final Insets myInsets;
    /**
     * The color the icon is set to.
     */
    private Color myColor;
    /**
     * Constructor for an utility icon.
     * 
     * @param theColor the color of the icon.
     */
    public UtilityIcon(final Color theColor) { 
        myColor   = theColor;
        myBorder  = Color.black;
        myInsets  = new Insets(1, 1, 1, 1);
    }
    /**
     * Returns the current color.
     * 
     * @return myColor that is currently displayed.
     */
    public Color getColor() {
        return myColor;
    }
    /**
     * Sets the color to the parameter.
     * 
     * @param theColor color passed in.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    @Override
    public int getIconHeight() {
        return COLOR_HEIGHT;
    }
    @Override
    public int getIconWidth() {
        return COLOR_WIDTH;
    }
    @Override
    public void paintIcon(final Component theComponent, final Graphics theG,
                          final int theX, final int theY) {
        int x = theX;
        int y = theY;
        theG.setColor(myBorder);
        theG.drawRect(theX, theY, COLOR_WIDTH - 1, COLOR_HEIGHT - 2);

        x += myInsets.left;
        y += myInsets.top;

        final int w = COLOR_WIDTH  - myInsets.left - myInsets.right;
        final int h = COLOR_HEIGHT - myInsets.top  - myInsets.bottom - 1;

        theG.setColor(myColor);
        theG.fillRect(x, y, w, h);
    }
}
