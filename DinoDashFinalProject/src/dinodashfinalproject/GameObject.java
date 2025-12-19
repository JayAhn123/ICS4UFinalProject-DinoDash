package dinodashfinalproject;

/* Jay Ahn
 * Dec 19, 2025
 * GameObject Class for Dino Dash
 */
public class GameObject {

    //attributes
    int x;//x position of the game object
    int y;//y position of the game object
    int width;//width of the game object
    int height;//height of the game object

    /**
     * Primary Constructor that instantiates game object
     *
     * @param width - width of the game object
     * @param height - height of the game object
     */
    public GameObject(int width, int height) {
        x = 0;
        y = 0;
        this.width = width;
        this.height = height;
    }

    /**
     * Secondary Constructor that instantiates game object
     *
     * @param x - x coordinate for game object
     * @param y - y coordinate for game object
     * @param width - width for game object
     * @param height - height for game object
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Getters
    /**
     * gets x position of the game object
     *
     * @return - x position of the game object
     */
    public int getX() {
        return x;//returns x position
    }

    /**
     * gets y position of the game object
     *
     * @return - y position of the game object
     */
    public int getY() {
        return y;//returns y position
    }

    /**
     * gets width of the game object
     *
     * @return - width of the game object
     */
    public int getWidth() {
        return width;//returns width
    }

    /**
     * gets height of the game object
     *
     * @return - height of the game object
     */
    public int getHeight() {
        return height;//returns height
    }

    //Setters
    /**
     * sets x position of the game object
     *
     * @param x - new x position
     */
    public void setX(int x) {
        this.x = x;//sets x position
    }

    /**
     * sets y position of the game object
     *
     * @param y - new y position
     */
    public void setY(int y) {
        this.y = y;//sets y position
    }

    /**
     * sets width of the game object
     *
     * @param width - new width
     */
    public void setWidth(int width) {
        this.width = width;//sets width
    }

    /**
     * sets height of the game object
     *
     * @param height - new height
     */
    public void setHeight(int height) {
        this.height = height;//sets height
    }

    /**
     * determines if other game object is equal to current game object
     *
     * @param other - other game object that is being compared
     * @return - boolean; true if they are equal, false if they are not equal
     */
    public boolean equals(GameObject other) {
        //if attributes of current game object and other game object is equal
        if (this.x == other.x && this.y == other.x && this.width == other.width && this.height == other.height) {
            return true;//return true
        } else {//else
            return false;//return false
        }
    }

    /**
     *
     * @return
     */
    public GameObject clone() {

    }

}
