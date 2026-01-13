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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    private final Color brown = new Color(175, 87, 14);
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
    Button equipSkin2Button;
    Button equipSkin3Button;
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
    Button quitButton;
    Button continueButton;
    Button leaderboardButton;
    Button searchButton;
    Button creditsButton;
    Image titleScreenGround;
    private String gameState = "titleScreen";
    boolean pause;
    String name = "";
    int score;
    static Clip clip;//audio clip setup for background music

    //variables for testing
    ArrayList<Ground> groundLevel1 = new ArrayList();
    ArrayList<GameItem> itemLevel1 = new ArrayList();
    ArrayList<GameItem> tempItemLevel1 = new ArrayList();
    ArrayList<Enemy> enemyLevel1 = new ArrayList();
    Player player = new Player();

    ArrayList<Ground> groundLevel2 = new ArrayList();
    ArrayList<GameItem> itemLevel2 = new ArrayList();
    ArrayList<GameItem> tempItemLevel2 = new ArrayList();
    ArrayList<Enemy> enemyLevel2 = new ArrayList();

    ArrayList<Ground> groundLevel3 = new ArrayList();
    ArrayList<GameItem> itemLevel3 = new ArrayList();
    ArrayList<GameItem> tempItemLevel3 = new ArrayList();
    ArrayList<Enemy> enemyLevel3 = new ArrayList();

    ArrayList<Ground> groundLevel4 = new ArrayList();
    ArrayList<GameItem> itemLevel4 = new ArrayList();
    ArrayList<GameItem> tempItemLevel4 = new ArrayList();
    ArrayList<Enemy> enemyLevel4 = new ArrayList();

    ArrayList<Ground> groundLevel5 = new ArrayList();
    ArrayList<GameItem> itemLevel5 = new ArrayList();
    ArrayList<GameItem> tempItemLevel5 = new ArrayList();
    ArrayList<Enemy> enemyLevel5 = new ArrayList();

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
            g2d.setColor(darkGreen);//sets color to dark green
            //draws a box that shows the amount of coin user has
            g2d.fillRect(435, 421, 165, 50);
            //draw background rectangles for skins
            if (player.equippedSkin == 1) {
                g2d.fillRect(100, 100, 100, 150);

            }
            if (player.equippedSkin == 2) {
                g2d.fillRect(300, 100, 100, 150);

            }
            if (player.equippedSkin == 3) {
                g2d.fillRect(500, 100, 100, 150);

            }
            //draws String that indicates user's coin
            g2d.setFont(headerFont);//sets font
            g2d.setColor(Color.black);//sets color
            g2d.drawRect(434, 420, 165, 50);//draws border for the coin box
            g2d.drawString("Coin: " + player.getCoins(), 450, 456);//draws amount of user's coins
            //draw the buttons to buy the skins
            equipSkin1Button.draw(g2d, mouseX, mouseY);
            if (player.skin2Bought) {
                equipSkin2Button.draw(g2d, mouseX, mouseY);
            } else {
                buySkin2Button.draw(g2d, mouseX, mouseY);
            }
            if (player.skin3Bought) {
                equipSkin3Button.draw(g2d, mouseX, mouseY);
            } else {
                buySkin3Button.draw(g2d, mouseX, mouseY);
            }

            //draw the back button
            backButton.draw(g2d, mouseX, mouseY);

            //draw the skinds
            g2d.drawImage(player.skin1rightStill1, 118, 145, null);
            g2d.drawImage(player.skin2rightStill1, 318, 145, null);
            g2d.drawImage(player.skin3rightStill1, 518, 145, null);

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
            g2d.drawString("- A to move left, D to move right, W to jump and P to pause the game", 35, 175);
            g2d.drawString("- To eliminate enemies, jump on them twice, or once with lucky jump", 35, 195);
            g2d.drawString("There are 3 powerups that can be found in the game which will either:", 25, 235);
            g2d.drawString("- Extra Heart: Gives one more life (Max Heart is 5)", 35, 255);
            g2d.drawString("- High Jump: Makes dinosaur jump higher", 35, 275);
            g2d.drawString("- Speed Boost: Makes dinosaur run faster", 35, 295);
            g2d.drawString("Shop: purchase or equip unlocked skins", 25, 335);
            g2d.drawString("- Purchase new skins using coins, they are obtainable through all the modes", 25, 355);
            g2d.drawString("TIP: Infinite mode is more effective when it comes to collecting lots of coins", 25, 375);
            creditsButton.draw(g2d, mouseX, mouseY);
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
            leaderboardButton.draw(g2d, mouseX, mouseY);

        } else if (gameState.equals("level1")) {

            playLevel(g2d, groundLevel1, itemLevel1, tempItemLevel1, enemyLevel1);

        } else if (gameState.equals("level2")) {
            playLevel(g2d, groundLevel2, itemLevel2, tempItemLevel2, enemyLevel2);
        } else if (gameState.equals("level3")) {
            playLevel(g2d, groundLevel3, itemLevel3, tempItemLevel3, enemyLevel3);
        } else if (gameState.equals("level4")) {
            playLevel(g2d, groundLevel4, itemLevel4, tempItemLevel4, enemyLevel4);
        } else if (gameState.equals("level5")) {
            playLevel(g2d, groundLevel5, itemLevel5, tempItemLevel5, enemyLevel5);
        } else if (gameState.equals("infiniteMode")) {

            enemyLevel6.removeAll(enemyToRemove);

            if (enemyLevel6.size() < 5) {
                enemyLevel6.add(new Enemy((int) (Math.random() * 950) - 100, 475, -100, 850));
                score++;
            }
            g2d.setFont(headerFont);//sets font to header font
            g2d.drawString("Score: " + (score - 5), 280, 35);//draws user's current score
            playLevel(g2d, groundLevel6, itemLevel6, tempItemLevel6, enemyLevel6);

        } else if (gameState.equals("gameOver")) {
            g2d.setColor(Color.black);
            g2d.setFont(titleFont);
            g2d.drawString("You Died", 215, 200);
            returnButton.draw(g2d, mouseX, mouseY);
        } else if (gameState.equals("win")) {
            g2d.setColor(Color.black);
            g2d.setFont(titleFont);
            g2d.drawString("You Won", 215, 200);
            continueButton.draw(g2d, mouseX, mouseY);
        } else if (gameState.equals("leaderboard")) {
            backButton.draw(g2d, mouseX, mouseY);//draws the back button for leaderboard
            searchButton.draw(g2d, mouseX, mouseY);//draws the search button for leaderboard
            ArrayList<Integer> scores = new ArrayList();
            ArrayList<String> names = new ArrayList();
            getScores(scores, names);
            sortScores(scores, names);
            g2d.setColor(Color.black);
            g2d.setFont(titleFont);
            g2d.drawString("Leaderboard", 120, 50);
            g2d.setFont(headerFont);
            g2d.drawString("Name", 140, 90);
            g2d.drawString("Score", 430, 90);
            g2d.setFont(infoTextFont);
            for (int i = 0; i < 10; i++) {
                g2d.drawString((i + 1) + ". " + names.get(i), 140, i * 30 + 120);
                g2d.drawString(scores.get(i) + "", 430, i * 30 + 120);
            }
        } else if (gameState.equals("credits")) {
            backButton.draw(g2d, mouseX, mouseY);
            g2d.setFont(titleFont);
            g2d.setColor(Color.black);
            g2d.drawString("Credits", 210, 70);
            g2d.setFont(headerFont);
            g2d.drawString("Araib - lead programmmer", 150, 150);
            g2d.drawString("Bernie - Level Design", 150, 200);
            g2d.drawString("Jay - project manager", 150, 250);
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
        equipSkin2Button = new Button(300, 260, 100, 50, "equipButton", "equipButtonHover");
        equipSkin3Button = new Button(500, 260, 100, 50, "equipButton", "equipButtonHover");
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
        continueButton = new Button(300, 280, 100, 50, "continueButton", "continueButtonHover");
        quitButton = new Button(300, 400, 100, 50, "quitButton", "quitButtonHover");
        leaderboardButton = new Button(300, 280, 100, 50, "highScoresButton", "highScoresButtonHover");
        searchButton = new Button(300, 420, 100, 50, "searchButton", "searchButtonHover");
        creditsButton = new Button(550, 420, 100, 50, "creditsButton", "creditsButtonHover");
        pause = false;
        //add to arraylist of test level
        //Level 1
        groundLevel1.add(new Ground(0, 320, 400, false));
        itemLevel1.add(new Coin(200, 290));

        groundLevel1.add(new Ground(550, 280, 100, false));
        itemLevel1.add(new Coin(580, 250));

        groundLevel1.add(new Ground(800, 260, 200, false));
        enemyLevel1.add(new Enemy(850, 210, 800, 1000));

        groundLevel1.add(new Ground(1100, 300, 150, false));
        itemLevel1.add(new Coin(1150, 270));
        itemLevel1.add(new Coin(1200, 270));

        groundLevel1.add(new Ground(1450, 260, 100, false));
        groundLevel1.add(new Ground(1700, 220, 100, false));
        itemLevel1.add(new Coin(1730, 190));

        groundLevel1.add(new Ground(2050, 320, 400, false));
        enemyLevel1.add(new Enemy(2150, 270, 2050, 2450));
        itemLevel1.add(new Heart(2250, 290));

        groundLevel1.add(new Ground(2500, 280, 100, false));
        groundLevel1.add(new Ground(2750, 240, 100, false));
        itemLevel1.add(new Coin(2780, 210));

        groundLevel1.add(new Ground(3100, 320, 250, false));
        itemLevel1.add(new SpeedPowerup(3200, 290));

        groundLevel1.add(new Ground(3500, 260, 200, false));
        enemyLevel1.add(new Enemy(3550, 210, 3500, 3700));

        groundLevel1.add(new Ground(3850, 300, 150, false));
        itemLevel1.add(new Coin(3900, 270));
        itemLevel1.add(new Coin(3950, 270));

        groundLevel1.add(new Ground(4250, 260, 100, false));
        groundLevel1.add(new Ground(4500, 220, 100, false));
        itemLevel1.add(new Coin(4530, 190));

        groundLevel1.add(new Ground(4850, 320, 400, false));
        enemyLevel1.add(new Enemy(4950, 270, 4850, 5250));
        itemLevel1.add(new JumpPowerup(5100, 290));

        groundLevel1.add(new Ground(5400, 280, 100, false));
        groundLevel1.add(new Ground(5650, 240, 100, false));
        itemLevel1.add(new Coin(5680, 210));

        groundLevel1.add(new Ground(6000, 320, 300, false));
        itemLevel1.add(new Heart(6150, 290));
        itemLevel1.add(new Coin(6200, 290));

        groundLevel1.add(new Ground(6450, 280, 100, false));
        groundLevel1.add(new Ground(6700, 240, 100, false));
        groundLevel1.add(new Ground(6950, 200, 100, false));
        itemLevel1.add(new Coin(6980, 170));

        groundLevel1.add(new Ground(7200, 320, 600, true));
        itemLevel1.add(new Coin(7400, 290));
        itemLevel1.add(new Coin(7450, 290));
        itemLevel1.add(new Heart(7500, 290));

        //level 2
        groundLevel2.add(new Ground(-10, 320, 400, false));
        itemLevel2.add(new Coin(180, 290));

        groundLevel2.add(new Ground(520, 270, 100, false));
        itemLevel2.add(new Coin(550, 240));

        groundLevel2.add(new Ground(760, 240, 100, false));
        enemyLevel2.add(new Enemy(780, 190, 760, 860));

        groundLevel2.add(new Ground(1050, 300, 150, false));
        enemyLevel2.add(new Enemy(1080, 250, 1050, 1200));
        itemLevel2.add(new Coin(1150, 270));

        groundLevel2.add(new Ground(1400, 260, 100, false));
        groundLevel2.add(new Ground(1650, 220, 100, false));
        itemLevel2.add(new Coin(1680, 190));

        groundLevel2.add(new Ground(1950, 320, 250, false));
        enemyLevel2.add(new Enemy(2000, 270, 1950, 2200));

        groundLevel2.add(new Ground(2350, 280, 100, false));
        groundLevel2.add(new Ground(2600, 240, 100, false));
        enemyLevel2.add(new Enemy(2620, 190, 2600, 2700));
        itemLevel2.add(new Coin(2630, 210));

        groundLevel2.add(new Ground(2950, 320, 250, false));
        itemLevel2.add(new SpeedPowerup(3050, 290));

        groundLevel2.add(new Ground(3300, 260, 100, false));
        enemyLevel2.add(new Enemy(3320, 210, 3300, 3400));

        groundLevel2.add(new Ground(3650, 300, 150, false));
        enemyLevel2.add(new Enemy(3700, 250, 3650, 3800));
        itemLevel2.add(new Coin(3750, 270));

        groundLevel2.add(new Ground(4050, 260, 100, false));
        groundLevel2.add(new Ground(4300, 220, 100, false));
        itemLevel2.add(new Coin(4330, 190));

        groundLevel2.add(new Ground(4650, 320, 300, false));
        enemyLevel2.add(new Enemy(4700, 270, 4650, 4950));
        itemLevel2.add(new Heart(4900, 290));

        groundLevel2.add(new Ground(5100, 280, 100, false));
        groundLevel2.add(new Ground(5350, 240, 100, false));
        enemyLevel2.add(new Enemy(5380, 190, 5350, 5450));
        itemLevel2.add(new Coin(5380, 210));

        groundLevel2.add(new Ground(5700, 200, 100, false));
        groundLevel2.add(new Ground(5950, 240, 100, false));
        itemLevel2.add(new Coin(5980, 210));

        groundLevel2.add(new Ground(6300, 320, 300, false));
        enemyLevel2.add(new Enemy(6350, 270, 6300, 6600));
        itemLevel2.add(new JumpPowerup(6500, 290));

        groundLevel2.add(new Ground(6750, 280, 100, false));
        groundLevel2.add(new Ground(7000, 240, 100, false));
        groundLevel2.add(new Ground(7250, 200, 100, false));
        enemyLevel2.add(new Enemy(7280, 150, 7250, 7350));
        itemLevel2.add(new Coin(7280, 170));

        groundLevel2.add(new Ground(7600, 320, 650, true));
        itemLevel2.add(new Coin(7800, 290));
        itemLevel2.add(new Coin(7850, 290));
        itemLevel2.add(new Heart(7950, 290));

        //level 3
        groundLevel3.add(new Ground(0, 320, 400, false));
        itemLevel3.add(new Coin(200, 290));

        groundLevel3.add(new Ground(500, 270, 250, false));
        enemyLevel3.add(new Enemy(550, 220, 500, 750));

        groundLevel3.add(new Ground(850, 220, 250, false));
        enemyLevel3.add(new Enemy(900, 170, 850, 1100));
        enemyLevel3.add(new Enemy(1000, 170, 850, 1100));

        groundLevel3.add(new Ground(1200, 320, 300, false));
        itemLevel3.add(new Coin(1300, 290));
        itemLevel3.add(new Coin(1350, 290));

        groundLevel3.add(new Ground(1600, 300, 250, false));
        groundLevel3.add(new Ground(1600, 180, 200, false));
        enemyLevel3.add(new Enemy(1650, 250, 1600, 1850));

        groundLevel3.add(new Ground(1950, 320, 350, false));
        enemyLevel3.add(new Enemy(2050, 270, 1950, 2300));
        enemyLevel3.add(new Enemy(2200, 270, 1950, 2300));

        groundLevel3.add(new Ground(2400, 260, 250, false));
        itemLevel3.add(new Coin(2500, 230));

        groundLevel3.add(new Ground(2750, 210, 250, false));
        enemyLevel3.add(new Enemy(2800, 160, 2750, 3000));

        groundLevel3.add(new Ground(3100, 170, 250, false));
        enemyLevel3.add(new Enemy(3150, 120, 3100, 3350));
        enemyLevel3.add(new Enemy(3250, 120, 3100, 3350));

        groundLevel3.add(new Ground(3450, 320, 300, false));
        itemLevel3.add(new Heart(3600, 290));

        groundLevel3.add(new Ground(3800, 280, 250, false));
        groundLevel3.add(new Ground(3950, 160, 200, false));
        enemyLevel3.add(new Enemy(3850, 230, 3800, 4050));

        groundLevel3.add(new Ground(4250, 320, 350, false));
        enemyLevel3.add(new Enemy(4350, 270, 4250, 4600));

        groundLevel3.add(new Ground(4700, 260, 250, false));
        enemyLevel3.add(new Enemy(4750, 210, 4700, 4950));
        enemyLevel3.add(new Enemy(4850, 210, 4700, 4950));

        groundLevel3.add(new Ground(5050, 220, 250, false));
        itemLevel3.add(new Coin(5150, 190));

        groundLevel3.add(new Ground(5400, 320, 300, false));
        enemyLevel3.add(new Enemy(5500, 270, 5400, 5700));

        groundLevel3.add(new Ground(5800, 260, 250, false));
        groundLevel3.add(new Ground(5950, 140, 200, false));
        enemyLevel3.add(new Enemy(5850, 210, 5800, 6050));

        groundLevel3.add(new Ground(6300, 200, 250, false));
        enemyLevel3.add(new Enemy(6350, 150, 6300, 6550));
        enemyLevel3.add(new Enemy(6450, 150, 6300, 6550));

        groundLevel3.add(new Ground(6650, 320, 350, false));
        itemLevel3.add(new SpeedPowerup(6800, 290));

        groundLevel3.add(new Ground(7100, 260, 250, false));
        enemyLevel3.add(new Enemy(7150, 210, 7100, 7350));

        groundLevel3.add(new Ground(7450, 220, 250, false));
        enemyLevel3.add(new Enemy(7500, 170, 7450, 7700));
        enemyLevel3.add(new Enemy(7600, 170, 7450, 7700));

        groundLevel3.add(new Ground(7800, 320, 600, true));
        itemLevel3.add(new Coin(8000, 290));
        itemLevel3.add(new Coin(8050, 290));
        itemLevel3.add(new Heart(8150, 290));

        //level 4
        groundLevel4.add(new Ground(0, 320, 400, false));
        itemLevel4.add(new Coin(200, 290));

        groundLevel4.add(new Ground(600, 260, 200, false));
        enemyLevel4.add(new Enemy(650, 210, 600, 800));

        groundLevel4.add(new Ground(900, 200, 200, false));

        groundLevel4.add(new Ground(1200, 260, 150, false));
        enemyLevel4.add(new Enemy(1225, 210, 1200, 1350));

        groundLevel4.add(new Ground(1450, 320, 150, false));

        groundLevel4.add(new Ground(1650, 220, 100, false));

        groundLevel4.add(new Ground(1850, 160, 100, false));

        groundLevel4.add(new Ground(2050, 120, 100, false));

        groundLevel4.add(new Ground(2250, 320, 200, false));
        itemLevel4.add(new Coin(2350, 290));

        groundLevel4.add(new Ground(2550, 260, 150, false));
        enemyLevel4.add(new Enemy(2575, 210, 2550, 2700));

        groundLevel4.add(new Ground(2800, 320, 100, false));

        groundLevel4.add(new Ground(-300, 280, 200, false));
        enemyLevel4.add(new Enemy(-250, 230, -300, -100));

        groundLevel4.add(new Ground(-650, 220, 200, false));

        groundLevel4.add(new Ground(-950, 260, 250, false));
        enemyLevel4.add(new Enemy(-900, 210, -950, -700));
        enemyLevel4.add(new Enemy(-800, 210, -950, -700));

        groundLevel4.add(new Ground(-1300, 180, 200, false));

        groundLevel4.add(new Ground(-1600, 320, 300, false));
        itemLevel4.add(new Heart(-1450, 290));

        groundLevel4.add(new Ground(-2000, 260, 200, false));
        enemyLevel4.add(new Enemy(-1950, 210, -2000, -1800));

        groundLevel4.add(new Ground(-2300, 200, 200, false));

        groundLevel4.add(new Ground(-2600, 140, 200, false));
        enemyLevel4.add(new Enemy(-2550, 90, -2600, -2400));

        groundLevel4.add(new Ground(-2900, 80, 200, false));

        groundLevel4.add(new Ground(-3200, 320, 300, false));
        itemLevel4.add(new SpeedPowerup(-3050, 290));

        groundLevel4.add(new Ground(-3600, 260, 250, false));
        enemyLevel4.add(new Enemy(-3550, 210, -3600, -3350));
        enemyLevel4.add(new Enemy(-3450, 210, -3600, -3350));

        groundLevel4.add(new Ground(-4000, 320, 500, true));
        itemLevel4.add(new Coin(-3800, 290));
        itemLevel4.add(new Coin(-3750, 290));

        //infinite mode
        groundLevel6.add(new Ground(-110, 525, 1000, false));
        groundLevel6.add(new Ground(340, 290, 100, false));
        groundLevel6.add(new Ground(90, 360, 50, false));
        groundLevel6.add(new Ground(640, 360, 50, false));
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
        } else if (e.getKeyChar() == 'p' && (gameState.equals("level1") || gameState.equals("level2") || gameState.equals("level3") || gameState.equals("level4") || gameState.equals("level5") || gameState.equals("infiniteMode"))) {
            if (pause) {
                pause = false;
            } else {
                pause = true;
            }
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
                clip.close();//stops playing menu music
                playBackgroundSound("shop");//playing shop music
            } else if (playBtn.wasClicked(mouseX, mouseY)) {
                gameState = "levelSelectScreen";
                clip.close();//stops playing menu song
                playBackgroundSound("levelSong");//play level selection song
            }

        } else if (gameState.equals("infoScreen")) {//do this for all gamestates and buttons
            if (backButton.wasClicked(mouseX, mouseY)) {
                gameState = "titleScreen";
            } else if (creditsButton.wasClicked(mouseX, mouseY)) {
                gameState = "credits";
            }

        } else if (gameState.equals("shopScreen")) {
            if (backButton.wasClicked(mouseX, mouseY)) {
                gameState = "titleScreen";
                clip.close();//stops playing shop music
                playBackgroundSound("menu");//plays main menu song
            } else if (!player.skin2Bought && buySkin2Button.wasClicked(mouseX, mouseY) && player.getCoins() >= 50) {
                player.skin2Bought = true;
                player.setCoins(player.getCoins() - 50);
            } else if (!player.skin3Bought && buySkin3Button.wasClicked(mouseX, mouseY) && player.getCoins() >= 100) {
                player.skin3Bought = true;
                player.setCoins(player.getCoins() - 100);
            } else if (equipSkin1Button.wasClicked(mouseX, mouseY)) {
                player.equippedSkin = 1;
            } else if (equipSkin2Button.wasClicked(mouseX, mouseY) && player.skin2Bought) {
                player.equippedSkin = 2;
            } else if (equipSkin3Button.wasClicked(mouseX, mouseY) && player.skin3Bought) {
                player.equippedSkin = 3;
            }

        } else if (gameState.equals("levelSelectScreen")) {//else if user is in level selection screen
            if (backButton.wasClicked(mouseX, mouseY)) {//if user clicked the back button
                gameState = "titleScreen";//sets game state to title screen
                clip.close();//stops playing level select screen
                playBackgroundSound("menu");//play level selection song
            } else if (infiniteModeButton.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel6, tempItemLevel6, enemyLevel6);
                enemyLevel6.clear();
                score = 0;
                while (name == null || name.equals("")) {
                    name = JOptionPane.showInputDialog("What is your name? (Press q to cancel infinite mode)");
                }
                if (name.equalsIgnoreCase("q")) {//if user entered q for their name
                    gameState = "levelSelectScreen";//sets game state back to level selection screen
                    name = null;//sets name back to null so that user can play infinite mode later
                } else {//else any other name
                    clip.close();//stops level selection song
                    playBackgroundSound("infinite");//play infinite mode song
                    gameState = "infiniteMode";//sets game state to infinite mode (starts infinite mode)
                }
            } else if (lvl1Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel1, tempItemLevel1, enemyLevel1);
                clip.close();//stops level selection song
                playBackgroundSound("lvl1");//plays level 1 background music
                gameState = "level1";
            } else if (lvl2Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel2, tempItemLevel2, enemyLevel2);
                clip.close();//stops level selection song
                playBackgroundSound("lvl2");//plays level 2 background music
                gameState = "level2";
            } else if (lvl3Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel3, tempItemLevel3, enemyLevel3);
                clip.close();//stops level selection song
                playBackgroundSound("lvl3");//plays level 3 background music
                gameState = "level3";
            } else if (lvl4Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel4, tempItemLevel4, enemyLevel4);
                clip.close();//stops level selection song
                playBackgroundSound("lvl4");//plays level 4 background music
                gameState = "level4";
            } else if (lvl5Button.wasClicked(mouseX, mouseY)) {
                resetLevel(itemLevel5, tempItemLevel5, enemyLevel5);
                clip.close();//stops level selection song
                playBackgroundSound("lvl5");//plays level 5 background music
                gameState = "level5";
            } else if (leaderboardButton.wasClicked(mouseX, mouseY)) {
                gameState = "leaderboard";
            }

        } else if (gameState.equals("leaderboard")) {//else if user is in leaderboard screen
            if (backButton.wasClicked(mouseX, mouseY)) {//if back button was clicked
                gameState = "levelSelectScreen";//game state sets back to level selection screen
            } else if (searchButton.wasClicked(mouseX, mouseY)) {
                ArrayList<Integer> scores = new ArrayList();
                ArrayList<Integer> indexOfScores = new ArrayList();
                ArrayList<String> names = new ArrayList();
                int highest = -1;
                getScores(scores, names);
                String name = JOptionPane.showInputDialog("Enter the person whose score you are looking for:");
                for (int i = 0; i < names.size() - 1; i++) {
                    if (name.equals(names.get(i))) {
                        indexOfScores.add(i);
                    }
                }

                if (indexOfScores.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Person not found.");
                } else if (indexOfScores.size() == 1) {
                    highest = scores.get(indexOfScores.get(0));
                    JOptionPane.showMessageDialog(null, name + "'s highest score is " + highest);

                } else {
                    for (int i = 0; i < indexOfScores.size() - 1; i++) {
                        if (scores.get(indexOfScores.get(i)) > highest) {
                            highest = scores.get(indexOfScores.get(i));
                        }
                    }
                    JOptionPane.showMessageDialog(null, name + "'s highest score is " + highest);
                }
            }
        } else if (gameState.equals("gameOver")) {
            if (returnButton.wasClicked(mouseX, mouseY)) {
                playBackgroundSound("levelSong");//plays level selection song
                gameState = "levelSelectScreen";
            }
        } else if (gameState.equals("win")) {
            if (continueButton.wasClicked(mouseX, mouseY)) {
                clip.close();//stops playing any music
                playBackgroundSound("levelSong");//plays level selection song
                gameState = "levelSelectScreen";
            }
        } else if (pause && (gameState.equals("level1") || gameState.equals("level2") || gameState.equals("level3") || gameState.equals("level4") || gameState.equals("level5") || gameState.equals("infiniteMode"))) {
            if (quitButton.wasClicked(mouseX, mouseY)) {
                gameState = "levelSelectScreen";
            }
        } else if (gameState.equals("credits")) {
            if (backButton.wasClicked(mouseX, mouseY)) {//if back button was clicked
                gameState = "infoScreen";//game state sets back to level selection screen
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
        pause = false;
    }

    public void playLevel(Graphics2D g2d, ArrayList<Ground> groundLevel, ArrayList<GameItem> itemLevel, ArrayList<GameItem> tempItemLevel, ArrayList<Enemy> enemyLevel) {
        //test level
        if (!pause) {
            player.move(up_pressed, down_pressed, left_pressed, right_pressed, groundLevel); //move player
            GameObject.setXOffset(player.getX() - Player.getScreenXPosition());//update offsets after movement
            GameObject.setYOffset(player.getY() - Player.getScreenYPosition());
            player.checkDeath();
        }
        player.draw(g2d, pause);//draw player
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
            if (!pause) {
                enemy.move();
            }
            enemy.draw(g2d);
            enemy.collisionProcedure(player, tempItemLevel, gameState, enemyToRemove);
        }
        if (player.isDead()) {
            clip.close();//stops any background music playing
            GameObject.playSound("deadSound");//plays music when user dies
            if (gameState.equals("infiniteMode")) {
                saveScore();
            }
            gameState = "gameOver";
        }
        if (player.isWin()) {
            clip.close();//stops playing level music
            playBackgroundSound("victory");//plays victory sound effect
            gameState = "win";
        }

        if (pause) {//if pause is true
            g2d.setColor(Color.black);//set color to black
            //draws two vertical lines for the pause symbol
            g2d.fillRect(450, 75, 15, 35);//left line
            g2d.fillRect(480, 75, 15, 35);//right line
            g2d.setFont(titleFont);//sets font to title font
            g2d.drawString("Paused", 195, 110);//draws paused string
            g2d.setFont(infoTextFont);//sets font to info text font
            g2d.drawString("Press P again to resume", 255, 150);//draws description on how to continue
            quitButton.draw(g2d, mouseX, mouseY);//draws the quit button
        }
    }

    public void saveScore() {
        try {
            // Create a FileWriter object
            // to write in the file
            FileWriter fWriter = new FileWriter("src/dinodashfinalproject/Scores.txt", true);

            // Writing into file
            fWriter.write("\n" + name + "\n" + (score - 5));

            // Closing the file writing connection
            fWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void getScores(ArrayList<Integer> scores, ArrayList<String> names) {
        try {
            File f = new File("src/dinodashfinalproject/Scores.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                names.add(s.nextLine());
                scores.add(Integer.parseInt(s.nextLine()));

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    public void sortScores(ArrayList<Integer> scores, ArrayList<String> names) {
        quickSortDescending(scores, 0, scores.size() - 1, names);
    }

    /**
     * a method that sorts an arrayList in descending order using the quicksort
     * algorithm
     *
     * @param arr - the arrayList to be sorted
     * @param left - the index of the left side of the split
     * @param right - the index of the right side of the split
     * @param names - the array of names associated with the scores
     */
    public static void quickSortDescending(ArrayList<Integer> arr, int left, int right, ArrayList<String> names) {
        //if the left index is greater than or equal to the right index that means we have broken down the array
        //all the way and the sort is complete
        if (left >= right) {
            return;
        }

        //setup index variables
        int i = left;
        int j = right;

        //get the pivot points value
        int pivot = arr.get((i + j) / 2);

        //while left index is less than the right index - we do this to make sure i is looking on the left half of the section and j is looking at the
        //right half of the section and they dont cross over each other and go to the other side of the section of the array we are sorting
        while (i < j) {
            //keep going through the left side of the array until we reach a number that is less than the pivot
            //and needs to be switched to the other side
            while (arr.get(i) > pivot) {
                i++;//move down array
            }
            //go through the right side of the array until we reach a number that is greater than the pivot
            //and needs to be switched to the left side
            while (arr.get(j) < pivot) {
                j--;//move up array
            }
            //now that we have found numbers that are below and above the pivot if they left ones index is less
            //than the rights ones we switch their positions
            if (i <= j) {// this makes sure we are switching the number that are actually on incorrect side and that i or j havent 
                //just crossed over to the lower/higher than pivot side and found a number that is less than the pivot and is on the right side or 
                //is higher than the pivot and on the left side of the pivot because those are where they are supposed to be

                int temp = arr.get(i);//swap the elements
                String temp2 = names.get(i);//swap the elements
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                names.set(i, names.get(j));
                names.set(j, temp2);
                i++;//move the left index right one
                j--;//move the right index left one
            }

        }
        //now continue sorting the array breaking it apart into to parts and then sorting each part
        quickSortDescending(arr, left, j, names);
        quickSortDescending(arr, i, right, names);

    }

    /**
     * plays background music
     *
     * @param soundName - audio file name
     */
    public static void playBackgroundSound(String soundName) {
        try {//attempts to open file and play audio
            File sound = new File("src/dinodashfinalproject/soundEffects/" + soundName + ".wav");//sets new file to sound file
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(sound);//gets audio file and converts it into audio input stream which is java's standard way to read raw audio data
            clip = AudioSystem.getClip();//initialize clip
            clip.open(audioInput);//clip opens the audio input
            clip.loop(Clip.LOOP_CONTINUOUSLY);//loops the sound effect until stopped
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {//catches any error that may occur
            System.out.println("Error: " + e);//prints out the error
        }
    }
}
