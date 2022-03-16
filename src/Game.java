import java.util.*;
class Ship {
    int x;
    int y;
    public Ship (int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}
public class Game {
    public static char[][] board = new char[10][10];
    public static char[][] playerBoard = new char[10][10];
    static ArrayList<Ship> ships = new ArrayList<>();

        public static void main (String[]args){

            startGame();
        }
        static void createBoard () {
            int row;
            int col = 0;
            for (row = 0; row < board.length; row++) {
                for (col = 0; col < board[row].length; col++) {
                    board[row][col] = '-';
                    playerBoard[row][col] = '-';
                }
            }
            createShip(4);
            createShip(4);
            createShip(5);
        }
    static void createPlayerBoard () {
        int row;
        int col = 0;
        for (row = 0; row < playerBoard.length; row++) {
            for (col = 0; col < playerBoard[row].length; col++) {
                playerBoard[row][col] = '-';
            }
        }
    }
        static void printBoard () {
            int a = 0;
            int row;
            int col;
            //System.out.println("Arraylist contains: " + ships.toString());
            System.out.println("   A    B   C   D   E   F   G   H   I   J");
            for (row = 0; row < board.length; row++) {
                System.out.print(a + ": ");
                a = a + 1;
                for (col = 0; col < board[row].length; col++) {
                    //System.out.print(board[row][col] + "\t");
                    System.out.print(playerBoard[row][col] + "\t");
                }
                System.out.println();
            }
        }
        static void createShip ( int size) {
            Random random = new Random();
            int a;
            boolean point = true;
            if (size == 5 || size == 4) {
                while (point) {
                    boolean occupied=false;
                    int randomX = random.nextInt(5);
                    int randomY = random.nextInt(5);
                    int position = random.nextInt() % 2;
                    if (position == 1) {
                        for (a = 0; a < size; a++) {
                            if (board[randomX + a][randomY] == 's') {
                                 occupied=true;
                            }
                        }
                        if(!occupied) {
                            for (a = 0; a < size; a++) {
                                board[randomX + a][randomY] = 's';
                                Ship ship = new Ship(randomX + a, randomY);
                                ships.add(ship);
                            }
                            point=false;
                        }
                    }
                 else if(position == 0){
                    for (a = 0; a < size; a++) {
                        if (board[randomX][randomY + a] == 's') {
                            occupied=true;
                        }
                    }
                        if(!occupied) {
                            for (a = 0; a < size; a++) {
                                board[randomX][randomY + a] = 's';
                                Ship ship = new Ship(randomX, randomY + a);
                                ships.add(ship);
                            }
                            point=false;
                        }
                    }

                }
            }
        }
        public static boolean gameOver() {
            if(ships.size() == 0) {
                return true;
            } return false;

          }
        static void startGame () {
            int responseCol;
            int responseRow=0;
            Scanner in = new Scanner(System.in);
            createBoard();
            createPlayerBoard();
            printBoard();

            System.out.println("You need to find 2 Battleships with length 4 and one Destroyer with length 5 :");
            while (gameOver() == false) {
                boolean correctAnswer = true;
                //int responseRow = Integer.parseInt(in.nextLine());
                String userInput="";
                String col="";
                     while (userInput.equals("")) {
                         System.out.println("Give a row number:");
                          userInput = in.nextLine();
                         if (!userInput.matches("[0123456789]")) {
                             System.out.println("Wrong number, please enter the correct number.");
                             userInput = "";
                             continue;
                         }
                       responseRow = Integer.parseInt(userInput);
                     }
                while (col.equals("")) {
                    System.out.println("Give a column number:");
                     col = in.nextLine();
                    if (!col.matches("[ABCDEFGHIJ]")) {
                        System.out.println("Wrong character, please enter the correct character.");
                        col = "";
                        continue;
                    }
                    //responseRow = Integer.parseInt(userInput);
                }

                    switch (col) {
                        case "A":
                            responseCol = 0;
                            break;
                        case "B":
                            responseCol = 1;
                            break;
                        case "C":
                            responseCol = 2;
                            break;
                        case "D":
                            responseCol = 3;
                            break;
                        case "E":
                            responseCol = 4;
                            break;
                        case "F":
                            responseCol = 5;
                            break;
                        case "G":
                            responseCol = 6;
                            break;
                        case "H":
                            responseCol = 7;
                            break;
                        case "I":
                            responseCol = 8;
                            break;
                        case "J":
                            responseCol = 9;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + col);
                    }
                    if (board[responseRow][responseCol] == 's') {
                        playerBoard[responseRow][responseCol] = 'o';
                        System.out.println("You hit a ship!");
                        for (int i = 0; i < ships.size(); i++) {
                            Ship ship = ships.get(i);
                            if (ship.x == responseRow && ship.y == responseCol) {
                                ships.remove(i);
                                break;
                            }
                        }
                    } else {
                        playerBoard[responseRow][responseCol] = 'X';
                        System.out.println("You missed!");
                    }
                    printBoard();
                }
          //  }
            System.out.println("Well done,you found all ships. Game over!");
        }
    }

