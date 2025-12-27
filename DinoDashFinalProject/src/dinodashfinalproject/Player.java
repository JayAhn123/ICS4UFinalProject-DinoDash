/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

/**
 *
 * @author mubas
 */
public class Player extends GameObject {

    //attributes
    private static final int screenXPosition = 325;
    private static final int screenYPosition = 213;

    public Player(int x, int y, String skin) {
        super(x, y, 50, 100, skin);
    }

    public Player clone() {
        return new Player(x, y, imageName);
    }

}
