/* Araib, Jay, Bernie
 * January 14 2026
 * Jump powerup class that extends game item
 */
package dinodashfinalproject;

public class JumpPowerup extends GameItem {

    //attributes
    private long startTime;
    private long currentTime;
    private long timeActivated;
    private boolean activated;

    /**
     * primary constructor for jumpPowerup object
     *
     * @param x - x position of jumpPowerup item
     * @param y - y position of jumpPowerup item
     */
    public JumpPowerup(int x, int y) {
        super(x, y, "JumpPowerup");//chain to superclas constructor image will always be the same as a JumpPowerup image
        activated = false;//initialize activated to false

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
                playSound("jumpCollect");//plays sound effect when user collects jump boost
                player.setJumpHeight(-13);//if they do make the player jump higher
                startTime = System.nanoTime();//get the start time of the when the powerup started
                visible = false;//set visible to false
                activated = true;//sets active to true

            }

        } else if (!visible && player.getJumpHeight() != -10 && activated) {//if its not visible which means it has already been collected and the boost is active
            //and the player jump hieght has not already been reset back then this runs

            //get current time
            currentTime = System.nanoTime();
            //see how long it has lasted for
            timeActivated = currentTime - startTime;
            //if it  is more than 5 seconds
            if (timeActivated / 100000000 > 50) {
                player.setJumpHeight(-10);//reset jump heigh back to normal
                activated = false;//sets active to false

            }
        }
    }

    /**
     * clone method that will return a new jumpPowerup that is the same
     *
     * @return - the new jumpPowerup item
     */
    public JumpPowerup clone() {
        return new JumpPowerup(x, y); // return new jumpPowerup
    }

    /**
     * this method returns a string of all attributes
     *
     * @return the string of all the attributes
     */
    public String toString() {
        return super.toString() + "\n" + startTime + "\n" + currentTime + "\n" + timeActivated + "\n" + activated;
    }

    /**
     * this is a setter for the activated attribute
     *
     * @param activated - boolean of if its activated or not
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * This is a getter for the activated attribute
     *
     * @return - a boolean true is the powerup is active false if not
     */
    public boolean isActivated() {
        return activated;
    }
}
