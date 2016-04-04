package imageviewer.persistence;

import imageviewer.model.Bitmap;
import imageviewer.model.Image;
import imageviewer.model.RealImage;
import imageviewer.ui.SwingBitmap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    String filename;

    public ImageLoader(String filename) {
        this.filename = filename;
    }

    public Image load(){
        return new RealImage(loadBitmap());
    }

    private Bitmap loadBitmap() {
        return new SwingBitmap(loadBufferedImage());
    }

    private BufferedImage loadBufferedImage() {
        try {
            return ImageIO.read(new File(filename));
        }
        catch (IOException ex) {
            return null;
        }
    }
}
