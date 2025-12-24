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
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author arbas9208
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    //global variables
    private Thread animator;
    private final int DELAY = 20;
    private final Color lightBlue = new Color(143, 217, 251);
    private final Color darkGreen = new Color(66, 165, 70);
    private final Font titleFont = loadTitleFont((float) 80);
    private boolean up_pressed = false;
    private boolean down_pressed = false;
    private boolean left_pressed = false;
    private boolean right_pressed = false;
    private boolean mouse_pressed = false;
    private int mouseX;
    private int mouseY;
    Button playBtn;
    Button infoBtn;
    Button shopBtn;
    Button buySkin1Button;
    Button buySkin2Button;
    Button backButton;
    Image titleScreenGround;
    private String gameState = "titleScreen";

    /**
     * This method loads the titleFont from the file and turns it into a font
     *
     * @param size - the size of the font
     * @return - the font we need
     */
    private Font loadTitleFont(float size) {
        //try opening a connection to the file and making the font
        try {
            // This looks for ThaleahFat.ttf 
            //first it makes an inputStream so we can get a connection to the data
            //getClass() makes it so that we are looking in the same spot as the code is running
            //then we get ResourcesAsStream and it looks for that file specifically and opens a connection to it
            InputStream is = getClass().getResourceAsStream("ThaleahFat.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            //then we return the font
            //Font.createFont turns it into a font assuming it is in truetype font format and since it is at 1 point size 
            //we change that with the deriveFont to the size we need which will make a new font based on the old one to the 
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
        
        //draw based of what the gamestate is
        if (gameState.equals("titleScreen")) {
            //set background colour
            this.setBackground(lightBlue);
            //draw Game title
            g2d.setColor(Color.black);
            g2d.setFont(titleFont);
            g2d.drawString("Dino Dash", 180, 120);
            playBtn.draw(g2d, mouseX, mouseY);//draw buttons
            infoBtn.draw(g2d, mouseX, mouseY);
            shopBtn.draw(g2d, mouseX, mouseY);
            //draw ground
            g2d.drawImage(titleScreenGround, 0, 390, null);
        } else if (gameState.equals("shopScreen")) {
            //draw background rectangles for skins
            g2d.setColor(darkGreen);
            g2d.fillRect(180, 100, 100, 150);
            g2d.fillRect(380, 100, 100, 150);
            //draw the buttons to buy the skins
            buySkin1Button.draw(g2d, mouseX, mouseY);
            buySkin2Button.draw(g2d, mouseX, mouseY);
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);

        } else if (gameState.equals("infoScreen")) {
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);
            
        } else if (gameState.equals("levelSelectScreen")) {
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);
            
        }
    }

    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g); //invoke our custom drawing method
    }

    /**
     * this method is called after the JPanel is added to the JFrame we can
     * perform start up tasks here
     */
    @Override
    public void addNotify() {
        super.addNotify();
        //setup thread
        animator = new Thread(this);
        animator.start();
        //setup input listeners
        setFocusable(true); // allow the panel to have focus
        requestFocusInWindow(); //focus to this panel right now
        addKeyListener(this); //add all the listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        playBtn = new Button(280, 180, 100, 50, "playButton", "playButtonHover");//create buttons for main menu
        infoBtn = new Button(280, 240, 100, 50, "infoButton", "infoButtonHover");
        shopBtn = new Button(280, 300, 100, 50, "shopButton", "shopButtonHover");
        //make buttons for shop
        buySkin1Button = new Button(180, 260, 100, 50, "buyButton", "buyButtonHover");
        buySkin2Button = new Button(380, 260, 100, 50, "buyButton", "buyButtonHover");
        //make back button
        backButton = new Button(20, 420, 100, 50, "backButton", "backButtonHover");
        //load image
        titleScreenGround = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/TitleImg.png")).getImage();
    }

    /**
     * this method is called only once, when the Thread starts
     */
    @Override
    public void run() {

        //setup variables for timing
        long beforeTime, timeDiff, sleep;
        //get the current time
        beforeTime = System.currentTimeMillis();

        while (true) { //this loop runs once every 20 ms (the DELAY) should be 50 fps

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

    //all the input listener methods
    @Override
    public void keyTyped(KeyEvent e) {//dont need to do anything
    }

    /**
     * this method is called every time a key is pressed and it checks which one
     * was pressed and updates that key's state
     *
     * @param e - the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') { //depending on key pressed set its pressed state to true
            up_pressed = true;
        } else if (e.getKeyChar() == 'a') {
            left_pressed = true;
        } else if (e.getKeyChar() == 's') {
            down_pressed = true;
        } else if (e.getKeyChar() == 'd') {
            right_pressed = true;
        }
    }

    /**
     * this method is called every time a key is released and it checks which
     * one was released and updates that key's state
     *
     * @param e - the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') { //depending on key pressed set its pressed state to true
            up_pressed = false;
        } else if (e.getKeyChar() == 'a') {
            left_pressed = false;
        } else if (e.getKeyChar() == 's') {
            down_pressed = false;
        } else if (e.getKeyChar() == 'd') {
            right_pressed = false;
        }
    }

    /**
     * this method runs whenever the mouse is clicked and it handled the mouse
     * events
     *
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX(); //update the location
        mouseY = e.getY();

        //check which was clicked and based off of that do what needs to be done
        if (infoBtn.wasClicked(mouseX, mouseY)) {
            gameState = "infoScreen";
        } else if (shopBtn.wasClicked(mouseX, mouseY)) {
            gameState = "shopScreen";
        } else if (playBtn.wasClicked(mouseX, mouseY)) {
            gameState = "levelSelectScreen";
        } else if (backButton.wasClicked(mouseX, mouseY)) {
            gameState = "titleScreen";
        }
    }

    /**
     * this method runs when the mouse is pressed down and it updates the state
     * of mouse_Pressed
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouse_pressed = true; //set it to true
    }

    /**
     * this method runs when the mouse is released and it updates the state of
     * mouse_Pressed
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouse_pressed = false; //set it to false
    }

    @Override
    public void mouseEntered(MouseEvent e) { //dont need to do anything
    }

    @Override
    public void mouseExited(MouseEvent e) { //dont need to do anything
    }

    @Override
    public void mouseDragged(MouseEvent e) { //dont need to do anything
    }

    /**
     * this method runs anytime the mouse is moved and it updates the x and y
     * coordinates of the mouse
     *
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX(); //update the location
        mouseY = e.getY();
    }
}
