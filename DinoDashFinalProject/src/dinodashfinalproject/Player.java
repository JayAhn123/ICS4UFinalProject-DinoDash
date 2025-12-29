/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

//import packages
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author arbas
 */
public class Player extends GameObject {

    //attributes
    private static final int SCREENXPOSITION = 325;
    private static final int SCREENYPOSITION = 213;
    double xSpeed = 0;
    double ySpeed = 0;
    int coins;
    int hearts;
    Image heartImage;
    int jumpHeight;

    /**
     * primary constructor
     */
    public Player() {
        super(325, 213, 50, 100, "GroundImg"); //chain to superclass constructor
        //no paramters needed ass dino will always start with default skin, at that
        //x,y position and with that width and height
        coins = 0; //set other attributes to defualts
        hearts = 3;
        heartImage = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/playerHeart.png")).getImage();
        jumpHeight = -6;

    }

    /**
     * clone method for player
     *
     * @return - the cloned player
     */
    public Player clone() {
        return new Player();//INCOMPLETE
    }

    /**
     * method that draws player to screen
     *
     * @param g2d - the graphics2D object to draw with
     */
    public void draw(Graphics2D g2d) {
        g2d.fillRect(SCREENXPOSITION, SCREENYPOSITION, width, height);//incomplete placeholder rectangle currently
    }

    /**
     * this method will update the player's x and y position
     *
     * @param up_pressed - if the up key is pressed
     * @param down_pressed - if the down key is pressed
     * @param left_pressed - if the left key is pressed
     * @param right_pressed - if the right key is pressed
     * @param groundTiles - an array list of all the ground tiles on the level
     * which is needed for collisions
     */
    public void move(boolean up_pressed, boolean down_pressed, boolean left_pressed, boolean right_pressed, ArrayList<Ground> groundTiles) {
        //if either both left and right are pressed or not pressed we are not moving at all so reduce the xspeed
        if (left_pressed == right_pressed) {
            xSpeed *= 0.8;//xspeed become 80% of itself
        } else if (left_pressed) {
            //if left is pressed lower x speed by 1 to move left by becomeing a negative xspeed which we add to 
            //our x location 
            xSpeed--;
        } else if (right_pressed) {
            //if right is pressed increase our x speed to add to our x position as 
            //increasing our x position make the dino move to the right
            xSpeed++;
        }

        //to prevent sliding when not moving if our x speed become small enough in either direction the make it 0
        //since when not moving we make 80% less than last time which will take foreever to lower
        if ((xSpeed > 0 && xSpeed < 0.75) || (xSpeed < 0 && xSpeed > -0.75)) {
            //set to 0 when x speed is either less than 0.75 moving left or right
            xSpeed = 0;
        }

        //since we add to xspeed when moving we need to put a limit otherwise we will infinitly get faster
        //s once we reach max speed if our speed goes above then just set it back to max speed
        if (xSpeed > 7) { //do negative and positive because we can go in both directions left or right
            xSpeed = 7; //reset back to max
        } else if (xSpeed < -7) {
            xSpeed = -7;//reset back to max
        }

        //then we check if the user wants to jump
        if (up_pressed) {
            //if they do then move the hitbox 1 pixel down to see if we have ground
            //underneath us which is what we need to jump - if we are midair we cant jump
            hitbox.y++;

            //then for each title in the array check if the hitboxes are intersecting
            for (Ground groundTile : groundTiles) {
                if (groundTile.hitbox.intersects(hitbox)) {
                    ySpeed = jumpHeight;//if they do intersect set y speed to -6 so we jump
                }
            }
            //then once we are done checking put the hitbox back to the right spot
            hitbox.y--;
        }

        //add to y speed for gravity that will pull us back down
        ySpeed += 0.3;

        //we also need to set limit for gravity speed otherwise it will just keep speeding up
        if (ySpeed > 13) {
            ySpeed = 13; //if it gets above 13 then set it to 13
        }

        //now we check collisions with ground and walls
        //horizontal collision first
        //first move the hitbox to where the dino is about to go
        hitbox.x += xSpeed;
        //then check through each ground tile
        for (Ground groundTile : groundTiles) {
            if (groundTile.hitbox.intersects(hitbox)) {//if the dino is going to hit the walls
                hitbox.x -= xSpeed;//we reset hitbox location back to what it currently is
                while (!groundTile.hitbox.intersects(hitbox)) {//while we do not intersect with the hitbox of the wall
                    hitbox.x += Math.signum(xSpeed); //move hitbox closer
                    //(to make this work in both direcition we use math.signum of xspeed so if xspeed is positive and we are moving right)
                    //(then the signum will reutrn positive 1 and we will move hitbox 1 pixel to the right)
                    //(if the xspeed is negative and we are moving to the left the signum fo xspeed will return negative 1)
                    //(so we will move the hitbox 1 pixel left until we hit the wall)
                }
                //once we hit the wall move hitbox away from the wall one pixel then set the x speed 
                //to zero because we collide with wall
                //and move the x to hitbox x which is in the right location (1 pixel away from the wall we collide with)
                hitbox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitbox.x;
            }
        }

        //vertical collisions next (same process as with horizontal collision but going up and down instead)
        hitbox.y += ySpeed;
        for (Ground groundTile : groundTiles) {
            if (groundTile.hitbox.intersects(hitbox)) {
                hitbox.y -= ySpeed;
                while (!groundTile.hitbox.intersects(hitbox)) {
                    hitbox.y += Math.signum(ySpeed);
                }
                hitbox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitbox.y;
            }
        }

        //now that we sorted movement graivty and collisions the hitbox will be in the right location and we set x and y 
        //to the x and y of the hitbox
        x = hitbox.x;
        y = hitbox.y;

    }

