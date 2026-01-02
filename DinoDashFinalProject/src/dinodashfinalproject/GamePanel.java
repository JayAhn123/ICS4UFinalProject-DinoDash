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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
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
    private final Font headerFont = titleFont.deriveFont((float) 40);
    private final Font infoTextFont = titleFont.deriveFont((float) 18);
    private boolean up_pressed = false;
    private boolean down_pressed = false;
    private boolean left_pressed = false;
    private boolean right_pressed = false;
    private int mouseX;
    private int mouseY;
    Button playBtn;
    Button infoBtn;
    Button shopBtn;
    Button equipSkin1Button;
    Button buySkin2Button;
    Button buySkin3Button;
    Button backButton;
    Button lvl1Button;
    Button lvl2Button;
    Button lvl3Button;
    Button lvl4Button;
    Button lvl5Button;
    Button infiniteModeButton;
    Button returnButton;
    Image titleScreenGround;
    private String gameState = "titleScreen";

    //variables for testing
    ArrayList<Ground> groundLevel1 = new ArrayList();
    ArrayList<GameItem> itemLevel1 = new ArrayList();
    ArrayList<GameItem> tempItemLevel1 = new ArrayList();
    ArrayList<Enemy> enemyLevel1 = new ArrayList();
    Player player = new Player();

    //infinite mode
    ArrayList<Ground> groundLevel6 = new ArrayList();
    ArrayList<GameItem> itemLevel6 = new ArrayList();
    ArrayList<GameItem> tempItemLevel6 = new ArrayList();
    ArrayList<Enemy> enemyLevel6 = new ArrayList();
    ArrayList<Enemy> enemyToRemove = new ArrayList();

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
            g2d.fillRect(100, 100, 100, 150);
            g2d.fillRect(300, 100, 100, 150);
            g2d.fillRect(500, 100, 100, 150);
            //draw the buttons to buy the skins
            equipSkin1Button.draw(g2d, mouseX, mouseY);
            buySkin2Button.draw(g2d, mouseX, mouseY);
            buySkin3Button.draw(g2d, mouseX, mouseY);
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);

        } else if (gameState.equals("infoScreen")) {
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);
            //set font and draw info
            g2d.setColor(Color.black);
            g2d.setFont(headerFont); //show header
            g2d.drawString("Welcome to Dino Dash", 170, 30);
            g2d.setFont(infoTextFont);//switch font and show rest of info
            g2d.drawString("There are two modes: Infinite mode and level mode.", 25, 75);
            g2d.drawString("- In level mode the goal is to reach the end of the level without dying", 35, 95);
            g2d.drawString("- In infinite mode the goal is to defeat as many enemies as you can without dying", 35, 115);
            g2d.drawString("Game Tutorial:", 25, 155);
            g2d.drawString("- A to move left, D to move right and W to jump", 35, 175);
            g2d.drawString("- In the game, press P to pause the game", 35, 195);

            g2d.drawString("There are 3 powerups that can be found in the game which will either:", 25, 235);
            g2d.drawString("- Extra Heart: Gives one more life", 35, 255);
            g2d.drawString("- High Jump: Makes dinosaur jump higher", 35, 275);
            g2d.drawString("- Speed Boost: Makes dinosaur run faster", 35, 295);
            g2d.drawString("Shop: purchase or equip unlocked skins", 25, 335);
            g2d.drawString("- Purchase new skins using coins, they are obtainable through all the modes", 25, 355);
            g2d.drawString("TIP: Infinite mode is more effective when it comes to collecting lots of coins", 25, 375);
        } else if (gameState.equals("levelSelectScreen")) {
            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);
            //draw title
            g2d.setFont(headerFont);
            g2d.setColor(Color.black);
            g2d.drawString("Level Select", 235, 40);
            //draw buttons
            lvl1Button.draw(g2d, mouseX, mouseY);
            lvl2Button.draw(g2d, mouseX, mouseY);
            lvl3Button.draw(g2d, mouseX, mouseY);
            lvl4Button.draw(g2d, mouseX, mouseY);
            lvl5Button.draw(g2d, mouseX, mouseY);
            infiniteModeButton.draw(g2d, mouseX, mouseY);

        } else if (gameState.equals("level1")) {

            playLevel(g2d, groundLevel1, itemLevel1, tempItemLevel1, enemyLevel1);

        } else if (gameState.equals("level2")) {
        } else if (gameState.equals("level3")) {
        } else if (gameState.equals("level4")) {
        } else if (gameState.equals("level5")) {
        } else if (gameState.equals("infiniteMode")) {

            enemyLevel6.removeAll(enemyToRemove);

            if (enemyLevel6.size() < 5) {
                enemyLevel6.add(new Enemy((int) (Math.random() * 950) - 100, 270, -100, 850));
            }

            playLevel(g2d, groundLevel6, itemLevel6, tempItemLevel6, enemyLevel6);

        } else if (gameState.equals("gameOver")) {
            g2d.setColor(Color.black);
            g2d.setFont(titleFont);
            g2d.drawString("You Died", 215, 200);
            returnButton.draw(g2d, mouseX, mouseY);
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
        //add buttons on title screen
        playBtn = new Button(280, 180, 100, 50, "playButton", "playButtonHover");//create buttons for main menu
        infoBtn = new Button(280, 240, 100, 50, "infoButton", "infoButtonHover");
        shopBtn = new Button(280, 300, 100, 50, "shopButton", "shopButtonHover");
        //make buttons for shop
        equipSkin1Button = new Button(100, 260, 100, 50, "equipButton", "equipButtonHover");
        buySkin2Button = new Button(300, 260, 100, 50, "buyButton", "buyButtonHover");
        buySkin3Button = new Button(500, 260, 100, 50, "buyButton", "buyButtonHover");
        //make back button
        backButton = new Button(20, 420, 100, 50, "backButton", "backButtonHover");
        //make buttons for level select
        lvl1Button = new Button(125, 100, 50, 50, "lvl1Button", "lvl1ButtonHover");
        lvl2Button = new Button(225, 100, 50, 50, "lvl2Button", "lvl2ButtonHover");
        lvl3Button = new Button(325, 100, 50, 50, "lvl3Button", "lvl3ButtonHover");
        lvl4Button = new Button(425, 100, 50, 50, "lvl4Button", "lvl4ButtonHover");
        lvl5Button = new Button(525, 100, 50, 50, "lvl5Button", "lvl5ButtonHover");
        infiniteModeButton = new Button(290, 200, 120, 50, "infiniteModeButton", "infiniteModeButtonHover");
        //load image
        titleScreenGround = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/TitleImg.png")).getImage();
        returnButton = new Button(300, 280, 100, 50, "returnButton", "returnButtonHover");
        //add to arraylist of test level
        groundLevel1.add(new Ground(10, 316, 1050));
        itemLevel1.add(new Coin(20, 291));
        itemLevel1.add(new Heart(60, 291));
        itemLevel1.add(new JumpPowerup(100, 291));
        itemLevel1.add(new SpeedPowerup(140, 291));
        enemyLevel1.add(new Enemy(700, 266, 500, 1000));

        groundLevel6.add(new Ground(-100, 320, 1000));
        groundLevel6.add(new Ground(350, 100, 100));
        groundLevel6.add(new Ground(100, 170, 50));
        groundLevel6.add(new Ground(650, 170, 50));
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

    @Override
    public void mouseClicked(MouseEvent e) {//dont need to do anything
    }

    @Override
    public void mousePressed(MouseEvent e) {//dont need to do anything
    }

    /**
     * this method runs when the mouse is released and it handles the clicks
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) { //COMMENTS NOT DONE
        mouseX = e.getX(); //update the location
        mouseY = e.getY();

        //check which was clicked and based off of that do what needs to be done
        if (gameState.equals("titleScreen")) {//check which gamestate it is then check if anybuttons in that gamestate were clicked

            if (infoBtn.wasClicked(mouseX, mouseY)) {
                gameState = "infoScreen";
            } else if (shopBtn.wasClicked(mouseX, mouseY)) {
                gameState = "shopScreen";
            } else if (playBtn.wasClicked(mouseX, mouseY)) {
                gameState = "levelSelectScreen";
            }

        } else if (gameState.equals("infoScreen")) {//do this for all gamestates and buttons

            if (backButton.wasClicked(mouseX, mouseY)) {
                gameState = "titleScreen";
            }

        } else if (gameState.equals("shopScreen")) {

            if (backButton.wasClicked(mouseX, mouseY)) {
                gameState = "titleScreen";
            }

        } else if (gameState.equals("levelSelectScreen")) {

            if (backButton.wasClicked(mouseX, mouseY)) {
                gameState = "titleScreen";
            } else if (infiniteModeButton.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel6, tempItemLevel6, enemyLevel6);
                enemyLevel6.clear();
                gameState = "infiniteMode";
            } else if (lvl1Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel1, tempItemLevel1, enemyLevel1);
                gameState = "level1";
            } else if (lvl2Button.wasClicked(mouseX, mouseY)) {
                gameState = "level2";
            } else if (lvl3Button.wasClicked(mouseX, mouseY)) {
                gameState = "level3";
            } else if (lvl4Button.wasClicked(mouseX, mouseY)) {
                gameState = "level4";
            } else if (lvl5Button.wasClicked(mouseX, mouseY)) {
                gameState = "level5";
            }

        } else if (gameState.equals("gameOver")) {
            if (returnButton.wasClicked(mouseX, mouseY)) {
                gameState = "levelSelectScreen";
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { //dont need to do anything
    }

    @Override
    public void mouseExited(MouseEvent e) { //dont need to do anything
    }

    /**
     * this method runs anytime the user clicks and drags the mouse and it
     * updates the mouse position as when dragging mouseMoved will not update
     * the mouse's position
     *
     * @param e - the mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX(); //update the location of the mouse
        mouseY = e.getY();
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

    public void resetLevel(ArrayList<GameItem> itemLevel, ArrayList<GameItem> tempItemLevel, ArrayList<Enemy> enemyLevel) {
        player.reset();
        tempItemLevel.clear();
        for (GameItem gameItem : itemLevel) {
            gameItem.reset();
        }
        for (Enemy enemy : enemyLevel) {
            enemy.reset();
        }
    }

    public void playLevel(Graphics2D g2d, ArrayList<Ground> groundLevel, ArrayList<GameItem> itemLevel, ArrayList<GameItem> tempItemLevel, ArrayList<Enemy> enemyLevel) {
        //test level
        player.move(up_pressed, down_pressed, left_pressed, right_pressed, groundLevel); //move player
        GameObject.setXOffset(player.getX() - Player.getScreenXPosition());//update offsets after movement
        GameObject.setYOffset(player.getY() - Player.getScreenYPosition());
        player.draw(g2d);//draw player
        player.checkDeath();
        player.drawHearts(g2d);//draw the amount of hearts th eplayer has
        player.drawCoins(g2d, infoTextFont);//draw the amount of couns the player has
        for (Ground groundTile : groundLevel) {//for each ground tile draw it
            groundTile.draw(g2d);
        }
        for (GameItem gameItem : itemLevel) {
            gameItem.draw(g2d);
            gameItem.collisionProcedure(player);
        }
        for (GameItem gameItem : tempItemLevel) {
            gameItem.draw(g2d);
            gameItem.collisionProcedure(player);
        }
        for (Enemy enemy : enemyLevel) {
            enemy.move();
            enemy.draw(g2d);
            enemy.collisionProcedure(player, tempItemLevel, gameState, enemyToRemove);
        }
        if (player.isDead()) {
            gameState = "gameOver";
        }
    }
}
