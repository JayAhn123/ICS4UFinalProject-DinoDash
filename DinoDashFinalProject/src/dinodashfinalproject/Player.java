/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author mubas
 */
public class Player extends GameObject {

    //attributes
    private static final int screenXPosition = 325;
    private static final int screenYPosition = 213;
    double xSpeed = 0;
    double ySpeed = 0;

    public Player(int x, int y, String skin) {
        super(x, y, 50, 100, skin);
    }

    public Player clone() {
        return new Player(x, y, imageName);
    }

    public void draw(Graphics2D g2d) {
        g2d.fillRect(screenXPosition, screenYPosition, width, height);
    }

    public void move(boolean up_pressed, boolean down_pressed, boolean left_pressed, boolean right_pressed, ArrayList<Ground> groundTiles) {
        if (left_pressed == right_pressed) {
            xSpeed *= 0.8;
        } else if (left_pressed) {
            xSpeed--;
        } else if (right_pressed) {
            xSpeed++;
        }

        if ((xSpeed > 0 && xSpeed < 0.75) || (xSpeed < 0 && xSpeed > -0.75)) {
            xSpeed = 0;
        }

        if (xSpeed > 7) {
            xSpeed = 7;
        } else if (xSpeed < -7) {
            xSpeed = -7;
        }

        if (up_pressed) {

            hitbox.y++;

            for (Ground groundTile : groundTiles) {
                if (groundTile.hitbox.intersects(hitbox)) {
                    ySpeed = -6;
                }
            }

            hitbox.y--;
        }

        //gravity
        ySpeed += 0.3;

        //collisions with ground
        //horizontal
        hitbox.x += xSpeed;
        for (Ground groundTile : groundTiles) {
            if (groundTile.hitbox.intersects(hitbox)) {
                hitbox.x -= xSpeed;
                while (!groundTile.hitbox.intersects(hitbox)) {
                    hitbox.x += Math.signum(xSpeed);
                }
                hitbox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitbox.x;
            }
        }

        //vertical
        hitbox.y += ySpeed;
        for (Ground groundTile : groundTiles) {
            if (groundTile.hitbox.intersects(hitbox)) {
                hitbox.y -= ySpeed;
                while (!groundTile.hitbox.intersects(hitbox)) {
                    hitbox.y += Math.signum(ySpeed);
                }
                hitbox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitbox.y;
            }
        }

        x = hitbox.x;
        y = hitbox.y;

    }

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public static int getScreenXPosition() {
        return screenXPosition;
    }

    public static int getScreenYPosition() {
        return screenYPosition;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

}
