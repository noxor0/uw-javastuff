/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.Board;

/**
 * Makes the Tetron frame and holds the panels for the game.
 * Contains a key handling class (should i move this?).
 * 
 * @author concox
 * @version 1.0
 *
 */
public final class Tetron extends Observable implements Observer {
    /**
     * The int to divide the current block size.
     */
    public static final int BLOCK_DIVISOR = 10;
    /**
     * Starting board width.
     */
    private static final int BOARD_WIDTH = 10;
    /**
     * Generated ID.
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -1084097475555495304L;
    /**
     * The starting width of the game.
     */
    private static final int START_WIDTH = 630;
    /**
     * The starting height of the game.
     */
    private static final int START_HEIGHT = 655;
    /**
     * Starting timer speed.
     */
    private static final int DEFAULT_SPEED = 800;
    /**
     * The height of the game panel.
     */
    private int myGuiHeight = START_HEIGHT;
    /**
     * The width of the game panel.
     */
    private int myGuiWidth = START_WIDTH;
    /**
     * Holds the entire game.
     */
    private final JFrame myFrame = new JFrame("Tetron");
    /**
     * Creates a panel to render information about the game.
     */
    private final InfoPanel myInfoPanel = new InfoPanel(this);
    /**
     * The game logic.
     */
    private Board myBoard = new Board();
    /**
     * Creates a panel to render the game.
     */
    private final GamePanel myGamePanel = new GamePanel(myBoard);
    /**
     * Timer refresh rate.
     */
    private int myTimerSpeed = DEFAULT_SPEED;
    /**
     * The start new game menu item.
     */
    private final JMenuItem myNewGameItem = new JMenuItem("New Game");
    /**
     * The pause game menu item.
     */
    private final JMenuItem myPauseItem = new JMenuItem("Pause");
    /**
     * The end game menu item.
     */
    private final JMenuItem myEndGameItem = new JMenuItem("End Game");
    /**
     * Creates a timer that determines game speed and refresh rate.
     */
    private final Timer myTimer = new Timer(myTimerSpeed, new UpdateBoard());
    /**
     * The controller that the user plays the game with.
     */
    private final KeyStick myController = new KeyStick();
    /**
     * Current state of the game.
     * You think this is a game?
     */
    private GameState myGameState = GameState.PreGame;
    /**
     * Current level of the game.
     */
    private int myLevel;
    /**
     * Current hot keys.
     */
    private char[] myHotkeys = new char[] {'a', 'e', 'o', KeyEvent.VK_SPACE, '.', '\''};
    /**
     * User selected width of the board.
     */
    private JSlider mySlider;
    /**
     * The start method, initializes the panel.
     * @throws IOException 
     */
    public void start() {
        final MusicBox musicPlayer = new MusicBox(); 
        final JPanel layoutPanel = new JPanel();
        final JPanel gamePanel = new JPanel();
        final int borderThickness = 7;
        final Color clear = new Color(0, true);
        
        layoutPanel.setLayout(new GridLayout(1, 2));
        layoutPanel.setBackground(Color.DARK_GRAY);
        layoutPanel.add(gamePanel);
        layoutPanel.add(myInfoPanel);
        
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setMinimumSize(new Dimension(myGuiWidth / 2, myGuiHeight));
        gamePanel.setBorder(BorderFactory.createLineBorder(clear, borderThickness));
        gamePanel.setBackground(Color.DARK_GRAY);
        gamePanel.add(myGamePanel);
        
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createGameMenu());
        menuBar.add(createOptionsMenu());
        myFrame.setJMenuBar(menuBar);
        myFrame.setResizable(false);
        myFrame.add(layoutPanel);
        myFrame.addKeyListener(myController);
        myFrame.setSize(START_WIDTH, START_HEIGHT);
        myFrame.setMinimumSize(new Dimension(START_WIDTH, START_HEIGHT));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
        addObserver(musicPlayer);
        addObserver(myGamePanel);
        addObserver(myInfoPanel);
        gameInstructWindow();

        myFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent theEvent) {
                myGuiHeight = myFrame.getHeight();
                myGuiWidth = myFrame.getWidth();
                setChanged();
                notifyObservers(new Dimension(myGamePanel.
                                              getWidth(), myGamePanel.getHeight()));
            }
        });
    }
    @Override
    public void update(final Observable theOb, final Object theArg) {
        if (theArg.toString().equals("true") || theArg.toString().equals("false")) {
            final boolean gameOver = Boolean.valueOf(theArg.toString()); 
            if (gameOver) {
                myGameState = GameState.GameOver;
                setChanged();
                notifyObservers(myGameState);
            }
        }
        if (theArg instanceof Integer[]) {
            if (myLevel != myInfoPanel.getLevel()) {
                final int multiplier = 75;
                final int lowestTimerSpeed = 25;
                myLevel = myInfoPanel.getLevel();                    
                myTimerSpeed = DEFAULT_SPEED - (myLevel * multiplier);
                if (myTimerSpeed > lowestTimerSpeed) {
                    myTimer.setDelay(myTimerSpeed); 
                } else {
                    myTimer.setDelay(lowestTimerSpeed);
                }
            }
        }
        //check game state any time something happens
        myNewGameItem.setEnabled(myGameState != GameState.CurrentGame 
                        || myGameState != GameState.Paused);
        myEndGameItem.setEnabled(myGameState == GameState.CurrentGame);
        myPauseItem.setEnabled(myGameState == GameState.CurrentGame);  
        mySlider.setEnabled(myGameState == GameState.PreGame
                        || myGameState == GameState.GameOver);
        
    }
    /**
     * Returns the width of the frame.
     * @return the width
     */
    public int getWidth() {
        return myGuiWidth;
    }
    /**
     * Returns the height of the frame.
     * @return the height
     */
    public int getHeight() {
        return myGuiHeight;
    }
    /**
     * Starts a new game.
     */
    private void newGame() {
        if (mySlider.getValue() != BOARD_WIDTH) {
            myBoard = new Board(mySlider.getValue(), mySlider.getValue() * 2);
            myGamePanel.newBoard(myBoard);
        }
        myBoard.addObserver(myGamePanel);
        myBoard.addObserver(myInfoPanel);
        myBoard.addObserver(this);
        myBoard.addObserver(myController);
        myGameState = GameState.CurrentGame;
        setChanged();
        notifyObservers(myGameState);
        myBoard.newGame();                
        myTimer.setDelay(DEFAULT_SPEED);
        myTimer.start();
    }
    /**
     * Ends the game.
     */
    private void endGame() {
        myGameState = GameState.PreGame;
        myBoard.newGame();
        myTimer.stop();
        setChanged();
        notifyObservers(myGameState);
    }
    /**
     * Displays the introduction / hotkey set up window.
     */
    private void gameInstructWindow() {
        final int returnVal = JOptionPane.showConfirmDialog(myFrame,
                                                            gameInstructString(),
                                                            "Intro to Tetron",
                                                            JOptionPane.YES_NO_OPTION);
        if (returnVal == 0) {
            final String dvorak = "Dvorak";
            final String wasd = "WASD";
            final String[] availBindings = {dvorak, "ArrowKeys coming soon", wasd};
            final String input = (String) 
                            JOptionPane.showInputDialog(null, "Which KeyBinding?",
                                                               "Chose the Keybinding", 
                                                               JOptionPane.QUESTION_MESSAGE, 
                                                               null,
                                                               availBindings,
                                                               availBindings[0]);
            if (dvorak.equals(input)) {
                myHotkeys = new char[] 
                    {'a', 'e', 'o', KeyEvent.VK_SPACE, '.', '\''}; 
            }
            if (wasd.equals(input)) {
                myHotkeys = new char[] 
                    {'a', 's', 'd', KeyEvent.VK_SPACE, 'e', 'q'};                
            }
            JOptionPane.showConfirmDialog(myFrame, listKeys(), "Tetron ready!",
                                                                JOptionPane.PLAIN_MESSAGE);
        }
      
    }
    /**
     * Creates the introduction when the game is first launched.
     * 
     * @return the instructions of the game 'splash'.
     */
    private String gameInstructString() {
        final StringBuilder sb = new StringBuilder(200);
        sb.append("Tetron, a mix between Tron and Tetris \n"
                        + "Lots of features coming soon! \n\n"
                        + "Scoring: 1 point for each line cleared. \n"
                        + "Double score for clearing more line! \n\n");
        sb.append(listKeys());
        sb.append("Would you like to change these?");
        return sb.toString();
    }
    /**
     * @return keys and their functions
     */
    private String listKeys() {
        final StringBuilder sb = new StringBuilder("Here are your current controls: \n");
        final String[] bindings 
        = new String[]{"Left: ", "Right: ", "Down: ", "Drop: ", "CW Rotate: ", "CCW Rotate: "};
        int i = 0;
        for (final char c : myHotkeys) {
            sb.append(bindings[i++]);
            if (c == ' ') {
                sb.append("spacebar");
            } else {
                sb.append(c);                
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    /**
     * Creates the game menu.
     * 
     * @return a completed game menu
     */
    private JMenu createGameMenu() {
        final JMenu gameMenu = new JMenu("Game");
        //new game
        gameMenu.setMnemonic(KeyEvent.VK_G);
        myNewGameItem.setMnemonic(KeyEvent.VK_N);
        myNewGameItem.setAccelerator(KeyStroke.getKeyStroke("control N"));
        myNewGameItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                newGame();
            }
        });
        gameMenu.add(myNewGameItem);
        //pause
        myPauseItem.setEnabled(false);
        myPauseItem.setMnemonic(KeyEvent.VK_ESCAPE);
        myPauseItem.setAccelerator(KeyStroke.getKeyStroke
                                 (KeyEvent.VK_ESCAPE, KeyEvent.VK_CONTROL));
        myPauseItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (myGameState == GameState.CurrentGame) {
                    myGameState = GameState.Paused;
                    myTimer.stop();
                    setChanged();
                    notifyObservers(myGameState);
                    
                } else if (myGameState == GameState.Paused) {
                    myGameState = GameState.CurrentGame;
                    myTimer.start();
                    setChanged();
                    notifyObservers(myGameState);
                }
            }
        });
        gameMenu.add(myPauseItem);
        //end game
        myEndGameItem.setEnabled(false);
        myEndGameItem.setMnemonic(KeyEvent.VK_E);
        myEndGameItem.setAccelerator(KeyStroke.getKeyStroke("control X"));
        myEndGameItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                endGame();
            }
        });
        gameMenu.add(myEndGameItem);
        gameMenu.addSeparator();
        //exit
        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_Q);
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myFrame.dispose();
                myTimer.stop();
            }
        });
        gameMenu.add(exitItem);
        return gameMenu;
    }
    /**
     * Creates the options menu.
     * 
     * @return a completed options menu
     */
    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        final JMenuItem gameInfoItem = new JMenuItem("Game Info...");
        gameInfoItem.setMnemonic(KeyEvent.VK_I);
        gameInfoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                gameInstructWindow();
            }
        });
        optionsMenu.add(gameInfoItem);
        final int maxWidth = 30;
        final int majorSpace = 10;
        final JMenu widthMenu = new JMenu("Board Width");
        widthMenu.add("Change the width of the board");
        widthMenu.add("the height is twice the width");
        widthMenu.add("Cannot change size in-game");
        widthMenu.setEnabled(true);
        mySlider = new JSlider(SwingConstants.HORIZONTAL,
                                               1, maxWidth, 1);
        mySlider.setMinimum(BOARD_WIDTH);
        mySlider.setSnapToTicks(true);
        mySlider.setMajorTickSpacing(majorSpace);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        widthMenu.add(mySlider);
        optionsMenu.add(widthMenu);
        
        final JCheckBoxMenuItem muteItem = new JCheckBoxMenuItem("Mute");
        muteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Boolean isMuted = muteItem.isSelected();
                setChanged();
                notifyObservers(isMuted);
            }
        });
        optionsMenu.add(muteItem);
        final JMenuItem nextSong = new JMenuItem("Next");
        nextSong.setAccelerator(KeyStroke.getKeyStroke("control T"));
        nextSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                setChanged();
                notifyObservers("skip");
            }
        });
        optionsMenu.add(nextSong);
        optionsMenu.addSeparator();
        final JMenuItem about = new JMenuItem("About...");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showConfirmDialog(myFrame, "Tron Tetris - Connor Cox \n"
                                + "TCSS - Spring 2016\n"
                                + "Soundtrack: Tron Legacy OST, 2010\n"
                                + "Walt Disney Records\n"
                                + "I did not create this album,\n"
                                + "I am using it for educational purposes.", 
                                "About this program...", JOptionPane.PLAIN_MESSAGE);
            }   
        });
        optionsMenu.add(about);
        return optionsMenu;
    }
    /**
     * Gets the current game state of the game.
     * 
     * @return the current game state.
     */
    public GameState getGameState() {
        return myGameState;
    }
    /**
     * The timer action that moves the game one step. Updates all the panels. 
     * 
     * @author concox
     * @version 1.0
     *
     */
    private final class UpdateBoard implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            if (myGameState == GameState.CurrentGame) {
                myBoard.step();
                myGamePanel.step();  
                myInfoPanel.step();
            }
            if (myGameState == GameState.GameOver) {
                myTimer.stop();
                final int reply = JOptionPane.showConfirmDialog(myFrame, "You suck! \n "
                                + "do you want to restart?", "Game over"
                                , JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    endGame();
                } else {
                    newGame();
                }
            }
        }
    }
  /**
  * Key Listener for the game. Controls the block.
  * 
  * @author concox
  * @version 1.0
  */
    //yeah, i never got around to changing this
    private final class KeyStick implements KeyListener, Observer {
        @Override
        public void keyPressed(final KeyEvent theArg) {
            final int drop = 3;
            final int rotatecw = 4;
            final int rotateccw = 5;
            if (myGameState == GameState.CurrentGame) {
                if (theArg.getKeyChar() == myHotkeys[0]) {
                    myBoard.left();
                }
                if (theArg.getKeyChar() == myHotkeys[1]) {
                    myBoard.right();
                }
                if (theArg.getKeyChar() == myHotkeys[2]) {
                    myBoard.down();
                }
                if (theArg.getKeyChar() == myHotkeys[drop]) {
                    myBoard.drop();
                }
                if (theArg.getKeyChar() == myHotkeys[rotatecw]) {
                    myBoard.rotateCW();
                }
                if (theArg.getKeyChar() == myHotkeys[rotateccw]) {
                    myBoard.rotateCCW();
                }
            }
        }
        @Override
         public void keyReleased(final KeyEvent arg0) {
             //nothing 
        }
        @Override
         public void keyTyped(final KeyEvent arg0) {
             //Nothing
        }
        @Override
        public void update(final Observable theOb, final Object theArg) {
            if (theArg instanceof String) {
                myGamePanel.step();
            }
        }
    }    
}
