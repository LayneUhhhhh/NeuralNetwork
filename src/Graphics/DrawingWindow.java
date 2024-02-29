package Graphics;
import CustomGame.Game;
import CustomGame.Tile;
import CustomGame.TileMap;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawingWindow extends JFrame {

    private TileMap T;
    public static Graphics g;

    public DrawingWindow(TileMap t) {
        this.T = t;
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

            }
        };
        getContentPane().add(panel);
    }

    // Method to perform drawing
    public void draw(Graphics g) {
        List<Tile> tList = T.getTilesOnMapList();
        int i = 0;
        for(Tile t: tList){

            if(t.contents != null) {
                g.setColor(Color.RED);
            }
            else{
                g.setColor(Color.BLACK);
            }
            g.fillRect(t.x * 9, t.y * 9, 8, 8);
            //System.out.print(t.x + ":" + t.y + "\n");
        }

        g.setColor(Color.BLUE);
        String directionString = Game.rDirection ? "true" : "false";
        g.drawChars(directionString.toCharArray(), 0, directionString.length(), 450, 450);

    }

}