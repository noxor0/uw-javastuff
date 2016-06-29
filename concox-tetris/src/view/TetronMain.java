/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

import java.awt.EventQueue;

/**
 * Contains main method of the program.
 * 
 * @author concox
 * @version 1.0
 */
public final class TetronMain {

    /**
     * private constructor, should not create this class.
     */
    private TetronMain() {
        throw new IllegalStateException();
    }
    /**
     * Starts tetron.
     * 
     * @param theArgs terminal stuff.
     */
    public static void main(final String[] theArgs) {
        final Tetron view = new Tetron();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.start();
            }
        });
    }
}