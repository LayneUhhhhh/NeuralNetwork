package CustomGame;

public class Tile {

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        containsCreature = false;
    }

    public boolean containsCreature;
    public int x;
    public int y;

}
