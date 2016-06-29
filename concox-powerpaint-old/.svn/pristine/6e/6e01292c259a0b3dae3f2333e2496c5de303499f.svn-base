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
public class ToolAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = -6510501109460486661L;
    /**
     * 
     */
    private final ToolInterface myTool;
    /**
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
}
