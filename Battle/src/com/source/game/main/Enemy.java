package com.source.game.main;

import java.awt.*;

/**
 * Created by Quinn on 11/14/2014.
 */
public class Enemy {
    private double x;
    private  double y;
    private Textures tex;

    public Enemy(double x, double y, Textures tex) {
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    /**
     * Updates the enemy movement.
     */
    public void tick() {
        y += 1;
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy, (int)x, (int)y, null);
    }
}
