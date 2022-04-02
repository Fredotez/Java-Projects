/*
*@author Frederic Desjardins
*@date November 22th 2020
*Filename: OthelloNetworkModalViewController.java
*Course: CST8221 Java Apllication Programming
* Assignment: 2 part 1
*/


package othello;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import othello.OthelloViewController;




public class OthelloNetworkModalViewController extends JDialog
{

    /** Whether the user pressed the Connect button. */
    private Boolean hasConnected=false;
    
    /** A reference to the private inner Controller class for use by the two buttons. */
    private Controller handler = new Controller();
    
    /** The CombobBox you will need to create.*/
    //NOTE:  You're free to rename it, but as there are some references to it in this stub,
    //you'll need to be consistent when renaming them all.
    private JComboBox portInput;
    
    /** The text field where the user will enter the hostname to connect to.*/
    //As above, you're free to rename this.  But be careful.
    private JTextField addressInput;

    
    private JTextField nameInput;
    
    
    private JLabel statusLabel;

    
    private boolean validPort = false;
    /** Constructor for OthelloNetworkModalViewController 
    * @author Frederic Desjardins
    * Instantiate the UI and all its components
    */

public OthelloNetworkModalViewController (JFrame mainView)
    {
        //In Swing, it's ideal if we provide reference frame this will sit atop.
        //The title isn't relevant since we want this to be an undecorated dialog.
        super(mainView,"Enter Network Address",true);
        
        this.setResizable(false);
        
        //Important note!  Uncomment this line ONLY when you're nearly ready.
        //It'll be a lot harder to get rid of the modal when it's undecorated.
        //So save uncommenting this for nearly last, when you've debugged everything
        //and you're doing your final testing.
        
        //setUndecorated(true); 
        
        
        //This will hold your UI.  You may rename it if you want to.
        Container networkPanel = getContentPane();
        
      
        //Now you're on your own!  Put your own UI in here.
        //Stick to GridLayout, BorderLayout and FlowLayout this
        //time around.
       
        JPanel ui = new JPanel(new BorderLayout());
        ui.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));        
         
        /*Address Section*/
        JPanel addressLabelSection = new JPanel(new FlowLayout());        
        JLabel addressLabel = new JLabel("Address:");
        addressLabelSection.add(addressLabel);
        
        JPanel addressInputSection = new JPanel(new FlowLayout());
        addressInput = new JTextField();        
        addressInput.setPreferredSize(new Dimension(300, (addressInput.getPreferredSize().height + 5)));
        addressInputSection.add(addressInput);   
        
        
        JPanel addressSection = new JPanel(new BorderLayout());
        addressSection.add(addressLabelSection, BorderLayout.WEST);
        addressSection.add(addressInputSection, BorderLayout.CENTER);
        /*End of address Section*/
        
        /*Port Section*/
        JPanel portLabelSection = new JPanel(new FlowLayout());
        JLabel portLabel = new JLabel("Port:    ");
        portLabelSection.add(portLabel);
        
        String[] defaultPorts = {"31000","41000","51000"};
        
        JPanel portInputSection = new JPanel(new FlowLayout());
        portInput = new JComboBox<>(defaultPorts);        
        portInput.setEditable(true);
        //portInput.setPreferredSize(new Dimension(80, (addressInput.getPreferredSize().height)));
        portInputSection.add(portInput);
        
        JPanel portSection = new JPanel(new FlowLayout());
        portSection.add(portLabelSection);
        portSection.add(portInputSection);
        /*End of Port Section*/
        
        
        /*Name Section*/
        JPanel nameLabelSection = new JPanel(new FlowLayout());        
        JLabel nameLabel = new JLabel("Name:");
        nameLabelSection.add(nameLabel);
        
        JPanel nameInputSection = new JPanel(new FlowLayout());
        nameInput = new JTextField();        
        nameInput.setPreferredSize(new Dimension(300, (addressInput.getPreferredSize().height + 5)));
        nameInputSection.add(nameInput);
        
        JPanel nameSection = new JPanel(new BorderLayout());
        nameSection.add(nameLabelSection, BorderLayout.WEST);
        nameSection.add(nameInputSection, BorderLayout.CENTER);
        /*End of Name Section*/
        
        JPanel statusLabelSection = new JPanel(new FlowLayout());
        statusLabel = new JLabel("Status:");
        statusLabelSection.add(statusLabel, BorderLayout.EAST);
        
        JPanel parentStatusLabelSection = new JPanel(new BorderLayout());
        parentStatusLabelSection.add(statusLabelSection, BorderLayout.NORTH);
        
        JTextArea statusArea = new JTextArea();
        statusArea.setPreferredSize(new Dimension(statusArea.getSize().width, 60));
        statusArea.setBackground(getContentPane().getBackground());
        statusArea.setEditable(false);
        parentStatusLabelSection.add(statusArea, BorderLayout.SOUTH);
        
        JPanel buttonSection = new JPanel(new FlowLayout());
        JButton connect = new JButton("Connect");
        connect.setActionCommand("C");
        connect.addActionListener(handler);
        
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("X");
        cancel.addActionListener(handler);
        
        buttonSection.add(connect);
        buttonSection.add(cancel);
        
        JPanel parentButtonSection = new JPanel(new BorderLayout());
        parentButtonSection.add(buttonSection, BorderLayout.SOUTH);
        
       
        JPanel subPanelSouth = new JPanel(new BorderLayout());
               
        subPanelSouth.add(parentStatusLabelSection, BorderLayout.WEST);
        subPanelSouth.add(parentButtonSection, BorderLayout.EAST);
        
        JPanel subPanelNorth = new JPanel(new BorderLayout());
        subPanelNorth.add(addressSection, BorderLayout.NORTH);
        subPanelNorth.add(portSection, BorderLayout.WEST);
        subPanelNorth.add(nameSection, BorderLayout.SOUTH);
        
        ui.add(subPanelNorth, BorderLayout.CENTER);    
       
        ui.add(subPanelSouth, BorderLayout.SOUTH);          
        
        networkPanel.add(ui);
        
        
        //This statement should be the last one.
        pack();
    }

    @Override
     public String getName()
    {
       
        if (hasConnected)
        {
            return (nameInput.getText());
        }
        else
        {
            //You can return whatever error message you like.  This isn't cast in stone.
            return ("Error:  Invalid Name or attempt cancelled.");
        }
    }
        

    /** This method returns the value the user has entered.
        @return The actual value, unless there was an error or the user pressed the cancel button.
    */

    public String getAddress()
    {
        
        if (hasConnected)
        {
            return (addressInput.getText());
        }
        else
        {
            //You can return whatever error message you like.  This isn't cast in stone.
            return ("Error:  Invalid Address or attempt cancelled.");
        }
    }

    /** This method returns the port the user has selected OR ENTERED in the Combo Box.
    @return The port selected.  Returns -1 on invalid port or cancel pressed.
    */
    
    public int getPort()
    {
        int portNum;
        if (hasConnected)
        {
            //Ensure the user has entered digits.
            //You should probably also ensure the port numbers are in the correct range.
            //I did not.  That's from 0 to 65535, by the way.  Treat it like invalid input.
            try
            {
                portNum = Integer.parseInt((String)portInput.getSelectedItem());
                if (portNum >= 0 && portNum <=65535) {
                   validPort = true;
                } else {
                validPort = false;
                portNum = -1;
                }
                
            }
                catch(NumberFormatException nfe)
            {
                //I've been using a negative portnum as an error state in my main code.
                portNum = -1;
                validPort = false;
            }

            return portNum;
        }
        return -1;
    }
    
    /** Responsible for final cleanup and hiding the modal. Does not do much at the moment.*/
    public void hideModal()
    {
        //Add any code that you may want to do after the user input has been processed
        //and you're figuratively closing up the shop.
        setVisible(false);
        
    }
    
    /** Returns whether or not the user had pressed connect.
    @return True if the user pressed Connect, false if the user backed out with cancel.
    */
    public boolean pressedConnect()
    {
        return hasConnected;
    }

    
    /** The Controller for managing user input in the network dialogue.
    @author Daniel Cormier
    @version 1.3
    @since 1.8.0_261
    @see OthelloViewController
    */
   
    private class Controller implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            String s = evt.getActionCommand();
                      
            //I set the action command on my connect button to "C".
            if ("C".equals(s))
            {                
                //System.out.println("Connected Pressed");
                //In Assignment 2-2, we will be making revisions here.
                //This would be a great place to update the "Status" portion of the UI.
                hasConnected=true;
                
                if (!validPort) {
                    statusLabel.setText("Error: Valid Port Ranges are from 0 to 65535");
                }
            } 
            else //My "Cancel" button has an action command of "X" and gets called here.
            {

                hasConnected=false;
                
            }
            //Hide the modal. For part 2, we may not want to hide the modal right away.
            hideModal();
        }
        
    }
}
        

        

