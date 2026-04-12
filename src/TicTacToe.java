import java.util.Scanner;

class TicTacToe
{

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String player;
        int count = 0;

        int rowMove;
        int colMove;
        boolean playAgain;

        do {
            clearBoard();
            display();
            do {
                player = "X";
                do {
                    rowMove = SafeInput.getRangedInt(in, "Player 1 enter your row position [1-3]", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Player 1 enter your column position [1-3]", 1, 3);
                    rowMove -= 1;
                    colMove -= 1;
                } while (!isValidMove(rowMove, colMove));

                board[rowMove][colMove] = "X";
                display();
                if (count >= 4 && isWin(player)) {
                    System.out.println("Player 1 Wins!");
                    break;
                } else if (count >= 7 && isTie()) {
                    System.out.println("It's a Tie!");
                    break;
                }
                count += 1;

                player = "O";
                do {
                    rowMove = SafeInput.getRangedInt(in, "Player 2 enter your row position [1-3]", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Player 2 enter your column position [1-3]", 1, 3);
                    rowMove -= 1;
                    colMove -= 1;
                } while (!isValidMove(rowMove, colMove));

                board[rowMove][colMove] = "O";
                display();
                if (count >= 4 && isWin(player)) {
                    System.out.println("Player 2 Wins!");
                    break;
                } else if (count >= 7 && isTie()) {
                    System.out.println("It's a Tie!");
                    break;
                }
                count += 1;
            } while (!isTie());
            playAgain = SafeInput.getYNConfirm(in, "Play Again");
        } while(playAgain);
    }

    /**
     * A method that sets all the board elements to a space.
     *
     */
    private static void clearBoard()
    {
        for(int row=0; row < ROWS; row++)
        {
            for(int col=0; col < COLS; col++)
            {
                board[row][col] = " "; // make this cell a space
            }
        }
    }

    /**
     * A method that displays the board.
     *
     */
    private static void display()
    {
        System.out.println("TIC-TAC-TOE GAME");
        System.out.println("----------------");
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col]);
                if (col < COLS)
                    System.out.print(" | ");
                if (col == COLS-1)
                    System.out.print("\n");
            }
        }
        System.out.println("----------------");
    }

    /**
     * A method that returns true if there is a space at the given proposed
     * move coordinates which means it is a legal move.
     *
     * @param row
     * @param col
     * @return
     */
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))  // is it a space?
            retVal = true;
        else
            System.out.print("Invalid Move: Position is taken.\n");
        return retVal;
    }

    /**
     *
     * A method that checks to see if there is a win state on the current board
     * for the specified player (X or O). This method in turn calls three additional
     * methods that break down the 3 kinds of wins that are possible.
     *
     * @param player        String with specified player X or O
     * @return              True for win or False for no win condition
     */
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }

    /**
     * A method that checks for a col win for specified player
     *
     * @param player        String with specified player X or O
     * @return              True for win or False for no win condition
     */

    private static boolean isColWin(String player)
    {
        for(int col=0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
                return true;
        }
        return false; // no column win
    }

    /**
     * A method that checks for a row win for the specified player
     *
     * @param player        String with specified player X or O
     * @return              True for win or False for no win condition
     */
    private static boolean isRowWin(String player)
    {
        for(int row=0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
                return true;
        }
        return false; // no row win
    }

    /**
     * A method that checks for a diagonal win for the specified player
     *
     * @param player        String with specified player X or O
     * @return              True for win or False for no win condition
     */
    private static boolean isDiagnalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            return true;
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            return true;
        else
            return false;   // no diagonal win
    }

    /**
     * A method that checks for a tie condition
     *
     * @return              True for tie condition and False for no tie condition
     */
    private static boolean isTie()
    {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == " ")
                    return false;
            }
        }
            return true;
    }
}