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
public class JumpPowerup extends GameItem {

    private long startTime;
    private long currentTime;
    private long timeActivated;

    public JumpPowerup(int x, int y) {
        super(x, y, "JumpPowerup");
    }

    public void collisionProcedure(Player player) {
        if (visible) {
            if (player.hitbox.intersects(hitbox)) {
                player.setJumpHeight(-9);
                startTime = System.nanoTime();
                visible = false;
            }

        } else if (!visible && player.getJumpHeight() != -6) {
            currentTime = System.nanoTime();
            timeActivated = currentTime - startTime;
            if (timeActivated / 100000000 > 50) {
                player.setJumpHeight(-6);
            }
        }
    }

    public GameObject clone() {
        return new JumpPowerup(x, y);
    }
}
