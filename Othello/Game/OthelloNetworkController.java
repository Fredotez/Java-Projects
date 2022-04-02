/*
*@author Frederic Desjardins
*@date December 12th 2020
*Filename: OthelloNetworkController.java
*Course: CST8221 Java Apllication Programming
* Assignment: 2 part 2
*/
package othello;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

 /**
 * @author Frederic Desjardins
 * purpose: This class handles client side connection to the server
 */
public class OthelloNetworkController implements Runnable {
    
    int port = 62000;
    String address = "";
    
    Socket client;
    
    String submitTextFieldInput = "";
    
    PrintWriter clientOut;
    
    Scanner clientIn;
    Scanner keyboard;
    
    String clientName;
    
    JTextArea gameDetails;
    
    boolean connected = false;
    
    //Thread connect; 
    
 /**
 * @author Frederic Desjardins
 * purpose: Constructor for OthelloNetworkController, connects to server using Socket
 */
    
    public OthelloNetworkController(String address, int port, String name, JTextArea gameUpdates) throws IOException {
        
        System.out.println("Connecting on address: " + address + ", Port: " + port);
        try {
        clientName = name;
        
        client = new Socket(address,port);
        System.out.println("Created Socket from controller.");
        connected = true;
        clientIn = new Scanner(client.getInputStream());      
        
        clientOut = new PrintWriter(client.getOutputStream(), true);
        
        gameDetails = gameUpdates;
        
        clientOut.println(clientName);
        } catch (ConnectException ce) {
            connected = false;
            System.out.println("Server is not available at the moment");
            
            
        } 
        
        
 
    }
    
 /**
 * @author Frederic Desjardins
 * purpose: Run() is called when OthelloNetworkController thread is started. 
 * monitors messages from server and displays on JTextArea.
 */
    
    @Override
    public void run() {
           
        int counter = 0;         
        
        System.out.println("IN THREAD...");
        
        try {
        
        while (true) {          
        
                      
            if (clientIn.hasNextLine()) {
               
                String serverIn = clientIn.nextLine();
                gameDetails.append("\n" + serverIn);
            } else {
                
                break;
            }

                   
        } 
        } catch (NullPointerException np) {
            System.out.println("Server is not available at the moment");
        } finally {
            try {   
                gameDetails.append("\nServer has disconnected, closing connection...");
                client.close();
                gameDetails.append("\nConnection closed.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       
        
        
    }
    
 /**
 * @author Frederic Desjardins
 * purpose: Used to get input from the text field passed as text and sends a message to the server.
 */    
    public void sendMessage(String text) {
        
        clientOut.println(text);
    }
    
    
}
