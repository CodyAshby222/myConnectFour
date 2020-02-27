import java.io.IOException;

public class ConnectFourController {

    private ConnectFourView view = new ConnectFourView();
    private Player human1 = new Human();
    private Player human2 = new Human();
    private Player computer1 = new Computer();
    private Player computer2 = new Computer();
    private char[][] connectFourBoard = new char[6][7];
    private int turns = 0;


    public ConnectFourController() {
    }

    public void run() throws IOException {
        boolean exitRequested = false;
        do{
            view.displayMainMenu();
            int userSelection = view.getUserNum(1,4);
            switch (userSelection){
                case 1:
                    gameTime(human1,human2);
                    break;
                case 2:
                    gameTime(human1,computer1);
                    break;
                case 3:
                    gameTime(computer1,computer2);
                    break;
                case 4:
                    exitRequested = true;
                    break;
                default:
                    System.out.println("Incorrect selection. Please try again.");
            }
        }while(!exitRequested);
    }


    public void emptyBoard(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                connectFourBoard[row][col] = '-';
                System.out.print("[ " + connectFourBoard[row][col] + " ]");
            }
            System.out.println();
        }
    }

    public void displayBoard(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                System.out.print("[ " + connectFourBoard[row][col] + " ]");
            }
            System.out.println();
        }
    }

    public void dropPiece(char userInput, int col){
            if (connectFourBoard[0][col] != '-') {
                System.out.println("Column is already full");
                return;
            }
                for (int row = connectFourBoard.length - 1; row >= 0; row--) {
                    if (connectFourBoard[row][col] == '-') {
                        connectFourBoard[row][col] = userInput;
                        return;
                    }
                }
    }

    public boolean drawCondition(){
        if(connectFourBoard[0][0] != '-' && connectFourBoard[0][1] != '-' && connectFourBoard[0][2] != '-'
                && connectFourBoard[0][3] != '-' && connectFourBoard[0][4] != '-' && connectFourBoard[0][5] != '-' && connectFourBoard[0][6] != '-'){
            System.out.println("IT'S A DRAW!");
            return true;
        }
        return false;
    }

    public boolean horizontalWin() {
        boolean winner = false;
        for (int row = 0; row < connectFourBoard.length; row++) {
            for (int col = 0; col < connectFourBoard[row].length - 3; col++) {
                if (connectFourBoard[row][col] != '-' && connectFourBoard[row][col] == connectFourBoard[row][col + 1]
                        && connectFourBoard[row][col] == connectFourBoard[row][col + 2] && connectFourBoard[row][col] == connectFourBoard[row][col + 3]) {
                    winner = true;
                    break;
                }
            }
        }
        return winner;
    }

    public boolean verticalWin () {
        boolean winner = false;
        for(int col = 0; col < connectFourBoard.length; col++){
            for(int row = 0; row < connectFourBoard.length - 3; row++){
                if (connectFourBoard[row][col] != '-' && connectFourBoard[row][col] == connectFourBoard[row + 1][col]
                        && connectFourBoard[row][col] == connectFourBoard[row + 2][col] && connectFourBoard[row][col] == connectFourBoard[row + 3][col]) {
                    winner = true;
                    break;
                }
            }
        }
        return winner;
    }

    public boolean diagonalWin(){
        boolean winner = false;
        for(int row = 0; row < connectFourBoard.length-3; row++){
            for(int col = 0; col < connectFourBoard[row].length - 3; col++){
                if (connectFourBoard[row][col] != '-' && connectFourBoard[row][col] == connectFourBoard[row + 1][col + 1]
                        && connectFourBoard[row][col] == connectFourBoard[row + 2][col + 2] && connectFourBoard[row][col] == connectFourBoard[row + 3][col + 3]) {
                    winner = true;
                    break;
                }
            }
        }
        return winner;
    }

    public boolean oppositeDiagonalWin(){
        boolean winner = false;
        for(int row = 0; row < connectFourBoard.length-3; row++){
            for(int col = 3; col < connectFourBoard[row].length; col++){
                if (connectFourBoard[row][col] != '-' && connectFourBoard[row][col] == connectFourBoard[row + 1][col - 1]
                        && connectFourBoard[row][col] == connectFourBoard[row + 2][col - 2] && connectFourBoard[row][col] == connectFourBoard[row + 3][col - 3]) {
                    winner = true;
                    break;
                }
            }
        }
        return winner;
    }

    public void changePlayer(Player player1, Player player2) throws IOException {
        int turn = turns == 0 ? 1 : 0;
        //(turns % 2 == 0 ? true : false
        if(turns%2 == 0){
            view.showMessage(player1.getName() + " please enter a number between 1-7 to drop " + player1.getColor());
            try {
                dropPiece(player1.getColor(), player1.userDropPiece());
            }catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("Incorrect input. Please enter a number between 1-7");
                dropPiece(player1.getColor(), player1.userDropPiece());
            }
            if (verticalWin() || horizontalWin() || diagonalWin() || oppositeDiagonalWin()){
                System.out.println(player1.getName() + " you are the WINNER!");
            }
        }
        else {
            view.showMessage(player2.getName() + " please enter a number between 1-7 to drop  " + player2.getColor());
            try {
                dropPiece(player2.getColor(), player2.userDropPiece());
            }
            catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("Incorrect input. Please enter a number between 1-7");
                dropPiece(player2.getColor(), player2.userDropPiece());
            }
            if (verticalWin() || horizontalWin() || diagonalWin() || oppositeDiagonalWin()){
                System.out.println(player2.getName() + " you are the WINNER!");
            }
        }
        turns++;
    }

    public void gameTime(Player player1, Player player2) throws IOException {
        emptyBoard();
        view.showMessage("Enter Player One Name:");
        player1.setName(view.getUserName(1));
        player1.setColor('Y');
        view.showMessage("Enter Player Two Name:");
        player2.setName(view.getUserName(1));
        player2.setColor('R');
        displayBoard();

        boolean endGame;
        do {
            System.out.println(player1.getName() + ": Yellow  |  " + player2.getName() + ": Red");
            changePlayer(player1,player2);
            endGame = verticalWin() || horizontalWin() || diagonalWin() || oppositeDiagonalWin() || drawCondition();
            displayBoard();

        }while(!endGame);
    }
}


