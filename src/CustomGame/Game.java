package CustomGame;

import NeuralNetProject.InputNueron;
import CustomGame.TileMap;
import Graphics.DrawingWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CustomGame.Creature;
import Graphics.DrawingWindow;

import javax.swing.*;

public class Game {

    private List<Creature> gameCreatures;
    private TileMap gameMap;
    private int round;

    public Game(){
        this.gameCreatures = new ArrayList<>();
        this.gameMap = new TileMap();
        this.gameMap.generate100x100Map();
        this.round = 0;
    }

    public void gameLoop() throws IOException {



            while(true){
                this.round++;
                System.out.print("round: " + round + "\n");
                for(Creature c: gameCreatures){
                    c.Tick(gameMap);
                }

                if (round == 1 || round % 50 == 0) {
                    SwingUtilities.invokeLater(() -> {
                        DrawingWindow gameWindow = new DrawingWindow(this.gameMap);
                        gameWindow.setVisible(true);
                    });
                    try {
                        System.in.readNBytes(1);
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            gameRules();
            }


    }

    public void generateCreatures(int amount){

        Random rand = new Random();
        int x, y;

        for(int i = 0; i < amount; i++){
            x = rand.nextInt(100);
            y = rand.nextInt(100);

            while (gameMap.getTilesOnMapList().get((x * 100) + y).contents != null){
                x = rand.nextInt(100);
                y = rand.nextInt(100);
            }

            Creature c = new Creature(x, y);
            gameMap.getTilesOnMapList().get((x * 100) + y).contents = c;
            gameCreatures.add(c);
            System.out.print(i + "\n");
        }
    }

    private void gameRules(){
        
        Random rand = new Random();

        if(round % 50 == 0){
            List<Creature> tempList = new ArrayList<>(gameCreatures);
            for(Creature c: tempList){
                if(c.x > 50)
                gameCreatures.remove(c);
                System.out.print("remove\n");
            }
            int tempSize = gameCreatures.size();
            for(int i = tempSize; i < 100; i++){

                int evolution = rand.nextInt(10);
                if(evolution == 9)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewConnections());
                else if(evolution == 10)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewNeuron());
                else 
                    gameCreatures.add(new Creature(gameCreatures.get(rand.nextInt(tempSize))));
            }
            for(Creature c: gameCreatures){
                while (c.UpdatePos(this.gameMap, rand.nextInt(100), rand.nextInt(100)));
            }
        }
    }

}
