/*
*@author Frederic Desjardins
*@date November 22th 2020
*Filename: OthelloViewController.java
*Course: CST8221 Java Apllication Programming
* Assignment: 2 part 1
*/


package othello;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.util.ArrayList;

/* Here is a stub of the OthelloModel, containing all the test boards listed in
 * in the assignment file for your implementation.
 */

public class OthelloModel
{
	private int[][] board = new int[7][7];
	
	public static final int NORMAL=0;
	public static final int CORNER_TEST=1;
	public static final int OUTER_TEST=2;
        public static final int TEST_CAPTURE=3;
	public static final int TEST_CAPTURE2=4;
	public static final int UNWINNABLE=5;
	public static final int INNER_TEST=6;
    
	public static final int EMPTY=0;
	public static final int BLACK=1;
	public static final int WHITE=2;
        
       

	public void initialize(int mode)
	{
		switch (mode)
		{
                    
                /*
                    I have change the bottom left corner to be black/white
                    instead of white/black; Originally once you complete the 2 corners
                    it is impossible to finish the other two because it is blacks turn;
                    
                    */
		case CORNER_TEST: 
			board = new int[][]{
				{2, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 1, 0},
                                {1, 0, 0, 0, 0, 0, 0, 2}};

            break;
		case OUTER_TEST:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
		case INNER_TEST:
			board = new int[][] {
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}};
			break;
		case UNWINNABLE:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
		case TEST_CAPTURE:
			board=new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 2, 0, 2, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
				
		case TEST_CAPTURE2:
			board=new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 2, 2, 1, 2, 1, 1},
				{1, 2, 2, 2, 2, 2, 1, 1},
				{1, 2, 2, 0, 2, 2, 1, 1},
				{1, 2, 2, 2, 2, 1, 1, 1},
				{1, 2, 1, 2, 2, 2, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1}};
				break;
		default:
			board = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				
		}
                
	}
        
        
       /** int getBoard
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * returns content of the board at x,y.
    *
    */
    public int getBoard(int x, int y) {
        
        return board[x][y];
        
    }
       /** boolean canMove
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * checks if player has a valid move
    * 
    */
        
    public boolean canMove(int player) {
        
      boolean canMove = false;
        for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (isValid(i,j,player)) {                
                        canMove = true;
                        return canMove;
                    }
                }
            }
        
      return canMove;
    }
    
     /** boolean isValid
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * checks if the move at x,y is valid for current player;
    * Check in all directions for a piece of the opposite color and one space after for a piece of the same color,
    * A move can only be valid if a piece is captured.
    * 
    */
    
    public boolean isValid(int x, int y, int player) {
         boolean isValid=false;
         int opColor;
         
      if (player == 1) {
        opColor = 2;
    } else {
        opColor = 1;
    }
      
      int xPosition = 0;
      int yPosition = 0;


  //if spot is empty, check if move is valid
  if (board[x][y]==0)
  {
    
     if  (isValid == true) {
        return isValid;
    } else if(x+1<8 &&board[x+1][y]==opColor) //check down
    {
      xPosition = x;
      yPosition = y;
      while (board[xPosition+1][yPosition] == opColor && xPosition < 8)  {
          
         
          if ((xPosition + 2) > 7) {
            isValid=false;
            break;
          } else if (board[xPosition+2][yPosition] == player) {
              
              isValid = true;
              break;
          } else {
            xPosition++;              
          
          }
          
      }
    } 
    
    if  (isValid == true) {
        return isValid;
    } else if(y+1<8 &&board[x][y+1]==opColor)//check right
    {
   
      xPosition = x;
      yPosition = y;
     
          
      while (board[xPosition][yPosition+1] == opColor && yPosition < 8)  {
          
          
          if ((yPosition + 2) > 7) {
            
            isValid=false;
            
            break;
          }
          if (board[xPosition][yPosition+2] == player) {
              
              isValid = true;
              
              break;
          } 
            
            yPosition++;
            
      }
    } 
    
    if  (isValid == true) {
        return isValid;
    } else if (y-1>-1 &&board[x][y-1]==opColor) //check left
    {
      xPosition = x;
      yPosition = y; 
      
      while (board[xPosition][yPosition-1] == opColor && yPosition > -1)  {
          
          if ((yPosition - 2) < 0) {
            isValid=false;
            break;
          }
          
          if (board[xPosition][yPosition-2] == player) {
              
              isValid = true;
              break;
          } 
            yPosition--;

      } 
    } 
    
    if  (isValid == true) {
        return isValid;
    } else if (x-1>-1 &&board[x-1][y]==opColor) //check up
    { 
      
      xPosition = x;
      yPosition = y; 
      
      while (board[xPosition-1][yPosition] == opColor && xPosition > -1)  {
          
          if ((xPosition - 2) < 0) {
            isValid=false;
            break;
          }
          
          if (board[xPosition-2][yPosition] == player) {
              
              isValid = true;
              break;
          } 
            xPosition--;

      }  
 
    } 
    
    if  (isValid == true) {
        return isValid;
    } else if(x-1>-1 && y+1<8 &&board[x-1][y+1]==opColor) //check up right
    {
      
      xPosition = x;
      yPosition = y; 
      
      while (board[xPosition-1][yPosition+1] == opColor && xPosition > -1 && yPosition < 8)  {
          
          
          
          if ((xPosition - 2) < 0 || (yPosition + 2) > 7) {
            isValid=false;
            break;
          }
          
          if (board[xPosition-2][yPosition+2] == player) {
              
              isValid = true;
              break;
          } 
            xPosition--;
            yPosition++;
                 
        
          
      }  
        
      
    } 
    
    if  (isValid == true) {
        return isValid;
    } else if (x+1<8 && y+1<8 &&board[x+1][y+1]==opColor) //check down right 
    {   
      xPosition = x;
      yPosition = y;
      while (board[xPosition+1][yPosition+1] == opColor && xPosition < 8 && yPosition < 8)  {
          
          
          if ((xPosition + 2) > 7 || (yPosition + 2) > 7) {
            isValid=false;
            break;
          }
          
          if (board[xPosition+2][yPosition+2] == player) {
              
              isValid = true;
              break;
          } 
            xPosition++;
            yPosition++;
  
          
      }
    }
    
    if  (isValid == true) {
        return isValid;
    } else if (x-1>-1 && y-1>-1 &&board[x-1][y-1]==opColor) //check up Left
    {
      xPosition = x;
      yPosition = y; 
      
      while (board[xPosition-1][yPosition-1] == opColor && xPosition > -1 && yPosition > -1)  {
          
          if ((xPosition - 2) < 0 || (yPosition - 2) < 0) {
            isValid=false;
            break;
          }
          
          if (board[xPosition-2][yPosition-2] == player) {
              
              isValid = true;
              break;
          } 
            xPosition--;
            yPosition--;

      }
    }
    
   
    if  (isValid == true) {
        return isValid;
    } else if (x+1<8 && y-1>-1 &&board[x+1][y-1]==opColor) //check down left
    {
      xPosition = x;
      yPosition = y; 
      
      while (board[xPosition+1][yPosition-1] == opColor && xPosition < 8 && yPosition > -1)  {
          
          
          if ((xPosition + 2) > 7 || (yPosition - 2) < 0) {
            isValid=false;
            break;
          }
          
          if (board[xPosition+2][yPosition-2] == player) {
              
              isValid = true;
              break;
          } 
            xPosition++;
            yPosition--;

      }
    }
    
  }
  return isValid;
}
    
 /** int direction
    * @author Frederic Desjardins
    * Converts pieces to the same colour as player.
    * ex: colDir, rowDir represent the direction, (+1,+1) is [x+1][y+1] so down right in the table;
    * 
    */
