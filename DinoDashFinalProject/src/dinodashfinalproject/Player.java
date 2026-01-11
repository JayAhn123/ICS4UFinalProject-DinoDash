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
    ArrayList<Image> skin1rightStill = new ArrayList();
    ArrayList<Image> skin1rightRun = new ArrayList();
    ArrayList<Image> skin1leftStill = new ArrayList();
    ArrayList<Image> skin1leftRun = new ArrayList();
    ArrayList<Image> skin2rightStill = new ArrayList();
    ArrayList<Image> skin2rightRun = new ArrayList();
    ArrayList<Image> skin2leftStill = new ArrayList();
    ArrayList<Image> skin2leftRun = new ArrayList();
    ArrayList<Image> skin3rightStill = new ArrayList();
    ArrayList<Image> skin3rightRun = new ArrayList();
    ArrayList<Image> skin3leftStill = new ArrayList();
    ArrayList<Image> skin3leftRun = new ArrayList();
    Image skin1rightStill1;
    Image skin1rightStill2;
    Image skin1rightStill3;
    Image skin1rightStill4;
    Image skin1rightStill5;
    Image skin1rightStill6;
    Image skin1rightStill7;
    Image skin1rightStill8;
    Image skin1rightRun1;
    Image skin1rightRun2;
    Image skin1rightRun3;
    Image skin1rightRun4;
    Image skin1rightRun5;
    Image skin1rightRun6;
    Image skin1rightRun7;
    Image skin1rightRun8;
    Image skin1rightJump1;
    Image skin1rightJump2;
    Image skin1leftStill1;
    Image skin1leftStill2;
    Image skin1leftStill3;
    Image skin1leftStill4;
    Image skin1leftStill5;
    Image skin1leftStill6;
    Image skin1leftStill7;
    Image skin1leftStill8;
    Image skin1leftRun1;
    Image skin1leftRun2;
    Image skin1leftRun3;
    Image skin1leftRun4;
    Image skin1leftRun5;
    Image skin1leftRun6;
    Image skin1leftRun7;
    Image skin1leftRun8;
    Image skin1leftJump1;
    Image skin1leftJump2;

    Image skin2rightStill1;
    Image skin2rightStill2;
    Image skin2rightStill3;
    Image skin2rightStill4;
    Image skin2rightStill5;
    Image skin2rightStill6;
    Image skin2rightStill7;
    Image skin2rightStill8;
    Image skin2rightRun1;
    Image skin2rightRun2;
    Image skin2rightRun3;
    Image skin2rightRun4;
    Image skin2rightRun5;
    Image skin2rightRun6;
    Image skin2rightRun7;
    Image skin2rightRun8;
    Image skin2rightJump1;
    Image skin2rightJump2;
    Image skin2leftStill1;
    Image skin2leftStill2;
    Image skin2leftStill3;
    Image skin2leftStill4;
    Image skin2leftStill5;
    Image skin2leftStill6;
    Image skin2leftStill7;
    Image skin2leftStill8;
    Image skin2leftRun1;
    Image skin2leftRun2;
    Image skin2leftRun3;
    Image skin2leftRun4;
    Image skin2leftRun5;
    Image skin2leftRun6;
    Image skin2leftRun7;
    Image skin2leftRun8;
    Image skin2leftJump1;
    Image skin2leftJump2;

    Image skin3rightStill1;
    Image skin3rightStill2;
    Image skin3rightStill3;
    Image skin3rightStill4;
    Image skin3rightStill5;
    Image skin3rightStill6;
    Image skin3rightStill7;
    Image skin3rightStill8;
    Image skin3rightRun1;
    Image skin3rightRun2;
    Image skin3rightRun3;
    Image skin3rightRun4;
    Image skin3rightRun5;
    Image skin3rightRun6;
    Image skin3rightRun7;
    Image skin3rightRun8;
    Image skin3rightJump1;
    Image skin3rightJump2;
    Image skin3leftStill1;
    Image skin3leftStill2;
    Image skin3leftStill3;
    Image skin3leftStill4;
    Image skin3leftStill5;
    Image skin3leftStill6;
    Image skin3leftStill7;
    Image skin3leftStill8;
    Image skin3leftRun1;
    Image skin3leftRun2;
    Image skin3leftRun3;
    Image skin3leftRun4;
    Image skin3leftRun5;
    Image skin3leftRun6;
    Image skin3leftRun7;
    Image skin3leftRun8;
    Image skin3leftJump1;
    Image skin3leftJump2;
    boolean facingRight;
    boolean airborne;
    int jumpAnimationCount;
    int moveAnimationCount;
    int idleAnimationCount;
    boolean dead;
    boolean win;
    boolean skin2Bought;
    boolean skin3Bought;
    int equippedSkin;

    /**
     * primary constructor
     */
    public Player() {
        super(325, 213, 64, 64, "GroundImg"); //chain to superclass constructor
        //no paramters needed ass dino will always start with default skin, at that
        //x,y position and with that width and height
        coins = 160; //set other attributes to defualts
        hearts = 3;
        heartImage = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/playerHeart.png")).getImage();
        jumpHeight = -10;
        maxSpeed = 7;
        loadSkins();
        facingRight = true;
        airborne = false;
        jumpAnimationCount = 0;
        moveAnimationCount = 0;
        idleAnimationCount = 0;
        dead = false;
        win = false;
        skin2Bought = false;
        skin3Bought = false;
        equippedSkin = 1;
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
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1rightJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2rightJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3rightJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    }
                } else {
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1rightJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2rightJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3rightJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    }
                }
            } else {
                if (jumpAnimationCount < 6) {
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1leftJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2leftJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3leftJump1, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    }
                } else {
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1leftJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2leftJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3leftJump2, SCREENXPOSITION, SCREENYPOSITION, null);//incomplete placeholder rectangle currently
                    }
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
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1rightStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2rightStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3rightStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    }
                } else {
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1leftStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2leftStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3leftStill.get(idleAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
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
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1rightRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2rightRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3rightRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    }
                } else {
                    if (equippedSkin == 1) {
                        g2d.drawImage(skin1leftRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 2) {
                        g2d.drawImage(skin2leftRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
                    } else if (equippedSkin == 3) {
                        g2d.drawImage(skin3leftRun.get(moveAnimationCount / 5), SCREENXPOSITION, SCREENYPOSITION, null);
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
                    playSound("jumpSound");//plays jump sound effect
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
                    airborne = false;//reset airborne to false and reset animation counter
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

    private void loadSkins() {
        skin1rightStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur1.png")).getImage();
        skin1rightStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur2.png")).getImage();
        skin1rightStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur3.png")).getImage();
        skin1rightStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur4.png")).getImage();
        skin1rightStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur5.png")).getImage();
        skin1rightStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur6.png")).getImage();
        skin1rightStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur7.png")).getImage();
        skin1rightStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur8.png")).getImage();
        skin1rightStill.add(skin1rightStill1);
        skin1rightStill.add(skin1rightStill2);
        skin1rightStill.add(skin1rightStill3);
        skin1rightStill.add(skin1rightStill4);
        skin1rightStill.add(skin1rightStill5);
        skin1rightStill.add(skin1rightStill6);
        skin1rightStill.add(skin1rightStill7);
        skin1rightStill.add(skin1rightStill8);
        skin1rightRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur9.png")).getImage();
        skin1rightRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur10.png")).getImage();
        skin1rightRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur11.png")).getImage();
        skin1rightRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur12.png")).getImage();
        skin1rightRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur13.png")).getImage();
        skin1rightRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur14.png")).getImage();
        skin1rightRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur15.png")).getImage();
        skin1rightRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur16.png")).getImage();
        skin1rightRun.add(skin1rightRun1);
        skin1rightRun.add(skin1rightRun2);
        skin1rightRun.add(skin1rightRun3);
        skin1rightRun.add(skin1rightRun4);
        skin1rightRun.add(skin1rightRun5);
        skin1rightRun.add(skin1rightRun6);
        skin1rightRun.add(skin1rightRun7);
        skin1rightRun.add(skin1rightRun8);
        skin1rightJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur17.png")).getImage();
        skin1rightJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur18.png")).getImage();
        skin1leftStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur1Left.png")).getImage();
        skin1leftStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur2Left.png")).getImage();
        skin1leftStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur3Left.png")).getImage();
        skin1leftStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur4Left.png")).getImage();
        skin1leftStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur5Left.png")).getImage();
        skin1leftStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur6Left.png")).getImage();
        skin1leftStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur7Left.png")).getImage();
        skin1leftStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur8Left.png")).getImage();
        skin1leftStill.add(skin1leftStill1);
        skin1leftStill.add(skin1leftStill2);
        skin1leftStill.add(skin1leftStill3);
        skin1leftStill.add(skin1leftStill4);
        skin1leftStill.add(skin1leftStill5);
        skin1leftStill.add(skin1leftStill6);
        skin1leftStill.add(skin1leftStill7);
        skin1leftStill.add(skin1leftStill8);
        skin1leftRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur9Left.png")).getImage();
        skin1leftRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur10Left.png")).getImage();
        skin1leftRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur11Left.png")).getImage();
        skin1leftRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur12Left.png")).getImage();
        skin1leftRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur13Left.png")).getImage();
        skin1leftRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur14Left.png")).getImage();
        skin1leftRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur15Left.png")).getImage();
        skin1leftRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur16Left.png")).getImage();
        skin1leftRun.add(skin1leftRun1);
        skin1leftRun.add(skin1leftRun2);
        skin1leftRun.add(skin1leftRun3);
        skin1leftRun.add(skin1leftRun4);
        skin1leftRun.add(skin1leftRun5);
        skin1leftRun.add(skin1leftRun6);
        skin1leftRun.add(skin1leftRun7);
        skin1leftRun.add(skin1leftRun8);
        skin1leftJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur17Left.png")).getImage();
        skin1leftJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur18Left.png")).getImage();

        skin2rightStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur1.png")).getImage();
        skin2rightStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur2.png")).getImage();
        skin2rightStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur3.png")).getImage();
        skin2rightStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur4.png")).getImage();
        skin2rightStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur5.png")).getImage();
        skin2rightStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur6.png")).getImage();
        skin2rightStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur7.png")).getImage();
        skin2rightStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur8.png")).getImage();
        skin2rightStill.add(skin2rightStill1);
        skin2rightStill.add(skin2rightStill2);
        skin2rightStill.add(skin2rightStill3);
        skin2rightStill.add(skin2rightStill4);
        skin2rightStill.add(skin2rightStill5);
        skin2rightStill.add(skin2rightStill6);
        skin2rightStill.add(skin2rightStill7);
        skin2rightStill.add(skin2rightStill8);
        skin2rightRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur9.png")).getImage();
        skin2rightRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur10.png")).getImage();
        skin2rightRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur11.png")).getImage();
        skin2rightRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur12.png")).getImage();
        skin2rightRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur13.png")).getImage();
        skin2rightRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur14.png")).getImage();
        skin2rightRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur15.png")).getImage();
        skin2rightRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur16.png")).getImage();
        skin2rightRun.add(skin2rightRun1);
        skin2rightRun.add(skin2rightRun2);
        skin2rightRun.add(skin2rightRun3);
        skin2rightRun.add(skin2rightRun4);
        skin2rightRun.add(skin2rightRun5);
        skin2rightRun.add(skin2rightRun6);
        skin2rightRun.add(skin2rightRun7);
        skin2rightRun.add(skin2rightRun8);
        skin2rightJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur17.png")).getImage();
        skin2rightJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur18.png")).getImage();
        skin2leftStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur1Left.png")).getImage();
        skin2leftStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur2Left.png")).getImage();
        skin2leftStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur3Left.png")).getImage();
        skin2leftStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur4Left.png")).getImage();
        skin2leftStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur5Left.png")).getImage();
        skin2leftStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur6Left.png")).getImage();
        skin2leftStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur7Left.png")).getImage();
        skin2leftStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur8Left.png")).getImage();
        skin2leftStill.add(skin2leftStill1);
        skin2leftStill.add(skin2leftStill2);
        skin2leftStill.add(skin2leftStill3);
        skin2leftStill.add(skin2leftStill4);
        skin2leftStill.add(skin2leftStill5);
        skin2leftStill.add(skin2leftStill6);
        skin2leftStill.add(skin2leftStill7);
        skin2leftStill.add(skin2leftStill8);
        skin2leftRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur9Left.png")).getImage();
        skin2leftRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur10Left.png")).getImage();
        skin2leftRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur11Left.png")).getImage();
        skin2leftRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur12Left.png")).getImage();
        skin2leftRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur13Left.png")).getImage();
        skin2leftRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur14Left.png")).getImage();
        skin2leftRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur15Left.png")).getImage();
        skin2leftRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur16Left.png")).getImage();
        skin2leftRun.add(skin2leftRun1);
        skin2leftRun.add(skin2leftRun2);
        skin2leftRun.add(skin2leftRun3);
        skin2leftRun.add(skin2leftRun4);
        skin2leftRun.add(skin2leftRun5);
        skin2leftRun.add(skin2leftRun6);
        skin2leftRun.add(skin2leftRun7);
        skin2leftRun.add(skin2leftRun8);
        skin2leftJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur17Left.png")).getImage();
        skin2leftJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/RedDinosaur18Left.png")).getImage();

        skin3rightStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur1.png")).getImage();
        skin3rightStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur2.png")).getImage();
        skin3rightStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur3.png")).getImage();
        skin3rightStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur4.png")).getImage();
        skin3rightStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur5.png")).getImage();
        skin3rightStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur6.png")).getImage();
        skin3rightStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur7.png")).getImage();
        skin3rightStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur8.png")).getImage();
        skin3rightStill.add(skin3rightStill1);
        skin3rightStill.add(skin3rightStill2);
        skin3rightStill.add(skin3rightStill3);
        skin3rightStill.add(skin3rightStill4);
        skin3rightStill.add(skin3rightStill5);
        skin3rightStill.add(skin3rightStill6);
        skin3rightStill.add(skin3rightStill7);
        skin3rightStill.add(skin3rightStill8);
        skin3rightRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur9.png")).getImage();
        skin3rightRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur10.png")).getImage();
        skin3rightRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur11.png")).getImage();
        skin3rightRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur12.png")).getImage();
        skin3rightRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur13.png")).getImage();
        skin3rightRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur14.png")).getImage();
        skin3rightRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur15.png")).getImage();
        skin3rightRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur16.png")).getImage();
        skin3rightRun.add(skin3rightRun1);
        skin3rightRun.add(skin3rightRun2);
        skin3rightRun.add(skin3rightRun3);
        skin3rightRun.add(skin3rightRun4);
        skin3rightRun.add(skin3rightRun5);
        skin3rightRun.add(skin3rightRun6);
        skin3rightRun.add(skin3rightRun7);
        skin3rightRun.add(skin3rightRun8);
        skin3rightJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur17.png")).getImage();
        skin3rightJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur18.png")).getImage();
        skin3leftStill1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur1Left.png")).getImage();
        skin3leftStill2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur2Left.png")).getImage();
        skin3leftStill3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur3Left.png")).getImage();
        skin3leftStill4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur4Left.png")).getImage();
        skin3leftStill5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur5Left.png")).getImage();
        skin3leftStill6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur6Left.png")).getImage();
        skin3leftStill7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur7Left.png")).getImage();
        skin3leftStill8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur8Left.png")).getImage();
        skin3leftStill.add(skin3leftStill1);
        skin3leftStill.add(skin3leftStill2);
        skin3leftStill.add(skin3leftStill3);
        skin3leftStill.add(skin3leftStill4);
        skin3leftStill.add(skin3leftStill5);
        skin3leftStill.add(skin3leftStill6);
        skin3leftStill.add(skin3leftStill7);
        skin3leftStill.add(skin3leftStill8);
        skin3leftRun1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur9Left.png")).getImage();
        skin3leftRun2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur10Left.png")).getImage();
        skin3leftRun3 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur11Left.png")).getImage();
        skin3leftRun4 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur12Left.png")).getImage();
        skin3leftRun5 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur13Left.png")).getImage();
        skin3leftRun6 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur14Left.png")).getImage();
        skin3leftRun7 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur15Left.png")).getImage();
        skin3leftRun8 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur16Left.png")).getImage();
        skin3leftRun.add(skin3leftRun1);
        skin3leftRun.add(skin3leftRun2);
        skin3leftRun.add(skin3leftRun3);
        skin3leftRun.add(skin3leftRun4);
        skin3leftRun.add(skin3leftRun5);
        skin3leftRun.add(skin3leftRun6);
        skin3leftRun.add(skin3leftRun7);
        skin3leftRun.add(skin3leftRun8);
        skin3leftJump1 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur17Left.png")).getImage();
        skin3leftJump2 = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/YellowDinosaur18Left.png")).getImage();
    }

}
