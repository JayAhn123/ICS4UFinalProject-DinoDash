/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

//import packages
import java.awt.Graphics2D;

public class Ground extends GameObject {

    /**
     * primary constructor
     *
     * @param x - the x position on the map where the ground will be
     * @param y - the y position on the map where the ground will be
     * @param width - the width of the ground
     */
    public Ground(int x, int y, int width) {
        super(x, y, width, 50, "GroundImg");//chain to superclass constructor height is always 50
    }

    /**
     * Method that will clone the ground object into a new one
     *
     * @return - a new ground object
     */
    public Ground clone() {
        return new Ground(x, y, width);//return new ground object
    }

    /**
     * this method will draw the ground to the screen
     *
     * @param g2d - the Graphics2D object use to draw
     */
    public void draw(Graphics2D g2d) {
        //dino will always be in middle of screen so the object will move around the dino thus needing an offset that will change when dino moves
        for (int i = x - xOffset; i < x + width - xOffset; i += 50) {//for loop that will draw the ground piece by peice until it reaches the width
            g2d.drawImage(img, i, y - yOffset, null);//draw the image at correct position
        }
    }
}
