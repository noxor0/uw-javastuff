/*
 * TCSS 305 - Tetris
 * Spring 2016
 */
package view;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays and controls the sound for the game.
 * 
 * @author concox
 * @version 1.0
 */
public final class MusicBox implements Observer {
    /**
     * Appends a .wav at the end of the path name.
     */
    private static final String WAV_SUFFIX = ".wav";
    /**
     * Gets the absolute path of your directory. Could not get relative paths working.
     */
    private static final String ABSOLUTE_PREFIX = "audio/";
    /**
     * the Intro songs.
     */
    private static final String[] INTRO = {"pre/1", "pre/2", "pre/3"};
    /**
     * The in game songs.
     */
    private static final String[] GAME = {"game/1", "game/2", "game/3", "game/4",
        "game/5", "game/6", "game/7", "game/8", "game/9", "game/10" };
    /**
     * The paused songs.
     */
    private static final String[] PAUSED = {"pause/1", "pause/2", "pause/3"};
    /**
     * The game over songs.
     */
    private static final String[] OVER = {"over/1", "over/2"};
    /**
     * A random number generator to shuffle music.
     */
    private final Random myRand = new Random();
    /**
     * If the music player is enabled or not.
     */
    private boolean myIsMuted;
    /**
     * If the audio is going to loop or not.
     */
    private boolean myIsLoop;
    /**
     * The audio input stream.
     */
    private AudioInputStream myStream;
    /**
     * The music clip being played.
     */
    private Clip myClip;
    /**
     * The current file that is being played, will continue when unmuted.
     */
    private File myCurrent;
    /**
     * Holds the current state of the game, music changes depending on 
     * which game state the user is in.
     */
    private GameState myCurrentState;
    /**
     * Constructor for music player object.
     */
    public MusicBox() {        
        
        playIntro();
    }
    /**
     * plays the selected file.
     * 
     * @param theFile the file to be played
     */
    private void play(final File theFile) {
        myCurrent = theFile;
        if (!myIsMuted) {
            try {
                myStream = AudioSystem.getAudioInputStream(theFile);
                myClip = AudioSystem.getClip();
                myClip.open(myStream);
                if (myIsLoop) {
                    myClip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                myClip.start();
            } catch (final LineUnavailableException e) {
                e.printStackTrace();
            } catch (final UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(final Observable theOb, final Object theArg) {
        if (theArg instanceof Boolean) {
            myIsMuted = (Boolean) theArg;
            toggleMute();
        }
        if (theArg instanceof GameState) {
            myCurrentState = (GameState) theArg;
            nextSong();
        }
        if (theArg instanceof String) {
            final String skip = (String) theArg;
            if ("skip".equals(skip)) {
                nextSong();
            }
        }
    }
    /**
     * Plays the another song contained in the array.
     */
    private void nextSong() {
        switch (myCurrentState) {
            case Paused: playPaused();
            break;
            case CurrentGame: playGame();
            break;
            case GameOver: playOver();
            break;
            case PreGame: playIntro();
            break;
            default: break;
        }
    }
    /**
     * Mutes the music.
     */
    private void toggleMute() {
        if (myIsMuted) {
            myClip.stop();
        } else {
            play(myCurrent);
        }
    }
    /**
     * Plays the introduction music, before game has begun.
     */
    private void playIntro() {
        if (myClip != null) {
            myClip.stop();
        }
        myIsLoop = true;
        
        myCurrent = new File(ABSOLUTE_PREFIX
                             + INTRO[myRand.nextInt(INTRO.length)] + WAV_SUFFIX);
        
        play(myCurrent);
    }
    /**
     * Plays the game over music, when game over window is present.
     */
    private void playOver() {
        myClip.stop();
        myIsLoop = true;
        myCurrent = new File(ABSOLUTE_PREFIX
                             + OVER[myRand.nextInt(OVER.length)] + WAV_SUFFIX);
        play(myCurrent);
    }
    /**
     * Plays the in-game music. 
     */
    private void playGame() {
        myClip.stop();
        myIsLoop = true;
        myCurrent = new File(ABSOLUTE_PREFIX
                             + GAME[myRand.nextInt(GAME.length)] + WAV_SUFFIX);
        play(myCurrent);
    }
    /**
     * Plays the paused music.
     */
    private void playPaused() {
        myClip.stop();
        myIsLoop = true;
        myCurrent = new File(ABSOLUTE_PREFIX
                             + PAUSED[myRand.nextInt(PAUSED.length)] + WAV_SUFFIX);
        play(myCurrent);
    }
}
