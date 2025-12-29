/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

import java.awt.Graphics2D;

/**
 *
 * @author mubas
 */
public abstract class GameItem extends GameObject {

    //attributes
    protected boolean visible;

    public GameItem(int x, int y, String imageName) {
        super(x, y, 25, 25, imageName);
        visible = true;
        hitbox.x++;
        hitbox.y++;
        hitbox.width -= 2;
        hitbox.height -= 2;
    }

    public abstract void collisionProcedure(Player player);

    public void draw(Graphics2D g2d) {
        if (visible) {
            g2d.drawImage(img, x - xOffset, y - yOffset, null);//draw the image at correct position
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
