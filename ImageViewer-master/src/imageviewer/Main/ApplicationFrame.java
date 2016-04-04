package ImageViewer.Main;

import imageviewer.Control.ActionListenerFactory;
import imageviewer.ui.ImagePanel;
import imageviewer.ui.ImageViewer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame {

    private ActionListenerFactory factory;
    private int index = 0;
    private ImageViewer imageViewer;

    public ImageViewer getImageViewer() {
        return imageViewer;
    }

    public ApplicationFrame(ActionListenerFactory factory) throws HeadlessException {
        super("Image Browser");
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 768);
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((pantallaTamano.width / 2) - (this.getWidth() / 2), (pantallaTamano.height / 2) - (this.getHeight() / 2));
        this.createComponents();
    }

    private void createComponents() {
        this.add(createToolbarPanel(), BorderLayout.SOUTH);
        this.add(createImagePanel());
    }

    private JButton createPrevButton() {
        JButton button = new JButton("Anterior");
        button.addActionListener(factory.createActionListener("Prev"));
        return button;
    }

    private JButton createNextButton() {
        JButton button = new JButton("Siguiente");
        button.addActionListener(factory.createActionListener("Next"));
        return button;
    }

    private JPanel createImagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imageViewer = panel;
        return panel;
    }

    private JPanel createToolbarPanel() {
        JPanel panel = new JPanel();
        panel.add(createPrevButton());
        panel.add(createNextButton());
        return panel;
    }
}
