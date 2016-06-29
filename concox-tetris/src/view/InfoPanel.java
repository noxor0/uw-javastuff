/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Point;
import model.TetrisPiece;

/**
 * Panel that holds the next piece, info about the game and other features.
 * 
 * @author concox
 * @version 1.0
 *
 */
public final class InfoPanel extends JPanel implements Observer { 
    /**
     * Generated ID.
     */
    private static final long serialVersionUID = -2285932017913508795L;
    /**
     * Spacer padding.
     */
    private static final int PADDING = 40;
    /**
     * Smaller spacer padding.
     */
    private static final int SMALL_PADDING = 20;
    /**
     * The amount of lines needed to clear the level.
     */
    private static final int LINES_NEXT = 5;
    /**
     * Name of the game.
     */
    private static final String TETRON = "Tetron";
    /**
     * Size of the block.
     */
    private int myBlockSize;
    /**
     * The next piece to be played.
     */
    private TetrisPiece myNextPiece;
    /**
     * Info panel width.
     */
    private int myIPWidth;
    /**
     * Info panel height.
     */
    private int myIPHeight;
    /**
     * The player's current score.
     */
    private int myScore;
    /**
     * THe panel that displays the next piece.
     */
    private final NextPiecePanel myPiecePanel;
    /**
     * The panel that displays score and title.
     */
    private final TitlePanel myTitle;
    /**
     * The main gui object of the game.
     */
    private final Tetron myGui;
    /**
     * Current Level.
     */
    private int myLevel;
    /**
     * lines cleared till next wave.
     */
    private int myTillNextLevel = LINES_NEXT;
    /**
     * Creates the info panel. Nothing crazy here.
     * 
     * @param theGui the gui.
     */
    public InfoPanel(final Tetron theGui) {
        super();
        myGui = theGui;
        myIPWidth = myGui.getWidth() / 2;
        myIPHeight = myGui.getHeight();
        myPiecePanel = new NextPiecePanel();
        myBlockSize = myIPWidth / Tetron.BLOCK_DIVISOR;
        myTitle = new TitlePanel();
        setupLayout();
    }
    /**
     * Repaint the panel, updates information.
     */
    public void step() {
        repaint();
    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        myPiecePanel.paintComponent(theGraphics);
        myTitle.paintComponent(theGraphics);
        
    }
    @Override
    public void update(final Observable theOb, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myNextPiece = (TetrisPiece) theArg;
        }
        if (theArg instanceof Integer[]) {
            final Integer[] cleared = (Integer[]) theArg;
            myTillNextLevel -= cleared.length;
            if (myTillNextLevel <= 0) {
                myLevel++;
//                    myTillNextLevel = 2 + myLevel * 2;
                myTillNextLevel = LINES_NEXT;
            }
            if (cleared.length > 1) {
                myScore += 2 * cleared.length;
            } else {
                myScore += cleared.length;                    
            }
            repaint();
        }
        if (theOb.getClass().getSimpleName().equals(TETRON)) {
            if (theArg instanceof Dimension) {
                final Dimension newDim = (Dimension) theArg;
                myIPHeight = (int) newDim.getHeight();
                myIPWidth = (int) newDim.getWidth();
                myBlockSize = myIPWidth / 2 / Tetron.BLOCK_DIVISOR;
                repaint();
            }
            if (theArg instanceof GameState) {
                final GameState state = (GameState) theArg;
                if (state == GameState.PreGame) {
                    myLevel = 0;
                    myTillNextLevel = 2;
                    repaint();
                }
            }
        }
    }
    /**
     * Returns the current level of the game.
     * 
     * @return the current level.
     */
    public int getLevel() {
        return myLevel;
    }
    /**
     * Creates the layout of the info panel.
     */
    private void setupLayout() {
        final int borderThickness = 7;
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, borderThickness));
        setSize(myIPWidth, myIPHeight);
        setBackground(Color.DARK_GRAY);
        
        final BoxLayout box = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(box);
        add(myPiecePanel);
        add(Box.createHorizontalStrut(SMALL_PADDING));
        add(myTitle);
        add(Box.createHorizontalStrut(PADDING));
    }
    /**
     * Renders the next piece in a panel. 
     * 
     * @author concox
     * @version 1.0
     *
     */
    private final class NextPiecePanel extends JPanel {
        /**
         * Generated ID.
         */
        private static final long serialVersionUID = 1580209840325939046L;
        /**
         * Holds piece piece name and color.
         */
        private final Map<Character, Color> myPieceMap = new HashMap<>();
        /**
         * Constructor for the next piece panel.
         */
        NextPiecePanel() {
            super();
            final int panelX = myIPWidth;
            final int panelY = myIPHeight / 5;
            final Dimension panelDim = new Dimension(panelX, panelY);
            createMap();
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.GREEN));
            setPreferredSize(panelDim);
        }
        @Override
        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics;
            final int thickness = 4;
            final int panelDivisorThree = 3;
            final int panelDivisorFive = 5;
            if (myGui.getGameState() == GameState.CurrentGame) {
                if (myNextPiece != null) {
                    final char[] pieceChar = myNextPiece.toString().toCharArray();
                    g2d.setStroke(new BasicStroke(thickness));
                    g2d.setColor(myPieceMap.get(pieceChar[0]));
                    final Point[] nextPiece = myNextPiece.getPoints();
                    for (final Point p : nextPiece) {
                        g2d.drawOval(p.x() * myBlockSize + (myIPWidth / panelDivisorThree),
                                    -p.y() * myBlockSize + (myIPHeight / panelDivisorFive),
                                     myBlockSize, myBlockSize);           
                    }            
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
    
    /**
     * Renders the title, score, and keybindings of the game. 
     * 
     * @author concox
     * @version 1.0
     *
     */
    private final class TitlePanel extends JPanel {
        /**
         * Generated ID.
         */
        private static final long serialVersionUID = 1580209840325939046L;
        /**
         * The constructor for the Title panel.
         */
        TitlePanel() {
            super();
            final int panelX = 200;
            final int panelY = 100;
            final Dimension panelDim = new Dimension(panelX, panelY);
            final Color noBg = new Color(0, true);
            setBorder(BorderFactory.createLineBorder(Color.GREEN));
            setBackground(noBg);
            setPreferredSize(panelDim);
            setMinimumSize(panelDim);
        }
        @Override
        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics;
            final int correction = 10;
            final int nextLevelCorrection = 14;
            final int panelDivisorThree = 3;
            final int panelDivisorFour = 4;
            final int panelDivisorFive = 5;
            final int titleSize = myBlockSize;
            final int fontSize = myBlockSize - (myBlockSize / 3);
            
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, titleSize));
            g2d.drawString(TETRON, getWidth() / panelDivisorFour , titleSize);
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
            g2d.drawString("Tron meets Tetris", getWidth() / panelDivisorFive,
                           titleSize + fontSize);
            g2d.drawString("Current Level:  " + myLevel,
                           getWidth() / panelDivisorFive,
                           getHeight() / panelDivisorFour + fontSize + correction);
            g2d.drawString("Lines till next: " + myTillNextLevel,
                           getWidth() / panelDivisorFive,
                           getHeight() / panelDivisorThree + fontSize + nextLevelCorrection);
            g2d.drawString("Score: " + myScore, getWidth() / panelDivisorFour,
                           getHeight() / 2 + fontSize + correction);
        }
    }
}
