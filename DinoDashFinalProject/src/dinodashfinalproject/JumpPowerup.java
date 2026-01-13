/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

/**
 *
 * @author mubas
 */
public class JumpPowerup extends GameItem {

    //attributes
    private long startTime;
    private long currentTime;
    private long timeActivated;

    /**
     * primary constructor for jumpPowerup object
     *
     * @param x - x position of jumpPowerup item
     * @param y - y position of jumpPowerup item
     */
    public JumpPowerup(int x, int y) {
        super(x, y, "JumpPowerup");//chain to superclas constructor image will always be the same as a JumpPowerup image
    }

    /**
     * method that checks if the player collides with the powerup and then make
     * the player jump higher for 5 seconds if they do collide
     *
     * @param player - the player
     */
    public void collisionProcedure(Player player) {
        if (visible) { // if its visible and has not been collected yet
            if (player.hitbox.intersects(hitbox)) {//check if they collide
                GameObject.playSound("jumpCollect");//plays sound effect when user collects jump boost
                player.setJumpHeight(-13);//if they do make the player jump higher
                startTime = System.nanoTime();//get the start time of the when the powerup started
                visible = false;//set visible to false
            }

        } else if (!visible && player.getJumpHeight() != -10) {//if its not visible which means it has already been collected
            //and the player jump hieght has not already been reset back then this runs

            //get current time
            currentTime = System.nanoTime();
            //see how long it has lasted for
            timeActivated = currentTime - startTime;
            //if it  is more than 5 seconds
            if (timeActivated / 100000000 > 50) {
                player.setJumpHeight(-10);//reset jump heigh back to normal
            }
        }
    }

    /**
     * clone method that will return a new jumpPowerup that is the same
     *
     * @return - the new jumpPowerup item
     */
    public GameObject clone() {
        return new JumpPowerup(x, y); // return new jumpPowerup
    }
}
