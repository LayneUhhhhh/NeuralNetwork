package CustomGame;

import Graphics.DrawingWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

import static java.lang.Integer.valueOf;

public class Game {

    private List<Creature> gameCreatures;
    private TileMap gameMap;
    public static boolean last = false;
    public static boolean rDirection = true;
    public static int round = 0;

    public Game(){
        this.gameCreatures = new ArrayList<>();
        this.gameMap = new TileMap();
        this.gameMap.generate100x100Map();
        this.round = 0;
    }

    public void gameLoop() throws IOException {

        Scanner scanner = new Scanner(System.in);
        int k = 0;

            while(true){


                this.round++;
                //CheckIfGameCreaturesHaveTheSameXY("loop");
                //System.out.print("contents: " + gameMap.GetAmountOfTilesWithContents() + "\n");
                System.out.print("round: " + round + "\n");
                //System.out.print("Creatures: " + gameCreatures.size() + "\n");
                k = 0;
                for(Creature c: gameCreatures){
                    c.Tick(gameMap);
                    k++;
                    if (k == 99 && Creature.allMoves == 0){
                        this.last = true;
                    }
                }
                Creature.allMoves = 0;

                if (round % 10000000 == 0) {
                    SwingUtilities.invokeLater(() -> {
                        DrawingWindow gameWindow = new DrawingWindow(this.gameMap);
                        gameWindow.setVisible(true);
                    });

                    System.out.print("wow\n");
                    String input = scanner.nextLine();

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

        if(round % 100 == 0){

            int I = 0;
            List<Creature> tempList = new ArrayList<>();
            List<Creature> tempList2 = new ArrayList<>();
            if (rDirection)
                System.out.println("Right!");
            else
                System.out.println("Left!");
            for(Creature c: this.gameCreatures){
                System.out.println("X: " + c.x);
                if (c.moves < 1)
                    c.moves = c.moves - 1000;
                if(c.x < 10){
                    c.moves = c.moves + 100;
                }
            }

            Collections.sort(this.gameCreatures, new Comparator<Creature>() {
            @Override
            public int compare(Creature obj1, Creature obj2) {
                return Integer.compare(obj1.x, obj2.x);
            }
        });

            if(!rDirection)
                Collections.reverse(this.gameCreatures);

            for(Creature c: this.gameCreatures){
                tempList.add(c);
            }
            System.out.println("GC: " + gameCreatures.size());
            System.out.println("TL: " + tempList.size());
            for(int i = 0; i < 50; i++){
                    Creature C = tempList.get(i);
                    gameCreatures.remove(C);
                    C.RemoveFromMap(this.gameMap);
            }
            //CheckIfGameCreaturesHaveTheSameXY("1");
            int tempSize = valueOf(gameCreatures.size());

            for(int i = 0; i < tempSize; i++){

                int evolution = rand.nextInt(300);
                if(evolution == 1 || evolution == 2 || evolution == 3)
                    gameCreatures.add(gameCreatures.get(i).evolveNewCreatureWithNewConnections(this.gameMap));
                else if(evolution == 4)
                    gameCreatures.add(gameCreatures.get(i).evolveNewCreatureWithNewNeuron(this.gameMap));
                else if(evolution == 5 || evolution == 6 || evolution == 7)
                    gameCreatures.add(gameCreatures.get(i).evolveNewCreatureWithRemovedNeuron(this.gameMap));
                else if(evolution == 11 || evolution == 12)
                    gameCreatures.add(gameCreatures.get(i).evolveNewCreatureWithRemovedConnection(this.gameMap));
                else 
                    gameCreatures.add(new Creature(gameCreatures.get(i), this.gameMap));

                //System.out.print("i: " + i + "\n");
                //System.out.print("contentsIN: " + gameMap.GetAmountOfTilesWithContents() + "\n");
            }
            //CheckIfGameCreaturesHaveTheSameXY("2");
            //System.out.print("Creatures2: " + gameCreatures.size() + "\n");
            //System.out.print("contents2: " + gameMap.GetAmountOfTilesWithContents() + "\n");
            int i = 0;
            for(Creature c: gameCreatures){
                c.Brain.ResetNetActivity();
                c.moves = 0;
                boolean go = false;
                while (!go){
                    go = c.UpdatePos(this.gameMap, rand.nextInt(100), rand.nextInt(100));
                }
                i++;
            }
            if(rDirection)
                rDirection = false;
            else
                rDirection = true;
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
