import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        char player, computer;  // Two players

        //Choose player
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("Do you want to be X or O?");
        String input = keyboard.nextLine();
        if (input.equals("x") || input.equals("X")) {    // Assign either "X" or "O" to the player-variable
            player = 'X';
            computer = 'O';
        } else {                                         // If user types anything else than "X/O" the player is
            player = 'O';                                // assigned "O". Could make a condition to catch this
            computer = 'X';                              // and only allow "X" or "O" input.
        }


        char currentPlayer = player; // User starts everytime
        System.out.printf("Okay you are %C! I am %C! \n", player, computer);
        System.out.println("Your turn!\n");


        //Board
        char[][] board = new char[3][3];                // Creates an empty 3x3 matrix
        System.out.println("  1   2   3");
        System.out.println("1 " + (board[0][0]) + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("  ---------");
        System.out.println("2 " + (board[1][0]) + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("  ---------");
        System.out.println("3 " + (board[2][0]) + " | " + board[2][1] + " | " + board[2][2]);


        //Start Game
        boolean gameRunning = true;
        while (gameRunning) {

            if (currentPlayer == player) {              //If it's players turn then asks for user input
                boolean valid = false;                  //Valid turn is false until a valid move is made
                while (!valid) {
                    System.out.println("Enter row position");
                    int row = keyboard.nextInt() - 1;  // -1 because the board index starts at 0. Could also just write
                                                       // 0 1 2 on the board I guess.
                    System.out.println("Enter column position");
                    int coloumn = keyboard.nextInt() - 1;
                    if (row >= 0 && row <= 2 && coloumn >= 0 && coloumn <= 2) { //Checks that the position is an
                                                                                // existing index on the board
                        if (board[row][coloumn] == 0) {                         // Checks that the position is empty.
                            board[row][coloumn] = player;                // Assigns index the variable "O"or"X"
                            System.out.println(row);
                            valid = true;              // Move is valid, turn ends
                        } else {
                            System.out.println("This spot is taken, try again!");
                        }
                    } else {
                        System.out.println("Coordinate outside of board, try again!");
                    }
                }
            } else {
                // Computer's turn
                Random random = new Random();
                boolean valid = false; //Valid turn is false until a valid move is made
                while (!valid) {
                    int computerColoumn = random.nextInt(3);        // Random row and coloumn index.
                    int computerRow = random.nextInt(3);
                    if (board[computerRow][computerColoumn] == 0) { // Checks that the index is empty
                        board[computerRow][computerColoumn] = computer;  // Assigns the index the variable "X" or "O"
                        System.out.printf("My turn! I choose (%d, %d) \n", computerRow + 1, computerColoumn + 1);
                        valid = true;
                    }
                }
            }
            //Print board
            //Duplicated code, could have made a method to print the board and called it.
            System.out.println("  1   2   3");
            System.out.println("1 " + (board[0][0]) + " | " + board[0][1] + " | " + board[0][2]);
            System.out.println("  ---------");
            System.out.println("2 " + (board[1][0]) + " | " + board[1][1] + " | " + board[1][2]);
            System.out.println("  ---------");
            System.out.println("3 " + (board[2][0]) + " | " + board[2][1] + " | " + board[2][2]);

            // Checks if any coloumn, row or diagonal has 3 identical variables that matches the current player.
            // If yes then the game ends and current player have won
            // Could probably be done much better, but I'm tired :(
            if (
                    (board[0][0] == currentPlayer && board[0][1] == currentPlayer && board[0][2] == currentPlayer) ||
                            (board[1][0] == currentPlayer && board[1][1] == currentPlayer && board[1][2] == currentPlayer) ||
                            (board[2][0] == currentPlayer && board[2][1] == currentPlayer && board[2][2] == currentPlayer) ||
                            (board[0][0] == currentPlayer && board[1][0] == currentPlayer && board[2][0] == currentPlayer) ||
                            (board[0][1] == currentPlayer && board[1][1] == currentPlayer && board[2][1] == currentPlayer) ||
                            (board[0][2] == currentPlayer && board[1][2] == currentPlayer && board[2][2] == currentPlayer) ||
                            (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            ) {
                gameRunning = false;
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                // Check for a draw
                boolean isDraw = true;
                for (int i = 0; i < 3; i++) {      // Board is a 2Darray. To check all indexes it has to goe through
                    for (int j = 0; j < 3; j++) {  //all rows and coloumns.
                        if (board[i][j] == 0) {    //If any index is empty then the game is not a draw.
                            isDraw = false;
                            break;
                        }
                    }
                }

                if (isDraw) {                      //If the game is a draw it ends.
                    gameRunning = false;
                    System.out.println("It's a draw!");
                } else {
                    // Switch to the other player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

        }
    }


}

