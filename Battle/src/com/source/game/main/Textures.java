package com.source.game.main;


import com.source.game.main.Game;
import com.source.game.main.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/14/2014.
 */
public class Textures {
    public BufferedImage player, enemy;
    private SpriteSheet ss;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        getTextures();
    }

    /**
     * Grabs the images to be rendered
     * Goes: row, col, width, height.
     */
    private void getTextures() {
        player = ss.grabImage(1, 1, 32, 32);
        enemy = ss.grabImage(2, 1, 32, 32);
    }
}
