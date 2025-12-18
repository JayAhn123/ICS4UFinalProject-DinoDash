/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinodashfinalproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author arbas9208
 */
public class GamePanel extends JPanel{
    
    /**
     * Does the actual drawing
     *
     * @param g - the Graphics object to draw with
     */
    private void doDrawing(Graphics g) {
        Color brown = new Color(229, 151, 87);
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);

        g2d.fillRect(0, 0, getWidth(), getHeight());
        //draw head
        g2d.setColor(brown);
        g2d.fillOval(310, 100, 80, 80);

        //draw the eyes
        g2d.setColor(Color.white);
        g2d.fillOval(325, 130, 10, 10);
        g2d.fillOval(370, 130, 10, 10);

        //draw pupils
        g2d.setColor(Color.black);
        g2d.fillOval(328, 133, 5, 5);
        g2d.fillOval(373, 133, 5, 5);

        //draw mouth
        g2d.fillOval(325, 150, 50, 11);

        //draw eyebrows
        g2d.drawLine(325, 128, 335, 128);
        g2d.drawLine(370, 128, 380, 128);

        //draw body
        g2d.setColor(brown);
        g2d.fillRoundRect(320, 190, 60, 150, 20, 20);

        //draw arms
        g2d.fillRoundRect(280, 200, 30, 100, 20, 20);
        g2d.fillRoundRect(390, 200, 30, 100, 20, 20);

        //draw legs
        g2d.fillRoundRect(310, 350, 30, 120, 20, 20);
        g2d.fillRoundRect(360, 350, 30, 120, 20, 20);
    }

    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g); //invoke our custom drawing method
    }
}
