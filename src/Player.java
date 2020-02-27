import java.io.IOException;

abstract public class Player {
    private String name;
    private char color;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //set limitation on name
        this.name = name;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player Name = " + getName();
    }

    abstract int userDropPiece() throws IOException;
}
