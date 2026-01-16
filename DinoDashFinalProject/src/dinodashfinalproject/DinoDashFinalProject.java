/* Araib, Bernie, Jay
 * January 13 2026
 * Main class for the dinodash game which is the main jFrame
 */
package dinodashfinalproject;

//import Packages
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
        GamePanel panel = new GamePanel();
        add(panel);
        //set the size of the window
        setSize(700, 525);
        //make it visible 
        setVisible(true);
        //make it so they cant resize the window
        setResizable(false);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add window listener that will then save then the window is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {//when window closes
                save(panel);//call save method
            }
        });

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

    /**
     * this method saves the player
     *
     * @param panel - the gamePanel
     */
    public void save(GamePanel panel) {
        try {
            //make a connection to file
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Saves/playerSave.txt");
            //make objectoutput to write the player to the file with
            ObjectOutput s = new ObjectOutputStream(out);
            s.writeObject(panel.getPlayer());//write the player object to the file

        } catch (IOException e) {
            //catch and show error
            JOptionPane.showMessageDialog(null, "Error: " + e);

        }

    }

}
