import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class MyPanel extends JPanel implements KeyListener {

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;
    Pic apple = new Pic("./appleImg.jpeg");
    Image PlayerImg = new ImageIcon("./playerImg.jpg").getImage();

    int v_x = 0;
    int life = 5;
    String message;
    int score;
    boolean running = true;
    Image backgroundImg = new ImageIcon("./skyImg.jpeg").getImage();
    JButton starButton;
    final int DELAY = 75;
    ArrayList<Pic> Apples = new ArrayList<Pic>();
    Timer timer;

    MyPanel() {
        this.setLocation(0, 0);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.addKeyListener(this);
        this.setLayout(null);
        this.setFocusable(true);
        apple.setLocation(0, 0);
        apple.setSize(50, 50);
        this.setBackground(Color.BLACK);
        // this.startGame();
        // Apples.add(apple);
        // this.add(Apples.get(0));
        this.add(apple);
        Apples.add(apple);

        this.startGame();

    }

    Pic createApple(int x, int y) {
        // Random rnd = new Random();
        Pic apple = new Pic("./appleImg.jpeg");

        apple.setBounds(x, y, 50, 50);
        apple.v_y = -apple.v_y;
        add(apple);
        return apple;
    }

    public void show(int tmp) {
        System.out.println(tmp);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        // g2D.drawImage(backgroundImg, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

        // // g2D.drawLine(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        // g2D.draw

        g2D.drawImage(PlayerImg, v_x, PANEL_HEIGHT - 50, 150, 50, null);
        // // show(1);
        g2D.setColor(Color.blue);
        g2D.setFont(new Font("Ink Free", Font.BOLD, 25));
        g2D.drawString("Score: " + score, 0, 25);
        g2D.drawString("Life: " + life, PANEL_WIDTH - 100, 25);
        if (!running) {
            g2D.setColor(Color.RED);
            g2D.setFont(new Font("Ink Free", Font.BOLD, 55));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("You Lost! :'(",
                    (PANEL_WIDTH - metrics.stringWidth("You Lost! :'(")) / 2,
                    PANEL_HEIGHT / 2);
        }

    }

    public void startGame() {
        Thread rotateThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    for (int i = 0; i < Apples.size(); i++) {

                        Apples.get(i).angle += 3;
                        // show(Apples.get(i).angle);
                        if ((Apples.get(i).getX() > (PANEL_WIDTH - 50)) || (Apples.get(i).getX() < 0)) {
                            Apples.get(i).v_x = -(Apples.get(i).v_x);
                        }
                        if (collisionWithPlayer(Apples.get(i).getY(), Apples.get(i).getX())
                                || (Apples.get(i).getY() < 0)) {
                            Apples.get(i).v_y = -(Apples.get(i).v_y);
                        }
                        if (Apples.get(i).getY() == PANEL_WIDTH) {
                            life--;
                        }
                        if (life == 0) {

                            endGame();
                        }
                        Apples.get(i).setLocation(Apples.get(i).getX() + Apples.get(i).v_x,
                                Apples.get(i).getY() + Apples.get(i).v_y);
                        Apples.get(i).repaint();
                    }
                    // for (int i = 0; i < Apples.size(); i++) {

                    // }
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        });
        rotateThread.start();
        if (!running) {
            rotateThread.interrupt();
        }

        // timer = new Timer(DELAY, this);
    }

    public void move() {

    }

    public void endGame() {
        running = false;
        repaint();

    }

    public boolean collisionWithPlayer(int PositionY, int PositionX) {
        if ((((v_x <= PositionX) && (PositionX <= v_x + 150)) || ((v_x <= PositionX + 50)
                && (PositionX + 50 <= v_x + 150))) && PositionY == (PANEL_WIDTH - 100)) {
            score++;
            Apples.add(createApple(PositionX, PositionY - 5));
            return true;

        }
        return false;
    }
    // @Override
    // public void actionPerformed(ActionEvent e) {

    // }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {

            case 'd':
                if (v_x < (PANEL_WIDTH - 150)) {
                    v_x += 5;

                }
                break;
            case 'a':
                if (v_x > 0) {
                    v_x -= 5;
                }
                break;
        }
        System.out.println("the key Code is:" + e.getKeyCode());
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
