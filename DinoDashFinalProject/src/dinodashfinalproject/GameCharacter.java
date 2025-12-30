package dinodashfinalproject;

//import packages
import java.awt.Graphics2D;

/* Jay Ahn
 * Dec 29 2025
 * Game Character class that extends Game object
 */
public abstract class GameCharacter extends GameObject {
//INCOMPLETE 
    //attributes
    protected double xSpeed;
    protected double ySpeed;

    /**
     * Primary constructor that instantiates game character
     *
     * @param x - x position
     * @param y - y position
     * @param width - width of the character
     * @param height - height of the character
     * @param imageName - image name of the character
     * @param xSpeed - x speed of the character
     * @param ySpeed - y speed of the character
     */
    public GameCharacter(int x, int y, int width, int height, String imageName, double xSpeed, double ySpeed) {
        super(x, y, width, height, imageName);//chains constructor from game object class
        this.xSpeed = xSpeed;//sets x speed
        this.ySpeed = ySpeed;//sets y speed
    }

    //getters
    /**
     * gets x speed
     *
     * @return - x speed of the character
     */
    public double getXSpeed() {
        return xSpeed;//returns x speed
    }

    /**
     * gets y speed
     *
     * @return - y speed of the character
     */
    public double getYSpeed() {
        return ySpeed;//returns y speed
    }

    //setters
    /**
     * sets x speed
     *
     * @param xSpeed - new x speed
     */
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;//sets x speed
    }

    /**
     * sets y speed
     *
     * @param ySpeed - new y speed
     */
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;//sets y speed
    }

    //need to add draw, collision, clone, equals
    /**THIS TOSTRING IS NOT GOOD REFINE IT LATER
     * toString method for game character
     *
     * @return - formatted string that has information about the character
     */
    public String toString() {
        return "GameCharacter{" + "xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + '}';
    }
}
