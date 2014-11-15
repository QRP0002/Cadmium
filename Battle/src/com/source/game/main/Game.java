package com.source.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by Quinn on 11/11/2014.
 */
public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 400;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Cadmium";

    public boolean running = false;
    private Thread thread;

    //Buffers the whole window, and access the RGB colors.
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private Player p;
    private Controller c;
    private Textures tex;

    public void init() {
        requestFocus(); //Brings focus to the screen, so no clicking.
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("/Sprite_sheet_first_zelda.png");
            background = loader.loadImage("/test.png");
        }catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));
        tex = new Textures(this);
        p = new Player (200, 200, tex);
        c = new Controller(this, tex);
    }

    //Starts the thread.
    //Will prevent from starting thread up again.
    private synchronized void start(){
        if (running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start(); //Actual start of the thread.
    }

    //Stops the thread.
    private synchronized void stop() {
        if (!running){
            return;
        }
        running = false;
        //Will catch if joining threads together fails.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    //Game Loop, the heart of the game.
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0; //Will be 60 fps.
        double ns = 1000000000 / amountOfTicks;
        double delta = 0; //Time passed, so can catch up.
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() -  timer > 1000){
                timer += 1000; //Keeps from looping through again.
                System.out.println(updates + "Ticks, Fps " + frames);
                updates = 0; //Stops Tick from increasing, and resets.
                frames = 0; //Stops Frames from increasing, and resets.
            }
        }
        stop();
    }

    //Updates the game.
    private void tick(){
        p.tick();
        c.tick();
    }

    //Updates the renders.
    //Handles all the background rendering.
    private void render() {
        BufferStrategy bs = this.getBufferStrategy(); //this.refers to the canvas class.
        if (bs == null) {
            createBufferStrategy(3); //3 buffers created.
            return;
        }
        //Creates graphics context for drawing buffers.
        Graphics g = bs.getDrawGraphics();

        ////////////////////////////////////////////////////////////////////
        /////////////////SAVED FOR GRAPHICS////////////////////////////////
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background,0, 0, null);
        c.render(g);
        p.render(g);
        //////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////

        g.dispose(); //Will keep from looping around and setting bs = null.
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(5);
        }else if (key == KeyEvent.VK_LEFT){
            p.setVelX(-5);
        }else if (key == KeyEvent.VK_DOWN){
            p.setVelY(5);
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(-5);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        }else if (key == KeyEvent.VK_LEFT){
            p.setVelX(0);
        }else if (key == KeyEvent.VK_DOWN){
            p.setVelY(0);
        }else if(key == KeyEvent.VK_UP){
            p.setVelY(0);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows x button to close
        frame.setResizable(false); //Cannot Resize
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
}
