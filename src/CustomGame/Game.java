package CustomGame;

import NeuralNetProject.InputNueron;
import CustomGame.TileMap;
import Graphics.DrawingWindow;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import CustomGame.Creature;
import Graphics.DrawingWindow;

import javax.swing.*;

import static java.lang.Integer.valueOf;

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

        Scanner scanner = new Scanner(System.in);

            while(true){
                this.round++;
                //CheckIfGameCreaturesHaveTheSameXY("loop");
                //System.out.print("contents: " + gameMap.GetAmountOfTilesWithContents() + "\n");
                System.out.print("round: " + round + "\n");
                //System.out.print("Creatures: " + gameCreatures.size() + "\n");
                for(Creature c: gameCreatures){
                    c.Tick(gameMap);
                }

                if (round % 5000 == 0) {
                    SwingUtilities.invokeLater(() -> {
                        DrawingWindow gameWindow = new DrawingWindow(this.gameMap);
                        gameWindow.setVisible(true);
                    });

                    //System.out.print("wow\n");
                    //String input = scanner.nextLine();

                }

                boolean display = false;
                if(display) {
                    int i = 0;
                    for (Tile t : this.gameMap.getTilesOnMapList()) {
                        if (i % 100 == 0)
                            System.out.print("\n");

                        if (t.contents != null) {
                            System.out.print("o");
                        } else {
                            System.out.print("#");
                        }
                        i++;
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
            int I = 0;
            List<Creature> tempList = new ArrayList<>();
            for(Creature c: this.gameCreatures){
                tempList.add(c);
            }
            for(Creature c: tempList){
                if(c.x > 50) {
                    gameCreatures.remove(c);
                    c.RemoveFromMap(this.gameMap);
                    I++;
                }
            }
            //CheckIfGameCreaturesHaveTheSameXY("1");
            System.out.println("deaths: " + I);
            int tempSize = valueOf(gameCreatures.size());
            for(int i = valueOf(tempSize); i < 100; i++){

                int evolution = rand.nextInt(300);
                if(evolution == 1 || evolution == 2 || evolution == 3)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewConnections(this.gameMap));
                else if(evolution == 4)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithNewNeuron(this.gameMap));
                else if(evolution == 5 || evolution == 6 || evolution == 7 || evolution == 8 || evolution == 9 || evolution == 10)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithRemovedNeuron(this.gameMap));
                else if(evolution == 11 || evolution == 12)
                    gameCreatures.add(gameCreatures.get(rand.nextInt(tempSize)).evolveNewCreatureWithRemovedConnection(this.gameMap));
                else 
                    gameCreatures.add(new Creature(gameCreatures.get(rand.nextInt(tempSize)), this.gameMap));
                //System.out.print("i: " + i + "\n");
                //System.out.print("contentsIN: " + gameMap.GetAmountOfTilesWithContents() + "\n");
            }
            //CheckIfGameCreaturesHaveTheSameXY("2");
            //System.out.print("Creatures2: " + gameCreatures.size() + "\n");
            //System.out.print("contents2: " + gameMap.GetAmountOfTilesWithContents() + "\n");
            int i = 0;
            for(Creature c: gameCreatures){
                c.Brain.ResetNetActivity();
                boolean go = false;
                while (!go){
                    go = c.UpdatePos(this.gameMap, rand.nextInt(100), rand.nextInt(100));
                }
                i++;
            }
            //CheckIfGameCreaturesHaveTheSameXY("3");
            //System.out.print("Creatures3: " + gameCreatures.size() + "\n");
            //System.out.print("contents3: " + gameMap.GetAmountOfTilesWithContents() + "\n");
        }
    }

    public void CheckIfGameCreaturesHaveTheSameXY(String s){
        int i = 0;
        int j = 0;
        for (Creature c1: this.gameCreatures){
            j = 0;
            for(Creature c2: this.gameCreatures){
                if (c1.x == c2.x && c1.y == c2.y && i != j) {
                    System.out.print("Hit i" + i + " j" + j + " s" + s + "\n");
                    System.out.print("endXY Creature: " + c1.x + "-" + c1.y + "\n");
                }
                j++;
            }
            i++;
        }

    }


}
