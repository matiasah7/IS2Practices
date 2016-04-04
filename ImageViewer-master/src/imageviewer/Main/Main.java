package ImageViewer.Main;

import ImageViewer.Control.Command;
import ImageViewer.Control.NextImageCommand;
import ImageViewer.Control.PrevImageCommand;
import imageviewer.Control.ActionListenerFactory;
import imageviewer.model.Image;
import imageviewer.persistence.FolderImageLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        new Main().execute();
    }
    private static final String filename = "C:\\Users\\matiasah7\\Documents\\NetBeansProjects\\ImageVisor\\images";
    private Map<String, Command> commandMap;
    private ApplicationFrame frame;

    private void execute() {
        List<Image> images = new FolderImageLoader(filename).load();
        frame = createApplicationFrame();
        frame.getImageViewer().setImage(images.get(0));
        createCommands();
        frame.setVisible(true);

    }

    private ApplicationFrame createApplicationFrame() {
        return new ApplicationFrame(new ActionListenerFactory() {
            @Override
            public ActionListener createActionListener(final String action) {
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Command command = commandMap.get(action);
                        if (command == null)
                            return;
                        command.execute();
                    }
                };
            }
        });
    }

    private void createCommands() {
        commandMap = new HashMap<>();
        commandMap.put("Prev", new PrevImageCommand(frame.getImageViewer()));
        commandMap.put("Next", new NextImageCommand(frame.getImageViewer()));
    }
}
