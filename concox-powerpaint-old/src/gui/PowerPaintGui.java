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

import tools.Canvas;

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
     * 
     */
    private final JMenuBar myMenuBar = new JMenuBar();
    /**
     * Start method for the program, creates and populates the window.
     */
    public void start() {
        myFrame.setLayout(new BorderLayout());
        final JPanel canvas = new Canvas();
        PowerPaintMenus pPM = new PowerPaintMenus(canvas);
        pPM.populateActions();
        final Cursor crosshair = new Cursor(Cursor.CROSSHAIR_CURSOR);
        canvas.setCursor(crosshair);
        myFrame.add(canvas, BorderLayout.CENTER);
        
        final JToolBar toolBar = pPM.createToolBar();
        myFrame.add(toolBar, BorderLayout.SOUTH);
       
        myMenuBar.add(pPM.createFileMenu(myFrame));
        myMenuBar.add(pPM.createOptionsMenu());
        myMenuBar.add(pPM.createToolsMenu());
        myMenuBar.add(pPM.createHelpMenu());
        myFrame.setJMenuBar(myMenuBar);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
}
