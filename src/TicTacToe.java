import java.util.Scanner;

class TicTacToe{

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        clearBoard();
        String player;
        int count = 0;
        display();

        int rowMove;
        int colMove;

        do
        {
            player = "X";
            do{
                rowMove = SafeInput.getRangedInt(in, "Player 1 enter your row position [1-3]: ", 1,3 );
                colMove = SafeInput.getRangedInt(in, "Player 1 enter your column position [1-3]: ", 1,3 );
                rowMove -= 1;
                colMove -= 1;
            } while(!isValidMove(rowMove, colMove));

            board[rowMove][colMove] = "X";
            display();
            if(isWin(player))
            {
                System.out.println("Player 1 Wins!");
                break;
            }
            else if (isTie()) {
                System.out.println("It's a Tie!");
                break;
            }

            player = "O";
            do{
                rowMove = SafeInput.getRangedInt(in, "Player 2 enter your row position [1-3]: ", 1,3 );
                colMove = SafeInput.getRangedInt(in, "Player 2 enter your column position [1-3]: ", 1,3 );
                rowMove -= 1;
                colMove -= 1;
            } while(!isValidMove(rowMove, colMove));

            board[rowMove][colMove] = "O";
            display();
            if(isWin(player)){
                System.out.println("Player 2 Wins!");
                break;
            }
            else if (isTie()) {
                System.out.println("It's a Tie!");
                break;
            }
            count += 1;
        } while(!isTie());
    }
    private static void clearBoard()    // sets all the board elements to a space
    {
        for(int row=0; row < ROWS; row++)
        {
            for(int col=0; col < COLS; col++)
            {
                board[row][col] = " "; // make this cell a space
            }
        }
    }

    private static void display() {
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
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))  // is it a space?
            retVal = true;
        else
            System.out.print("Invalid Move: Position is taken.\n");
        return retVal;
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }


    private static boolean isColWin(String player)
    {
        for(int col=0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
                return true;
        }
        return false; // no column win
    }

    private static boolean isRowWin(String player)
    {
        for(int row=0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
                return true;
        }
        return false; // no row win
    }

    private static boolean isDiagnalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            return true;
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            return true;
        else
            return false;   // no diagonal win
    }

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

