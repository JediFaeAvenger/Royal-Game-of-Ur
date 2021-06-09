public class boardGamePiece
{
    DataStructure myBoard = new DataStructure();
    private int pRow, pCol;
    private boolean white;
    public int numBlackPieces = 0, numWhitePieces = 0, totalMoves;

    public boardGamePiece(boolean w, int r, int c)
    {
        pRow = r;
        pCol = c;
        white = w;

        totalMoves = 0;

        if (w)
            numWhitePieces++;
        else
            numBlackPieces++;
    }


    // uses an array of steps to move the piece starting on the bottom
    public void playerMove()
    {
        int[][] bottomSteps = {{-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {0, -1}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {0, 1}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}};

        pRow+=bottomSteps[totalMoves][1];
        pCol+=bottomSteps[totalMoves][0];

        totalMoves++;
    }

    // uses an array of steps to move the piece starting on the top
    public void AIMove()
    {
        int[][] topSteps = {{-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {0, 1}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {0, -1}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}};

        pRow+=topSteps[totalMoves][1];
        pCol+=topSteps[totalMoves][0];

        totalMoves++;
    }

    public boolean isWhite()
    {
        if (white)
                return true;
        else
            return false;
    }

    public int getTotalMoves()
    {
        return totalMoves;
    }

    public void setTotalMoves(int t)
    {
        totalMoves = t;
    }

    public int getRow()
    {
        return pRow;
    }

    public int getCol()
    {
        return pCol;
    }

    public void setRow(int r)
    {
        pRow = r;
    }

    public void setCol(int c)
    {
        pCol = c;
    }

    public void kick()
    {
        if (white)
        {
            setRow(2);
            setCol(4);
        }
        else if (!white)
        {
            setRow(0);
            setCol(4);
        }
    }
}
