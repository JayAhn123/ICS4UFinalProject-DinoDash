/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

import static dinodashfinalproject.GameObject.xOffset;
import static dinodashfinalproject.GameObject.yOffset;
import java.awt.Graphics2D;

/**
 *
 * @author mubas
 */
public class Heart extends GameItem {

    public Heart(int x, int y) {
        super(x, y, "Heart");
    }

    public void collisionProcedure(Player player) {
        if (visible) {
            if (player.hitbox.intersects(hitbox)) {
                player.setHearts(player.getHearts() + 1);
                visible = false;
            }
        }
    }

    public GameObject clone() {
        return new Heart(x, y);
    }

}
