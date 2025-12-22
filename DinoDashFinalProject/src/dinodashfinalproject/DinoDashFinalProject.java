/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dinodashfinalproject;

//import Packages
import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 *
 * @author JaAhn8882
 */
public class DinoDashFinalProject extends JFrame {

    
    
    /**
     * Default constructor
     */
    public DinoDashFinalProject() {
        //create the User interface
        initUI();
    }
    
    /**
     * Create the custom JFrame and set some options
     */
    private void initUI() {        
        //set title of the JFrame
        setTitle("Dino Dash");
        //add a custom JPanel to draw on
        add(new GamePanel());
        //set the size of the window
        setSize(700, 525);
        //make it visible 
        setVisible(true);
        //make it so they cant resize the window
        setResizable(false);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {        
        //makes sure that GUI updates nicely with the rest of the OS
        EventQueue.invokeLater(() -> {
            //create the JFrame
            DinoDashFinalProject frame = new DinoDashFinalProject();
        });
    }

}
