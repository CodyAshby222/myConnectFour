import java.util.Random;

public class Computer extends Player{
    public Computer(){
        this.setName("Computer 2");
        userDropPiece();
    }

    public Computer(String name) {
        super(name);
    }

    @Override
    int userDropPiece() {
        Random rng = new Random();
        return rng.nextInt(7);
    }

}
