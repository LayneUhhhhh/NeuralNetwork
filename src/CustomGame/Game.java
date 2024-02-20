package CustomGame;

import NeuralNetProject.InputNueron;
import CustomGame.TileMap;
import Graphics.DrawingWindow;
import java.util.List;
import CustomGame.Creature;
import Graphics.DrawingWindow;

public class Game {

    private List<Creature> gameCreatures;
    private TileMap gameMap;
    private DrawingWindow gameWindow;

    public Game(){
        this.gameCreatures = null;
        this.gameMap = new TileMap();
        this.gameMap.generate100x100Map();
        SwingUtilities.invokeLater(() -> {
            DrawingWindow gameWindow = new DrawingWindow(this.gameMap);
            gameWindow.setVisible(true);
        });
    }

    public void gameLoop(){

        while(true){
            for(Creature c: gameCreatures){
                c.Tick(gameMap);
            }
            gameWindow.draw();
            System.in.readNBytes(1);
        }

    }

    public void generateCreatures(int amount){

        Random rand = new Random();

        for(int i = 0; i < amount; i++){
            gameCreatures.add(new Creature(rand.nextInt(100)));
        }




    }

}
