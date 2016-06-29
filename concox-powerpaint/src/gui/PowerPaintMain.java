 /* 
 *TCSS 305 - Assignment 4: PowerPaint
 */
package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Runs PowerPaint by instantating and starting the GUI.
 * 
 * @author Concox
 * @version 1.0
 *
 */
public final class PowerPaintMain {
    /**
     * Private constructor.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    /**
     * The main method, creates the PowerPaintGui. Command line args ignored.
     * 
     * @param theArgs Command line args
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                } catch (final UnsupportedLookAndFeelException e) {
                  assert false;
                } catch (final ClassNotFoundException e) {
                  assert false;
                } catch (final InstantiationException e) { 
                      assert false;
                } catch (final IllegalAccessException e) {
                  assert false;
                }
                new PowerPaintGui().start();
            }
        });
    }
}
