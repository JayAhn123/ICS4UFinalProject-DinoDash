/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

//import necessary packages
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author
 */
public class Button extends GameObject {

    //attributes
    Image imgHover;
    String imageHoverName;

    /**
     * Primary constructor for the button object
     *
     * @param x the x position
     * @param y the y position
     * @param width the width
     * @param height the height
     * @param imageName the name of the image to be displayed
     * @param imageHoverName the name of the image to be displayed when the
     * mouse is hovering over the button
     */
    public Button(int x, int y, int width, int height, String imageName, String imageHoverName) {
        super(x, y, width, height, imageName); //chain to superclass constructor
        this.imageHoverName = imageHoverName; //set rest of attributes
        imgHover = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/" + this.imageHoverName + ".png")).getImage();
    }

    /**
     * This method draws the button
     *
     * @param g2d - the Graphics2D object we are drawing on
     * @param mouseX - the x position of the mouse
     * @param mouseY - the y position of the mouse
     */
    public void draw(Graphics2D g2d, int mouseX, int mouseY) {
        //if the mouse is on the button draw the hover version otherwise drar the normal version
        if (hitbox.contains(mouseX, mouseY)) {
            g2d.drawImage(imgHover, x, y, null);//draw the button with the image when its being hovered on
        } else {
            g2d.drawImage(img, x, y, null);//draw the button
        }
    }

    /**
     * This method clones the current button into a new one
     *
     * @return - the new cloned button
     */
    public Button clone() {
        return new Button(x, y, width, height, imageName, imageHoverName); //return the new button
    }

    /**
     * this checks if the mouse position is on the button and is only called
     * after mouse was released so if the mouse is on the button when it was
     * released that means they clicked this button
     *
     * @param mouseX - x location of the mouse
     * @param mouseY - y location of the mouse
     * @return - Boolean of true if it is on the button false if not
     */
    public boolean wasClicked(int mouseX, int mouseY) {
        return hitbox.contains(mouseX, mouseY); //return if the hitbox includes the mouse current coordinates
    }

}
