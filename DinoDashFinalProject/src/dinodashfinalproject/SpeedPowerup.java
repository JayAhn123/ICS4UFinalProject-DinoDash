/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

/**
 *
 * @author mubas
 */
public class SpeedPowerup extends GameItem {

    //attributes
    private long startTime;
    private long currentTime;
    private long timeActivated;

    /**
     * primary constructor for SpeedPowerup object
     *
     * @param x - x position of SpeedPowerup item
     * @param y - y position of SpeedPowerup item
     */
    public SpeedPowerup(int x, int y) {
        super(x, y, "SpeedPowerup");//chain to superclas constructor image will always be the same as a SpeedPowerup image
    }

    /**
     * method that checks if the player collides with the powerup and then make
     * the player run faster for 5 seconds if they do collide
     *
     * @param player - the player
     */
    public void collisionProcedure(Player player) {
        if (visible) { // if its visible and has not been collected yet
            if (player.hitbox.intersects(hitbox)) {//check if they collide
                player.setMaxSpeed(12);//if they do make the player run faster
                startTime = System.nanoTime();//get the start time of the when the powerup started
                visible = false;//set visible to false
            }

        } else if (!visible && player.getMaxSpeed() != 7) {//if its not visible which means it has already been collected
            //and the player max speed has not already been reset back then this runs

            //get current time
            currentTime = System.nanoTime();
            //see how long it has lasted for
            timeActivated = currentTime - startTime;
            //if it  is more than 5 seconds
            if (timeActivated / 100000000 > 50) {
                player.setMaxSpeed(7);//reset max speedback to normal
            }
        }
    }

    /**
     * clone method that will return a new SpeedPowerup that is the same
     *
     * @return - the new SpeedPowerup item
     */
    public GameObject clone() {
        return new SpeedPowerup(x, y); // return new SpeedPowerup
    }
}
