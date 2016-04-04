package imageviewer.persistence;

import imageviewer.model.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderImageLoader {

    private String path;

    public FolderImageLoader(String path) {
        this.path = path;
    }

    public List<Image> load() {
        return linkImages(loadImages());
    }

    private List<Image> loadImages() {
        List<Image> list = new ArrayList<>();
        for (String file : new File(path).list())
            list.add(new ProxyImage(new ImageLoader(path + "/" + file)));
        return list;
    }

    private List<Image> linkImages(List<Image> images) {
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            image.setNext(images.get((i + 1) % images.size()));
            image.setPrev(images.get((i + images.size() - 1) % images.size()));
        }
        return images;
    }
}
