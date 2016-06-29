/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package test;

import java.util.Observable;
import java.util.Observer;

import model.Board;

/**
 * Just learning how everything was set up.
 * 
 * @author noxor
 * @version 1.0
 */
public final class BoardTest implements Observer {
    /**
     * 
     */
    private boolean myGameOver;
    /**
     * Private.
     */
    private BoardTest() {
        
    }
    /**
     * Main.
     * 
     * @param theArgs terminal arguments. 
     */
    public static void main(final String[] theArgs) {
        final Board board = new Board();
        final BoardTest boardTest = new BoardTest();
        board.addObserver(boardTest);
        board.newGame();
        while (!boardTest.myGameOver) {
            board.step();
            System.out.println(board);
        }
    }
    
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg.toString().equals("true") || theArg.toString().equals("false")) {
            myGameOver = Boolean.valueOf(theArg.toString()); 
        }
    }
}
