/**
 * class NumberData keeps a list of 11 numbers in a 1-D array
 * 
 * @author (Mr. Heath) 
 * @version (May 2014)
 */
public class DataStructure
{
    // array of data
      private int xlimit, ylimit;
      private final int MULTIPLE = 2;
      private final int[][] BOARD = {{8, 0, 0, 0, 3, 3, 8, 0},
                                    {0, 0, 0, 8, 0, 0, 0, 0},
                                    {8, 0, 0, 0, 3, 3, 8, 0}};

      private int[][] display = {{8, 0, 0, 0, 3, 3, 8, 0},
                                 {0, 0, 0, 8, 0, 0, 0, 0},
                                 {8, 0, 0, 0, 3, 3, 8, 0}};

    /**
     * Constructor for objects of class NumberData
     */
    public DataStructure()
    {
       // Initialize the size of the array
      xlimit = BOARD.length;
      ylimit = BOARD[0].length;
    }

    /**
     * outputs the size limits of the data structure*/
    public int getXlimit()
    {
      return xlimit;
    }

    public int getYlimit()
    {
        return ylimit;
    }

    /**
     * outputs the number at position index
     */
    public int getThisNum(int row, int col)
    {
      return BOARD[row][col];
    }

    /**
     * outputs whether or not a position is empty
     */
    public boolean isNull(int row, int col)
    {
        return BOARD[row][col] == 3;
    }

    /**
     * outputs whether or not a position is a rosetta
     */
    public boolean isRosetta(int row, int col)
    {
        return BOARD[row][col] == 8;
    }

    /**
     * outputs whether or not there is white piece at a position
     */
    public boolean isWhite(int row, int col)
    {
        return display[row][col] == 6;
    }

    /**
     * outputs whether or not there is black piece at a position
     */
    public boolean isBlack(int row, int col)
    {
        return display[row][col] == 4;
    }

    /**
     * outputs a string concatenation of the numbers in the board array
     */
    public String boardToString()
    {
      String nums = "";
      for (int r = 0; r < xlimit; r++)
      {
          for (int c = 0; c < ylimit; c++)
              if (isNull(r, c))
                  nums += ("   ");
              else
                  nums += (BOARD[r][c] + "  ");
          nums += "\n";
      }
      return nums;
    }

    /**
     * outputs a string concatenation of the numbers in the array including pieces
     */
    public String displayToString()
    {
        String nums = "";
        for (int r = 0; r < xlimit; r++)
        {
            for (int c = 0; c < ylimit; c++)
                if (isNull(r, c))
                    nums += ("   ");
                else
                    nums += (display[r][c] + "  ");
            nums += "\n";
        }
        return nums;
    }

    // sets the index in the display data structure to the value for white
    public void setNumWhite(int row, int col)
    {
        display[row][col] = 6;
    }

    // sets the index in the display data structure to the value for black
    public void setNumBlack(int row, int col)
    {
        display[row][col] = 4;
    }

    // resets the index in the display data structure to the value for white
    public void reset(int row, int col)
    {
        display[row][col] = getThisNum(row, col);
    }
}