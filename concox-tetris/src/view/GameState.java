/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

/**
 * The current state of the game.
 * 
 * @author concox
 * @version 1.0
 */
public enum GameState {
    /**
     * Before the game has been started by the user.
     * Timer is stopped, and game is not playing. 
     */
    PreGame,
    /**
     * Game is currently being played.
     */
    CurrentGame,
    /**
     * Game is paused.
     */
    Paused,
    /**
     * Game is over.
     */
    GameOver;
}
