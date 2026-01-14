/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

/**
 *
 * @author mubas
 */
public class Heart extends GameItem {

    /**
     * primary constructor for heart object
     *
     * @param x - x position of heart item
     * @param y - y position of heart item
     */
    public Heart(int x, int y) {
        super(x, y, "Heart");//chain to superclas constructor image will always be the same as a heart image
    }

    /**
     * method that checks if the player collides with the heart and then add 1
     * to the heart count if the player does
     *
     * @param player - the player
     */
    public void collisionProcedure(Player player) {
        if (visible) {//check only if its visible
            if (player.hitbox.intersects(hitbox)) {//check if they collide
                if (player.getHearts() < 5) {
                    player.setHearts(player.getHearts() + 1);//if they do add 1 to player heart count
                    playSound("heartCollect");//makes sound effect when user collects heart
                }
                visible = false;//set visible to false if they collect the heart
            }
        }
    }

    /**
     * clone method that will return a new heart that is the same
     *
     * @return - the new heart item
     */
    public Heart clone() {
        return new Heart(x, y);//return new heart with same attributes
    }

}
