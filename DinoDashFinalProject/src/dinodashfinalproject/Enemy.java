/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author mubas
 */
public class Enemy extends GameObject {
    
    boolean visible;
    int startXRange;
    int endXRange;
    int xSpeed;
    int health;
    Rectangle safeHitbox;
    Image leftImage;
    
    public Enemy(int x, int y, int startXRange, int endXRange) {
        super(x, y, 50, 50, "EnemyRight");
        visible = true;
        this.startXRange = startXRange;
        this.endXRange = endXRange;
        health = 2;
        xSpeed = 4;
        safeHitbox = new Rectangle(x + 3, y, width - 6, 10);
        hitbox = new Rectangle(x, y + 11, width, 39);
        leftImage = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/EnemyLeft.png")).getImage();
    }

    /**
     * method that will draw the game item at the right spot
     *
     * @param g2d - the graphics2d object to draw on
     */
    public void draw(Graphics2D g2d) {
        if (visible) {//if its visible then draw otherwise dont
            if (xSpeed > 0) {
                g2d.drawImage(img, x - xOffset, y - yOffset, null);//draw the image at correct 
                //position taking into account the offsets because of the movement of the player
            } else {
                g2d.drawImage(leftImage, x - xOffset, y - yOffset, null);//draw the image at correct 
                //position taking into account the offsets because of the movement of the player
            }
        }
    }
    
    public void move() {
        x += xSpeed;
        
        if (x > endXRange) {
            xSpeed = -xSpeed;
        } else if (x < startXRange) {
            xSpeed = -xSpeed;
        }
        
        safeHitbox.x = x + 3;
        hitbox.x = x;
    }
    
    public void collisionProcedure(Player player, ArrayList<GameItem> tempItem, String gameState, ArrayList<Enemy> enemyToRemove) {
        if (visible) {
            if (player.hitbox.intersects(hitbox)) {
                player.setHearts(player.getHearts() - 1);
                if (player.getX() < x) {
                    player.setX(player.getX() - 60);
                    player.setXSpeed(-player.getMaxSpeed());
                } else {
                    player.setX(player.getX() + 60);
                    player.setXSpeed(player.getMaxSpeed());
                }
            } else if (player.hitbox.intersects(safeHitbox)) {
                health -= 1;
                player.setYSpeed(-5);
                if (health == 0) {
                    visible = false;
                    //run enemy drop
                    int chance = (int) (Math.random() * 20);
                    if (chance >=11 && chance <= 19) {
                        tempItem.add(new Coin(x, y + 24));
                    } else if (chance == 2) {
                        tempItem.add(new Heart(x, y + 24));
                    } else if (chance == 8) {
                        tempItem.add(new JumpPowerup(x, y + 24));
                    } else if (chance == 4) {
                        tempItem.add(new SpeedPowerup(x, y + 24));
                    }
                    
                    if (gameState.equals("infiniteMode")) {
                        enemyToRemove.add(this);
                    }
                }
            }
        }
    }
    
    public GameObject clone() {
        return new Enemy(x, y, startXRange, endXRange);

        //incomplete have to add other attributes
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param visible - a Boolean of true or false indicating whether or not it
     * is visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public int getStartXRange() {
        return startXRange;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param startXRange - a Boolean of true or false indicating whether or not
     * it is visible
     */
    public void setStartXRange(int startXRange) {
        this.startXRange = startXRange;
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public int getEndXRange() {
        return endXRange;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param endXRange - a Boolean of true or false indicating whether or not
     * it is visible
     */
    public void setEndXRange(int endXRange) {
        this.endXRange = endXRange;
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param health - a Boolean of true or false indicating whether or not it
     * is visible
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param xSpeed - a Boolean of true or false indicating whether or not it
     * is visible
     */
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * getter for is the game item is visible/should be on the map and able to
     * be interacted with
     *
     * @return - Boolean of true if it is visible, false if not
     */
    public Rectangle getSafeHitbox() {
        return safeHitbox;
    }

    /**
     * setter for if the game object is visible or not
     *
     * @param safeHitbox - a Boolean of true or false indicating whether or not
     * it is visible
     */
    public void setSafeHitbox(Rectangle safeHitbox) {
        this.safeHitbox = safeHitbox;
    }
    
    public void reset() {
        visible = true;
        health = 2;
    }
    
}
