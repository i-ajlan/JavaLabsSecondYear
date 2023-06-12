import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame() {
        MyPanel panel = new MyPanel();
        this.add(panel);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.pack();
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }
}
