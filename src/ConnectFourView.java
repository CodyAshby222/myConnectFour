import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectFourView {

    private BufferedReader in;

    public ConnectFourView() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        in = new BufferedReader(inputStreamReader);
    }

    public void displayMainMenu(){
        System.out.println(
                "1 - Human vs Human\n" +
                "2 - Human vs Computer\n" +
                "3 - Computer vs Computer\n" +
                "4 - Exit");
    }

    public int getUserNum(int min, int max) throws IOException {
        while(true) {
            String userInput = in.readLine();
            try{
                int input = Integer.parseInt(userInput);
                if(input < min || input > max){
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException ex){
                displayError("You must enter an integer between " + min + " and " + max);
            }
        }
    }

    public String getUserName(int minLength) throws IOException {
        while (true){
            String input = in.readLine();
            if(input.length() < minLength){
                displayError("The name must be at least " + minLength + " characters");
            }
            else{
                return input;
            }
        }
    }

    public void showMessage(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        ConnectFourController connectFourController = new ConnectFourController();
        connectFourController.run();
    }

    public void displayError(String msg){
        System.err.println(msg);
    }
}
