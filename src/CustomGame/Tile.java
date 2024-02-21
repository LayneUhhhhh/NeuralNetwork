package CustomGame;

public class Tile {

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        contents = null;
    }

    public Creature contents;
    public int x;
    public int y;

}
