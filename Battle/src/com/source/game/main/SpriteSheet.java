package com.source.game.main;

import java.awt.image.BufferedImage;


/**
 * Created by Quinn on 11/11/2014.
 */
public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    /**
     * Crops out the actual image.
     */
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return img;
    }
}


