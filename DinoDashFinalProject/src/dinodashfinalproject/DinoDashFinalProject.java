/*Araib, Bernie, Jay
 * January 13 2026
 * Main class for the dinodash game which is the main jFrame
 */
package dinodashfinalproject;

//import Packages
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.ImageIcon;


/* Jay Ahn, Araib Basit, Bernie Gao
 * Dec 29 2025
 * Main Class that displays the game
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
        setLocationRelativeTo(null);//center window on screen
        Image icon = new ImageIcon(getClass().getResource("/dinodashfinalproject/DarkGreenDinosaur1.png")).getImage();//get icon image to dark green dinosaur
        this.setIconImage(icon);//sets icon image to dark green dinosaur
        GamePanel.playBackgroundSound("menu");//play backgroudn music
    }

    public static void main(String[] args) {
        //makes sure that GUI updates nicely with the rest of the OS
        EventQueue.invokeLater(() -> {
            //create the JFrame
            DinoDashFinalProject frame = new DinoDashFinalProject();
        });
    }

}
