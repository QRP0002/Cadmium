package com.source.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/12/2014.
 */
public class Player {
    private double x;
    private double y;
    private BufferedImage player;
    private double velX = 0;
    private double velY = 0;

    public Player (double x, double y, Game game){
        this.x = x;
        this.y = y;
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        player = ss.grabImage(1, 1, 32, 32);
    }

    /**
     * Used to update character movement.
     */
    public void tick() {
        x += velX;
        y += velY;

        // Used to get boarder detection.
        if (x <= -8) { //Left edge.
            x = -8;
        }
        if (x >= 787) { //Right edge.
            x = 787;
        }
        if (y <= -4) { //Top edge.
            y = -4;
        }
        if (y >= 572) { //Bottom edge.
            y = 572;
        }
    }

    /**
     * Used to render the player image.
     */
    public void render(Graphics g) {
        g.drawImage(player, (int)x, (int)y, null);
    }

    /**
     * Used to get the "x" coordinate from keyboard input.
     * @return x value for Game class.
     */
    public double getX() {
        return x;
    }

    /**
     * Used to get the "y" coordinate from keyboard input.
     * @return y value for Game class.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the "x" value for Game class to call.
     * @param x for getX().
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Sets the "y" value for Game class to call.
     * @param y for getY().
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the Velocity movement of x for smooth key input.
     * @param velX takes in velX to adjust.
     */
    public void setVelX (double velX) {
        this.velX = velX;
    }

    /**
     * Sets the Velocity movement of y for smooth key input.
     * @param velY takes in velY to adjust.
     */
    public void setVelY (double velY) {
        this.velY = velY;
    }
}
