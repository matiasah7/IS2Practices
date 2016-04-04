
package imageviewer.model;

public class RealImage extends Image{

    private final Bitmap bitmap;
    private Image prevImage;
    private Image nextImage;

    public RealImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public Image getNext() {
        return nextImage;
    }

    @Override
    public Image getPrev() {
        return prevImage;
    }

    @Override
    public void setNext(Image image) {
        nextImage = image;
    }

    @Override
    public void setPrev(Image image) {
        prevImage = image;
    }

}


