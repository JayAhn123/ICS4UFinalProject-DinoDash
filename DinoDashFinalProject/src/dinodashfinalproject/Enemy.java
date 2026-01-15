/* Araib, Jay, Bernie
 * January 13 2026
 * enemy class for game
 */
package dinodashfinalproject;

//import necessary packages
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

    //attributes
    private boolean visible;
    private int startXRange;
    private int endXRange;
    private int xSpeed;
    private int health;
    private Rectangle safeHitbox;
    private Image leftImage;

    /**
     * primary constructor for enemy
     *
     * @param x - the x coordinate where the enemy spawns
     * @param y - the y coordinate where the enemy spawns
     * @param startXRange - the left boundary of where the enemy goes back and
     * forth between
     * @param endXRange - the right boundary of where the enemy goes back and
     * forth between
     */
    public Enemy(int x, int y, int startXRange, int endXRange) {
        super(x, y, 50, 50, "EnemyRight");//chain to superclass constructor
        visible = true;//set all other attributes to defaults
        this.startXRange = startXRange;//set ranges
        this.endXRange = endXRange;
        health = 2;
        xSpeed = 4;//set everything else
        safeHitbox = new Rectangle(x + 3, y, width - 6, 10);
        hitbox = new Rectangle(x, y + 11, width, 39);
        leftImage = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/EnemyLeft.png")).getImage();
    }

    /**
     * method that will draw the enemy at the right spot
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

    /**
     * this method moves the enemy
     */
    public void move() {
        //add the speed to the x position
        x += xSpeed;

        // if the enemy hits the edges of its range then switch the xspeed so it move in the other direction
        if (x > endXRange) {
            xSpeed = -xSpeed;
        } else if (x < startXRange) {
            xSpeed = -xSpeed;
        }

        //update the hitboxes
        safeHitbox.x = x + 3;
        hitbox.x = x;
    }

    /**
     * this method handles collisions with the player
     *
     * @param player - the player
     * @param tempItem - the list of temporary items in the level
     * @param gameState - the current gameState
     * @param enemyToRemove - the arrayList of enemies to remove used when in
     * infinite mode
     */
    public void collisionProcedure(Player player, ArrayList<GameItem> tempItem, String gameState, ArrayList<Enemy> enemyToRemove) {

        if (visible) {//if the enemy is alive and visible

            if (player.hitbox.intersects(hitbox)) {
                //if the player hits the dangeroues part of the enemy
                playSound("damagedSound");//plays sound effect when user takes damage
                player.setHearts(player.getHearts() - 1);//lower hearts by 1

                //depending on which side of the enemy the player is on push it back
                if (player.getX() < x) {
                    player.setX(player.getX() - 60);
                    player.setXSpeed(-player.getMaxSpeed());
                } else {
                    player.setX(player.getX() + 60);
                    player.setXSpeed(player.getMaxSpeed());
                }

            } else if (player.hitbox.intersects(safeHitbox)) {
                //if the player hits the safe part of the enemy
                //enemy loses health
                health -= 1;
                //bounce th eplayer back up
                player.setYSpeed(-5);
                playSound("enemyStomp");//playing sound effect for stepping on enemy 

                if (health == 0) {//if enemy health is 0 and it dies

                    visible = false;//set visible to false

                    //do enemy drop chances
                    //make a random number
                    int chance = (int) (Math.random() * 20);
                    //depending on the number drop nothing, coin, heart, or powerup
                    if (chance >= 11 && chance <= 19) {
                        tempItem.add(new Coin(x, y + 24));
                    } else if (chance == 2) {
                        tempItem.add(new Heart(x, y + 24));
                    } else if (chance == 8) {
                        tempItem.add(new JumpPowerup(x, y + 24));
                    } else if (chance == 4) {
                        tempItem.add(new SpeedPowerup(x, y + 24));
                    }

                    //if the gameState if infiniteMode then add this enemy to the list of enemies needed to be removed from the level
                    if (gameState.equals("infiniteMode")) {
                        enemyToRemove.add(this);
                    }

                }
            }
        }
    }

    /**
     * this method clones this enemy
     *
     * @return - a new cloned enemy
     */
    public Enemy clone() {
        Enemy other = new Enemy(x, y, startXRange, endXRange);//make new enemy
        other.health = this.health;//make attributes the same
        other.visible = this.visible;
        return other;//return it
    }

    /**
     * getter for if the game item is visible/should be on the map and able to
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
     * getter for the left boundary of the enemies movement
     *
     * @return - the x position of the boundary
     */
    public int getStartXRange() {
        return startXRange;
    }

    /**
     * setter for the left side of the enemies movement boundary
     *
     * @param startXRange - the x position of the boundary
     */
    public void setStartXRange(int startXRange) {
        this.startXRange = startXRange;
    }

    /**
     * getter for the right boundary of the enemies movement
     *
     * @return - the x position of the boundary
     */
    public int getEndXRange() {
        return endXRange;
    }

    /**
     * setter for the right side of the enemies movement boundary
     *
     * @param endXRange - the x position of the boundary
     */
    public void setEndXRange(int endXRange) {
        this.endXRange = endXRange;
    }

    /**
     * getter for health of enemy
     *
     * @return - the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter for health
     *
     * @param health - and integer for how many hearts the enemy has
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * getter for enemy x speed
     *
     * @return - an int for the speed
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * setter for x speed of enemy
     *
     * @param xSpeed - and int for the speed it moves at
     */
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * getter for the safe hitbox
     *
     * @return - the safe hitbox as a rectangle object
     */
    public Rectangle getSafeHitbox() {
        return safeHitbox;
    }

    /**
     * setter for the safe hitbox
     *
     * @param safeHitbox - a rectangle object for the hitbox
     */
    public void setSafeHitbox(Rectangle safeHitbox) {
        this.safeHitbox = safeHitbox;
    }

    /**
     *this method resets the enemy
     */
    public void reset() {
        visible = true;
        health = 2;
    }

    /**
     * this method returns a string of all attributes of the enemy
     *
     * @return - a string of attributes
     */
    public String toString() {
        return super.toString() + "\n" + xSpeed + "\n" + visible + "\n" + health + "\n"
                + startXRange + "\n" + endXRange + "\n" + leftImage.toString();
    }

    /**
     * this method checks if to enemies are the same
     *
     * @param other - the other enemy
     * @return - a Boolean for if they are the same
     */
    public boolean equals(Enemy other) {
        //if they are the same return true otherwise false
        if (super.equals(this) && other.visible == this.visible && other.startXRange == this.startXRange && other.health == this.health
                && other.endXRange == this.endXRange) {

            return true;
        } else {
            return false;
        }
    }

}
