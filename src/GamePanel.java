
import javax.imageio.ImageIO;
import javax.swing.*;
import world.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel implements Runnable {
    public GamePanel() {

        int scale = 32;

        setSize(Level.getInstance().getWidth() * scale, Level.getInstance().getHeight() * scale);
        setVisible(true);


        // Read the arena.txt file

        // Create a JPanel
        JPanel background = new JPanel();
        int[][] arena = Level.getInstance().getArena();
        background.setLayout(new GridLayout(arena.length, arena[0].length));

        BufferedImage[] tiles = new BufferedImage[4]; // Assuming 4 different tiles
        try {
            tiles[0] = ImageIO.read(new File("resources/grass1.png"));
            tiles[1] = ImageIO.read(new File("resources/path1.png"));
            tiles[2] = ImageIO.read(new File("resources/base.png"));
            tiles[3] = ImageIO.read(new File("resources/base.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Iterate over the 2D array
        for (int[] row : arena) {
            for (int cell : row) {
                // Create a JLabel with an ImageIcon
                JLabel label = new JLabel(new ImageIcon(tiles[cell]));

                // Add the JLabel to the JPanel
                background.add(label);
            }
        }

        // Add the JPanel to the JFrame
        add(background);

    }

    @Override
    public void run() {
        // game loop

    }
}
