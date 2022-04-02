/*
*@author Frederic Desjardins
*@date November 22th 2020
*Filename: OthelloSplashScreen.java
*Course: CST8221 Java Apllication Programming
* Assignment: 2 part 1
*/

package othello;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**Othello splash screen
 * @author Svillen Ranev, Daniel Cormier
 * @author Frederic Desjardins
 */


public class OthelloSplashScreen extends JWindow {
 /** Swing components are serializable and require serialVersionUID */
  private static final long serialVersionUID = 6248477390124803341L;
  /** Splash screen duration */
  private final int duration;
/**
  Default constructor. Sets the show time of the splash screen.
  * @param duration - how long the splash screen will run for
*/
  public OthelloSplashScreen(int duration) {
    this.duration = duration;
  }
/**
 Shows a splash screen in the center of the desktop
 for the amount of time given in the constructor.
*/
  public void showSplashWindow() {
   //create content pane
     JPanel content = new JPanel(new BorderLayout());
   // or use the window content pane
   //  JPanel content = (JPanel)getContentPane();
     content.setBackground(Color.GRAY);
    
    // Set the window's bounds, position the window in the center of the screen
    int width =  750;
    int height = 450;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    //set the location and the size of the window
    setBounds(x,y,width,height);

/* Create the splash screen by loading an image into the JLabel.
 * There are many ways of going about this.  You can experiment by
 * commenting out our line X and uncommenting one of the other JLabel lines.
 */
      
      
// accessing an image in a package named splash without using getResource - needs an absolute path 
// This does make it difficult to relocate the code, however. Relative paths are almost always better.
// JLabel label = new JLabel(new ImageIcon("c:\\...\\SplashSwing.gif"));

//image placed in the package source folder - presuming you were using a package.
//You'll want to adjust the directory name to match whatever package you used.
//JLabel label = new JLabel(new ImageIcon(getClass().getResource("/splash/SplashSwing.gif"))); 

//image placed in folder named resources placed into the source folder
//JLabel label = new JLabel(new ImageIcon(getClass().getResource("/splash/resources/SplashSwing.gif")));

//using URL with an image placed in resources folder in source folder
//Breaking the process down into steps:
//    URL imgURL = this.getClass().getResource("resources/SplashSwing.gif");
//    Image img = Toolkit.getDefaultToolkit().getImage(imgURL);
//    JLabel label = new JLabel(new ImageIcon(img));

//accessing an image in the default packge
//works if compiled and run from command prompt - not if run from Netbeans
    JLabel label = new JLabel(new ImageIcon("othello/Othello.png"));

    //JLabel label = new JLabel(new ImageIcon(getClass().getResource("C:/Users/fmdes/Documents/NetBeansProjects/Othello/src/Batman.gif"))); 
  
    JLabel demo = new JLabel("Welcome to Frederic's Othello game", JLabel.CENTER);
    demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
    content.add(label, BorderLayout.CENTER);
    content.add(demo, BorderLayout.SOUTH);
    // create custom RGB color
    Color customColor = new Color(44, 197, 211);
    content.setBorder(BorderFactory.createLineBorder(customColor, 10));
    
    //replace the window content pane with the content JPanel
      setContentPane(content);

    //make the splash window visible
    setVisible(true);

    // Snooze for awhile, pretending the code is loading something awesome while
    // our splashscreen is entertaining the user.
    try {
    	
    	 Thread.sleep(duration); }
    catch (InterruptedException e) {/*log an error here?*//*e.printStackTrace();*/}
    //destroy the window and release all resources
    dispose(); 
    //You can hide the splash window. The resources will not be released.
    //setVisible(false);
  }

}// end SplashScreenDemo class