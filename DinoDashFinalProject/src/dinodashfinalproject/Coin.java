/* Araib, Jay, Bernie
 * January 14 2026
 * coin class for game
 */
package dinodashfinalproject;

/**
 *
 * @author
 */
public class Coin extends GameItem {

    /**
     * primary constructor for Coin object
     *
     * @param x - x position of coin
     * @param y - y position of coin
     */
    public Coin(int x, int y) {
        super(x, y, "Coin");//chain to superclas constructor image will always be the same as a coin image
    }

    /**
     * method that checks if the player collides with the coin and then add 1 to
     * the coin count if the player does
     *
     * @param player - the player
     */
    public void collisionProcedure(Player player) {
        if (visible) {//check only if its visible
            if (player.hitbox.intersects(hitbox)) {//check if they collide
                playSound("coinSound");//plays sound effect when user gets a coin
                player.setCoins(player.getCoins() + 1);//if they do add 1 to player coin count
                visible = false;//set visible to false if they collect the coin
            }
        }
    }

    /**
     * clone method that will return a new coin that is the same
     *
     * @return - the new coin
     */
    public Coin clone() {
        return new Coin(x, y);//return new coin with same attributes
    }

}
