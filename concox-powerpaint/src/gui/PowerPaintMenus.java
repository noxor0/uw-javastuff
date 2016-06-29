/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package gui;

import actions.ToolAction;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.ToolInterface;

/**
 * A utility class that holds all the information about the current PowerPaint window. 
 * Class also creates and populates the menus and the tool bar. 
 * 
 * @author concox
 * @version 1.0
 */
public final class PowerPaintMenus {    
    /**
     * Red component to avoid magic number.
     */
    private static final int UW_RED = 51;
    /**
     * GReen component to avoid magic number.
     */
    private static final int UW_GREEN = 111;
    /**
     * Red component to avoid magic number.
     */
    private static final int UW_RED_GOLD = 232;
    /**
     * Blue component to avoid magic number.
     */
    private static final int UW_BLUE_GOLD = 211;
    /**
     * GReen component to avoid magic number.
     */
    private static final int UW_GREEN_GOLD = 162;
    /**
     * Slider for the thickness.
     */
    private static JSlider mySlider;
    /**
     * Determines if the drawings are going to be filled or not.
     */
    private static boolean myIsFilled;
    /**
     * UW Purple, default draw color.
     */
    private static final Color UW_PURPLE = new Color(UW_RED, 0, UW_GREEN);
    /**
     * UW Gold, default fill color.
     */
    private static final Color UW_GOLD = new Color(UW_RED_GOLD, UW_BLUE_GOLD, UW_GREEN_GOLD);
    /**
     * UW Purple, default draw color.
     */
    private static Color myDrawColor = UW_PURPLE;
    /**
     * UW Gold, default fill color.
     */
    private static Color myFillColor =  UW_GOLD;
    /**
     * The tool that the workspace is using. Pencil is default. 
     */
    private static ToolInterface myTool = new PencilTool();
    /**
     * Holds a list of tool actions. Populates tool menus, 
     * and links buttons with their actions.
     */
    private static List<ToolAction> myListOfTools = new ArrayList<>();
    /**
     * A flag to clear the canvas. The canvas class will clear 
     * and empty the list of drawings when this is true.
     */
    private static boolean myClearMe;
    /**
     * The clear menu item, when clicked will clear the canvas.
     * Needed a larger scope so that methods could change the item's status. 
     */
    private static JMenuItem myClearItem = new JMenuItem("Clear" , KeyEvent.VK_C);
    /**
     * Private construct, should not be called.
     * Utility class.
     */
    private PowerPaintMenus() {
        throw new IllegalStateException();
    }
    /**
     * Holds a list of tool actions. Populates tool menus, 
     * and links buttons with their actions.
     * Additional tools are easily added, simply add a new tool to the List. 
     * Automatically add the tool to the the menus and its actions are set.
     */
    protected static void populateActions() {
        myListOfTools.add(new ToolAction(new PencilTool()));
        myListOfTools.add(new ToolAction(new LineTool()));
        myListOfTools.add(new ToolAction(new EllipseTool()));
        myListOfTools.add(new ToolAction(new RectangleTool()));
        //Add more tools here!
        //Create a new tool interface and add to array list!
    }
    //menu populate
    /**
     * Creates a file menu and its sub menus (if any).
     * 
     * @param theFrame the JFrame of the GUI.
     * @return a completed file menu.
     */
    protected static JMenu createFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        //clear
        myClearItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                toggleClearMe();
            }
        });
        myClearItem.setEnabled(false);
        fileMenu.add(myClearItem);
        //undo
        final JMenuItem undoItem = new JMenuItem("Undo", KeyEvent.VK_U);
        //okay this doesn't work super well - just click after you press it!
        undoItem.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        undoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (!Canvas.isClear()) {
                    Canvas.undoDrawing();
                    if (Canvas.isClear()) {
                        myClearItem.setEnabled(false);                        
                    }
                }
            }
        });
        fileMenu.add(undoItem);
        //quit
        fileMenu.addSeparator();
        final JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispose();
            }
        });
        fileMenu.add(quitItem);
        return fileMenu;
    }
    /**
     * Creates an option menu and its sub menus (if any).
     * Color icons are added here.
     * More notes nested in method for further break down.
     * 
     * @return a completed options menu.
     */
    protected static JMenu createOptionsMenu() {
        final int maxThick = 20;
        final int majorSpace = 5;
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        //thickness : slider
        final JMenu thicknessMenu = new JMenu("Thickness");
        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0, maxThick, 1);
        mySlider.setMajorTickSpacing(majorSpace);
        mySlider.setMinorTickSpacing(1);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        thicknessMenu.add(mySlider);
        optionsMenu.add(thicknessMenu);
        optionsMenu.addSeparator();
        final JColorChooser colorChooser = new JColorChooser();
        //draw color
        final JMenuItem drawColorItem = new JMenuItem("Draw Color...");
        final UtilityIcon drawIcon = new UtilityIcon(myDrawColor);
        drawColorItem.setIcon(drawIcon);
        drawColorItem.setMnemonic(KeyEvent.VK_D);
        drawColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color drawColorChosen = JColorChooser.showDialog
                                (colorChooser, "Choose Draw Color", Color.BLACK);
                if (drawColorChosen != null) {
                    myDrawColor = drawColorChosen;
                    drawIcon.setColor(myDrawColor);
                }
            }
        });
        optionsMenu.add(drawColorItem);
        //fill color
        final JMenuItem fillColorItem = new JMenuItem("Fill Color...");
        final UtilityIcon fillIcon = new UtilityIcon(myFillColor); 
        fillColorItem.setIcon(fillIcon);
        fillColorItem.setMnemonic(KeyEvent.VK_F);
        fillColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color fillColorChosen = JColorChooser.showDialog
                                (colorChooser, "Choose Fill Color", Color.WHITE);
                if (fillColorChosen != null) {
                    myFillColor = fillColorChosen;
                    fillIcon.setColor(myFillColor);
                }
            }
        });
        optionsMenu.add(fillColorItem);
        optionsMenu.addSeparator();
        //fill checkbox
        final JCheckBoxMenuItem fillCheck = new JCheckBoxMenuItem("Fill");
        fillCheck.setAccelerator(KeyStroke.getKeyStroke("control F"));
        fillCheck.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myIsFilled = fillCheck.getState();
            }
        });
        optionsMenu.add(fillCheck);
        return optionsMenu;
    }
    /**
     * Creates a tool menu and its sub menus (if any).
     * Uses the list of tool actions to fill the radio group.
     * 
     * @return a completed tools menu.
     */
    protected static JMenu createToolsMenu() {
        final JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        final ButtonGroup toolGroup = new ButtonGroup();
        JRadioButtonMenuItem radioButton;
        for (final ToolAction t : myListOfTools) {
            radioButton = new JRadioButtonMenuItem(t);
            toolsMenu.add(radioButton);
            toolGroup.add(radioButton);
        }
        return toolsMenu;
    }
    /**
     * Creates a help menu and its sub menus (if any).
     * Holds notes from the programmer.
     * 
     * @return a completed help menu.
     */
    protected static JMenu createHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "TCSS 305 \n Spring 2016 \n Connor Cox",
                                              "About the Program", 
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new FileImageIcon().createFileIcon());
            }
        });
        helpMenu.add(aboutItem);
        return helpMenu;
    }
    /**
     * Creates the move-able tool bar using the list of Tool Actions.
     * 
     * @return a completed tool bar.
     */
    protected static JToolBar createToolBar() {
        final JToolBar toolBar = new JToolBar();
        JToggleButton tb;
        final ButtonGroup toolGroup = new ButtonGroup();
        for (final ToolAction t : myListOfTools) {
            tb = new JToggleButton(t);
            if (t.getImagePath() != null) {
                tb.setIcon(new FileImageIcon().createFileIcon
                           (t.getImagePath()));                
            } 
            toolBar.add(tb);
            toolGroup.add(tb);
        }
        return toolBar;
    }
    //mutators
    /**
     * Switches the current status of the myClearMe.
     * Used to notify the canvas to clear its Drawing ArrayList.
     * 
     */
    public static void toggleClearMe() {
        if (myClearMe) {
            myClearMe = false;
        } else {
            myClearMe = true;            
        }
    }
    /**
     * Sets the current tool to the parameter.
     * 
     * @param theTool the tool to be set as current.
     */
    public static void setTool(final ToolInterface theTool) {
        myTool = theTool;
    }
    /**
     * Enables the clear button when there are drawings in the array.
     */
    public static void enableClearItem() {
        myClearItem.setEnabled(true);            
    }
    /**
     * Disables the clear button when there are drawings in the array.
     */
    public static void disableClearItem() {
        myClearItem.setEnabled(false);            
    }
    //queries
    /**
     * Determines if program fills the objects drawn or not.
     * 
     * @return if the filled check box is checked
     */
    public static boolean isFilled() {
        return myIsFilled;
    }
    /**
     * Determines if the canvas needs to be cleared or not.
     * 
     * @return returns the clear me boolean status
     */
    public static boolean doClearMe() {
        return myClearMe;
    }
    /**
     * Gets the current value of the thickness value from the slider.
     * 
     * @return Slider number value for thickness
     */
    public static int getThickness() {
        return mySlider.getValue();
    }
    /**
     * Returns the current fill color for the drawing.
     * 
     * @return fill color for the program.
     */
    public static Color getFillColor() {
        return myFillColor;
    }
    /**
     * Returns the current draw color for the drawing.
     * 
     * @return Draw color for the program.
     */
    public static Color getDrawColor() {
        return myDrawColor;
    }
    /**
     * Returns the current tool in use.
     * 
     * @return the tool the tool that is currently used.
     */
    public static ToolInterface getTool() {
        return myTool;
    }
}
