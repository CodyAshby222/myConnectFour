import java.io.IOException;

public class Human extends Player{
    ConnectFourView connectFourView = new ConnectFourView();

    public Human(){
        this.setName("Human 1");
    }

    public Human(String name) {
        super(name);
    }

    @Override
    int userDropPiece() throws IOException {
        return connectFourView.getUserNum(1,7)-1;
    }


}
