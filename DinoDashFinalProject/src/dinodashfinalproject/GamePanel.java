/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

//import Packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Rectangle;

/**
 *
 * @author arbas9208
 */
public class GamePanel extends JPanel implements Runnable {

    //global variables
    private Thread animator;
    private final int DELAY = 20;
    private Color lightBlue = new Color(143, 217, 251);
    private Font titleFont = loadTitleFont((float)80);
    
    /**
     * This method loads the titleFont from the file and turns it into a font
     * @param size - the size of the font
     * @return - the font we need
     */
    private Font loadTitleFont(float size) {
        //try opening a connection to the file and making the font
        try {
            // This looks for ThaleahFat.ttf 
            //first it makes an inputStream so we can get a connection to the data
            //getClass() makes it so that we are looking in the same spot as the code is running
            //then we get ResourcesAsStream and it looks for that fiel specifically and opens a connection to it
            InputStream is = getClass().getResourceAsStream("ThaleahFat.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            //then we return the font
            //Font.createFont turns it into a font assuming it is in truetype font format and since it is at 1 point size 
            //we change that with the deriveFont to the size we need which will make a new fotn based on the old one to the 
            //size to the value we enter (needs to be a float to refer to size)
        } catch (FontFormatException | IOException | NullPointerException e) { //catch errors
            return new Font("Arial", Font.PLAIN, (int) size); // Fallback if file is missing or cant be read
        }
    }

    /**
     * Does the actual drawing
     *
     * @param g - the Graphics object to draw with
     */
    private void doDrawing(Graphics g) {

        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        //set background colour
        this.setBackground(lightBlue);
        //draw Game title
        g2d.setColor(Color.black);
        g2d.setFont(titleFont);
        g2d.drawString("Dino Dash", 180, 120);
        //make a new rectanlge for the quit button and then draw it
        Rectangle QuitBtn = new Rectangle(100,100,100,100);
        g2d.setColor(Color.white);
        g2d.fill(QuitBtn);

    }

    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g); //invoke our custom drawing method
    }

    /**
     * this method is called after the JPanel is added to the JFrame we can
     * perform start up tasks here
     *
     * @Override
     */
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    /**
     * this method is called only once, when the Thread starts
     *
     * @Override
     */
    public void run() {

        //setup variables for timing
        long beforeTime, timeDiff, sleep;
        //get the current time
        beforeTime = System.currentTimeMillis();

        while (true) { //this loop runs once every 20 ms (the DELAY) shloud be 50 fps

            //redraws the screen (calling the paint component method)
            repaint();

            //calculate how much time has passed since the last call
            //this allows smooth updates and our ball will move at a constant speed (as opposed to being dependent on processor availability)
            timeDiff = System.currentTimeMillis() - beforeTime;

            //calculate how much time to wait before the next call
            sleep = DELAY - timeDiff;

            //always wait at least 2 ms
            if (sleep < 0) {
                sleep = 2;
            }

            //try to actually wait
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                //threads can be interrupted from other threads
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //get the new current time
            beforeTime = System.currentTimeMillis();
        }
    }
}
