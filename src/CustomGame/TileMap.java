package CustomGame;

import java.util.ArrayList;
import java.util.List;

public class TileMap {

    public TileMap() {
    }

    protected List<Tile> TilesOnMap = new ArrayList<>();

    public void generate100x100Map(){
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                TilesOnMap.add(new Tile(i, j));
            }
        }

    }

    public List<Tile> getTilesOnMapList(){
        return TilesOnMap;
    }

}