private int direction(int row, int column, int colour, int colDir, int rowDir)
  {
    int count = 0;
    int currentRow= row + rowDir;
    int currentCol = column + colDir;
   
    if (currentRow==8 || currentRow<0 || currentCol==8 || currentCol<0)
    {
       
      return 0;
    }
    while (board[currentRow][currentCol]== 1 || board[currentRow][currentCol]== 2)
    {
      
      if (board[currentRow][currentCol]==colour)
      {
       
        while(!(row==currentRow && column==currentCol))
        {
          
          if (board[currentRow][currentCol] != colour) {
              count++;
          }
          board[currentRow][currentCol]=colour;          
          currentRow=currentRow-rowDir;
          currentCol=currentCol-colDir;
        }
        break;
      }else
      {
       
      currentRow=currentRow + rowDir;
      currentCol=currentCol + colDir;
      }
      if (currentRow<0 || currentCol<0 || currentRow==8 || currentCol==8)
      { 
        break;
      }
    }
    return count;
  }
   
 /** 
    * @author Frederic Desjardins
    * @param x - position on x axis
    * @param y - position on y axis
    * @param player - player turn
    * moves a piece at position x,y
    * checks if the move is valid using isValid()
    * changes all pieces to their appropriate colour using direction()
    * 
    */

    public int move(int x, int y, int player) {
        
        int count = 0;
        if (board[x][y] == 0) {
            boolean valid = isValid(x,y,player);
            
            if (valid) {
                board[x][y] = player;
                    //check above & below
                count += direction(x, y, player, 0, -1);
                count += direction(x, y, player, 0, 1);
                //check right & right 
                count += direction(x, y, player, 1,0);
                count += direction(x, y, player, -1, 0);
                //check corners
                count += direction(x, y, player, 1,1);
                count += direction(x, y, player, 1,-1);
                count += direction(x, y, player, -1,1);
                count += direction(x, y, player, -1,-1);
                
                return count;
            } else return -1;            
        } else {
            return -1;
        }
    }
    
     /** int getChip
    * @author Frederic Desjardins
    * returns how many chips player has currently
    * 
    */
    public int getChips(int player) {

            int numChips = 0;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (board[i][j] == player) {                
                        numChips++;
                    }                   
                }
            }
        return numChips;
    }
}