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

import tools.Canvas;
import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.ToolInterface;

/**
 * @author concox
 * @version 1.0
 */
public final class PowerPaintMenus {    
    /**
     * Slider for the thickness.
     */
    private JSlider mySlider;
    /**
     * 
     */
    private boolean myIsFilled;
    /**
     * Red component to avoid magic number.
     */
    private final int UW_RED = 51;
    /**
     * GReen component to avoid magic number.
     */
    private final int UW_GREEN = 111;
    /**
     * 
     */
    private Color myDrawColor = new Color(UW_RED, 0, UW_GREEN);
    /**
     * 
     */
    private Color myFillColor =  Color.WHITE;
    /**
     * 
     */
    private ToolInterface myTool = new PencilTool();
    /**
     * 
     */
    private List<ToolAction> myListOfTools = new ArrayList<>();
    /**
     * 
     */
    private Canvas myCanvas;
    /**
     * Private construct, should not be called.
     * @param theCanvas 
     */
    public PowerPaintMenus(final Canvas theCanvas) {
        myCanvas = theCanvas;
    }
    /**
     * 
     */
    protected void populateActions() {
        myListOfTools.add(new ToolAction(new PencilTool()));
        myListOfTools.add(new ToolAction(new LineTool()));
        myListOfTools.add(new ToolAction(new EllipseTool()));
        myListOfTools.add(new ToolAction(new RectangleTool()));
    }
    /**
     * 
     * @param theFrame the JFrame of the GUI.
     * @return a completed file menu.
     */
    public JMenu createFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        //clear
        final JMenuItem clearItem = new JMenuItem("Clear" , KeyEvent.VK_C);
        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myCanvas.clearDrawings();
                myCanvas.repaint();
            }
        });
        fileMenu.add(clearItem);
        //quit
        fileMenu.addSeparator();
        final JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispose();
            }
        });
        fileMenu.add(quitItem);
        return fileMenu;
    }
    /**
     * @return a completed options menu.
     * 
     */
    public JMenu createOptionsMenu() {
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
        final JMenuItem drawColorItem = new JMenuItem("Draw Color" /*icon here*/);
        drawColorItem.setMnemonic(KeyEvent.VK_D);
        drawColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawColor = JColorChooser.showDialog
                                (colorChooser, "Choose Draw Color", Color.BLACK);
            }
        });
        optionsMenu.add(drawColorItem);
        //fill color
        final JMenuItem fillColorItem = new JMenuItem("Fill Color" /*icon here*/);
        fillColorItem.setMnemonic(KeyEvent.VK_F);
        fillColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myFillColor = JColorChooser.showDialog
                                (colorChooser, "Choose Fill Color", Color.WHITE);
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
     * 
     * @return a completed tools menu.
     */
    public JMenu createToolsMenu() {
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
     * 
     * @return a completed help menu.
     */
    public JMenu createHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem aboutItem = new JMenuItem("About" /*icon*/);
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "TCSS 305 \n Spring 2016 \n Connor Cox",
                                              "About the Program", 
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);
        return helpMenu;
        
    }
    /**
     * @return a completed tool bar.
     */
    public JToolBar createToolBar() {
        final JToolBar toolBar = new JToolBar();
        JToggleButton tb;
        final ButtonGroup toolGroup = new ButtonGroup();
        for (final ToolAction t : myListOfTools) {
            tb = new JToggleButton(t);
            toolBar.add(tb);
            toolGroup.add(tb);
        }
        
        return toolBar;
    }
    /**
     * @return Slider number value for thickness
     */
    public int getThickness() {
        return mySlider.getValue();
    }
    /**
     * Determines if program fills the objects drawn or not.
     * 
     * @return if the filled check box is checked
     */
    public boolean isFilled() {
        return myIsFilled;
    }
    /**
     * @return fill color for the program.
     */
    public Color getFillColor() {
        return myFillColor;
    }
    /**
     * @return Draw color for the program.
     */
    public Color getDrawColor() {
        return myDrawColor;
    }
    /**
     * @return the tool.
     */
    public ToolInterface getTool() {
        return myTool;
    }
    /**
     * @param theTool the tool.
     */
    public void setTool(final ToolInterface theTool) {
        myTool = theTool;
        
    }
}
