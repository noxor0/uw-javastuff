 /* 
 *TCSS 305 - Assignment 4: PowerPaint
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Holds the bulk of the GUI code for the PowerPaint project.
 * Creates and holds the interactive window.
 * 
 * @author concox
 * @version 1.0
 */
public final class PowerPaintGui {
    /**
     * Makes the JFrame that holds all the content.
     */
    private final JFrame myFrame = new JFrame("PowerPaint");
    /**
     * Creates the Menubar that holds menu options.
     */
    private final JMenuBar myMenuBar = new JMenuBar();
    /**
     * Start method for the program, creates and populates the window.
     */
    protected void start() {
        PowerPaintMenus.populateActions();
        myFrame.setLayout(new BorderLayout());
        myFrame.setIconImage(new FileImageIcon().createFileImage());
        final JPanel canvas = new Canvas();
        final Cursor crosshair = new Cursor(Cursor.CROSSHAIR_CURSOR);
        canvas.setCursor(crosshair);
        myFrame.add(canvas, BorderLayout.CENTER);
        
        final JToolBar toolBar = PowerPaintMenus.createToolBar();
        myFrame.add(toolBar, BorderLayout.SOUTH);
       
        myMenuBar.add(PowerPaintMenus.createFileMenu(myFrame));
        myMenuBar.add(PowerPaintMenus.createOptionsMenu());
        myMenuBar.add(PowerPaintMenus.createToolsMenu());
        myMenuBar.add(PowerPaintMenus.createHelpMenu());
        myFrame.setJMenuBar(myMenuBar);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
}
