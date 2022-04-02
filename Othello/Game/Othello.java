/*
*@author Frederic Desjardins
*@date November 15th 2020
*Filename: Othello.java
*Course: CST8221 Java Apllication Programming
* Assignment: 1 part 2
*/
package othello;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**Othello class
 *
 * @author Frederic Desjardins
 */
public class Othello {
    
    /** responsible for launching application
     * @param args
     * 
     */
    public static void main(String[] args) {
    
        /*
     int duration = 5000;
    if(args.length == 1){
    	try{
    	 duration = Integer.parseInt(args[0]);
    	}catch (NumberFormatException nfe){
    	  System.out.println("Wrong command line argument: must be an integer number");
    	  System.out.println("The default duration 10000 milliseconds will be used");
    	  //mfe.printStackTrace();	
    	} 
    }
    // Create the screen
    OthelloSplashScreen splashWindow = new OthelloSplashScreen(duration);
    //Show the Splash screen 
    splashWindow.showSplashWindow();
    //Create and display the main application GUI
    EventQueue.invokeLater(new Runnable(){
       @Override
       public void run(){ 	
        JFrame frame = new JFrame("Application Frame");
        frame.setMinimumSize(new Dimension(700, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//screen center
      //frame.setLocationByPlatform(true);
        frame.setVisible(true);  
       }
     });
    
    */
      OthelloViewController view = new OthelloViewController();
      
 
      
     
  }//end main
}// end SplashScreenDemo class

    

