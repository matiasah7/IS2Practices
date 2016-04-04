
package imageviewer.Control;

import java.awt.event.ActionListener;

public interface ActionListenerFactory {

    public ActionListener createActionListener(String action);
}