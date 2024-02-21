package CustomGame;

import NeuralNetProject.InputNueron;
import CustomGame.TileMap;
import Graphics.DrawingWindow;
import java.util.List;
import java.util.Random;

import CustomGame.Creature;
import Graphics.DrawingWindow;

public class Game {

    private List<Creature> gameCreatures;
    private TileMap gameMap;
    private DrawingWindow gameWindow;
    private int round;

    public Game(){
        this.gameCreatures = null;
        this.gameMap = new TileMap();
        this.gameMap.generate100x100Map();
        SwingUtilities.invokeLater(() -> {
            DrawingWindow gameWindow = new DrawingWindow(this.gameMap);
            gameWindow.setVisible(true);
        });
        this.round = 0;
    }

    public void gameLoop(){

        while(true){
            this.round++;
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
            gameCreatures.add(new Creature(rand.nextInt(100), rand.nextInt(100)));
        }
    }

    private void gameRules(){
        
        Random rand = new Random();

        if(round % 50 == 0){
            for(Creature c: gameCreatures){
                if(c.x > 50)
                gameCreatures.remove(c);
            }
            int tempSize = gameCreatures.size();
            for(int i = tempSize; i < 100; i++){

                int evolution = rand.nextInt(10);
                if(evolution == 9)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewConnections());
                else if(evolution == 10)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewConnections());
                else 
                    gameCreatures.add(new Creature(gameCreatures.get(rand.nextInt(tempSize))));
            }
            for(Creature c: gameCreatures){
                c.x = randInt(100);
                c.x = randInt(100);
            }
        }
    }

}
