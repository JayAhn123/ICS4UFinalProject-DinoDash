/* Araib, Jay, Bernie
 * January 12 2026
 * button object for menus in the game that extends game object
 */
package dinodashfinalproject;

//import necessary packages
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Button extends GameObject {

    //attributes
    private Image imgHover;
    private String imageHoverName;

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
        //make image
        URL url = GameObject.class.getResource(imageHoverName + ".png");
        ImageIcon pic = new ImageIcon(url);
        imgHover = pic.getImage();
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

    /**
     * this method sets the imageHoverName
     *
     * @param imageHoverName - the new imageHoverName
     */
    public void setImageHoverName(String imageHoverName) {
        this.imageHoverName = imageHoverName; //update imageHoverName
    }

    /**
     * this method gets the imageHoverName
     *
     * @return - the imageHoverName
     */
    public String getImageHoverName() {
        return imageHoverName; //return imageHoverName
    }

    /**
     * this method sets the hover image
     *
     * @param imgHover - the new hover image
     */
    public void setImgHover(Image imgHover) {
        this.imgHover = imgHover; //update img
    }

    /**
     * this method gets the hover image
     *
     * @return - the hover image
     */
    public Image getImgHover() {
        return imgHover; //return img
    }

    /**
     * toString method that formats button into string
     *
     * @return - formatted string about the button
     */
    public String toString() {
        return super.toString() + "\n" + imageHoverName + "\n" + img.toString();
    }

    /**
     * equals method for button
     *
     * @param obj - other button checking for equality
     * @return - boolean whether they are equal or not
     */
    public boolean equals(Button obj) {
        //if two buttons have same attributes
        if (super.equals(obj) && this.imageHoverName.equals(obj.imageHoverName) && this.imageName.equals(obj.imageName)) {
            return true;//return true
        } else {//else they are not equal
            return false;//return false
        }
    }

}
