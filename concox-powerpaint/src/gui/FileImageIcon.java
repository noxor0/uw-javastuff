/*
 * TCSS 305 - PowerPaint - Spring 2016
 */
package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Simple utility class used import and load the images used as icon.
 * 
 * @author noxor
 * @version 1.0
 *
 */
public final class FileImageIcon {
    /**
     * Path name to the Image being used as logo/icon.
     */
    private static final String IMAGE_PATH = "/icons/logo.png";
    /**
     * Creates an icon from a file.
     * 
     * @param thePath the path to the image.
     * @return an icon from a file
     */
    protected Icon createFileIcon(final String thePath) {
        ImageIcon icon = null;
        BufferedImage img = null;
        final InputStream in = getClass().getResourceAsStream(thePath);
        try {
            img = ImageIO.read(in);
        } catch (final IOException e) {
            createErrorDialog();
        }
        if (img != null) {
            icon = new ImageIcon(img);
        }
        return icon;
    }
    /**
     * Creates an icon from a file.
     * If no parameter is passed in, it defaults to the image.
     * @return an icon from a file
     */
    protected Icon createFileIcon() {
        ImageIcon icon = null;
        BufferedImage img = null;
        final InputStream in = getClass().getResourceAsStream(IMAGE_PATH);
        try {
            img = ImageIO.read(in);
        } catch (final IOException e) {
            createErrorDialog();
        }
        if (img != null) {
            icon = new ImageIcon(img);
        }
        return icon;
    }
    /**
     * Creates an image from file.
     * 
     * @param thePath the path to the image.
     * @return the image from the path.
     */
    protected Image createFileImage(final String thePath) {
        BufferedImage img = null;
        final InputStream in = getClass().getResourceAsStream(thePath);
        try {
            img = ImageIO.read(in);
        } catch (final IOException e) {
            createErrorDialog();
        }
        return img;
    }
    /**
     * Creates an image from file.
     * If no parameter is passed in, it defaults to the image.
     * 
     * @return the image from the path.
     */
    protected Image createFileImage() {
        BufferedImage img = null;
        final InputStream in = getClass().getResourceAsStream(IMAGE_PATH);
        try {
            img = ImageIO.read(in);
        } catch (final IOException e) {
            createErrorDialog();
        }
        return img;
    }
    /**
     * Simply creates an error message. This is used when file not found.
     */
    private void createErrorDialog() {
        JOptionPane.showMessageDialog(null, "Could not find icon",
                                      "Icon not found!", JOptionPane.ERROR_MESSAGE);
    }
}
