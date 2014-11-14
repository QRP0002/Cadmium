package com.source.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Quinn on 11/13/2014.
 */
public class KeyInput extends KeyAdapter{
    Game game;

    public KeyInput(Game game) {
        this.game = game; //Calls keyboard input from Game class.
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}
