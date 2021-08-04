package view;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 * Classe ImagePanel permettant d'instancier une image dans un JComponent
 * @author Jaouen MARIE
 * @version 1.0
 */
public class ImagePanel extends JComponent {
    private BufferedImage image;

    public ImagePanel(String filePath) {
        this.setOpaque(false);
        try {
            this.image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImagePanel(Dimension d, String filePath) {
        this.setOpaque(false);
        setPreferredSize(d);
        try {
            this.image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}