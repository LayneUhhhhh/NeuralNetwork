import CustomGame.Creature;
import CustomGame.TileMap;
import Graphics.DrawingWindow;
import NeuralNetProject.NueralNet;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //Creature c = new Creature(10, 10, new NueralNet());
        System.out.println("Hello world!");
        //TileMap t = new TileMap();
        //t.generate100x100Map();
        //t.getTilesOnMapList().get(5).containsCreature = true;
        
        SwingUtilities.invokeLater(() -> {
            DrawingWindow D = new DrawingWindow(t);
            D.setVisible(true);
        });

    }
}