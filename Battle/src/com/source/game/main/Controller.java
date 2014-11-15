package com.source.game.main;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Quinn on 11/14/2014.
 */
public class Controller {

    Game game;
    Enemy e;
    Textures tex;

    public Controller(Game game, Textures tex ) {
        this.game = game;
        this.tex = tex;
        e = new Enemy(100, 100, tex);
    }

    public void tick() {
        e.tick();
    }

    public void render(Graphics g) {
        e.render(g);
    }
}
