package com.source.game.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loads in the buffered Images.
 * Created by Quinn on 11/11/2014.
 */
public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