    /**
     * getter for the xSpeed
     *
     * @return the xSpeed
     */
    public double getXSpeed() {
        return xSpeed;
    }

    /**
     * getter for y speed
     *
     * @return the ySpeed
     */
    public double getYSpeed() {
        return ySpeed;
    }

    /**
     * getter for the x position at which the dino is always drawn at
     *
     * @return the screenXPosition
     */
    public static int getScreenXPosition() {
        return SCREENXPOSITION;
    }

    /**
     * getter for the x position at which the dino is always drawn at
     *
     * @return the screenYPosition
     */
    public static int getScreenYPosition() {
        return SCREENYPOSITION;
    }

    /**
     * setter for x speed
     *
     * @param xSpeed - the new x speed
     */
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * setter for y speed
     *
     * @param ySpeed the new y speed
     */
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * setter for the amount of coins the player has
     *
     * @param coins - the amount of coins the player should have
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * getter for the amount of coins the player has
     *
     * @return - the amount of coins as an int
     */
    public int getCoins() {
        return coins;
    }

    /**
     * setter for the amount of hearts the player has
     *
     * @param hearts - the amount of hearts as an int
     */
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    /**
     * getter for the amount of hearts the player has
     *
     * @return - the amount of hearts as an int
     */
    public int getHearts() {
        return hearts;
    }

    /**
     * setter for the jump height of the player
     *
     * @param jumpHeight the value we make the ySpeed of the player when they
     * jump as an int
     */
    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    /**
     * getter for jump height of the player
     *
     * @return - the value we make the ySpeed of the player when they jump as an
     * int
     */
    public int getJumpHeight() {
        return jumpHeight;
    }

    /**
     * a method that draws the amount of coins the player has as a string in the
     * upper right corner
     *
     * @param g2d - the graphics2d object to draw on
     * @param font - font used to draw
     */
    public void drawCoins(Graphics2D g2d, Font font) {
        g2d.setColor(Color.black);//set colour and font
        g2d.setFont(font);
        g2d.drawString("Coins: " + coins, 600, 18); // draw string at correct position
    }

    /**
     * method that draw the amount of hearts the user has
     *
     * @param g2d - the graphics2d object to draw on
     */
    public void drawHearts(Graphics2D g2d) {
        //for loop that will start where the first heart should be drawn and will keep going to 
        //the next x position for the next heart and will run as many times as there are hearts
        for (int i = 10; i < (hearts * 23) + 10; i += 23) {
            g2d.drawImage(heartImage, i, 10, null);//draw the image at the right x position
        }
    }

}
