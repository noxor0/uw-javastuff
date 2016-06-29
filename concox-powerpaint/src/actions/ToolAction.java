/* 
 * TCSS 305 - Assignment 4: PowerPaint
 */
package actions;

import gui.PowerPaintMenus;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import tools.ToolInterface;

/**
 * Actions for tools.
 * 
 * @author concox
 * @version 1.0
 *
 */
public final class ToolAction extends AbstractAction {
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = -6510501109460486661L;
    /**
     * The tool being assigned to the action.
     */
    private final ToolInterface myTool;
    /**
     * Creates the ToolAction. Adds the name to the menus,
     * sets the Mnemonic to the first letter of the tool.  
     * 
     * actionPerformed() sets the tool, and shows it as selected.
     * 
     * @param theTool tool passed in.
     */
    public ToolAction(final ToolInterface theTool) {
        super(theTool.getName());
        putValue(Action.MNEMONIC_KEY, KeyEvent.
                 getExtendedKeyCodeForChar(theTool.getName().codePointAt(0)));
        myTool = theTool;
    }
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        putValue(Action.SELECTED_KEY, true);
        PowerPaintMenus.setTool(myTool);
    }
    /**
     * Gets the path to the tool's icon.
     * 
     * @return the tool's path to the icon
     */
    public String getImagePath() {
        return myTool.getImagePath();
    }
}
