/* Araib, Jay, Bernie
 * January 14 2026
 * abstract gameItem class for game
 */
package dinodashfinalproject;

//import packages
import java.awt.Graphics2D;

/**
 *
 * @author
 */
public abstract class GameItem extends GameObject {

    //attributes
    protected boolean visible;

    /**
     * primary constructor
     *
     * @param x - the x location of the item
     * @param y - the y location of the item
     * @param imageName - the name of the image to be displayed for the item
     */
    public GameItem(int x, int y, String imageName) {
        super(x, y, 25, 25, imageName);//chain to superclass instructor
        visible = true; //set other attributes
        hitbox.x++;//adjust the hitbox to be smaller as it is rectangular in shape
        //and the image will be circular so it will be more accurate
        hitbox.y++;
        hitbox.width -= 2;
        hitbox.height -= 2;
    }

    //abstract method for collisionsnd what happend when a collision happens
    public abstract void collisionProcedure(Player player);

    /**
     * this method resets the gameItem
     */
    public void reset() {
        visible = true;//reset visible
    }

    /**
     * method that will draw the game item at the right spot
     *
     * @param g2d - the graphics2d object to draw on
     */
    public void draw(Graphics2D g2d) {
        if (visible) {//if its visible then draw otherwise dont
            g2d.drawImage(img, x - xOffset, y - yOffset, null);//draw the image at correct 
            //position taking into account the offsets because of the movement of the player
        }
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
     * this method checks if 2 gameitems are the same
     *
     * @param other - the other game item
     * @return - true if they are the same false if not
     */
    public boolean equals(GameItem other) {
        //if they are the same return true otherwise false
        if (super.equals(other) && other.visible == this.visible) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method returns a string of all attributes
     *
     * @return the string of all the attributes
     */
    public String toString() {
        return super.toString() + "\n" + visible;
    }

    //abstract method for cloning game items
    public abstract GameItem clone();
}
