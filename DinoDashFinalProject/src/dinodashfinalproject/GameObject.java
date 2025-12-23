/* Jay Ahn
 * Dec 19, 2025
 * GameObject Class for Dino Dash
 */
package dinodashfinalproject;

//import packages needed
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class GameObject {

    //attributes
    int x;//x position of the game object
    int y;//y position of the game object
    int width;//width of the game object
    int height;//height of the game object
    Rectangle hitbox; //the hitbox of the game object which is a rectangle type object
    String imageName;
    Image img; //the image that should be displayed for the object

    /**
     * Primary Constructor that instantiates game object
     *
     * @param width - width of the game object
     * @param height - height of the game object
     * @param imageName - the name of the image that should be displayed for the
     * object
     */
    public GameObject(int width, int height, String imageName) {
        x = 0; //set all attributes
        y = 0;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x, y, width, height);
        this.imageName = imageName;
        img = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/" + this.imageName + ".jpg")).getImage();
    }

    /**
     * Secondary Constructor that instantiates game object
     *
     * @param x - x coordinate for game object
     * @param y - y coordinate for game object
     * @param width - width for game object
     * @param height - height for game object
     * @param imageName - the name of the image file for the object that should
     * be displayed
     */
    public GameObject(int x, int y, int width, int height, String imageName) {
        this(width, height, imageName); //chain to primary constructor then set rest of attributes
        this.x = x;
        this.y = y;
        hitbox.setLocation(this.x, this.y);
    }

    //Getters
    /**
     * gets x position of the game object
     *
     * @return - x position of the game object
     */
    protected int getX() {
        return x;//returns x position
    }

    /**
     * gets y position of the game object
     *
     * @return - y position of the game object
     */
    protected int getY() {
        return y;//returns y position
    }

    /**
     * gets width of the game object
     *
     * @return - width of the game object
     */
    protected int getWidth() {
        return width;//returns width
    }

    /**
     * gets height of the game object
     *
     * @return - height of the game object
     */
    protected int getHeight() {
        return height;//returns height
    }

    /**
     * gets hitbox of the game object
     *
     * @return - hitbox of the game object
     */
    protected Rectangle getHitbox() {
        return hitbox;//returns the rectangle
    }

    //Setters
    /**
     * sets x position of the game object
     *
     * @param x - new x position
     */
    protected void setX(int x) {
        this.x = x;//sets x position
        hitbox.setLocation(x, y); //updates hitbox location
    }

    /**
     * sets y position of the game object
     *
     * @param y - new y position
     */
    protected void setY(int y) {
        this.y = y;//sets y position
        hitbox.setLocation(x, y);//updates hitbox
    }

    /**
     * sets width of the game object
     *
     * @param width - new width
     */
    protected void setWidth(int width) {
        this.width = width;//sets width
    }

    /**
     * sets height of the game object
     *
     * @param height - new height
     */
    protected void setHeight(int height) {
        this.height = height;//sets height
    }

    /**
     * sets hitbox of the game object
     *
     * @param hitbox - new hitbox
     */
    protected void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;//sets hitbox
    }

    /**
     * determines if other game object is equal to current game object
     *
     * @param other - other game object that is being compared
     * @return - boolean; true if they are equal, false if they are not equal
     */
    protected boolean equals(GameObject other) {
        //if attributes of current game object and other game object is equal
        if (this.x == other.x && this.y == other.y && this.width == other.width && this.height == other.height && this.imageName.equals(other.imageName)) {
            return true;//return true
        } else {//else
            return false;//return false
        }
    }

    /**
     * Abstract method that is supposed to clone the object
     *
     * @return
     */
    protected abstract GameObject clone();

    /**
     * Method that draws the GameObject at its spot
     *
     * @param g2d the 2DGrpahics object you are drawing on
     */
    protected void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);//draw the object
    }

}
