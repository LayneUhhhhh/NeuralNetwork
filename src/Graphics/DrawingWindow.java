package Graphics;
import CustomGame.Tile;
import CustomGame.TileMap;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawingWindow extends JFrame {

    private TileMap T;
    private Graphics g;

    public DrawingWindow(TileMap t) {
        this.t = t;
        setTitle("Drawing Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        // Create a JPanel for drawing
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
                this.g = g;
            }
        };
        getContentPane().add(panel);
    }

    // Method to perform drawing
    public void draw() {
        List<Tile> tList = T.getTilesOnMapList();
        for(Tile t: tList){
            if(t.containsCreature == false)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);
            g.fillRect(t.x * 10, t.y * 10, 9, 9);
            System.out.print(t.x + ":" + t.y + "\n");
        }


    }

}