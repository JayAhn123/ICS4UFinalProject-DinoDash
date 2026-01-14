/* Jay Ahn. Araib, Bernie
 * Jan 13, 2025
 * GameObject Class for Dino Dash
 */
package dinodashfinalproject;

//import packages needed
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class GameObject {

    //attributes
    protected int x;//x position of the game object
    protected int y;//y position of the game object
    protected int width;//width of the game object
    protected int height;//height of the game object
    protected Rectangle hitbox; //the hitbox of the game object which is a rectangle type object
    protected String imageName; // the name of the image
    protected Image img; //the image that should be displayed for the object
    protected static int xOffset;//x and y offset for the moving parts of the game
    protected static int yOffset;

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
        this.imageName = imageName;//make image
        img = new ImageIcon(this.getClass().getResource("/dinodashfinalproject/" + this.imageName + ".png")).getImage();
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
        hitbox.width = width; //update hitbox
    }

    /**
     * sets height of the game object
     *
     * @param height - new height
     */
    protected void setHeight(int height) {
        this.height = height;//sets height
        hitbox.height = height;// update hitbox
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
     * this method sets the xOffset
     *
     * @param offset - the new x offset
     */
    public static void setXOffset(int offset) {
        xOffset = offset; //update offset
    }

    /**
     * this method sets the yOffset
     *
     * @param offset - the new y offset
     */
    public static void setYOffset(int offset) {
        yOffset = offset; //update offset
    }

    /**
     * this method gets the x offset
     *
     * @return - the x offset
     */
    public static int getXOffset() {
        return xOffset; //return offset
    }

    /**
     * this method gets the y offset
     *
     * @return - the y offset
     */
    public static int getYOffset() {
        return yOffset; //return offset
    }

    /**
     * this method sets the imageName
     *
     * @param imageName - the new imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName; //update imageName
    }

    /**
     * this method gets the image Name
     *
     * @return - the image Name
     */
    public String getImageName() {
        return imageName; //return imageName
    }

    /**
     * this method sets the image
     *
     * @param image - the new image
     */
    public void setImg(Image image) {
        this.img = image; //update img
    }

    /**
     * this method gets the image
     *
     * @return - the image
     */
    public Image getImg() {
        return img; //return img
    }

    /**
     * determines if other game object is equal to current game object
     *
     * @param other - other game object that is being compared
     * @return - boolean; true if they are equal, false if they are not equal
     */
    public boolean equals(GameObject other) {
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
     * @return - copy of the object
     */
    public abstract GameObject clone();

    /**
     * plays sound effect
     *
     * @param soundName - name of the audio file
     */
    public void playSound(String soundName) {
        try {//attempts to open file and play audio
            File sound = new File("src/dinodashfinalproject/soundEffects/" + soundName + ".wav");//sets new file to sound file
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(sound);//gets audio file and converts it into audio input stream which is java's standard way to read raw audio data
            Clip clip = AudioSystem.getClip();//initialize clip
            clip.open(audioInput);//clip opens the audio input
            clip.start();//plays the sound effect
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {//catches any error that may occur
            System.out.println("Error: " + e);//prints out the error
        }
    }

    /**
     * this method returns the toString
     */
    public String toString() {
        return x + "\n" + y + "\n" + width + "\n" + height + "\n" + hitbox.toString() + "\n" + imageName + "\n"
                + img.toString() + "\n" + xOffset + "\n" + yOffset;
    }
}
