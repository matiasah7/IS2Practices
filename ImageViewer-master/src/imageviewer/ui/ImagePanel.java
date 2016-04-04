
package imageviewer.ui;

import imageviewer.model.Image;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageViewer {

    private Image image;
    private int initialX;
    private int offset;

    public void show(Image image) {
        this.image = image;
        repaint();
    }

    public ImagePanel() {
        this.offset = 0;
        this.hookEvents();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        if (image == null)
            return;
        super.paint(graphics);
        graphics.drawImage(getBitmap(), offset, 0, null);
        if (offset == 0)
            return;
        if (offset < 0)
            graphics.drawImage(getBufferedImage((SwingBitmap) image.getNext().getBitmap()), image.getBitmap().getWidth() + offset, 0, null);
        if (offset > 0)
            graphics.drawImage(getBufferedImage((SwingBitmap) image.getPrev().getBitmap()), offset - image.getBitmap().getWidth(), 0, null);
    }

    private BufferedImage getBitmap() {
        if (image.getBitmap() instanceof SwingBitmap)
            return getBufferedImage((SwingBitmap) image.getBitmap());
        return null;
    }

    private BufferedImage getBufferedImage(SwingBitmap swingBitmap) {
        return swingBitmap.getBufferedImage();
    }

    private void hookEvents() {
        this.hookMouseListener();
        this.hookMouseMotionListener();
    }

    private void hookMouseListener() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (offset > image.getBitmap().getWidth() / 2)
                    showPrevImage();
                else if (offset < -image.getBitmap().getWidth() / 2)
                    showNextImage();
                offset = 0;
                repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    private void showPrevImage() {
        image = image.getPrev();
    }

    private void showNextImage() {
        image = image.getNext();
    }

    private void hookMouseMotionListener() {
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                offset = e.getX() - initialX;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
}