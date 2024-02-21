import CustomGame.Creature;
import CustomGame.Game;
import CustomGame.TileMap;
import Graphics.DrawingWindow;
import NeuralNetProject.NueralNet;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Creature c = new Creature(10, 10, new NueralNet());
        System.out.println("Hello world!");
        //TileMap t = new TileMap();
        //t.generate100x100Map();
        //t.getTilesOnMapList().get(5).containsCreature = true;
        Game g = new Game();
        g.generateCreatures(100);
        g.gameLoop();


    }
}