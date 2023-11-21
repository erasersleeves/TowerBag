import javax.swing.*;
import java.awt.Color;

public class Main{
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Star Ship");
        window.setSize(600, 600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        window.add(gamePanel);

    }

}