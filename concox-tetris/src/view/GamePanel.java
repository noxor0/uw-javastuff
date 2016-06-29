/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Board;

/**
 * Panel where Tetron is rendered.
 * 
 * @author concox
 * @version 1.0
 *
 */
public final class GamePanel extends JPanel implements Observer {
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = -6725478929377017550L;
    /**
     * Width of the panel.
     */
    private static final int WIDTH = 300;
    /**
     * Height of the panel.
     */
    private static final int HEIGHT = 600;
    /**
     * Correction to compensate for pre-screen piece loading.
     */
    private static final int ROWCORRECT = -5;
    /**
     * Correction to compensate for extra characters before each line.
     */
    private static final int COLCORRECT = -2;
    /**
     * Holds piece piece name and color.
     */
    private final Map<Character, Color> myPieceMap = new HashMap<>();
    /**
     * The game logic.
     */
    private Board myBoard;
    /**
     * Block size.
     */
    private int myBlockSize;
    /**
     * Tron blue color. 
     */
    private final Color myTronBlue = new Color(24, 202, 230);
    /**
     * Current state of the game.
     */
    private GameState myGameState = GameState.PreGame;
    /**
     * Creates the game panel.
     * 
     * @param theBoard board.
     */
    public GamePanel(final Board theBoard) {
        super();
        myBoard = theBoard;
        myBlockSize = WIDTH / myBoard.getWidth();
        createMap();
        setBorder(BorderFactory.createLineBorder(myTronBlue));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }    
    /**
     * Steps the game forward, just repaints the panel.
     */
    public void step() {
        repaint();
    }
    /**
     * Updates game panel with a new board size, if changed.
     * 
     * @param theBoard the new board.
     */
    public void newBoard(final Board theBoard) {
        myBoard = theBoard;
    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        final int thickness = 4;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        final String boardStr = myBoard.toString();
        int row = ROWCORRECT;
        int col = COLCORRECT;
        g2d.setStroke(new BasicStroke(thickness));
        for (final char unit : boardStr.toCharArray()) {
            if (myPieceMap.containsKey(unit)) {
                g2d.setColor(myPieceMap.get(unit));
                g2d.drawOval(col * myBlockSize,
                             row * myBlockSize, myBlockSize, myBlockSize);
            }
            if (unit == '\n') {
                col = COLCORRECT;
                row++;
            }
            col++;
        }    
        if (myGameState == GameState.Paused) {
            final Color pauseGrey = new Color(909090210, true);
            g2d.setColor(pauseGrey);
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
        }
    }
    @Override
    public void update(final Observable theOb, final Object theArg) {
        if (theOb.getClass().getSimpleName().equals("Tetron")) {
            myBlockSize = WIDTH / myBoard.getWidth();
            if (theArg instanceof Dimension) {
                myBlockSize = WIDTH / 2 / Tetron.BLOCK_DIVISOR;
                myBlockSize = HEIGHT / (Tetron.BLOCK_DIVISOR * 2);
                repaint();
            }
            if (theArg instanceof GameState) {
                myGameState = (GameState) theArg;
                repaint();
            }
        }
    }
    /**
     * Populates piece map.
     */
    private void createMap() {
        final Color tronOrange = new Color(223, 116, 12);
        final Color tronPane = new Color(230, 255, 255);
        final Color tronYellow = new Color(225, 230, 77);
        final Color tronCyan = new Color(111, 195, 223);
        
        myPieceMap.put('I', tronOrange);
        myPieceMap.put('J', tronOrange);
        myPieceMap.put('0', tronCyan);
        myPieceMap.put('T', tronCyan);
        myPieceMap.put('Z', tronYellow);
        myPieceMap.put('S', tronYellow);
        myPieceMap.put('L', tronPane);
    }
    
}
