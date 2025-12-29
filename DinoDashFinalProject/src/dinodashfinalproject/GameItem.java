/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

//import packages
import java.awt.Graphics2D;

/**
 *
 * @author mubas
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

}
