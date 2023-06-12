import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.util.*;

class Pic extends JComponent {
    Image img;
    int angle = 0;
    int v_x = new Random().nextInt(5);
    // int v_y = new Random().nextInt(2);
    int v_y = 1;

    Pic(String path) {
        img = new ImageIcon(path).getImage();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = g2.getTransform();
        at.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);
        g2.setTransform(at);
        g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
