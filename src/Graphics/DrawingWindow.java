package Graphics;
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
            if (i % 100 == 0)
                System.out.print("\n");

            if(t.contents != null) {
                g.setColor(Color.RED);
                System.out.print("O");
            }
            else{
                g.setColor(Color.BLACK);
                System.out.print("+");
            }
            g.fillRect(t.x * 10, t.y * 10, 9, 9);
            //System.out.print(t.x + ":" + t.y + "\n");
        }


    }

}