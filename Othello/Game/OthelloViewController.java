/*
*@author Frederic Desjardins
*@date November 15th 2020
*Filename: OthelloViewController.java
*Course: CST8221 Java Apllication Programming
*Assignment: 1 part 2
*/

package othello;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/** Controller for Othello 
 * @author Frederic Desjardins
 * This class is responsible for handling changes to the UI
 * 
*/
public class OthelloViewController extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private final int width = 1010;
    private final int height = 620;
       
    String white = "Images/white_s.png";
    
    String black = "Images/black_s.png";
    
    String dog = "Images/dog.png";
    
    String cat = "Images/cat.png";
    
    String pumpkin = "Images/pumpkin.png";
    
    String bats = "Images/bat.png";
    
    String checkMarkPath = "Images/checkmark.png";
    
    String p1FilePath = black;
    
    String p2FilePath = white;
    
    ImageIcon p1Piece = new ImageIcon(p1FilePath);
    
    ImageIcon p2Piece = new ImageIcon(p2FilePath);
    
    ImageIcon checkMark = new ImageIcon(checkMarkPath);
    
    Dimension frameSize = new Dimension(width,height);
    
    Dimension boardSize = new Dimension(480,480);
    
    Dimension submitSize = new Dimension(1010,50);
    
    Dimension squareSize = new Dimension(60,60);
    
    FlowLayout boardSectionLayout = new FlowLayout(FlowLayout.LEFT);
    GridLayout checkerBoardLayout = new GridLayout(8,8);
    BorderLayout frameLayout = new BorderLayout();
       
    OthelloModel model = new OthelloModel();
    
     //Creates the checker board
    JLabel[][] checkerBoard = new JLabel[8][8];
    
    ButtonGroup scenarios = new ButtonGroup();
    
    public JTextArea gameDetails;
    String textArea = "Player 1 has been initilized with 2 pieces\nPlayer 2 has been initilized with 2 pieces\n";
     
    JLabel pi1, pi2;
    
    private OthelloNetworkController connection;
    
    JTextField submitField;
    
    /** Constructor for Othello 
    * @author Frederic Desjardins
    * Instantiate the UI and all its components
    */
  
    public OthelloViewController() {
        
        JFrame othello = new JFrame( "Checker Board" );
        othello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        othello.setBackground(Color.DARK_GRAY);
        //othello.setLayout(new BorderLayout(4,4));         
        othello.setMinimumSize(frameSize);
        othello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        othello.setLocationRelativeTo(null);
        othello.setResizable(false);
        
        Controller controller = new Controller();
        
        JMenuBar gameSettings = new JMenuBar();
        othello.setJMenuBar(gameSettings);
        
        /******************************/
        
        JMenu file = new JMenu("File");
        gameSettings.add(file);
        
        JMenuItem newGame = new JMenuItem("New Game");
        file.add(newGame);
        newGame.addActionListener(controller);
        
        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        load.setEnabled(false);        
        //load.addActionListener(controller);
        
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.setEnabled(false);
        //save.addActionListener(controller);
        
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        /******************************/
        JMenu game = new JMenu("Game");
        gameSettings.add(game);
        
        JMenu reskinPieces = new JMenu("Reskin Pieces");
        game.add(reskinPieces);
        
        ButtonGroup reskins = new ButtonGroup();
        
        JRadioButton normalPieces = new JRadioButton("Normal Pieces");
        reskins.add(normalPieces);
        reskinPieces.add(normalPieces);
        normalPieces.addActionListener(controller);
        
        JRadioButton cats = new JRadioButton("Cats vs Dogs");
        reskins.add(cats);
        reskinPieces.add(cats);
        cats.addActionListener(controller);
        
        JRadioButton bats = new JRadioButton("Pumpkins vs Bats");
        reskins.add(bats);
        reskinPieces.add(bats);
        bats.addActionListener(controller);
        /******************************/
        
        JMenu debugScenarios = new JMenu("Debug Scenarios");
               
        
        JRadioButton normal = new JRadioButton("Normal Game");
        scenarios.add(normal);
        debugScenarios.add(normal);
        normal.addActionListener(controller);
        
        
        JRadioButton corner = new JRadioButton("Corner Test");
        scenarios.add(corner);
        debugScenarios.add(corner);
        corner.addActionListener(controller);
        
        JRadioButton side = new JRadioButton("Side Test");
        scenarios.add(side);
        debugScenarios.add(side);
        side.addActionListener(controller);
        
        JRadioButton captureOne = new JRadioButton("1x Capture Test");
        scenarios.add(captureOne);
        debugScenarios.add(captureOne);
        captureOne.addActionListener(controller);
        
        JRadioButton captureTwo = new JRadioButton("2x Capture Test");
        scenarios.add(captureTwo);
        debugScenarios.add(captureTwo);
        captureTwo.addActionListener(controller);
        
        JRadioButton empty = new JRadioButton("Empty Board");
        scenarios.add(empty);
        debugScenarios.add(empty);
        empty.addActionListener(controller);
        
        JRadioButton inner = new JRadioButton("Inner Capture Test");
        scenarios.add(inner);
        debugScenarios.add(inner);
        inner.addActionListener(controller);
        
      
        game.add(debugScenarios);
        
        /******************************/
        JMenu network = new JMenu("Network");
        gameSettings.add(network);
        
        JMenuItem newConnection = new JMenuItem("New Connection");
        network.add(newConnection);
        newConnection.addActionListener(controller);
        
        
        JMenuItem disconnect = new JMenuItem("Disconnect");
        disconnect.addActionListener(controller);
        network.add(disconnect);

        
        /******************************/
        
        JMenu help = new JMenu("Help");
        gameSettings.add(help);
        
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        about.addActionListener(controller);
      
        /******************************/
        
        JPanel boardSection = new JPanel(new BorderLayout());
        boardSection.setBackground(Color.GRAY);
        boardSection.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        //Panel with checkerboard
        JPanel board = new JPanel();
        //board.setPreferredSize(boardSize);
        board.setLayout(checkerBoardLayout);
        //board.setOpaque(true);
        board.setBackground(Color.GRAY);
        board.setVisible(true);
        
        boardSection.add(board, BorderLayout.WEST); 
        
        int boxPosition = 1;
        
        for (int i = 0; i < checkerBoard[0].length; i++) {            
     
            for (int j = 0; j < checkerBoard[1].length; j++) {
               
                JLabel box = new JLabel();
                
                box.setPreferredSize(squareSize);
                box.setOpaque(true);
                //box.setBackground(Color.black);
                 box.setVisible(true);
                   
                //checkerBoard[i][j] = box;
                
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    box.setBackground(Color.BLACK);
                } else {
                    box.setBackground(Color.WHITE);
                }
                
               
                checkerBoard[i][j] = box;
                board.add(box);
                boxPosition++;

              }

        }    
             
        
        //Y Buttons
        JPanel yBoard = new JPanel(new GridLayout(8,1));
        //yBoard.setPreferredSize(new Dimension(60,480));        
           yBoard.setBackground(Color.GRAY);
        //yBoard.setOpaque(true);
        //yBoard.setLayout(checkerBoardLayout);
        yBoard.setVisible(true);       
        
        for (int j = 0; j < 8; j++) {

               
                String text = ""+(j+1);
                String ActionCommand = "" + (j+1);
                JButton moveRow = createButton(text,ActionCommand,Color.BLACK,new JButton().getBackground(), controller);
                moveRow.setPreferredSize(squareSize);      
                yBoard.add(moveRow);
            
              } 
        
        boardSection.add(yBoard, BorderLayout.CENTER);
      
        //X Buttons
        JPanel xBoard = new JPanel(new GridLayout(1,9));
       
        String[] letters = new String[]{"A","B","C","D","E","F","G","H","Move"};
        Font moveButtonFont = new Font("Arial", Font.PLAIN, 10);
        
        ButtonGroup yButtons = new ButtonGroup();
        
        for (int j = 0; j < 9; j++) {

                
                String ActionCommand = letters[j];
                JButton moveColumn = createButton(letters[j],ActionCommand,Color.BLACK,new JButton().getBackground(), controller);
                moveColumn.setPreferredSize(new Dimension(60,60));  
                                
                         
                if (j == 8) {
                
                    moveColumn.setBackground(Color.WHITE);
                    moveColumn.setFont(moveButtonFont);
                } 
                xBoard.add(moveColumn);
            
              } 
        
        boardSection.add(xBoard, BorderLayout.SOUTH);
        othello.add(boardSection,BorderLayout.WEST);
        
        initializeBoard(0, 0);
        //Submit section with textfield and button
        JPanel submit = new JPanel(new BorderLayout());
        submit.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        submitField = new JTextField();  
        
       
        
        JButton submitButton = createButton("Submit","Submit",Color.RED,Color.BLACK, controller);
        
                
        submit.add(submitField, BorderLayout.CENTER);
        submit.add(submitButton, BorderLayout.EAST);
        
        othello.add(submit, BorderLayout.SOUTH);
         
        //Section for output and player pieces
        JPanel outputSection = new JPanel(new BorderLayout(2,2));
        outputSection.setBackground(Color.GRAY);
        othello.add(outputSection,BorderLayout.CENTER);  
  
        //Panel for checkbox and Label
        JPanel validMoves = new JPanel(new FlowLayout(FlowLayout.LEFT));
        validMoves.setPreferredSize(new Dimension(0,40));
        //validMoves.setBackground(Color.DARK_GRAY);
        outputSection.add(validMoves,BorderLayout.NORTH); 
        
        JCheckBox valid = new JCheckBox();
        validMoves.add(valid);
        
        valid.addActionListener(controller);
        valid.setActionCommand("validSelected");
        
        
        JLabel label = new JLabel("Show Valid Moves");
        validMoves.add(label);
        
        
        validMoves.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        JPanel initPieces = new JPanel(new BorderLayout());
        
        initPieces.setBackground(Color.PINK);   
        initPieces.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        gameDetails = new JTextArea(textArea);
        gameDetails.setBackground(Color.PINK);  
        gameDetails.setEditable(false);
        
        initPieces.add(gameDetails, BorderLayout.CENTER);
               
        
        outputSection.add(initPieces,BorderLayout.CENTER);
        
        JPanel pieces = new JPanel(new BorderLayout(0,0));
  
        pieces.setBackground(Color.RED);
        outputSection.add(pieces,BorderLayout.SOUTH);
        pieces.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        JPanel player1 = new JPanel(new BorderLayout());
        player1.setPreferredSize(new Dimension(0,50));
        //player1.setBackground(Color.YELLOW);
        pieces.add(player1,BorderLayout.NORTH);
        
        JLabel player1Pieces = new JLabel("Player 1 Pieces:");
        player1.add(player1Pieces,BorderLayout.CENTER);        
      
        pi1 = new JLabel(p1Piece);
        //pi.setVisible(true);
        pi1.setText("2  ");
        
        player1.add(pi1, BorderLayout.EAST);
        
        JPanel player2 = new JPanel(new BorderLayout());
        player2.setPreferredSize(new Dimension(0,50));
        //player2.setBackground(Color.LIGHT_GREY);
        pieces.add(player2,BorderLayout.SOUTH);
        
        JLabel player2Pieces = new JLabel("Player 2 Pieces:");        
        player2.add(player2Pieces,BorderLayout.CENTER);        
        
        pi2 = new JLabel(p2Piece);
        //pi.setVisible(true);
        pi2.setText("2  ");
        
                
        player2.add(pi2,BorderLayout.EAST);
        othello.setVisible(true);

    }
    /** JButton createButton
    * @author Frederic Desjardins
    * is responsable for creating buttons.
    * returns type JButton
    * called in OthelloViewController()
    */
       
        private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler){
        // Creates the button with the passed text argument
        JButton button = new JButton(text);
       
        if(ac != null) {
            button.setActionCommand(ac);
        }
        
        // Sets colors and fonts of button
        button.setBackground(bg);
        button.setForeground(fg);
        button.addActionListener(handler);
        
        return button;
    }
     /** void updateBoard
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * @param selected - if show valid moves checkbox is selected
    * updates board with new values after events
    * returns type void
    * called in actionPerformed()
    */

    public void updateBoard(int x, int y, int player, boolean selected) {
        
       
       for (int i = 0; i < 8; i++) {

           for (int j = 0; j < 8; j++) {

               switch(model.getBoard(i,j)) {

                   case 0:
                        if (model.isValid(i,j,player) && selected == true)
                            checkerBoard[i][j].setIcon(checkMark);
                        else 
                            checkerBoard[i][j].setIcon(null);
                       break;
                   case 1:
                       checkerBoard[i][j].setIcon(p1Piece);
                       checkerBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   case 2:
                       checkerBoard[i][j].setIcon(p2Piece);
                       checkerBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                       break;
               }

           }
       }
    }

    
    /** boolean playerMove
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * is responsible for creating buttons.
    * returns type boolean
    * called in actionPerformed()
    */
        
    public boolean playerMove(int x, int y, int player) {
        
        boolean playerMove = false;
        int chipsCaptured = model.move(x,y,player);
       
        
        if (chipsCaptured > 0) {
           textArea += ("Player " + player + " has captured " + chipsCaptured + " pieces\n");
           playerMove = true;
       } else {        
            textArea += ("Invalid move.\n");
            playerMove = false;
        }
        
       
      
        gameDetails.setText(textArea);
        return playerMove;
    }
    
    /** boolean checkEndOfGame()
    * @author Frederic Desjardins
    * Checks if game has ended and determines a winner.
    * returns type boolean
    * called in actionPerformed()
    */
    
    public boolean checkEndOfGame(int player) {
     
        boolean endOfGame = false;
        
        if (model.canMove(player)) {
            endOfGame = false;
        } else {
            endOfGame = true;
            int blackChips = model.getChips(1);
            int whiteChips = model.getChips(2);
            
            if (blackChips > whiteChips) {
                textArea += "Black Wins!\n";
            } else if (blackChips < whiteChips) {
                textArea += "White Wins!\n";
            } else {
                textArea += "Tie game!\n";
            }       
            
            textArea += "End of game \n\n\n";
            textArea += "Select (new game) to play again.";
        }
        
        gameDetails.setText(textArea);
        return endOfGame;
        
    }
      
    /** void InitializeBoard
    * @author Frederic Desjardins
    * @param reskin: 
    * select skin of pieces
    * @param mode:
    * debug scenario
    * Initializes board at the start of the game.
    * returns type void
    * called in actionPerformed()
    */
    public void initializeBoard(int mode, int reskin) {

       model.initialize(mode);
    
       
       if (reskin == 0) {
           p1FilePath = black;
           p2FilePath = white;
       } else if (reskin == 1) {
           p1FilePath = pumpkin;
           p2FilePath = bats;
       } else if (reskin == 2) {
           p1FilePath = cat;
           p2FilePath = dog;
       }
        
        
        p2Piece = new ImageIcon(p2FilePath);
        p1Piece = new ImageIcon(p1FilePath);
        checkMark = new ImageIcon(checkMarkPath);
        

       for (int i = 0; i < 8; i++) {

           for (int j = 0; j < 8; j++) {

               switch(model.getBoard(i,j)) {

                   case 0:
                       checkerBoard[i][j].setIcon(null);
                       checkerBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   case 1:
                       checkerBoard[i][j].setIcon(p1Piece);
                       checkerBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                       break;
                   case 2:
                       checkerBoard[i][j].setIcon(p2Piece);
                       checkerBoard[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                       break;
               }

           }
       }

    }
    

    OthelloNetworkModalViewController networkDialog = new OthelloNetworkModalViewController(this);  
    
    
    
   /** Class Controller
    * @author Frederic Desjardins
    * This class handles all events that happen in game.
    * 
    */
    
class Controller implements ActionListener {
    
    int reskin= 0;
    int debugScene = 0;   
    
    int moveX = 0;
    int moveY = 0;
    int player = 1;
    
     int blackChips;
     int whiteChips;
    
    boolean validMoveSelected = false;
    
    
    /** Class actionPerfomed
    * @author Frederic Desjardins
    * This implements a switch to detect which events occur.
    * Calls methods from OthelloViewController as needed.
    * 
    */
   
    public void actionPerformed(ActionEvent e) {              
       

        switch (e.getActionCommand()) {
            case "Normal Game":
                debugScene = 0; break;
            case "Corner Test":
                debugScene = 1; break;
            case "Side Test":
                debugScene = 2; break;
            case "1x Capture Test":
                debugScene = 3; break;
            case "2x Capture Test":
                debugScene = 4; break;
            case "Empty Board":
                debugScene = 5; break;
            case "Inner Capture Test":
                debugScene = 6; break;
            case "Normal Pieces":
                reskin = 0; break;
            case "Pumpkins vs Bats": 
                System.out.println("pump");
                reskin = 1; break;
            case "Cats vs Dogs":
                reskin = 2; break;
            case "New Game":
               
                blackChips = model.getChips(1);
                whiteChips = model.getChips(2);
                
                
                pi1.setText(blackChips + " ");
                pi2.setText(whiteChips + " ");
                
                player = 1;   
                textArea = "";
                initializeBoard(debugScene, reskin);
                textArea += "Player 1 has been initilized with " + blackChips + " pieces\n";
                textArea += "Player 2 has been initilized with "+ whiteChips + " pieces\n";
                gameDetails.setText(textArea);
                checkEndOfGame(player);
                break;
            case "A":
                moveX = 0;
                break;
            case "B":
                moveX = 1;
                break;
            case "C":
                moveX = 2;
                break;
            case "D":
                moveX = 3;
                break;
            case "E":
                moveX = 4;
                break;
            case "F":
                moveX = 5;
                break;
            case "G":
                moveX = 6;
                break;
            case "H":
                moveX = 7;
                break;
            case "1":
                moveY = 0;
                break;
            case "2":
                moveY = 1;
                break;
            case "3":
                moveY = 2;
                break;
            case "4":
                moveY = 3;
                break;
            case "5":
                moveY = 4;
                break;
            case "6":
                moveY = 5;
                break;
            case "7":
                moveY = 6;
                break;
            case "8":
                moveY = 7;
                break;
            case "Move":
                
                
                if (playerMove(moveY,moveX,player)) {
                    if (player == 1) {
                        player = 2;
                    } else {
                        player = 1;
                    }
                }
                updateBoard(moveX,moveY,player,validMoveSelected);
                
                blackChips = model.getChips(1);
                whiteChips = model.getChips(2);
                pi1.setText(blackChips + " ");
                pi2.setText(whiteChips + " ");
                checkEndOfGame(player);
                break;
            case "Skip":
                if (player == 1) {
                        player = 2;
                    } else {
                        player = 1;
                    }
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "Othello Game \nby Frederic Desjardins\nNovember 2020", "About", JOptionPane.PLAIN_MESSAGE);
                break;
            case "validSelected" :
                if (validMoveSelected == false) {
                    validMoveSelected = true;
                } else {
                    validMoveSelected = false;
                }
                updateBoard(moveX,moveY,player,validMoveSelected);
                break;
            case "Exit" :
                System.exit(0);
            case "New Connection":
                              
                Point thisLocation = getLocation();
                Dimension parentSize = getSize();
                Dimension dialogSize = networkDialog.getSize();
                int offsetX = (parentSize.width-dialogSize.width)/2+thisLocation.x;
                int offsetY = (parentSize.height-dialogSize.height)/2+thisLocation.y;
                networkDialog.setLocation(offsetX,offsetY);
                networkDialog.setVisible(true); 

                networkDialog.setResizable(false);  
                
                if (networkDialog.pressedConnect()) {  
                    
                    String address = networkDialog.getAddress();
                    int port;
                    try {
                    port = networkDialog.getPort();
                    //String clientName = "Fredo";                   
                    } catch (NumberFormatException nf) { 
                        textArea += "Invalid port, using default port: 62000\n";
                         port = 62000;
                    }
                    String clientName = networkDialog.getName();
                                        
                    textArea += "Negotiating Connection to " + address + " on Port " + port + "\n";
                                        
                    try {
                        
                        connection = new OthelloNetworkController(address,port,clientName, gameDetails);
                        
                        if (connection.connected == true) {
                            Thread thread = new Thread(connection);           
                            thread.start(); 
                        } else {
                            textArea += "Connection failed.\n";
                        }
                                                        
                    } 
                    catch (IOException ex) {
                        ex.printStackTrace();
                    } 
                }
                        
                gameDetails.setText(textArea);
                break;
                
            case "Disconnect":
                gameDetails.append("Disconnecting from network connection.");
                break;
                
            case "Submit":              

                
                String input = submitField.getText();
                
                try {
                connection.sendMessage(input);
                } catch (NullPointerException np) {
                    System.out.println("You are not connected to Server");
                }
                break;
            default:
                System.out.println(e.getActionCommand());
                break;
        }
    }
        
}
    
    
}



