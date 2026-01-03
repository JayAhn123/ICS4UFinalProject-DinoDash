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
    int maxSpeed;
    Image rightStill1;
    Image rightStill2;
    Image rightStill3;
    Image rightStill4;
    Image rightStill5;
    Image rightStill6;
    Image rightStill7;
    Image rightStill8;
    Image rightRun1;
    Image rightRun2;
    Image rightRun3;
    Image rightRun4;
    Image rightRun5;
    Image rightRun6;
    Image rightRun7;
    Image rightRun8;
    Image rightJump1;
    Image rightJump2;
    Image leftStill1;
    Image leftStill2;
    Image leftStill3;
    Image leftStill4;
    Image leftStill5;
    Image leftStill6;
    Image leftStill7;
    Image leftStill8;
    Image leftRun1;
    Image leftRun2;
    Image leftRun3;
    Image leftRun4;
    Image leftRun5;
    Image leftRun6;
    Image leftRun7;
    Image leftRun8;
    Image leftJump1;
    Image leftJump2;
    boolean facingRight;
    boolean airborne;
    int jumpAnimationCount;
    int moveAnimationCount;
    int idleAnimationCount;
    boolean dead;
    boolean win;

    /**
     * primary constructor
     */
    public Player() {
        super(325, 213, 64, 64, "GroundImg"); //chain to superclass constructor
        //no paramters needed ass dino will always start with default skin, at that
        //x,y position and with that width and height
        coins = 0; //set other attributes to defualts
        hearts = 3;
        heartImage = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/playerHeart.png")).getImage();
        jumpHeight = -10;
        maxSpeed = 7;
        rightStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur1.png")).getImage();
        rightStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur2.png")).getImage();
        rightStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur3.png")).getImage();
        rightStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur4.png")).getImage();
        rightStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur5.png")).getImage();
        rightStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur6.png")).getImage();
        rightStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur7.png")).getImage();
        rightStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur8.png")).getImage();
        rightRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur9.png")).getImage();
        rightRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur10.png")).getImage();
        rightRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur11.png")).getImage();
        rightRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur12.png")).getImage();
        rightRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur13.png")).getImage();
        rightRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur14.png")).getImage();
        rightRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur15.png")).getImage();
        rightRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur16.png")).getImage();
        rightJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur17.png")).getImage();
        rightJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur18.png")).getImage();
        leftStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur1Left.png")).getImage();
        leftStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur2Left.png")).getImage();
        leftStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur3Left.png")).getImage();
        leftStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur4Left.png")).getImage();
        leftStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur5Left.png")).getImage();
        leftStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur6Left.png")).getImage();
        leftStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur7Left.png")).getImage();
        leftStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur8Left.png")).getImage();
        leftRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur9Left.png")).getImage();
        leftRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur10Left.png")).getImage();
        leftRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur11Left.png")).getImage();
        leftRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur12Left.png")).getImage();
        leftRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur13Left.png")).getImage();
        leftRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur14Left.png")).getImage();
        leftRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur15Left.png")).getImage();
        leftRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur16Left.png")).getImage();
        leftJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur17Left.png")).getImage();
        leftJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur18Left.png")).getImage();
        facingRight = true;
        airborne = false;
        jumpAnimationCount = 0;
        moveAnimationCount = 0;
        idleAnimationCount = 0;
        dead = false;
        win = false;
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
     * @param pause - if they game is pause or not
     */
    public void draw(Graphics2D g2d, boolean pause) {
        if (airborne) {
            if (!pause) {
                jumpAnimationCount++;
            }
            if (facingRight) {
                if (jumpAnimationCount < 6) {
                    g2d.drawImage(rightJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                } else {
                    g2d.drawImage(rightJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                }
            } else {
                if (jumpAnimationCount < 6) {
                    g2d.drawImage(leftJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                } else {
                    g2d.drawImage(leftJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                }
            }
        } else {
            if (xSpeed == 0) {
                if (!pause) {
                    idleAnimationCount++;
                }

                if (idleAnimationCount >= 40) {
                    idleAnimationCount = 0;
                }

                if (facingRight) {
                    if (idleAnimationCount < 5) {
                        g2d.drawImage(rightStill1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 5 && idleAnimationCount < 10) {
                        g2d.drawImage(rightStill2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 10 && idleAnimationCount < 15) {
                        g2d.drawImage(rightStill3, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 15 && idleAnimationCount < 20) {
                        g2d.drawImage(rightStill4, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 20 && idleAnimationCount < 25) {
                        g2d.drawImage(rightStill5, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 25 && idleAnimationCount < 30) {
                        g2d.drawImage(rightStill6, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 30 && idleAnimationCount < 35) {
                        g2d.drawImage(rightStill7, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 35 && idleAnimationCount < 40) {
                        g2d.drawImage(rightStill8, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    }
                } else {
                    if (idleAnimationCount < 5) {
                        g2d.drawImage(leftStill1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 5 && idleAnimationCount < 10) {
                        g2d.drawImage(leftStill2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 10 && idleAnimationCount < 15) {
                        g2d.drawImage(leftStill3, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 15 && idleAnimationCount < 20) {
                        g2d.drawImage(leftStill4, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 20 && idleAnimationCount < 25) {
                        g2d.drawImage(leftStill5, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 25 && idleAnimationCount < 30) {
                        g2d.drawImage(leftStill6, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 30 && idleAnimationCount < 35) {
                        g2d.drawImage(leftStill7, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (idleAnimationCount >= 35 && idleAnimationCount < 40) {
                        g2d.drawImage(leftStill8, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    }
                }

            } else {
                if (!pause) {
                    moveAnimationCount++;
                }

                if (moveAnimationCount >= 40) {
                    moveAnimationCount = 0;
                }

                if (facingRight) {
                    if (moveAnimationCount < 5) {
                        g2d.drawImage(rightRun1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 5 && moveAnimationCount < 10) {
                        g2d.drawImage(rightRun2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 10 && moveAnimationCount < 15) {
                        g2d.drawImage(rightRun3, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 15 && moveAnimationCount < 20) {
                        g2d.drawImage(rightRun4, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 20 && moveAnimationCount < 25) {
                        g2d.drawImage(rightRun5, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 25 && moveAnimationCount < 30) {
                        g2d.drawImage(rightRun6, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 30 && moveAnimationCount < 35) {
                        g2d.drawImage(rightRun7, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 35 && moveAnimationCount < 40) {
                        g2d.drawImage(rightRun8, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    }
                } else {
                    if (moveAnimationCount < 5) {
                        g2d.drawImage(leftRun1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 5 && moveAnimationCount < 10) {
                        g2d.drawImage(leftRun2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 10 && moveAnimationCount < 15) {
                        g2d.drawImage(leftRun3, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 15 && moveAnimationCount < 20) {
                        g2d.drawImage(leftRun4, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 20 && moveAnimationCount < 25) {
                        g2d.drawImage(leftRun5, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 25 && moveAnimationCount < 30) {
                        g2d.drawImage(leftRun6, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 30 && moveAnimationCount < 35) {
                        g2d.drawImage(leftRun7, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    } else if (moveAnimationCount >= 35 && moveAnimationCount < 40) {
                        g2d.drawImage(leftRun8, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently

                    }
                }
            }
        }
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
            facingRight = false;//update direction faced
        } else if (right_pressed) {
            //if right is pressed increase our x speed to add to our x position as 
            //increasing our x position make the dino move to the right
            xSpeed++;
            facingRight = true;//update direction faced
        }

        //to prevent sliding when not moving if our x speed become small enough in either direction the make it 0
        //since when not moving we make 80% less than last time which will take foreever to lower
        if ((xSpeed > 0 && xSpeed < 0.75) || (xSpeed < 0 && xSpeed > -0.75)) {
            //set to 0 when x speed is either less than 0.75 moving left or right
            xSpeed = 0;
        }

        //since we add to xspeed when moving we need to put a limit otherwise we will infinitly get faster
        //s once we reach max speed if our speed goes above then just set it back to max speed
        if (xSpeed > maxSpeed) { //do negative and positive because we can go in both directions left or right
            xSpeed = maxSpeed; //reset back to max
        } else if (xSpeed < -maxSpeed) {
            xSpeed = -maxSpeed;//reset back to max
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
                    airborne = true; //set airborne to true because we are now in air and need to do air animation
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
                if (ySpeed > 0) {
                    airborne = false;//rset airborne to false and reset animation counter
                    jumpAnimationCount = 0;
                }
                ySpeed = 0;
                y = hitbox.y;
            }
        }

        //now that we sorted movement graivty and collisions the hitbox will be in the right location and we set x and y 
        //to the x and y of the hitbox
        x = hitbox.x;
        y = hitbox.y;

        for (Ground groundTile : groundTiles) {
            hitbox.y++;
            if (groundTile.isWinBlock() && groundTile.hitbox.intersects(hitbox)) {
                win = true;
            }
            hitbox.y--;
        }
    }

    public void checkDeath() {
        if (hearts < 1 || y > 600) {
            dead = true;
        }
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
     * setter for the max speed
     *
     * @param maxSpeed the max speed of the player
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * getter for max speed of the player
     *
     * @return - the max speed
     */
    public int getMaxSpeed() {
        return maxSpeed;
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

    /**
     * method that returns a boolean true is dead false if not
     *
     * @return - a boolean true if dead false if alive
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * method that sets whether or not the player is dead
     *
     * @param dead - true if dead false if not
     *
     */
    public void setDead(boolean dead) {
        this.dead = dead;//set variable
    }

    /**
     * method that returns a boolean true is the player won false if not
     *
     * @return - a boolean true if the player won false if not
     */
    public boolean isWin() {
        return win;
    }

    /**
     * method that sets whether or not the player won
     *
     * @param win - true if won false if not
     *
     */
    public void setWin(boolean win) {
        this.win = win;//set variable
    }

    public void reset() {
        dead = false;
        win = false;
        hearts = 3;
        xOffset = 0;
        yOffset = 0;
        setX(SCREENXPOSITION);
        setY(SCREENYPOSITION);
        xSpeed = 0;
        ySpeed = 0;
        airborne = false;
        maxSpeed = 7;
        jumpHeight = -10;
    }

}
